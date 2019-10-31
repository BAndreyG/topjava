package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional//(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        if (ref!=null) return null;
        meal.setUser(ref);
        if (meal.isNew()){
            em.persist(meal);
            return meal;
        }else if (userId==meal.getUser().getId()){
            em.merge(meal);
            return meal;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal ref=em.getReference(Meal.class,id);
        if (ref!=null) return false;
        if (userId==ref.getUser().getId()) {
            Meal u=em.find(Meal.class,id);
            //if (em.find(Meal.class,id))
            return em.createNamedQuery(Meal.DELETE, Meal.class)
                    .setParameter("id", id)
                    .setParameter("userId",userId)
                    .executeUpdate() != 0;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return em.find(Meal.class,id);
        //return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED,Meal.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {
        return null;
    }
}