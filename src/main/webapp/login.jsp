<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${lang}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - Login page</title>

    <link href="css/login.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Navbar -->
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

    <!-- Login and Registration Panel -->
    <div class="login-panel panel panel-default">
        <div class="panel-heading"><fmt:message key="login.label.welcome"/></div>
        <div class="panel-body">

            <ul class="nav nav-tabs">
                <li <c:if test="${isRegister != 'true'}"> class="active"</c:if>>
                    <a href="#login" data-toggle="tab">Sign in</a></li>
                <li <c:if test="${isRegister == 'true'}"> class="active"</c:if>>
                    <a href="#register" data-toggle="tab">Registration</a></li>
            </ul>

            <!-- Login tab -->
            <div class="tab-content">
                <div class="tab-pane fade in <c:if test="${isRegister != 'true'}">active</c:if>" id="login">
                    <form role="form" method="POST" action="./login">
                        <!-- TODO: change field length (maxlength= "") -->
                        </br>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-envelope"></i>
                                </span>
                                <input class="form-control" placeholder="E-mail" name="email" type="email" required
                                       value="<c:out value="${email}"/>" autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" placeholder="Password" name="password" maxlength="40"
                                       type="password" required>
                            </div>
                        </div>
                        <input type="hidden" name="token" value="${token}">
                        <button class="btn btn-sm btn-success" type="submit">Login</button>
                    </form>
                </div>

                <!-- Registration tab -->
                <div class="tab-pane fade in <c:if test="${isRegister == 'true'}">active</c:if>" id="register">
                    <form role="form" method="POST" action="./registration">
                        </br>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-envelope"></i>
                                </span>
                                <input class="form-control" placeholder="E-mail" name="email" type="email"
                                       value="<c:out value="${email}"/>" required autofocus>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" placeholder="First name" name="firstname" maxlength="35"
                                       value="<c:out value="${firstname}"/>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" placeholder="Last name" name="lastname" maxlength="35"
                                       value="<c:out value="${lastname}"/>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" placeholder="Password" name="password" maxlength="40"
                                       type="password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" placeholder="Repeat password" name="repeat_password"
                                       maxlength="40" type="password"
                                       required>
                            </div>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="teacher">I am a teacher
                            </label>
                        </div>
                        <input type="hidden" name="token" value="${token}">
                        <button class="btn btn-sm btn-success" type="submit">Register</button>
                    </form>
                </div>

            </div>
            <c:if test="${! (empty errorMsg)}">
                </br>
                <div class="alert alert-danger">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <a class="close" data-dismiss="alert" href="#">×</a><c:out value="${errorMsg}"/>
                </div>
            </c:if>
        </div>

    </div>

    <!-- Footer -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
