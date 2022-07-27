<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 18.01.2022
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="doctors.specialization" var="specialization"/>
<fmt:message key="doctors.category" var="category"/>
<fmt:message key="doctors.appointment" var="appointment"/>


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


<div class="album py-5 bg-light">
   <div class="container">
       <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

<c:forEach var="doctor" items="${requestScope.doctor_list}">
    <div class="col">
        <div class="card shadow-sm">

            <img class="bd-placeholder-img card-img-top" src="${doctor.photoPath}" class="img-thumbnail" alt="${doctor.lastName}"  width="98%"/>

            <div class="card-body">
                <b><p class="card-text">${doctor.lastName} ${doctor.firstName} ${doctor.middleName}</p></b>
                <p><br><b>${specialization}:</b> ${doctor.specialization.name} </p>
                <p><br><b>${category}:</b> ${doctor.category}</p>

                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">

                            <c:choose>
                                <c:when test="${sessionScope.user_role eq 'PATIENT'}">
                                    <button class="btn btn-outline-success me-2" type="button">
                                        <a href="${pageContext.request.contextPath}/jsp/pages/patient/make_appointment.jsp" >${appointment}</a>
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-outline-success me-2" type="button">
                                        <a href="${pageContext.request.contextPath}/jsp/pages/login.jsp" >${appointment}</a>
                                    </button>
                                </c:otherwise>
                            </c:choose>


                    </div>
                </div>
            </div>

        </div>
    </div>
</c:forEach>

       </div>
   </div>
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
