<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update your Reservation </title>
</head>
<body>
    <form action="ReservationServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="reservationId" value="${reservation.id}" >
        Start Date: <input type="text" name="startDate" placeholder="${reservation.startDate}" /><br><br>
        End Date: <input type="text" name="endDate" placeholder="${reservation.endDate}"/><br><br>
        <select name="vehicleId">
            <c:forEach var="vehic" items="${VEHICLE_LIST}">
                <option value="${vehic.id}">${vehic.houseProducer} ${vehic.model}</option>
            </c:forEach>
        </select><br><br>
        <input type="submit" name="update">
    </form>
</body>
</html>
