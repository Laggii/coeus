<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Coeus - Login page</title>
    <link href="css/login.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<!-- Login Panel -->
<div class="login-panel panel panel-default">
    <div class="panel-heading">Welcome to Coeus</div>
    <div class="panel-body">

        <ul class="nav nav-tabs">
            <li class="active"><a href="#login" data-toggle="tab">Sign in</a></li>
            <li><a href="#register" data-toggle="tab">Registration</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane fade in active" id="login">
                <form role="form" method="POST">
                    <!-- TODO: change field length -->
                    </br>
                    <div class="form-group">
                        <input class="form-control" placeholder="E-mail" name="email" type="email" required autofocus>
                    </div>
                    <div class="form-group">
                        <input class="form-control" placeholder="Password" name="password" type="password" required>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
                        </label>
                    </div>
                    <button class="btn btn-sm btn-success" type="submit">Login</button>
                </form>
            </div>

            <div class="tab-pane fade" id="register">
                <form role="form" method="POST">
                    </br>
                    <div class="form-group">
                        <input class="form-control" placeholder="E-mail" name="email" type="email" required autofocus>
                    </div>
                    <div class="form-group">
                        <input class="form-control" placeholder="Password" name="password" type="password" required>
                    </div>
                    <div class="form-group">
                        <input class="form-control" placeholder="Repeat password" name="password" type="password"
                               required>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input name="remember" type="checkbox" value="I am a teacher">I am a teacher
                        </label>
                    </div>
                    <button class="btn btn-sm btn-success" type="submit">Register</button>
                </form>
            </div>

        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
