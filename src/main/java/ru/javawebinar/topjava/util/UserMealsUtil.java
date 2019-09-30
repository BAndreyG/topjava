package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        List<UserMeal> list=new ArrayList<>();
        List<UserMealWithExceed> result=new ArrayList<>();
        boolean fat=true;
        LocalDate day0=mealList.get(0).getDateTime().toLocalDate();
        HashMap<List<UserMeal>,Boolean> data=new HashMap();
        int dayCalories=0;
        TimeUtil timeUtil=new TimeUtil();
        for (UserMeal userMeal:mealList) {
            if (userMeal.getDateTime().toLocalDate().compareTo(day0) != 0) {
                if (dayCalories > caloriesPerDay) {
                    fat = false;
                }
                data.put(list, fat);
                list = new ArrayList<>();
                fat = true;
                dayCalories=0;
                day0=userMeal.getDateTime().toLocalDate();
            }
            dayCalories += userMeal.getCalories();
            list.add(userMeal);
            }
        if (dayCalories > caloriesPerDay) {
            fat = false;
        }
        data.put(list, fat);
        for (List<UserMeal> l:data.keySet()) {
            for (UserMeal um:l) {
                if (timeUtil.isBetween(um.getDateTime().toLocalTime(),startTime,endTime))result.add(new UserMealWithExceed(um.getDateTime(),um.getDescription(),um.getCalories(),data.get(l)));
            }
        }
        return result;
    }

}