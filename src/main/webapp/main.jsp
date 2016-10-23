<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - Profile page</title>
    <!-- navbar / footer css should be be included here -->
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Navbar-->
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="row">
        <!-- user menu -->
        <%@ include file="/WEB-INF/jspf/usermenu.jspf" %>

        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Information</h3>
                </div>

                <div class="panel-body">
                    <c:if test="${! (empty errorMsg)}">
                        </br>
                        <div class="alert alert-danger">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            <a class="close" data-dismiss="alert" href="#">×</a><c:out value="${errorMsg}"/>
                        </div>
                    </c:if>
                </div>
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
