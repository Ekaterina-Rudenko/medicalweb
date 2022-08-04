<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<c:import url="header.jsp"/>

<div class="row justify-content-center">
    <div class="col-lg-9">
        <div class="text-center">
            <h1>Our medical centre has different services for every patient</h1>
            <p> We aim to serve our local community by providing you and your family with excellent medical care in a modern healthcare facility. By choosing the HEALTHY Medical Centre, you will take a step towards the medical facilities of the future. We hope to provide the best health care  for patients.</p>>
            <h1>Get to know our team</h1>
            <p class="">As a centre focused on family medicine, we are more than happy to look after you and your family in a continuing way, making sure to keep you healthy and well looked after at all times. Get to know the team working
                at our medical centre in Minsk</p>
            <a href="${pageContext.request.contextPath}/controller?command=show_all_doctors" class="btn btn-primary ms-2">Our doctors</a>
        </div>
    </div>
</div>

<br>

<br>
<div class="help-section mt-65">
    <div class="container">
        <div class="text-center">
            <h1>How can we help you today? </h1>
            <p class="mb-5">Check the list of medical services we provide or book your doctor appointment</p>
            <a href="${pageContext.request.contextPath}/controller?command=show_all_services" class="btn btn-primary mb-3 mb-lg-0">See medical services</a>
            <c:choose>
                <c:when test="${sessionScope.user_role eq 'PATIENT'}">
                    <a href="${pageContext.request.contextPath}/jsp/pages/patient/make_appointment.jsp" class="btn btn-primary ms-2">Make an appointment</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/jsp/pages/login.jsp" class="btn btn-primary ms-2">Make an appointment</a>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>
<br>
<br>
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