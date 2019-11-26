package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;


import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals"; //profile

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Meal get(@PathVariable("id)") int id) {
        return super.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
        public List<MealTo> getAll () {
            return super.getAll();
        }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        super.delete(id);
    }


}