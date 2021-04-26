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
    <title>Create new Customer</title>
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
            <h3>  Create new User</h3>
            <br>
                <form action="UserServlet" method="POST" class="card p-4 form-group">
                    <input type="hidden" name="action" value="create"/>

                    <label for="name" class="form-label">Name</label>
                    <input type="text" name="name" required id="name" class="form-control"/><br>

                    <label for="surname" class="form-label">Surname</label>
                    <input type="text" name="surname" required id="surname" class="form-control"/><br>

                    <label for="email" class="form-label">E-mail</label>
                    <input type="text" name="email"  required id="email" class="form-control"/><br>

                    <label for="birthday" class="form-label">Birthday</label>
                    <input type="text" name="birthday" placeholder="DD/MM/YYYY" required id="birthday" class="form-control"/><br>

                    <label for="taxcode" class="form-label">Tax Code</label>
                    <input type="text" name="taxcode" required id="taxcode" class="form-control"/><br>

                    <label for="password" class="form-label">Password</label>
                    <input type="text" name="password" required id="password" class="form-control">
                    <br>
                    <div class="row justify-content-center">
                        <select name="role" class="form-select">
                            <option value="true">Admin</option>
                            <option value="false">Customer</option>
                        </select>
                    </div>

                    <div class="row justify-content-center">
                        <input type="submit" name="Create" class="btn btn-secondary mt-4">
                    </div>
                </form>
        </div>
    </div>
</body>
</html>
