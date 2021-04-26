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
                        <th scope="col">Name</th>
                        <th scope="col">Surname</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Tax Code</th>
                        <th scope="col">E-Mail</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <c:forEach var="user" items="${USER_LIST}" >
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.birthday}</td>
                        <td>${user.taxCode}</td>
                        <td>${user.email}</td>
                        <td>
                            <div style="display: flex;">
                                <form action="UserServlet" method="GET">
                                    <input type="hidden" name="action" value="update"/>
                                    <input type="hidden" name="userId" value="${user.id}"/>
                                    <input type="submit" class="btn btn-secondary mr-1" value="update"/>
                                </form>
                                <form action="UserServlet" method="POST">
                                    <input type="hidden" name="action" value="delete"/>
                                    <input type="hidden" name="userId" value="${user.id}"/>
                                    <input type="submit" class="btn btn-secondary" value="delete" onclick="return confirm('Are you sure you want delete this user?')"/>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="row justify-content-center">
                <form action="UserServlet" method="GET">
                    <input type="hidden" name="action" value="create">
                    <button type="submit" class="btn btn-secondary">Create new User</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
