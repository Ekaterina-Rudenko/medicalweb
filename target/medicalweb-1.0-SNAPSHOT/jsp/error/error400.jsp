<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 25.01.2022
  Time: 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>
<fmt:message key="error.back" var="back_home"/>

<html>
<head>
    <title>Error 400</title>
</head>
<body>
<c:import url="../pages/header.jsp"/>
request from ${pageContext.errorData.requestURI} failed
<hr/>
exception=  ${pageContext.exception}
<hr/>
status= ${pageContext.errorData.statusCode}
<hr/>
servlet name= ${pageContext.errorData.servletName}
<hr/>
<a href="index.jsp">${back_home}</a>
<hr/>
<c:import url="../pages/footer.jsp"/>
</body>
</html>
