<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 15/04/2021
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
    zio zio <br>
    <hr>
</head>
<body>
Hello CUSTOMER <br><br>

Lista Prenotazioni per utente <br>
${error}<br>

<table border="1px">
    <tr>
        <th>Id</th>
        <th>Vehicle</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Action</th>
    </tr>

    <c:forEach var="reserv" items="${RESERVATION_LIST}" >
    <tr>
        <td>${reserv.id}</td>
        <td>${reserv.vehicle.houseProducer} ${reserv.vehicle.model}</td>
        <td>${reserv.startDate}</td>
        <td>${reserv.endDate}</td>
        <td>
            <div style="display: flex;">
                <form action="ReservationServlet" method="GET">
                    <input type="hidden" name="action" value="update"/>
                    <input type="hidden" name="reservId" value="${reserv.id}"/>
                    <input type="submit" value="update"/>
                </form>
                <form action="ReservationServlet" method="POST">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="reservId" value="${reserv.id}"/>
                    <input type="submit" value="delete" onclick="return confirm('Are you sure you want delete this user?')"/>
                </form>
            </div>
        </td>
    </tr>
    </c:forEach>
</table>
    <form action="ReservationServlet" method="GET">
        <input type="hidden" name="action" value="create">
        <button type="submit">Create a new Reservation</button>
    </form>
</body>
</html>
