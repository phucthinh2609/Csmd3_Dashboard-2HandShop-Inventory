<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 14/06/2022
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="styles.css"%>
    </style>
</head>
<body>
    <div class="container">
        <header>Login Form</header>
        <form>
            <div class="input-field">
                <input type="text" name="username" required>
                <label>Email or Username</label>
            </div>
            <div class="input-field">
                <input class="pswrd" type="password" name="password" required>
                <span class="show">SHOW</span>
                <label>Password</label>
            </div>
            <div class="button">
                <div class="inner">
                </div>
                <button>LOGIN</button>
            </div>
        </form>
        <div class="auth">
            Or login with</div>
        <div class="links">
            <div class="facebook">
                <i class="fab fa-facebook-square"><span>Facebook</span></i>
            </div>
            <div class="google">
                <i class="fab fa-google-plus-square"><span>Google</span></i>
            </div>
        </div>
        <div class="signup">
            Not a member? <a href="#">Signup now</a>
        </div>
    </div>
</body>
</html>
