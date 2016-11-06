<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://coeus.com/jsp/tags/customtags" prefix="customtags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - Profile</title>

    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-formhelpers.min.css" rel="stylesheet">
    <link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
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
                                    <i>
                                        <c:choose>
                                            <c:when test="${userProfile.isStudent}">Student</c:when>
                                            <c:when test="${userProfile.isTeacher}">Teacher</c:when>
                                            <c:when test="${userProfile.isAdmin}">Admin</c:when>
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
                                Phone: <span class="bfh-phone" data-format="+d (ddd) ddd-dd-dd"
                                             data-number="${userProfile.phone}"></span>
                                <br>
                                Registration date: <fmt:formatDate value="${userProfile.regDate}"/>
                                <br>
                            </address>
                            <c:if test="${! (userProfile.userId == user.userId)}">
                                <c:choose>
                                    <c:when test="${(empty isFriend)}"><a class="btn btn-info btn-xs" role="button"
                                                                          href="./main?action=addfriend&id=${userProfile.userId}">Add
                                        to Friends</a></c:when>
                                    <c:when test="${! (empty isFriend)}"><a class="btn btn-warning btn-xs" role="button"
                                                                            href="./main?action=delfriend&id=${userProfile.userId}">Remove
                                        Friend</a></c:when>
                                </c:choose>
                                <a class="btn btn-info btn-xs" role="button" href="#">Send Message</a>
                            </c:if>
                            <c:if test="${! (empty errorMsg)}">
                                </br>
                                </br>
                                <customtags:printMessage message="${errorMsg}" type="error"/>
                            </c:if>
                            <c:if test="${! (empty successMsg)}">
                                </br>
                                </br>
                                <customtags:printMessage message="${successMsg}" type="success"/>
                            </c:if>
                        </div>
                        <div class="media-right">
                            <img class="media-object avatar img-circle img-thumbnail"
                                 src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjA1IiBoZWlnaHQ9IjE4MCIgdmlld0JveD0iMCAwIDIwNSAxODAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MTgwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTU3YzJmOGJmOGQgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTdjMmY4YmY4ZCI+PHJlY3Qgd2lkdGg9IjIwNSIgaGVpZ2h0PSIxODAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3OCIgeT0iOTQuNSI+MjA1eDE4MDwvdGV4dD48L2c+PC9nPjwvc3ZnPg=="
                                 style="width: 150px;height:150px;">
                        </div>
                    </div>
                </div>
            </div>

            <!-- user courses -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">User courses</h3>
                </div>
                <c:if test="${! (empty requestScope.userCourses)}">
                    <table id="courses" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Owner</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="userCourse" items="${requestScope.userCourses}">
                            <customtags:printCourse course="${userCourse}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>

            <!-- user friends -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">User friends</h3>
                </div>
                <c:if test="${! (empty requestScope.userFriends)}">
                    <table id="friends" class="table table-striped">
                        <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="friend" items="${requestScope.userFriends}">
                            <customtags:printUser user="${friend}"/>
                        </c:forEach>
                        </tbody>
                    </table>
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
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#courses').DataTable();
        $('#friends').DataTable();
    });
</script>
</body>
</html>