package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    @Autowired
    private MealRepository repository;

    public Meal save(int userId,Meal meal) {
        return checkNotFoundWithId(repository.save(userId,meal), meal.getId());
    }

    public void delete(int userId, int mealId) {
        checkNotFoundWithId(repository.delete(userId,mealId), mealId);
    }

    public Meal get(int userId, int mealId) {
        return checkNotFoundWithId(repository.get(userId, mealId), mealId);
    }


    public Collection<Meal> getAll(int userId) {
        return checkNotFoundWithId(repository.getAll(userId), userId);
    }
}