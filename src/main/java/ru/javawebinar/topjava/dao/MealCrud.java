package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealCrud {
    public void add(Meal meal);
    public void update(Meal meal);
    public void delete(Integer id);
    public List<Meal> getAll();
    public Meal getById(Integer id);
}
