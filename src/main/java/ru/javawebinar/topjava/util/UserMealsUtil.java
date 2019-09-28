package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> list=new ArrayList<>();
        UserMeal eat1=null;
        UserMeal eat2=null;
        UserMeal eat3=null;
        int dayCalories=0;
        TimeUtil timeUtil=new TimeUtil();
        for (UserMeal userMeal:mealList) {
            if (userMeal.getDescription().equals("Завтрак")){eat1=userMeal;dayCalories+=userMeal.getCalories();}
            if (userMeal.getDescription().equals("Обед")){eat2=userMeal;dayCalories+=userMeal.getCalories();}
            if (userMeal.getDescription().equals("Ужин")){
                eat3=userMeal;
                dayCalories+=userMeal.getCalories();
                if (dayCalories>caloriesPerDay){
                    if (timeUtil.isBetween(eat1.getDateTime().toLocalTime(),startTime,endTime))
                        list.add(new UserMealWithExceed(eat1.getDateTime(),eat1.getDescription(),eat1.getCalories(),false));
                    if (timeUtil.isBetween(eat2.getDateTime().toLocalTime(),startTime,endTime))
                        list.add(new UserMealWithExceed(eat2.getDateTime(),eat2.getDescription(),eat2.getCalories(),false));
                    if (timeUtil.isBetween(eat3.getDateTime().toLocalTime(),startTime,endTime))
                        list.add(new UserMealWithExceed(eat3.getDateTime(),eat3.getDescription(),eat3.getCalories(),false));
                }else {
                    if (timeUtil.isBetween(eat1.getDateTime().toLocalTime(),startTime,endTime))
                        list.add(new UserMealWithExceed(eat1.getDateTime(),eat1.getDescription(),eat1.getCalories(),true));
                    if (timeUtil.isBetween(eat2.getDateTime().toLocalTime(),startTime,endTime))
                        list.add(new UserMealWithExceed(eat2.getDateTime(),eat2.getDescription(),eat2.getCalories(),true));
                    if (timeUtil.isBetween(eat3.getDateTime().toLocalTime(),startTime,endTime))
                        list.add(new UserMealWithExceed(eat3.getDateTime(),eat3.getDescription(),eat3.getCalories(),true));
                }
                dayCalories=0;
            }
        }
        return list;
    }
}