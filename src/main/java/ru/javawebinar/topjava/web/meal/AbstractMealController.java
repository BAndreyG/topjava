package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;
import java.util.Collection;

public abstract class AbstractMealController {
    @Autowired
    private MealService service;
    protected final Logger log = LoggerFactory.getLogger(getClass());
    int userId= SecurityUtil.authUserId();

    public  Meal save(Meal meal) {
        log.info("save");
        return service.save(meal,userId);
    }
    public void delete(int id){
        log.info("delete {}", id);
        service.delete(id,userId);
    }
    public Meal get(int id){
        log.info("get {}", id);
        return service.get(id,userId);
    }
    public Collection<Meal> getAll(){
        log.info("getAll");
        return service.getAll(userId);
    }


  /*
    public Collection<Meal> getAll(){
        return repository.getAll(SecurityUtil.authUserId());
    }
 */
}
