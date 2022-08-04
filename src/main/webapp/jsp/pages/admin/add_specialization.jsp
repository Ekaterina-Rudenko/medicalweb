<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 03.02.2022
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Title</title>
</head>
<body>
<c:import url="../header.jsp"/>

<div class="container justify-content-center col-12 col-sm-6 mt-3">
    <h3 class="text-center p-3">Adding new specialization: </h3>
    <form role="form" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="add_specialization"/>
        <div class="row gy-3">
            <div class="form-group">
                <input type="text" name="spec_name" value="${param.spec_name}" class="form-control"
                       placeholder="Specialization name">
            </div>
        </div>
        <div class="text-center mb-3">
            <button type="submit" class="btn btn-success">Submit</button>
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
