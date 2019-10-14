package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(p->save(p,new Random().nextInt(2)));
        System.out.println(repository);
    }
    public boolean checkValidUser(int id, int userId){
        return id==userId;
    }

    @Override
    public Meal save(Meal meal,int userId) {
        if (checkValidUser(meal.getUserId(),userId)) {
            if (meal.isNew()) {
                meal.setId(counter.incrementAndGet());
                repository.put(meal.getId(), meal);
                return meal;
            }
            // treat case: update, but not present in storage
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id,int userId) {
        if (checkValidUser(id,userId)) return repository.remove(id) != null;
        return false;
    }

    @Override
    public Meal get(int id,int userId) {
        if (checkValidUser(id,userId))return repository.get(id);
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values()
                .stream()
                .filter(meal->checkValidUser(meal.getUserId(),userId))
                .sorted(new DateComparator())
                .collect(Collectors.toList());

    }
    class DateComparator implements Comparator<Meal>{

        @Override
        public int compare(Meal o1, Meal o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
}

