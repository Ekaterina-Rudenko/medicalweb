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

<fmt:message key="register.doctor.title" var="register_title"/>
<fmt:message key="register.first_name" var="first_name"/>
<fmt:message key="register.middle_name" var="middle_name"/>
<fmt:message key="register.last_name" var="last_name"/>
<fmt:message key="register.login" var="login"/>
<fmt:message key="register.password" var="password"/>
<fmt:message key="register.email" var="email"/>
<fmt:message key="register.phone" var="phone"/>
<fmt:message key="register.category" var="category"/>
<fmt:message key="register.specialization" var="specialization"/>
<fmt:message key="register.photo" var="photo"/>
<fmt:message key="register.repeated_password" var="repeated_password"/>
<fmt:message key="register.submit" var="register_submit"/>
<fmt:message key="register.name.helper" var="name_helper"/>
<fmt:message key="register.login.helper" var="login_helper"/>
<fmt:message key="register.password.helper" var="password_helper"/>
<fmt:message key="register.phone.helper" var="phone_helper"/>
<fmt:message key="register.email.helper" var="email_helper"/>
<fmt:message key="register.photo.helper" var="photo_helper"/>
<fmt:message key="registration.success.message" var="register_success"/>
<fmt:message key="register.category.first" var="first"/>
<fmt:message key="register.category.second" var="second"/>
<fmt:message key="register.category.higher" var="higher"/>

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

    <title>Title</title>
</head>
<body>

<c:import url="../header.jsp"/>

<div class="container justify-content-center col-12 col-sm-6 mt-3">
    <h3 class="text-center p-3">${register_title}</h3>
    <form role="form" action="${pageContext.request.contextPath}/controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add_doctor"/>
        <div class="row gy-3">
            <div class="form-group">
                <label class="form-label">${first_name}</label>
                <input type="text" name="first_name" class="form-control"
                       value="${param.first_name}" placeholder="Enter ${first_name}"
                       required pattern="^[A-Za-zА-Яа-я]{3,30}$">
                <c:if test="${!empty requestScope.invalid_first_name}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_first_name}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label">${middle_name}</label>
                <input type="text" name="middle_name" class="form-control"
                       value="${param.middle_name}" placeholder="Enter ${middle_name}"
                       required pattern="^[A-Za-zА-Яа-я]{3,30}$">
                <c:if test="${!empty requestScope.invalid_middle_name}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_middle_name}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label">${last_name}</label>
                <input type="text" name="last_name" class="form-control"
                       value="${param.last_name}" placeholder="Enter ${last_name}"
                       required pattern="^[A-Za-zА-Яа-я]{3,30}$">
                <c:if test="${!empty requestScope.invalid_last_name}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_last_name}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label">${login}</label>
                <input type="text" name="login" value="${param.login}" class="form-control"
                       placeholder="${login}" required pattern="^[A-Za-zА-Яа-я0-9_]{3,30}$">
                <small id="loginHelpBlock" class="form-text text-muted">${login_helper}</small>
                <c:if test="${!empty requestScope.invalid_login}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_login}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label">${password}</label>
                <input type="password" name="password" value="${param.password}"
                       class="form-control form-control-sm" placeholder="${password}"
                       required pattern="^[A-Za-zА-Яа-я0-9\.]{5,45}$">
                <small id="passwordHelpBlock" class="form-text text-muted">${password_helper}</small>
                <c:if test="${!empty requestScope.invalid_password}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_password}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label"><fmt:message key="register.repeated_password"/></label>
                <input type="password" name="repeated_password"
                       value="${param.repeated_password}" class="form-control form-control-sm"
                       placeholder="Repeat password" required pattern="^[A-Za-zА-Яа-я0-9\.]{5,45}$">

                <c:if test="${!empty requestScope.invalid_repeated_password}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_repeated_password}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label">${email}</label>
                <input type="email" name="email" value="${param.email}"
                       class="form-control form-control-sm" placeholder="${email_helper}"
                       required pattern="^[A-Za-z0-9\.]{3,30}@[a-z]{2,7}\.[a-z]{2,4}$">

                <c:if test="${!empty requestScope.invalid_email}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_email}"/>
                    </div>
                </c:if>
            </div>

            <div class="form-group">
                <label class="form-label">${phone}</label>
                <input type="text" name="phone" value="${param.phone}" class="form-control"
                       placeholder="44/25/29/33XXXXXXX" required pattern="(44|25|29|33)\d{7}">
                <small id="passwordHelpBlock" class="form-text text-muted">${phone_helper}</small>
                <c:if test="${!empty requestScope.invalid_phone_number}">
                    <div class="invalid-feedback-backend" style="color: red">
                        <fmt:message key="${requestScope.invalid_phone_number}"/>
                    </div>
                </c:if>
            </div>

            <div>
                <label class="form-label">${category}: </label>
                <p>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="category" id="inlineRadio1" value="SECOND"
                           checked>
                    <label class="form-check-label" for="inlineRadio1">${second}</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="category" id="inlineRadio2" value="FIRST">
                    <label class="form-check-label" for="inlineRadio2">${first}</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="category" id="inlineRadio3" value="HIGHER">
                    <label class="form-check-label" for="inlineRadio2">${higher}</label>
                </div>
            </div>

            <div>
                <label class="form-label">${category}: </label>
                <p>
                    <select name="specialization_id" class="form-select" required placeholder="${specialization}">
                        <c:forEach var="specialization" items="${sessionScope.specialization_list}">
                            <option value="${specialization.specializationId}">${specialization.name}</option>
                        </c:forEach>
                    </select><br>
            </div>


           <%-- <form name="upload_image" method="post" action="${pageContext.request.contextPath}/controller"
                  enctype="multipart/form-data">--%>
              <%--  <input type="hidden" name="command" value="upload_image">--%>
                <br>
                <div class="form-group">
                    <label class="form-label">${photo}</label>
                    <input type="file" name="image_path"  class="form-control">
                </div>
         <%--   </form>--%>

            <div class="text-center mb-3">
                <button type="submit" class="btn btn-success"><fmt:message key="register.submit"/></button>
            </div>


        </div>
    </form>
</div>
<c:import url="../footer.jsp"/>

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
