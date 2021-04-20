<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.project.management.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
    <div style="display: flex;">
        <form action="CarReservationServlet" method="GET">
            <input type="hidden" name="action" value="lsit">
            <input type="submit" value="Car Park">
        </form>
        <form>
            <input type="submit" value="Reservation">
        </form>
        <form action="UserServlet" method="GET">
            <input type="hidden" name="action" value="list">
            <input type="submit" value="HomePage">
        </form>
    </div>
    <hr>

  Hello ADMIN<br><br>
  LISTA CUSTOMER con pulsante ricerca<br><br>
  <form action=""></form>
    <table border="1px">
        <tr>
            <th>User id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>email</th>
            <th>Action</th>
        </tr>

        <c:forEach var="user" items="${USER_LIST}" >
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>
                    <div style="display: flex;">
                        <form action="UserServlet" method="GET">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="submit" value="update"/>
                        </form>
                        <form action="UserServlet" method="POST">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="userId" value="${user.id}"/>
                            <input type="submit" value="delete" onclick="return confirm('Are you sure you want delete this user?')"/>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>

    </table>
    <br><br>
  <form action="UserServlet" method="GET">
      <input type="hidden" name="action" value="create">
      <button type="submit">Create new User</button>
  </form>
</body>
</html>
