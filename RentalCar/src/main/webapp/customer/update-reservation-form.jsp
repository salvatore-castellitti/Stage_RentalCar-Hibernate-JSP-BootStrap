<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update your Reservation </title>
    <link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js" ></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js" ></script>
</head>
<body>
    <div class="d-flex">
        <div class="p-2">
            <form action="ReservationServlet" method="GET">
                <input type="hidden" name="action" value="listVehicle">
                <input type="submit" value="Car Park" class="btn btn-secondary mr-1">
            </form>
        </div>
        <div class="p-2">
            <form action="UserServlet" method="GET">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="profileUpdate" value="true">
                <input type="submit" value="Profile" class="btn btn-secondary mr-1">
            </form>
        </div>
        <div class="p-2">
            <form action="ReservationServlet" method="GET">
                <input type="hidden" name="action" value="list">
                <input type="submit" value="HomePage" class="btn btn-secondary mr-1">
            </form>
        </div>
        <div class="ml-auto p-2">
            <form action="UserServlet" method="GET">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout" class="btn btn-secondary mr-1">
            </form>
        </div>
    </div>
    <hr>

    <div class="row justify-content-center">
        <div class="col-auto">
            <h3> Update your Reservation</h3>
            <form action="ReservationServlet" method="post" class="card p-4 form-group">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="reservationId" value="${reservation.id}" >

                Start Date: <input type="date" id="startDate" name="startDate">
                <small id="startHelp" class="form-text text-muted">(last start Date ${reservation.startDate})</small>
                <br>
                End Date: <input type="date" id="endDate" name="endDate">
                <small id="endHelp" class="form-text text-muted">(last end Date ${reservation.endDate})</small>
                <br>

                <div class="row justify-content-center">
                    <select name="vehicleId" class="form-control">
                        <c:forEach var="vehic" items="${VEHICLE_LIST}">
                            <option value="${vehic.id}">${vehic.houseProducer} ${vehic.model}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="row justify-content-center">
                    <input type="submit" name="update" class="btm btn-secondary mt-4">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
