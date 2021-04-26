<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.management.model.User" %>
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
            <h3>List of Vehicle available</h3> <br>
                <table class="table table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>type</th>
                            <th>houseproducer</th>
                            <th>model</th>
                        </tr>
                    </thead>

                    <c:forEach var="car" items="${CARS_LIST}" >
                        <tr>
                            <td>${car.type}</td>
                            <td>${car.houseProducer}</td>
                            <td>${car.model}</td>
                        </tr>
                    </c:forEach>

                </table>
        </div>
    </div>
</body>
</html>
