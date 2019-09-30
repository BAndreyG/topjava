package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        TreeMap<LocalDate, List<UserMeal>> map = new TreeMap<>();
        mealList.forEach(k -> {
            List<UserMeal> list1 = map.get(k.getDateTime().toLocalDate());
            if (list1 == null) map.put(k.getDateTime().toLocalDate(), Collections.singletonList(k));
            else {
                list1 = new ArrayList<>(map.get(k.getDateTime().toLocalDate()));
                list1.add(k);
                map.put(k.getDateTime().toLocalDate(), list1);

            }
        });

        List<UserMealWithExceed> result = new ArrayList<>();
        map.forEach((k, v) -> {
            AtomicBoolean fat = new AtomicBoolean(false);
            AtomicInteger allCaloriesDay = new AtomicInteger();
            v.forEach(l -> {
                allCaloriesDay.addAndGet(l.getCalories());
            });
            if (allCaloriesDay.get() > caloriesPerDay) fat.set(true);
            v.forEach(l -> {
                if (new TimeUtil().isBetween(l.getDateTime().toLocalTime(), startTime, endTime))
                    result.add(new UserMealWithExceed(l.getDateTime(), l.getDescription(), l.getCalories(), fat.get()));
            });
            fat.set(false);
        });
        return result;
    }
}