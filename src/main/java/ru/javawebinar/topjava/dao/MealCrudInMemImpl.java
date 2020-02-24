package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealCrudInMemImpl implements MealCrud {

    private static AtomicInteger atomicInt = new AtomicInteger(0);

    public static List<Meal> MEAL_LIST = new ArrayList<>();

    public MealCrudInMemImpl(){
        add(new Meal(atomicInt.getAndIncrement(), LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(atomicInt.getAndIncrement(),LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        add(new Meal(atomicInt.getAndIncrement(),LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        add(new Meal(atomicInt.getAndIncrement(),LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        add(new Meal(atomicInt.getAndIncrement(),LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(atomicInt.getAndIncrement(),LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        add(new Meal(atomicInt.getAndIncrement(),LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public void add(Meal meal) {
        if (meal.getId() == null){
            meal.setId(atomicInt.getAndIncrement());
        }
        MEAL_LIST.add(meal);
    }

    @Override
    public void update(Meal meal) {
        Meal newMeal = getById(meal.getId());
        newMeal.setDateTime(meal.getDateTime());
        newMeal.setCalories(meal.getCalories());
        newMeal.setDescription(meal.getDescription());
    }

    @Override
    public void delete(Integer id) {
        MEAL_LIST.removeIf(meal -> (meal.getId().equals(id)));
    }

    @Override
    public List<Meal> getAll() {
        return MEAL_LIST;
    }

    @Override
    public Meal getById(Integer id) {
        for (Meal meal:MEAL_LIST){
            if (meal.getId().equals(id)){
                return meal;
            }
        }
        return null;
    }
}
