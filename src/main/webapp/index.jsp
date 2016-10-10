<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
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
            <h1>Welcome to Coeus!</h1>
            <p>Educational social network for students and teachers</p>
            <p><a class="btn btn-primary btn-lg" href="./login" role="button">Sign in &raquo;</a></p>
        </div>
    </div>

    <div class="container">
        <!-- Example row of columns -->
        <div class="row">
            <div class="col-md-4">
                <h2>For students</h2>
                <p>Communicate with your friends and teachers. Find an education course</p>
            </div>
            <div class="col-md-4">
                <h2>For teachers</h2>
                <p>Create and manage your courses with ease</p>
            </div>
            <div class="col-md-4">
                <h2>For admins</h2>
                <p>Monitor your social network. Take a full control.</p>
            </div>
        </div>

        <hr>

    </div>

    <!-- Footer -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
