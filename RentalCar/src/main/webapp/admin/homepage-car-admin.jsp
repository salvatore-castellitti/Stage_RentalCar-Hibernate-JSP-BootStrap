<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.management.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage Vehicle</title>
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
            <table class="table table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Type</th>
                        <th scope="col">House Producer</th>
                        <th scope="col">Model</th>
                        <th scope="col">License Plate</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <c:forEach var="car" items="${CARS_LIST}" >
                    <tr>
                        <td>${car.type}</td>
                        <td>${car.houseProducer}</td>
                        <td>${car.model}</td>
                        <td>${car.licensePlate}</td>

                        <td>
                            <div style="display: flex;">
                                <form action="CarReservationServlet" method="GET">
                                    <input type="hidden" name="action" value="update"/>
                                    <input type="hidden" name="carId" value="${car.id}"/>
                                    <input type="submit" value="update" class="btn btn-secondary mr-1"/>
                                </form>
                                <form action="CarReservationServlet" method="POST">
                                    <input type="hidden" name="action" value="delete"/>
                                    <input type="hidden" name="carId" value="${car.id}"/>
                                    <input type="submit" value="delete" onclick="return confirm('Are you sure you want delete this Vehicle?')" class="btn btn-secondary"/>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <div class="row justify-content-center">
        <form action="CarReservationServlet" method="GET">
          <input type="hidden" name="action" value="create">
          <button type="submit" class="btn btn-secondary">Create new Vehicle</button>
      </form>
    </div>
</body>
</html>
