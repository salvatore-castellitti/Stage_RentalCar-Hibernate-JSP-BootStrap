<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
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
            <h3>Your Reservation</h3> <br>
            <h4>${error}</h4> <br>
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Id</th>
                        <th>Vehicle</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Action</th>
                    </tr>
                </thead>

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
                                <input type="submit" value="update" class="btn btn-secondary mr-1"/>
                            </form>
                            <form action="ReservationServlet" method="POST">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="reservId" value="${reserv.id}"/>
                                <input type="submit" value="delete" onclick="return confirm('Are you sure you want delete this user?')"  class="btn btn-secondary"/>
                            </form>
                        </div>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="row justify-content-center">
        <form action="ReservationServlet" method="GET">
            <input type="hidden" name="action" value="createDate">
            <button type="submit" class="btn btn-secondary">Create a new Reservation</button>
        </form>
    </div>
</body>
</html>
