<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.management.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
    <div style="display: flex;">
        <form method="CarReservationServlet">
            <input type="submit" value="Car Park">
        </form>
        <form>
            <input type="submit" value="Reservation">
        </form>
        <form action="UserServlet" method="GET">
            <input type="hidden" name="action" value="list">
            <input type="submit" value="HomePage">
        </form>
    </div>
    <hr>

  Hello ADMIN<br><br>
  LISTA veicoli con pulsante ricerca da implementare<br><br>
    <table border="1px">
        <tr>
            <th>id</th>
            <th>type</th>
            <th>houseproducer</th>
            <th>model</th>
            <th>Action</th>
        </tr>

        <c:forEach var="car" items="${CARS_LIST}" >
            <tr>
                <td>${car.id}</td>
                <td>${car.type}</td>
                <td>${car.houseProducer}</td>
                <td>${car.model}</td>
                <td>
                    <div style="display: flex;">
                        <form action="CarReservationServlet" method="GET">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="carId" value="${car.id}"/>
                            <input type="submit" value="update"/>
                        </form>
                        <form action="CarReservationServlet" method="POST">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="carId" value="${car.id}"/>
                            <input type="submit" value="delete" onclick="return confirm('Are you sure you want delete this Vehicle?')"/>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br><br>
  <form action="CarReservationServlet" method="GET">
      <input type="hidden" name="action" value="create">
      <button type="submit">Create new Vehicle</button>
  </form>
</body>
</html>
