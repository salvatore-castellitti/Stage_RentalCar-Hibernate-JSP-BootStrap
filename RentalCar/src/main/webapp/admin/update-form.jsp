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
    <title>Create new Customer</title>
</head>
<body>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="userId" value="${user.id}" >
        Name: <input type="text" name="name" placeholder="${user.name}" /><br><br>
        Surname: <input type="text" name="surname" placeholder="${user.surname}"/><br><br>
        Email: <input type="text" name="email" placeholder="${user.email}"/><br><br>
        Password<input type="text" name="password" placeholder="${user.password}"/><br><br>
        <input type="submit" name="update">
    </form>
</body>
</html>
