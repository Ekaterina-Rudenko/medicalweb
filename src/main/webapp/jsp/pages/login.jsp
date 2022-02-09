<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 24.01.2022
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="log_in.title" var="login_title"/>
<fmt:message key="log_in.login" var="login"/>
<fmt:message key="log_in.password" var="password"/>
<fmt:message key="log_in.submit" var="submit"/>
<fmt:message key="log_in.register" var="register"/>
<fmt:message key="log_in.role_undefined.message" var="role_undefined"/>
<fmt:message key="log_in.status_blocked.message" var="status_blocked"/>
<fmt:message key="log_in.incorrect_login_or_password.message" var="incorrect_login_or_pass"/>
<fmt:message key="log_in.or_register" var="register_if"/>

<html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
    <link href="${pageContext.request.contextPath}/CSS/style.css" rel="stylesheet">

    <script type="text/javascript">
        window.history.forward();

        function noBack() {
            window.history.forward();
        }
    </script>
    <title>${login_title}</title>
</head>
<body>

<c:import url="header.jsp"/>

<div class="container justify-content-center col-12 col-sm-6 mt-3">
    <h3 class="text-center p-3">${login_title}</h3>
    <form role="form" action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="log_in"/>
        <div class="row gy-3">
            <div class="form-group" >
                <label class="form-label">${login}</label>
                <input type="text" name="login" value="${param.login}" class="form-control"
                       placeholder="${login}">
            </div>
            <div class="form-group" >
                <label class="form-label">${password}</label>
                <input type="text" name="password" value="${param.password}" class="form-control"
                       placeholder="${password}">
            </div>
            <c:if test="${!empty requestScope.user_role_undefined}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${requestScope.user_role_undefined}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                ${role_undefined}
            </div>
            <c:if test="${!empty requestScope.user_status_blocked}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${requestScope.user_status_blocked}"/>
                </div>
            </c:if>
            <div class="invalid-feedback">
                ${status_blocked}
            </div>
            <c:if test="${!empty requestScope.incorrect_login_or_pass}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${requestScope.incorrect_login_or_pass}"/>
                </div>
            </c:if>
          <%--  <div class="invalid-feedback">
                ${incorrect_login_or_pass}
            </div>--%>
            <div class="text-center mb-3">
                <button type="submit" class="btn btn-success">${submit}</button>
            </div>
            <div>
                <button type="button" class="border-primary" >
                <a href="${pageContext.request.contextPath}/jsp/pages/registration.jsp">${register_if}</a>
                </button>
            </div>

        </div>
    </form>
</div>
<hr/>
<c:import url="footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>

</body>
</html>
