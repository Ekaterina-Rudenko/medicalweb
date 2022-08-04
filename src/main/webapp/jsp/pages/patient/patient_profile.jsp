<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 03.02.2022
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="profile.name" var="name"/>
<fmt:message key="profile.email" var="email"/>
<fmt:message key="profile.phone" var="phone"/>
<fmt:message key="profile.gender" var="gender"/>
<fmt:message key="profile.birthdate" var="birtdate"/>
<fmt:message key="profile.balance" var="balance"/>
<fmt:message key="profile.visits" var="visits"/>
<fmt:message key="profile.settings" var="settings"/>
<fmt:message key="profile.patient_info" var="info"/>
<fmt:message key="profile.prescriptions" var="prescriptions"/>
<fmt:message key="profile.balance" var="balance"/>


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

<div class="container">
    <h3 class="text-center">Patient personal information: </h3>
    <table class="table table-bordered">
        <tr>
            <th>${name}</th>
            <td>
                <c:out value="${sessionScope.user.lastName}
                ${sessionScope.user.firstName}
                ${sessionScope.user.middleName}"/></td>
        </tr>
        <tr>
            <th>${email}</th>
            <td> <c:out value="${sessionScope.user.email}"/></td>
        </tr>
        <tr>
            <th>${phone}</th>
            <td><c:out value="80${sessionScope.user.phoneNumber}"/></td>
        </tr>
        <tr>
            <th>${gender}</th>
            <td><c:out value="${sessionScope.patient_gender}"/></td>
        </tr>
        <tr>
            <th>${birtdate}</th>
            <td><c:out value="${sessionScope.patient_birthdate}"/></td>
        </tr>
        <tr>
            <th>${balance}</th>
            <td><c:out value="${sessionScope.patient_balance} BYN"/></td>
        </tr>
    </table>
</div>

<div class="container">
<div class="list-group">
    <a href="${pageContext.request.contextPath}/controller?command=find_visits_by_patient_id"
       class="list-group-item list-group-item-action">
        View ${visits}
    </a>
    <a href="${pageContext.request.contextPath}/jsp/pages/patient/recommendations.jsp" class="list-group-item list-group-item-action">View ${prescriptions}</a>
    <a href="${pageContext.request.contextPath}/jsp/pages/patient/top_up_balance.jsp" class="list-group-item list-group-item-action">Top up ${balance}</a>
    <a href="${pageContext.request.contextPath}/jsp/pages/patient/profile_setting.jsp" class="list-group-item list-group-item-action">Profile ${settings}</a>
</div>
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
