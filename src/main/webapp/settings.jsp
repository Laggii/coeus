<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="userSettings" value="${not empty requestScope.userSettings ? requestScope.userSettings : sessionScope.user}"
       scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - Settings page</title>

    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Navbar-->
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="row">
        <!-- user menu -->
        <%@ include file="/WEB-INF/jspf/usermenu.jspf" %>

        <div class="col-md-9 personal-info">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Profile settings</h3>
                </div>

                <div class="panel-body">
                    <form role="form" class="col-md-6" method="POST" action="./main">
                        <div class="form-group">
                            <label class="control-label">Email:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-envelope"></i>
                                </span>
                                <input class="form-control" name="email" maxlength="254"
                                       type="email"
                                       value="<c:out value="${userSettings.email}"/>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">First name:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" name="firstname" maxlength="35"
                                       value="<c:out value="${userSettings.firstName}"/>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Last name:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <input class="form-control" name="lastname" maxlength="35"
                                       value="<c:out value="${userSettings.lastName}"/>" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Sex:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <select class="form-control" name="sex">
                                    <option value="m"
                                            <c:if test="${userSettings.gender.toString() eq 'm'}">selected</c:if>>Male
                                    </option>
                                    <option value="f"
                                            <c:if test="${userSettings.gender.toString() eq 'f'}">selected</c:if>>Female
                                    </option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Phone:</label>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                                <input name="phone" id="phone"
                                       class="form-control bfh-phone"
                                       type="text" value='${userSettings.phone}'
                                       data-format="+d (ddd) ddd-dd-dd" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Date of Birth:</label>
                            <div class="input-group">
                                <div class="bfh-datepicker" data-name="birthdate" id="birthdate"
                                     data-date='<fmt:formatDate value="${userSettings.birthDate}" pattern="dd.MM.yyyy"/>'
                                     data-format="d.m.y"
                                     data-min="01/01/1900" data-max="today" required>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="token" value="${token}">
                        <input type="hidden" name="action" value="editprofile">
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <button class="btn btn-primary" type="submit">Save changes</button>
                            </div>
                        </div>
                    </form>
                    <form role="form" class="col-md-6" method="POST" action="./main">
                        <div class="form-group">
                            <label class="control-label">Old password:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" name="old_password" maxlength="40"
                                       type="password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">New Password:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" name="new_password" maxlength="40"
                                       type="password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Repeat password:</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <input class="form-control" name="repeat_password"
                                       maxlength="40" type="password"
                                       required>
                            </div>
                        </div>
                        <input type="hidden" name="action" value="changepassword">
                        <input type="hidden" name="token" value="${token}">
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-8">
                                <button class="btn btn-primary" type="submit">Change password</button>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${! (empty errorMsg)}">
                    </br>
                    <div class="alert alert-danger" style="margin-left:30px;margin-right:30px;">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <a class="close" data-dismiss="alert" href="#">×</a><c:out value="${errorMsg}"/>
                    </div>
                </c:if>
                <c:if test="${! (empty successMsg)}">
                    </br>
                    <div class="alert alert-success" style="margin-left:30px;margin-right:30px;">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <a class="close" data-dismiss="alert" href="#">×</a><c:out value="${successMsg}"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-formhelpers.js"></script>
</body>
</html>