<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 16/04/2021
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Customer</title>
</head>
<body>
    <form action="CarReservationServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="carId" value="${car.id}" >
        Type: <input type="text" name="type" placeholder="${car.type}" /><br><br>
        House Producer: <input type="text" name="houseproducer" placeholder="${car.houseProducer}"/><br><br>
        Model: <input type="text" name="model" placeholder="${car.model}"/><br><br>
        Registration Year: <input type="text" name="registrationyear" placeholder="${car.registrationYear}"/><br><br>
        License Plate: <input type="text" name="password" placeholder="${car.licensePlate}"/><br><br>
        <input type="submit" name="update">
    </form>
</body>
</html>
