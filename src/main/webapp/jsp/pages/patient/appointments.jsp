<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 03.02.2022
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Title</title>
</head>
<body>

<c:import url="../header.jsp"/>

<button class="btn btn-outline-success me-2" type="button">
    <a href="${pageContext.request.contextPath}/controller?command=find_visits_by_patient_id">Show appointments</a>
</button>

<div class="container">
    <h3 class="text-center">The list of appointments</h3>
    <table class="table table-bordered">
        <tr>
            <th>Specialization</th>
            <th>Doctor</th>
            <th>Service</th>
            <th>Date</th>
            <th>Time</th>
            <th>Type of Payment</th>
            <th>State</th>
        </tr>

        <c:forEach items="${sessionScope.visit_list}" var="visit">
            <tr>
                <td>${visit.specialization.name}</td>
                <td>${visit.doctor.lastName} ${visit.doctor.firstName}</td>
                <td>${visit.service.serviceName}</td>
                <td>${visit.date}</td>
                <td>${visit.time}</td>
                <td>${visit.typeOfPayment}</td>
                <td>${visit.state}</td>

            </tr>
        </c:forEach>
    </table>
</div>

<c:import url="../footer.jsp"/>
</body>
</html>
