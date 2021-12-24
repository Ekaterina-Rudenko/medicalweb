<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2>"Upload Files!"
</h2>
<br/>
<form method="post" action="controller" enctype="multipart/form-data">
    <%-- <input type="text" name="number" value="1"/>--%>
    <input type="file" name="content"/>
    <input type="submit" value="upload">
</form>
</body>
</html>