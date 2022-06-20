<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: macbookair
  Date: 17/06/2022
  Time: 07:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
    <!-- Meta -->
    <%@include file="/cp/head/meta.jsp" %>
    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.jsp" %>
    <!-- select2 css -->
    <%@include file="/cp/head/select2-css.jsp" %>
    <!-- success-error css -->
    <link rel="stylesheet" href="/assets/css/success-error.css">
    <!-- dropzone css -->
    <%@include file="/cp/head/dropzone-css.jsp" %>
    <!-- Bootstrap Css --> <!-- Icons Css --> <!-- App Css-->
    <%@include file="/cp/head/main.jsp" %>


</head>

<body data-sidebar="dark">

<!-- Begin page -->
<div id="layout-wrapper">

    <%@include file="/cp/layout/page-topbar.jsp" %>
    <div class="vertical-menu">

        <div data-simplebar="" class="h-100">

            <!--- Sidemenu -->
            <%@include file="/cp/layout/sidebar-menu.jsp" %>
            <!-- Sidebar -->
        </div>
    </div>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->
    <div class="main-content">

        <div class="page-content">
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box d-flex align-items-center justify-content-between">
                            <h4 class="mb-0 font-size-18">Edit user</h4>

                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">User</a></li>
                                    <li class="breadcrumb-item active">Edit</li>
                                </ol>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <form method="post">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="fullName">Full Name</label>
                                                <input id="fullName" name="fullName" type="text" class="form-control" value="${user.getFullName()}">
                                            </div>
                                            <div class="form-group">
                                                <label for="mobile">Mobile</label>
                                                <input id="mobile" name="mobile" type="text" class="form-control" value="${user.getMobile()}">
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Email</label>
                                                <input id="email" name="email" type="email" class="form-control" value="${user.getEmail()}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Status</label>
                                                <select name="status" class="custom-select">
                                                    <c:if test='${user.getStatus() == "ACTIVE"}'>
                                                        <option name="status" value="ACTIVE" selected="ACTIVE">ACTIVE</option>
                                                        <option name="status" value="BLOCK">BLOCK</option>
                                                    </c:if>
                                                    <c:if test='${user.getStatus() == "BLOCK"}'>
                                                        <option name="status" value="ACTIVE" >ACTIVE</option>
                                                        <option name="status" value="BLOCK" selected="BLOCK">BLOCK</option>
                                                    </c:if>
                                                </select>
                                            </div>

                                        </div>

                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <div class="form-group">
                                                    <label for="password">Password</label>
                                                    <div class="input-group">
                                                        <input type="password" name="password" id="password"
                                                               class="form-control pwd" value="${user.getPassword()}">
                                                        <span class="input-group-append">
                                                                <button class="btn btn-default reveal" type="button">
                                                                    <i class="fa fa-eye"></i>
                                                                </button>
                                                            </span>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="form-group">
                                                    <label for="address">Address</label>
                                                    <input id="address" name="address" type="text" class="form-control" value="${user.getAddress()}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Role</label>
                                                <select name="role" class="custom-select">
                                                    <c:if test='${user.getRole() == "ADMIN"}'>
                                                        <option name="role" value="ADMIN" selected="ADMIN">ADMIN</option>
                                                        <option name="role" value="USER">USER</option>

                                                    </c:if>
                                                    <c:if test='${user.getRole() == "USER"}'>
                                                        <option name="role" value="ADMIN" >ADMIN</option>
                                                        <option name="role" value="USER" selected="USER">USER</option>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary mr-1 waves-effect waves-light">Save changes</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->
            </div>
            <!-- container-fluid -->
        </div>
        <!-- End Page-content -->

        <div class="footerr">
            <c:if test="${requestScope['success'] == true}">
                <ul class="success">
                    <li>Edit user '${user.getFullName()}' successful</li>
                </ul>
            </c:if>
            <c:if test="${requestScope['success'] == false}">
                <ul class="error">
                    <c:forEach items="${requestScope['errors']}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
    <!-- end main content-->
</div>
<!-- END layout-wrapper -->


<!-- JAVASCRIPT -->
<%@include file="/cp/script/javascript.jsp" %>

<script src="/assets/js/pages/password-hide-show.js"></script>
<!-- select 2 plugin -->
<%@include file="/cp/script/select-2-plugin.jsp" %>
<!-- dropzone plugin -->
<%@include file="/cp/script/dropzone-plugin.jsp" %>
<!-- init js -->
<%@include file="/cp/script/init-js.jsp" %>
<!-- App js -->
<%@include file="/cp/script/app-js.jsp" %>


</body>

</html>
