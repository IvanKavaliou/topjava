package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealCrud;
import ru.javawebinar.topjava.dao.MealCrudInMemImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String FORWARD_MEALS = "/meals.jsp";
    private static String INSERT_OR_EDIT = "/meal.jsp";


    private MealCrud mealCrud = new MealCrudInMemImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        String forward = FORWARD_MEALS;

        if(action != null) {
            if (action.equals("delete")) {
                Integer deleteMealId = Integer.parseInt(request.getParameter("mealId"));
                mealCrud.delete(deleteMealId);
                log.debug("delete meal, mealID: "+ deleteMealId.toString());
            }
            if (action.equals("edit")) {
                forward = INSERT_OR_EDIT;
                Integer editMealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = mealCrud.getById(editMealId);
                request.setAttribute("meal", meal);
            }
            if (action.equals("insert")){
                forward = INSERT_OR_EDIT;
            }
        }

        request.setAttribute("mealList", MealsUtil.crateMealsToList(mealCrud.getAll(),
                MealsUtil.DEFAULT_CALORIES_PER_DAY));
        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Meal meal = new Meal();
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.valueOf(request.getParameter("calories")));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));
        meal.setDateTime(dateTime);

        String mealId = request.getParameter("id");

        if(mealId == null || mealId.isEmpty()){
            mealCrud.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            mealCrud.update(meal);
        }
        request.setAttribute("mealList", MealsUtil.crateMealsToList(mealCrud.getAll(),
                MealsUtil.DEFAULT_CALORIES_PER_DAY));
        request.getRequestDispatcher(FORWARD_MEALS).forward(request, response);
    }

}
