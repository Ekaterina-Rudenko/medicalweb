
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 500</title>
</head>
<body>
request from ${pageContext.errorData.requestURI} failed
<hr/>
exception=  ${pageContext.exception}
<hr/>
status= ${pageContext.errorData.statusCode}
<hr/>
servlet name= ${pageContext.errorData.servletName}
<hr/>
<a href="index.jsp">ToIndex</a>
</body>
</html>
