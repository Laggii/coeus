<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://coeus.com/jsp/tags/customtags" prefix="customtags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - Course</title>

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
                    <h3 class="panel-title">Course Information</h3>
                </div>

                <div class="panel-body">

                    <div class="media">
                        <div class="media-body">
                            <address>
                                <h4 class="media-heading">
                                    <strong>${courseInfo.name}</strong>
                                </h4>
                                <br>
                                ID: <c:out value="${courseInfo.courseId}"/>
                                <br>
                                Description: ${courseInfo.description}
                                <br>
                                Teacher: <a
                                    href="./main?action=profile&id=${courseInfo.owner.userId}">${courseInfo.owner.firstName} ${courseInfo.owner.lastName}</a>
                                <br>
                                Date created: <fmt:formatDate value="${courseInfo.dateCreated}"/>
                                <br>
                            </address>
                            <c:choose>
                                <c:when test="${(empty isMember)}"><a class="btn btn-info btn-xs" role="button"
                                                                      href="./main?action=joincourse&id=${courseInfo.courseId}">Join
                                    Course</a></c:when>
                                <c:when test="${! (empty isMember)}"><a class="btn btn-warning btn-xs" role="button"
                                                                        href="./main?action=leavecourse&id=${courseInfo.courseId}">Leave
                                    Course</a></c:when>
                            </c:choose>
                            <c:if test="${(user.isTeacher && courseInfo.isOwner(user)) || user.isAdmin}">
                                <button type="button" class="btn btn-info btn-xs" data-toggle="modal"
                                        data-target="#courseModal">Edit Course
                                </button>
                                <a class="btn btn-danger btn-xs" role="button"
                                   href="./main?action=delcourse&id=${courseInfo.courseId}">Delete course</a>
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
                            <img class="media-object avatar img-square img-thumbnail"
                                 src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMjA1IiBoZWlnaHQ9IjE4MCIgdmlld0JveD0iMCAwIDIwNSAxODAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MTgwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTU3YzJmOGJmOGQgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTdjMmY4YmY4ZCI+PHJlY3Qgd2lkdGg9IjIwNSIgaGVpZ2h0PSIxODAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3OCIgeT0iOTQuNSI+MjA1eDE4MDwvdGV4dD48L2c+PC9nPjwvc3ZnPg=="
                                 style="width: 150px;height:150px;">
                        </div>
                    </div>
                </div>
            </div>

            <!-- users on course -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Course members</h3>
                </div>
                <c:if test="${! (empty requestScope.courseMembers)}">
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
                        <c:forEach var="member" items="${requestScope.courseMembers}">
                            <customtags:printUser user="${member}"/>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>

            <c:if test="${(user.isTeacher && courseInfo.isOwner(user)) || user.isAdmin}">
                <!-- Modal Edit Course-->
                <div id="courseModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Edit Course</h4>
                            </div>
                            <form method="POST" action="./main">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="control-label">Name:</label>
                                        <input class="form-control" name="name" maxlength="35"
                                               value="<c:out value="${courseInfo.name}"/>" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Description:</label>
                                        <textarea class="form-control" name="description" maxlength="255"
                                                  required><c:out value="${courseInfo.description}"/></textarea>
                                    </div>
                                    <input type="hidden" name="token" value="${token}">
                                    <input type="hidden" name="action" value="editcourse">
                                    <input type="hidden" name="id" value="${courseInfo.courseId}">

                                    <c:if test="${! (empty errorMsgModal)}">
                                        </br>
                                        <customtags:printMessage message="${errorMsgModal}" type="error"/>
                                    </c:if>
                                    <c:if test="${! (empty successMsgModal)}">
                                        </br>
                                        <customtags:printMessage message="${successMsgModal}" type="success"/>
                                    </c:if>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
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
<c:if test="${!(empty errorMsgModal) || !(empty successMsgModal)}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#courseModal').modal('show');
        });
    </script>
</c:if>
</body>
</html>