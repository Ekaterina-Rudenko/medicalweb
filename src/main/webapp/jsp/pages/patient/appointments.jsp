<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="cancel_visit.positive" var="positive_result"/>
<fmt:message key="cancel_visit.negative" var="negative_result"/>
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

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
    <link href="${pageContext.request.contextPath}/CSS/style.css" rel="stylesheet">

    <title>Title</title>
</head>
<body>

<c:import url="../header.jsp"/>

<c:choose>
    <c:when test="${param.action_result eq 'true'}">
        <div class="alert alert-success confirmation-message" id="message"><b
                class="valid_message">${positive_result}</b></div>
    </c:when>
    <c:when test="${param.action_result eq 'false'}">
        <div class="alert alert-warning confirmation-message" id="message"><b
                class="invalid_message">${negative_result}</b></div>
    </c:when>
</c:choose>

<button class="btn btn-outline-success me-2" type="button">
    <a href="${pageContext.request.contextPath}/controller?command=find_visits_by_patient_id">Show
        appointments</a>
</button>

<c:if test="${!empty requestScope.empty_visit_list}">
    <div class="invalid-feedback-backend" style="color: blue">
        <fmt:message key="${requestScope.empty_visit_list}"/>
    </div>
</c:if>

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
            <th></th>
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
                <td>
                    <c:if test="${visit.state eq 'NEW'}">
                        <a href="${pageContext.request.contextPath}/controller?command=cancel_visit&visit_id=${visit.visitId}">Cancel
                            visit</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<c:import url="../footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
</body>
</html>
