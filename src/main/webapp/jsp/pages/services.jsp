<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="services.title" var="services_title"/>
<fmt:message key="services.specialization" var="specialization"/>
<fmt:message key="services.service_name" var="name"/>
<fmt:message key="services.price" var="price"/>
<fmt:message key="register.category" var="category"/>

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
    <title>Title</title>
</head>
<body>

<c:import url="header.jsp"/>
<div class="container justify-content-center col-12 col-sm-6 mt-3">
<form role="form" action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="find_services_by_specialization"/>

    <div>
        <label class="form-label">Filter by specialization: </label>
        <p>
            <select name="specialization_id" class="form-select" required placeholder="${specialization}">
                <c:forEach var="specialization" items="${sessionScope.specialization_list}">
                    <option value="${specialization.specializationId}">${specialization.name}</option>
                </c:forEach>
            </select>
    </div>

    <div class="text-center mb-3">
        <button type="submit" class="btn btn-success">Filter</button>
        <button type="button" class="btn btn-success">
            <a href="${pageContext.request.contextPath}/controller?command=show_all_services">Disable filter</a>
        </button>
    </div>

</form>
</div>
<p>

    <div class="container">
        <h3 class="text-center">${services_title}</h3>
        <table class="table table-bordered">
            <tr>
                <th>${specialization}</th>
                <th>${name}</th>
                <th>${price}</th>
            </tr>

            <c:forEach items="${sessionScope.service_list}" var="service">
                <tr>
                    <td>${service.specialization.name}</td>
                    <td>${service.serviceName}</td>
                    <td>${service.price}</td>
                </tr>
            </c:forEach>

        </table>
    </div>

<c:import url="footer.jsp"/>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>