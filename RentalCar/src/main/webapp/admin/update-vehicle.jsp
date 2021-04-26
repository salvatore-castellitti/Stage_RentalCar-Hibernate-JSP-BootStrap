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
    <title>Update Vehicle</title>
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
            <h3>Update Vehicle</h3>
            <br>
            <form action="CarReservationServlet" method="POST" class="card p-4 form-group">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="carId" value="${car.id}" >

                <label for="type" class="form-label">Type</label>
                <input type="text" name="type" id="type" class="form-control" placeholder="${car.type}" /><br>

                <label for="houseProducer" class="form-label">House Producer</label>
                <input type="text" name="houseproducer" id="houseProducer" class="form-control" placeholder="${car.houseProducer}"/><br>

                <label for="model" class="form-label">Model</label>
                <input type="text" name="model" id="model" class="form-control" placeholder="${car.model}"/><br>

                <label for="registration" class="form-label">Registration Year</label>
                <input type="text" name="registrationyear" id="registration" class="form-control" placeholder="${car.registrationYear}"/><br>

                <label for="license" class="form-label">License Plate</label>
                <input type="text" name="licenseplate" id="license" class="form-control" placeholder="${car.licensePlate}"/>

                <div class="row justify-content-center">
                    <input type="submit" name="update" class="btn btn-secondary mt-4">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
