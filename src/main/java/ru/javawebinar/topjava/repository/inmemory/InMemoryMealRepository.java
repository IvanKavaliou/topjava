package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map <Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(1,new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(1,new Meal(1,LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(1,new Meal(1,LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(1,new Meal(1,LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(1,new Meal(1,LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(2,new Meal(2,LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(2,new Meal(2,LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int mealId) {
        Map<Integer, Meal> meals =  repository.get(userId);
        return meals != null && meals.remove(mealId) != null;
    }

    @Override
    public Meal get(int userId, int mealId) {
        Map<Integer, Meal> meals =  repository.get(userId);
        return  meals == null ? null : meals.get(mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId).values()
                .stream().sorted(Comparator.comparing(Meal::getDate)
                        .reversed()).collect(Collectors.toList());
    }
}

