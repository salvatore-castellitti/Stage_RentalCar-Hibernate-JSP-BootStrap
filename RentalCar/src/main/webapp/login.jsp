<%--
  Created by IntelliJ IDEA.
  User: Si2001
  Date: 15/04/2021
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login</title>
</head>
<body>
    <form action="UserServlet" method="GET">
        <input type="hidden" name="action" value="login">
        Email: <input type="text" name="email" required/><br>
        Password: <input type="text" name="password" required/><br>
        <button type="submit">Submit</button>
    </form>

</body>
</html>
