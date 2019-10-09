package ru.javawebinar.topjava.DAO;
import ru.javawebinar.topjava.model.*;
import java.util.List;

public interface mealDAO {
    List<MealTo> list();
    void addMealTo(MealTo mealTo);
    void delMealTo(MealTo mealTo);
    void updateMealTo(MealTo mealTo);
    MealTo getById(int id);
}
