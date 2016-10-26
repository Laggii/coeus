<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${lang}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - educational social network</title>

    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="container">

    <!-- Navbar-->
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

    <!-- Jumbotron -->
    <div class="jumbotron">
        <div class="container">
            <h1><fmt:message key="login.label.welcome" />!</h1>
            <p><fmt:message key="index.label.info" /></p>
            <p><a class="btn btn-primary btn-lg" href="./login" role="button"><fmt:message key="index.button.signin" /> &raquo;</a></p>
        </div>
    </div>

    <div class="container">
        <!-- Columns -->
        <div class="row">
            <div class="col-md-4">
                <h2><fmt:message key="index.label.student" /></h2>
                <p><fmt:message key="index.text.student" /></p>
            </div>
            <div class="col-md-4">
                <h2><fmt:message key="index.label.teacher" /></h2>
                <p><fmt:message key="index.text.teacher" /></p>
            </div>
            <div class="col-md-4">
                <h2><fmt:message key="index.label.admin" /></h2>
                <p><fmt:message key="index.text.admin" /></p>
            </div>
        </div>

    </div>

    <!-- Footer -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
