<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <title>Create A New User</title>
    <!-- Meta -->
    <%@include file="/cp/head/meta.jsp" %>
    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.jsp" %>
    <!-- select2 css -->
    <%@include file="/cp/head/select2-css.jsp" %>
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
                            <h4 class="mb-0 font-size-18">Create a new user</h4>

                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">User</a></li>
                                    <li class="breadcrumb-item active">Create</li>
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

                                <h4 class="card-title">Basic Information</h4>
                                <p class="card-title-desc">Fill all information below</p>

                                <form method="post">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="fullName">Full Name</label>
                                                <input id="fullName" name="fullName" type="text" class="form-control"
                                                       value="${user.getFullName()}">
                                            </div>
                                            <div class="form-group">
                                                <label for="mobile">Mobile</label>
                                                <input id="mobile" name="mobile" type="text" class="form-control"
                                                       value="${user.getMobile()}">
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Email</label>
                                                <input id="email" name="email" type="email" class="form-control"
                                                       value="${user.getEmail()}">
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
                                                    <input id="address" name="address" type="text" class="form-control"
                                                           value="${user.getAddress()}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Role</label>
                                                <select name="role" class="custom-select">
                                                    <option name="role" value="ADMIN">ADMIN</option>
                                                    <option name="role" value="USER" selected="USER">USER</option>
                                                </select>
                                            </div>

                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary mr-1 waves-effect waves-light">Create
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->
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
            </div>
            <!-- container-fluid -->
        </div>


        <!-- End Page-content -->
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