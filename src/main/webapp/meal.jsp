<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Meal form</title>
</head>
<body>
<h3><a href="meals">Meal list</a></h3>
<hr>
<form method="POST" action='meals' name="formAddMeal">
    ID : <input type="text" readonly="readonly" name="id" value="<c:out value="${meal.getId()}" />" /> <br />
    Date : <input type="datetime-local" name="date" value="${meal.getDateTime()}"><br />
    Description : <input type="text" name="description" value="<c:out value="${meal.getDescription()}" />" /> <br />
    Calories : <input type="text" name="calories" value="<c:out value="${meal.getCalories()}" />" /> <br />
    <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
