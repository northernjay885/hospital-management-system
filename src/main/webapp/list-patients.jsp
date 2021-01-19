<%--
  Created by IntelliJ IDEA.
  User: luorenjie
  Date: 1/18/21
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Records</title>
</head>
<body>

<div id="wrapper">
    <div id="header">
        <h2>Patient Records</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!-- put new button: Add Patient -->

        <input type="button" value="Add Patient"
               onclick="window.location.href='add-patient-form.jsp'; return false;"
               class="add-patient-button"
        />

        <table>

            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Inpatient</th>
            </tr>

            <c:forEach var="tempPatient" items="${PATIENT_LIST}">

                <!-- set up a link for each patient -->
                <c:url var="tempLink" value="patient">
                    <c:param name="command" value="LOAD" />
                    <c:param name="studentId" value="${tempPatient.id}" />
                </c:url>

                <!--  set up a link to delete a student -->
                <c:url var="deleteLink" value="patient">
                    <c:param name="command" value="DELETE" />
                    <c:param name="studentId" value="${tempPatient.id}" />
                </c:url>

                <tr>
                    <td> ${tempPatient.id} </td>
                    <td> ${tempPatient.firstName} </td>
                    <td> ${tempPatient.lastName} </td>
                    <td> ${tempPatient.inpatient} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this patient?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>




