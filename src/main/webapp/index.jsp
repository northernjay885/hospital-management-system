<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management System</title>
</head>
<body>
<h1><%= "Hospital Management System" %>
</h1>
<br/>
    <form action="login" method="POST">
        Username: <input type="text" name="username"/><br><br>
        Password: <input type="password" name="password"/><br><br>
        <input type="submit" value="login"/>
    </form>
</body>
</html>