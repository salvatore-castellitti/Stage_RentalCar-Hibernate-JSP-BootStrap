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
</head>
<body>
    <form action="UserServlet" method="POST">
        <input type="hidden" name="action" value="create"/>
        Name: <input type="text" name="name" required/><br><br>
        Surname: <input type="text" name="surname" required/><br><br>
        Email: <input type="text" name="email"  required/><br><br>
        Birthday: <input type="text" name="birthday" placeholder="DD/MM/YYYY"  required/><br><br>
        Tax Code<input type="text" name="taxcode" required/><br><br>
        Password: <input type="text" name="password" required><br><br>
        <select name="role">
            <option value="true">Admin</option>
            <option value="false">Customer</option>
        </select><br><br>
        <input type="submit" name="Create">
    </form>
</body>
</html>
