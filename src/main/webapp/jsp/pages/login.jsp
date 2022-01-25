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

<fmt:message key="log_in.title" var="login_title"/>
<fmt:message key="log_in.login" var="login"/>
<fmt:message key="log_in.password" var="password"/>
<fmt:message key="log_in.submit" var="submit"/>
<fmt:message key="log_in.register" var="register"/>

<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="login"/>
    ${login}<br/>
    <input type="text" name="login" value=""/>
    <br/>${password}<br/>
    <input type="text" name="password" value=""/>
    <br/>

    <input type="submit" value="Log in">


</form>
<hr/>
</body>
</html>
