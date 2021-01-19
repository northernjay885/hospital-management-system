<%--
  Created by IntelliJ IDEA.
  User: luorenjie
  Date: 1/19/21
  Time: 8:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Update Patient</title>

<%--    <link type="text/css" rel="stylesheet" href="css/style.css">--%>
<%--    <link type="text/css" rel="stylesheet" href="css/add-student-style.css">--%>
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>Update Patient</h2>
    </div>
</div>

<div id="container">
    <h3>Update Patient</h3>

    <form action="patient" method="GET">

        <input type="hidden" name="command" value="UPDATE" />

        <input type="hidden" name="patientId" value="${THE_PATIENT.id}" />

        <table>
            <tbody>
            <tr>
                <td><label>First name:</label></td>
                <td><input type="text" name="firstName"
                           value="${THE_PATIENT.firstName}" /></td>
            </tr>

            <tr>
                <td><label>Last name:</label></td>
                <td><input type="text" name="lastName"
                           value="${THE_PATIENT.lastName}" /></td>
            </tr>

            <tr>
                <td><label>PatientType:</label></td>
                <td><input type="text" name="patientType"
                           value="${THE_PATIENT.patientType}" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="patient">Back to List</a>
    </p>
</div>
</body>

</html>











