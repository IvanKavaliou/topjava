<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals list</h2>
    <table style="width:100%" border="1">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Excess</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="meal" items="${mealList}">
            <c:choose>
                <c:when test="${meal.isExcess()}">
                    <tr style="background-color: red">
                </c:when>
                <c:otherwise>
                    <tr style="background-color: green">
                </c:otherwise>
            </c:choose>
                <td>${meal.getId()}</td>
                <td>${meal.getDateTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td>${meal.isExcess()}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${meal.getId()}"/>">Edit</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${meal.getId()}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
<p><a href="meals?action=insert">Add User</a></p>
</body>
</html>