package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@Transactional
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            User u = em.getReference(User.class, userId);
            meal.setUser(u);
            em.persist(meal);
            return meal;}
        else {
            User u = em.find(User.class, userId);
            if (u.equals(meal.getUser())) {
                meal.setUser(u);
                return em.merge(meal);
            }
            return null;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("userId",userId)
                .executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        if (em.find(Meal.class,id)!=null) {
            User u = em.find(User.class, userId);
            if (em.find(Meal.class, id).getUser().equals(u)) {
                return em.find(Meal.class, id);
            }
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED,Meal.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN,Meal.class)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .setParameter("userId",userId)
                .getResultList();
    }
}