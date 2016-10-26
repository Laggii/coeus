<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://coeus.com/jsp/tags/customtags" prefix="customtags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${language}">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Coeus - Courses</title>

    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/footer.css" rel="stylesheet">
    <link href="css/table.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Navbar-->
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>

    <div class="row">
        <!-- user menu -->
        <%@ include file="/WEB-INF/jspf/usermenu.jspf" %>

        <!-- all courses -->
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title pull-left">All courses</h3>
                    <c:if test="${! (user.isStudent)}">
                        <button type="button" class="btn btn-success btn-xs pull-right" data-toggle="modal"
                                data-target="#courseModal">Create new
                        </button>
                    </c:if>
                    <div class="clearfix"></div>
                </div>
                <table id="courses" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Teacher</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="course" items="${requestScope.courses}">
                        <customtags:printCourse course="${course}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- user courses -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Your courses</h3>
                </div>
                <c:if test="${! (empty requestScope.userCourses)}">
                    <table id="usercourses" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Teacher</th>
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

            <!-- Modal Add Course-->
            <c:if test="${! (user.isStudent)}">
                <div id="courseModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create Course</h4>
                            </div>
                            <form method="POST" action="./main">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="control-label">Name:</label>
                                        <input class="form-control" name="name" maxlength="35"
                                               value="<c:out value="${name}"/>" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Description:</label>
                                        <textarea class="form-control" name="description" maxlength="255"
                                                  required><c:out value="${description}"/></textarea>
                                    </div>
                                    <input type="hidden" name="action" value="addcourse">
                                    <input type="hidden" name="token" value="${token}">
                                    <c:if test="${! (empty errorMsg)}">
                                        </br>
                                        <div class="alert alert-danger">
                                                    <span class="glyphicon glyphicon-exclamation-sign"
                                                          aria-hidden="true"></span>
                                            <a class="close" data-dismiss="alert" href="#">×</a><c:out
                                                value="${errorMsg}"/>
                                        </div>
                                    </c:if>
                                    <c:if test="${! (empty successMsg)}">
                                        </br>
                                        <div class="alert alert-success">
                                                    <span class="glyphicon glyphicon-exclamation-sign"
                                                          aria-hidden="true"></span>
                                            <a class="close" data-dismiss="alert" href="#">×</a><c:out
                                                value="${successMsg}"/>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                    </button>
                                    <button type="submit" class="btn btn-primary">Create</button>
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
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#courses').DataTable();
        $('#usercourses').DataTable();
    });
</script>
<c:if test="${!(empty errorMsg) || !(empty successMsg)}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#courseModal').modal('show');
        });
    </script>
</c:if>
</body>
</html>
