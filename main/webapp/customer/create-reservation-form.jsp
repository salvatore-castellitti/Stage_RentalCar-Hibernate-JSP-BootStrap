<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Reservation</title>
</head>
<body>
    <form action="ReservationServlet" method="POST">
        <input type="hidden" name="action" value="create"/>
        Start Date: <input type="text" name="startDate" placeholder="DD/MM/YYYY" required/><br><br>
        End Date: <input type="text" name="endDate" placeholder="DD/MM/YYYY" required/><br><br>
        Vehicle:
        <select name="vehicleId">
            <c:forEach var="vehic" items="${VEHICLE_LIST}">
                <option value="${vehic.id}">${vehic.houseProducer} ${vehic.model}</option>
            </c:forEach>
        </select><br><br>
        <input type="submit" name="Create">
    </form>
</body>
</html>
