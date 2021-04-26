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
    <link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="webjars/bootstrap/4.6.0/js/bootstrap.min.js" ></script>
    <script src="webjars/jquery/3.6.0/jquery.min.js" ></script>

</head>
<body>
    <div class="row justify-content-center">
        <div class="col-auto">
            <div class="row justify-content-center">
                <h3> Project A - The best Rental Car</h3>
            </div>
            <div class="row justify-content-center">
                <h4>Login</h4>
            </div>
            <form action="UserServlet" method="GET" class="card p-4 form-group">
                <input type="hidden" name="action" value="login"><br>

                <label for="email" class="form-label">E-mail</label>
                <input type="text" name="email" id="email" class="form-control" required/>

                <label for="password" class="form-label">Password</label>
                <input type="text" name="password" id="password" class="form-control" required/><br>

                <div class="row justify-content-center">
                    <button type="submit" class="btn btn-secondary mt-4">Submit</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
