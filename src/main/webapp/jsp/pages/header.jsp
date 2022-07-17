<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 17.01.2022
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="header.home" var="home"/>
<fmt:message key="header.title" var="title"/>
<fmt:message key="header.about_us" var="about_us"/>
<fmt:message key="header.services" var="services"/>
<fmt:message key="header.doctors" var="doctors"/>
<fmt:message key="header.contacts" var="contacts"/>
<fmt:message key="header.language" var="language"/>
<fmt:message key="header.register" var="register"/>
<fmt:message key="header.log_in" var="log_in"/>
<fmt:message key="header.log_out" var="log_out"/>
<fmt:message key="header.profile" var="profile"/>
<fmt:message key="header.create_a_visit" var="make_an_appointment"/>
<fmt:message key="admin.add.doctor" var="add_doctor"/>

<html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
    <title>Title</title>
</head>

<body>
<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/pages/main.jsp">
            <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="logo" width="130" height="130"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <li class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item fixed-top text-center">
                    <span class="navbar-brand mb-0 h1">${title}</span>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/pages/main.jsp">${home}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/pages/about_us.jsp">${about_us}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/controller?command=show_all_services">${services}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/pages/doctors.jsp">${doctors}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="${pageContext.request.contextPath}/jsp/pages/contacts.jsp">${contacts}</a>
                </li>


                <c:choose>
                    <c:when test="${sessionScope.user_role eq 'ADMIN'}">
                        <%@include file="fragment/admin_part.jspf" %>
                    </c:when>
                    <c:when test="${sessionScope.user_role eq 'PATIENT'}">
                        <%@include file="fragment/patient_part.jspf" %>
                    </c:when>
                    <c:when test="${sessionScope.user_role eq 'DOCTOR'}">
                        <%@include file="fragment/doctor_part.jspf" %>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-success me-2" type="button">
                            <a href="${pageContext.request.contextPath}/jsp/pages/login.jsp" >${make_an_appointment}</a>
                        </button>
                        <div>
                            <li class="nav-item">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/jsp/pages/registration.jsp">${register}</a>
                            </li>
                        </div>
                    </c:otherwise>
                </c:choose>

                <li class="nav-item">
                    <c:if test="${not empty sessionScope.user}">
                        <c:out value="${sessionScope.user.role}: ${sessionScope.user.firstName} ${sessionScope.user.middleName} ${sessionScope.user.lastName}"/>
                    </c:if>
                </li>

                <c:choose>
                    <c:when test="${sessionScope.user_role eq 'GUEST'}">
                        <li class="nav-item"><a class="nav-link"
                                                href="${pageContext.request.contextPath}/jsp/pages/login.jsp">${log_in}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link"
                                                href="${pageContext.request.contextPath}/controller?command=log_out"> ${log_out}</a>
                        </li>
                    </c:otherwise>
                </c:choose>


                <li class="nav-item dropdown ">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        ${language}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en">ENG</a>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru">RU</a>
                        </li>
                    </ul>
                </li>

            </ul>
    </div>
    </div>


</nav>

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

