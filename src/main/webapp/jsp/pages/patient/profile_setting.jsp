<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 8/2/2022
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>


<fmt:message key="profile.settings.title" var="title"/>
<fmt:message key="profile.settings.change_phone" var="change_phone"/>
<fmt:message key="profile.settings.change_email" var="change_email"/>
<fmt:message key="profile.settings.change_password" var="change_pass"/>
<fmt:message key="profile.setting.old_psw" var="old_pass"/>
<fmt:message key="profile.setting.new_psw" var="new_pass"/>
<fmt:message key="profile.setting.new_psw_confirm" var="conf_new_pass"/>
<fmt:message key="invalid_new_password_message" var="invalid_new_password_message"/>
<fmt:message key="incorrect_old_password_message" var="incorrect_old_password_message"/>
<fmt:message key="password_mismatch_message" var="password_mismatch_message"/>

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
    <c:when test="${!empty requestScope.incorrect_old_password}">
        <div class="alert alert-warning" id="message"><b
                class="invalid_message">${incorrect_old_password_message}</b></div>
    </c:when>
    <c:when test="${!empty requestScope.invalid_new_password}">
        <div class="alert alert-warning" id="message"><b
                class="invalid_message">${invalid_new_password_message}</b></div>
    </c:when>
    <c:when test="${!empty requestScope.password_mismatch}">
        <div class="alert alert-warning" id="message"><b
                class="invalid_message">${password_mismatch_message}</b></div>
    </c:when>
</c:choose>


<h3 class="text-center p-3">${change_pass}</h3>
<div class="container justify-content-center col-12 col-sm-6 mt-3">
    <form role="form" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="change_password"/>

        <%-- <div class="collapse ${requestScope.password_change_result eq 'true' or requestScope.password_change_result eq 'password_mismatch_message'
         or requestScope.password_change_result eq 'invalid_new_password_message' or requestScope.password_change_result eq 'incorrect_old_password_message' ? 'in' : ''}"
              id="psw_change">--%>

        <div class="form-group col-sm-4 indent">
            <label for="old_psw">${old_pass}</label>
            <input type="password" class="form-control" id="old_psw" name="old_password">
        </div>


        <div class="form-group col-sm-4 indent">
            <label for="new_psw">${new_pass}</label>
            <input type="password" class="form-control" id="new_psw" name="new_password">
        </div>

        <div class="form-group col-sm-4 indent">
            <label for="psw_confirmation">${conf_new_pass}</label>
            <input type="password" class="form-control" id="psw_confirmation"
                   name="new_password_confirm">
        </div>


        <div class="col-sm-1 indent">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
        <div class="col-sm-2">
        </div>

    </form>
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
