<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- Main -->
<%--    <%@include file="/cp/head/main.jsp" %>--%>
</head>
<body>
    <div class="container">
        <header>Login Form</header>
        <form method="post">
            <div class="input-field">
                <input type="text" name="username" value="${username}" required>
                <label>Email or Username</label>
            </div>
            <div class="input-field">
                <input class="pswrd" type="password" name="password" value="${password}" required>
                <span class="show">SHOW</span>
                <label>Password</label>
            </div>
            <div class="button">
                <div class="inner">
                </div>
                <button type="submit">LOGIN</button>
            </div>
        </form>
        <!-- start alert success or fail -->
        <div>
            <c:if test="${requestScope['success'] == true}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="mdi mdi-check-all mr-2"></i>
                    Create user successful
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
            </c:if>
            <c:if test="${requestScope['success'] == false}">
                <c:forEach items="${requestScope['errors']}" var="error">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="mdi mdi-block-helper mr-2"></i>
                            ${error}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <!-- end alert success or fail -->
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
