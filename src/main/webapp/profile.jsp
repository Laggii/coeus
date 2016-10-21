<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="userProfile" value="${not empty requestScope.userProfile ? requestScope.userProfile : sessionScope.user}"
       scope="page"/>
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

        <div class="col-md-9 personal-info">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">User Profile</h3>
                </div>

                <div class="panel-body">

                    <div class="media">
                        <div class="media-body">
                            <address>
                                <h4 class="media-heading">
                                    <strong>${userProfile.firstName} ${userProfile.lastName},</strong>
                                    <i><c:choose>
                                        <c:when test="${userProfile.roleId==1}">Student</c:when>
                                        <c:when test="${userProfile.roleId==2}">Teacher</c:when>
                                        <c:when test="${userProfile.roleId==3}">Admin</c:when>
                                        <c:otherwise>User</c:otherwise>
                                    </c:choose>
                                    </i>
                                </h4>
                                <br>
                                ID: <c:out value="${userProfile.userId}"/>
                                <br>
                                Email: ${userProfile.email}
                                <br>
                                Sex:
                                <c:choose>
                                    <c:when test="${userProfile.gender.toString() eq 'm'}">male</c:when>
                                    <c:when test="${userProfile.gender.toString() eq 'f'}">female</c:when>
                                </c:choose>
                                <br>
                                Date of Birth: <fmt:formatDate value="${userProfile.birthDate}"/>
                                <br>
                                Phone: ${userProfile.phone}
                                <br>
                                Registration date: <fmt:formatDate value="${userProfile.regDate}"/>
                                <br>
                            </address>
                            <c:if test="${! (userProfile.userId == user.userId)}">
                                <a class="btn btn-info btn-xs" role="button" href="#">Add to Friends</a>
                                <a class="btn btn-info btn-xs" role="button" href="#">Send message</a>
                            </c:if>
                        </div>
                        <div class="media-right">
                            <img class="media-object avatar img-circle img-thumbnail"
                                 src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjA1IiBoZWlnaHQ9IjE4MCIgdmlld0JveD0iMCAwIDIwNSAxODAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MTgwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTU3YzJmOGJmOGQgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTdjMmY4YmY4ZCI+PHJlY3Qgd2lkdGg9IjIwNSIgaGVpZ2h0PSIxODAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3OCIgeT0iOTQuNSI+MjA1eDE4MDwvdGV4dD48L2c+PC9nPjwvc3ZnPg=="
                                 style="width: 150px;height:150px;">
                        </div>
                    </div>
                    <br>
                    User Friends Table
                    <br>
                    User Courses Table
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Admin Panel(hide)</h3>
                </div>
                <div class="panel-body">
                    Delete user, change user info
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>