<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <tr>
        <th> Дата </th>
        <th> Описание </th>
        <th> Каллории </th>
    </tr>
    <c:forEach items="${list}" var="mealTo">
        <tr style="background-color:${mealTo.excess ? 'red' : 'greenYellow'}">
            <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm"))}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
