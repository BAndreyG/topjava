package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    boolean deleteMealByIdAndUserId (int id,int userId);

    Meal getByIdAndUserId(int id,int userId);

    List<Meal> getMealsByAllSorteAndUserId(int userId);

}
