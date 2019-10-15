package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import java.util.Collection;

@Controller
public class MealRestController extends AbstractMealController {

    @Override
    public Meal save(Meal meal) {
        return super.save(meal);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public Meal get(int id) {
        return super.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return super.getAll();
    }
}
/*
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
 */