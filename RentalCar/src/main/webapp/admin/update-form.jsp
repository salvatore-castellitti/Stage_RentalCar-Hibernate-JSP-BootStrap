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
    <title>Update Customer</title>
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
            <h3>Update data for user: ${user.name}</h3>
            <br>
            <form action="UserServlet" method="post" class="card p-4 form-group">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="userId" value="${user.id}" >
                <input type="hidden" name="birthday" value="">

                <label for="name" class="form-label">Name</label>
                <input type="text" name="name" id="name" class="form-control" placeholder="${user.name}" /><br>

                <label for="surname" class="form-label">Surname</label>
                <input type="text" name="surname" id="surname" class="form-control" placeholder="${user.surname}"/><br>

                <label for="email" class="form-label">E-mail</label>
                <input type="text" name="email" id="email" class="form-control" placeholder="${user.email}"/><br>

                <label for="password" class="form-label">Password</label>
                <input type="text" name="password" id="password" class="form-control" placeholder="${user.password}"/>

                <div class="row justify-content-center">
                    <input type="submit" name="update" class="btn btn-secondary mt-4">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
