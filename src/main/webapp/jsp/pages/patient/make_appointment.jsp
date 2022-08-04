<%--
  Created by IntelliJ IDEA.
  User: Rudebox
  Date: 27.01.2022
  Time: 7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="bundle/locale"/>

<fmt:message key="create_a_visit.title" var="make_appoint"/>
<fmt:message key="create_a_visit.specialization" var="specialization"/>
<fmt:message key="create_a_visit.continue" var="continue"/>
<fmt:message key="create_a_visit.doctor" var="doctor"/>
<fmt:message key="create_a_visit.service" var="service"/>
<fmt:message key="create_a_visit.date" var="date"/>
<fmt:message key="create_a_visit.time_slot" var="time"/>
<fmt:message key="create_a_visit.payment" var="payment"/>
<fmt:message key="create_a_visit.submit" var="submit"/>

<fmt:message key="empty_doctor_message" var="empty_doctor"/>
<fmt:message key="empty_visit_date_message" var="empty_date"/>
<fmt:message key="empty_service_message" var="empty_service"/>
<fmt:message key="invalid_visit_date_message" var="invalid_date"/>
<fmt:message key="no_slots_found_message" var="no_slots"/>
<fmt:message key="empty_doctor_list_message" var="no_doctors_found"/>
<fmt:message key="empty_service_list_message" var="no_services_found"/>

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


<div class="container justify-content-center col-12 col-sm-6 mt-3">
    <h3 class="text-center p-3">${make_appoint}</h3>


    <form role="form" action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="find_doctors_by_specialization"/>
        <div class="row gy-3">
            <div>
                <label id="specialization" class="form-label">1. ${specialization} </label>
                <select name="specialization_id" class="form-select" required
                        placeholder="${specialization}">
                    <c:forEach var="specialization" items="${sessionScope.specialization_list}">
                        <option value="${specialization.specializationId}">${specialization.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="text-left mb-3">
                <button type="submit" class="btn btn-primary btn-sm">continue</button>
            </div>
        </div>
    </form>

    <form role="form" action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="find_time_slots_by_doctor_and_date"/>
        <div class="row gy-3">
            <div>
                <label id="doctor" class="form-label">2. ${doctor} </label>
                <select name="doctor_id" class="form-select" required placeholder="${doctor}">
                    <c:forEach var="doctor" items="${sessionScope.doctor_list}">
                        <option value="${doctor.userId}">${doctor.lastName} ${doctor.firstName}</option>
                    </c:forEach>
                </select>

                <c:if test="${!empty requestScope.empty_doctor}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.empty_doctor}"/>
                    </div>
                </c:if>
            </div>

            <div>
                <label id="service" class="form-label">3. ${service} </label>
                <select name="service_id" class="form-select" required placeholder="${service}">
                    <c:forEach var="service" items="${sessionScope.service_list}">
                        <option value="${service.serviceId}">${service.serviceName}</option>
                    </c:forEach>
                </select>
                <c:if test="${!empty requestScope.empty_service}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.empty_service}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label id="date" class="form-label">4. ${date}</label>
                <input type="date" name="visit_date" value="${param.visit_date}"
                       class="form-control" placeholder=${date} required>
            </div>
            <c:if test="${!empty requestScope.invalid_visit_date}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${requestScope.invalid_visit_date}"/>
                </div>
            </c:if>
            <c:if test="${!empty requestScope.empty_visit_date}">
                <div class="invalid-feedback-backend" style="color: red">
                    <fmt:message key="${requestScope.empty_visit_date}"/>
                </div>
            </c:if>
            <div class="text-left mb-3">
                <button type="submit" class="btn btn-primary btn-sm">continue</button>
            </div>
        </div>
    </form>

    <form role="form" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="create_visit"/>
        <div class="row gy-3">
            <div>
                <label id="time" class="form-label">5. ${time} </label>
                <select name="visit_time" class="form-select" required placeholder="${time}">
                    <c:forEach var="slot" items="${sessionScope.slots_list}">
                        <option value="${slot}">${slot}</option>
                    </c:forEach>
                </select><br>
                <c:if test="${!empty requestScope.empty_visit_time}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.empty_visit_time}"/>
                    </div>
                </c:if>
            </div>


            <div>
                <label class="form-label">6. ${payment}: </label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type_payment"
                           id="inlineRadio1"
                           value="FREE" checked>
                    <label class="form-check-label" for="inlineRadio1">Free</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type_payment"
                           id="inlineRadio2"
                           value="INSURANCE">
                    <label class="form-check-label" for="inlineRadio2">Insurance</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="type_payment"
                           id="inlineRadio3"
                           value="BILLABLE">
                    <label class="form-check-label" for="inlineRadio2">Billable</label>
                </div>
                <c:if test="${!empty requestScope.empty_type_payment}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.empty_type_payment}"/>
                    </div>
                </c:if>
            </div>

            <div>
                <c:if test="${sessionScope.user_role eq 'ADMIN'}">
                    <div class="form-group">
                        <label class="form-label">Enter patient id</label>
                        <input type="text" name="patient_id" class="form-control"
                               value="${param.patient_id}" placeholder="Enter patient id"/>
                    </div>
                </c:if>
                <c:if test="${!empty requestScope.empty_patient}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.empty_patient}"/>
                    </div>
                </c:if>
            </div>
            <div class="text-center mb-3">
                <button type="submit" class="btn btn-success">${submit}</button>
            </div>
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

