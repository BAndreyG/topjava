package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;
    @Autowired
    public MealService(MealRepository repository){this.repository=repository;}

    public Meal save(Meal meal) {
        return repository.save(meal, SecurityUtil.authUserId());
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id,SecurityUtil.authUserId()), id);
    }

    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id,SecurityUtil.authUserId()), id);
    }

    public Collection<Meal> getAll(){
        return repository.getAll(SecurityUtil.authUserId());
    }
}