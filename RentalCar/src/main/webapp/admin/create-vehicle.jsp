<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 16/04/2021
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Vehicle</title>
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
            <h3>Insert new Vehicle</h3>
            <form action="CarReservationServlet" method="POST" class="card p-4 form-group">
                <input type="hidden" name="action" value="create"/>

                <label for="type" class="form-label">Name</label>
                <input type="text" name="type" id="type" class="form-control" required/><br>

                <label for="houseProducer" class="form-label">House Producer</label>
                <input type="text" name="houseproducer" id="houseProducer" class="form-control" required/><br>

                <label for="model" class="form-label">Model</label>
                Model: <input type="text" name="model" id="model" class="form-control" required/><br>

                <label for="registration" class="form-label">Name</label>
                Registration Year: <input type="text" name="registrationyear" placeholder="YYYY" id="registration" class="form-control" required/><br>

                <label for="license" class="form-label">Name</label>
                License Plate<input type="text" name="licenseplate" id="license" class="form-control" required/><br><br>

                <div class="row justify-content-center">
                    <input type="submit" name="Create" class="btn btn-secondary mt-4">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
