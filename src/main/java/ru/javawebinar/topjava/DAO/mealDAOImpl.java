package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class mealDAOImpl implements mealDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, MealTo> mealsDAO = new HashMap<>();

    static {
        mealsDAO.put(AUTO_ID.getAndIncrement(),new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500,false));
        mealsDAO.put(AUTO_ID.getAndIncrement(),new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000,false));
        mealsDAO.put(AUTO_ID.getAndIncrement(),new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000,true));
        mealsDAO.put(AUTO_ID.getAndIncrement(),new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500,true));
        mealsDAO.put(AUTO_ID.getAndIncrement(),new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510,true));
        mealsDAO.put(AUTO_ID.getAndIncrement(),new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500,false));
    }
    @Override
    public List<MealTo> list() {
        return new ArrayList<>(mealsDAO.values());
    }

    @Override
    public void addMealTo(MealTo mealTo) {
        mealsDAO.put(AUTO_ID.getAndIncrement(),mealTo);
    }

    @Override
    public void delMealTo(MealTo mealTo) {
        mealsDAO.remove(mealTo);
    }

    @Override
    public void updateMealTo(MealTo mealTo) {
        Optional<Integer> key=mealsDAO.entrySet()
                .stream()
                .filter(entry-> mealTo.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();
        if (key.isPresent())mealsDAO.put(key.get(),mealTo);
        else mealsDAO.put(AUTO_ID.getAndIncrement(),mealTo); }

    @Override
    public MealTo getById(int id) {
        return mealsDAO.get(id);
    }
}
