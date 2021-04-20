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
    <form action="CarReservationServlet" method="POST">
        <input type="hidden" name="action" value="create"/>

        Type: <input type="text" name="type" required/><br><br>
        House Producer: <input type="text" name="houseproducer" required/><br><br>
        Model: <input type="text" name="model"  required/><br><br>
        Registration Year: <input type="text" name="registrationyear" placeholder="YYYY"  required/><br><br>
        License Plate<input type="text" name="licenseplate" required/><br><br>

        <input type="submit" name="Create">
    </form>
</body>
</html>
