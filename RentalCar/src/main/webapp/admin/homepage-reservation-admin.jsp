<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.management.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage Reservation</title>
    <link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js" ></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js" ></script>
</head>
<body>
    <div class="d-flex">
        <div class="p-2">
            <form action="CarReservationServlet" method="GET">
                <input type="hidden" name="action" value="lsit">
                <input type="submit" class="btn btn-secondary mr-1" value="Car Park">
            </form>
        </div>
        <div class="p-2">
            <form action="CarReservationServlet" method="GET">
                <input type="hidden" name="action" value="listReservation">
                <input type="submit" value="Reservation" class="btn btn-secondary mr-1">
            </form>
        </div>
        <div class="p-2">
            <form action="UserServlet" method="GET">
                <input type="hidden" name="action" value="list">
                <input type="submit" value="HomePage" class="btn btn-secondary mr-1">
            </form>
        </div>
        <div class="ml-auto p-2">
            <form action="UserServlet" method="GET">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout" class="btn btn-secondary">
            </form>
        </div>
    </div>
    <hr>

    <div class="row justify-content-center">
        <div class="col-auto">
            <table  class="table table-bordered">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Customer</th>
                <th scope="col">Vehicle</th>
                <th scope="col">Start Date</th>
                <th scope="col">End</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <c:forEach var="reserv" items="${RESERVATION_LIST}" >
            <tr>
                <td>${reserv.user.name} ${reserv.user.surname}</td>
                <td>${reserv.vehicle.houseProducer} ${reserv.vehicle.model} ${reserv.vehicle.licensePlate}</td>
                <td>${reserv.startDate}</td>
                <td>${reserv.endDate}</td>
                <td>
                    <c:choose>
                        <c:when test="${reserv.confirmed}">
                            Confirmed
                        </c:when>
                        <c:otherwise>
                            <div style="display: flex;">
                                <form action="CarReservationServlet" method="POST">
                                    <input type="hidden" name="action" value="updateReservation"/>
                                    <input type="hidden" name="reservConfirm" value="true"/>
                                    <input type="hidden" name="reservationId" value="${reserv.id}"/>
                                    <input type="submit" value="update" class="btn btn-secondary"/>
                                </form>
                                <form action="CarReservationServlet" method="POST">
                                    <input type="hidden" name="action" value="deleteReservation"/>
                                    <input type="hidden" name="reservId" value="${reserv.id}"/>
                                    <input type="submit" value="delete" onclick="return confirm('Are you sure you want delete this Vehicle?')" class="btn btn-secondary"/>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>

    </table>
        </div>
    </div>

</body>
</html>
