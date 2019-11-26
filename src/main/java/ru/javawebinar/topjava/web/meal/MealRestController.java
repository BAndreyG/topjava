package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/profile/meals";

    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping
    public List<MealTo> getAll () {
            return super.getAll();
        }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        super.delete(id);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal m,@PathVariable int id){
        super.update(m, id);
    }

    @GetMapping("/between")
    public List<MealTo> getBetwen(@RequestParam(value = "startDateTime") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDateTime,
                                @RequestParam (value = "endDateTime") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDateTime){
        return super.getBetween(startDateTime.toLocalDate(),startDateTime.toLocalTime(),endDateTime.toLocalDate(),endDateTime.toLocalTime());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> create(@RequestBody Meal m){
        Meal createMeal=super.create(m);
        URI uri= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"/{id}")
                .buildAndExpand(createMeal.getId()).toUri();
        return ResponseEntity.created(uri).body(createMeal);//createMeal,httpHeaders, HttpStatus.CREATED);
    }
}