<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Users List</title>
    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.jsp"%>
    <!-- Main -->
    <%@include file="/cp/head/main.jsp"%>

</head>

<body data-sidebar="dark">

    <!-- Begin page -->
    <div id="layout-wrapper">

        <%@include file="/cp/layout/page-topbar.jsp"%>
        <div class="vertical-menu">

            <div data-simplebar="" class="h-100">

                <!--- Sidemenu -->
                <%@include file="/cp/layout/sidebar-menu.jsp"%>
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

<%--                start page title--%>
                    <div class="row">
                        <div class="col-12">
                            <div class="page-title-box d-flex align-items-center justify-content-between">
                                <h4 class="mb-0 font-size-18">Users List</h4>

                                <div class="page-title-right">
                                    <ol class="breadcrumb m-0">
                                        <li class="breadcrumb-item"><a href="javascript: void(0);">User</a></li>
                                        <li class="breadcrumb-item active">List</li>
                                    </ol>
                                </div>

                            </div>
                        </div>
                    </div>
<%--            end page title -->--%>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row mb-2">
                                        <div class="col-sm-4">
                                            <div class="search-box mr-2 mb-2 d-inline-block">
                                                <div class="position-relative">
                                                    <input type="text" class="form-control" placeholder="Search...">
                                                    <i class="bx bx-search-alt search-icon"></i>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-8">
                                            <div class="text-sm-right">
                                                <button type="button" class="btn btn-success btn-rounded waves-effect waves-light mb-2 mr-2">
                                                    <a href="users?action=create" class="text-white"><i class="mdi mdi-plus mr-1"></i> Add New User</a></button>
                                            </div>
                                        </div><!-- end col-->
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-centered table-nowrap table-hover">
                                            <thead class="thead-light">
                                            <tr>
                                                <th scope="col" style="width: 70px;">#</th>
                                                <th scope="col">Full Name</th>
                                                <th scope="col">Mobile</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Role</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${users}" var="user">
<%--                                                <tr>--%>
<%--                                                    <th scope="col" style="width: 70px;">#</th>--%>
<%--                                                    <th scope="col">${user.getFullName()}</th>--%>
<%--                                                    <th scope="col">${user.getMobile()}<</th>--%>
<%--                                                    <th scope="col">${user.getEmail()}<</th>--%>
<%--                                                    <th scope="col">${user.getRole()}<</th>--%>
<%--                                                    <th scope="col">${user.getStatus()}<</th>--%>
<%--                                                    <th scope="col">Action</th>--%>
<%--                                                </tr>--%>

                                                <tr>
                                                    <td>
                                                        <div class="avatar-xs">
                                                                <span class="avatar-title rounded-circle">
                                                                        ${fn:substring(user.getFullName(), 0, 1)}
                                                                </span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <h5 class="font-size-14 mb-1"><a href="#" class="text-dark">${user.getFullName()}</a></h5>
                                                    </td>
                                                    <td>${user.getMobile()}</td>
                                                    <td>${user.getEmail()}</td>
                                                    <td>${user.getRole()}</td>
                                                    <td>
                                                        <div>
                                                            <c:if test='${user.getStatus() == "ACTIVE"}'>
                                                                <span class="badge badge-soft-success font-size-11 m-1">ACTIVE</span>
                                                            </c:if>
                                                            <c:if test='${user.getStatus() == "BLOCK"}'>
                                                                <span class="badge badge-soft-danger font-size-11 m-1">BLOCK</span>
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <ul class="list-inline font-size-20 contact-links mb-0">
                                                            <li class="list-inline-item px-2">
                                                                <a href="/users?action=edit&id=${user.getId()}" data-toggle="tooltip" data-placement="top" title="Edit"><i class="bx bx-user-circle"></i></a>
                                                            </li>
                                                        </ul>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <ul class="pagination pagination-rounded justify-content-center mt-4">
                                                <li class="page-item disabled">
                                                    <a href="#" class="page-link"><i class="mdi mdi-chevron-left"></i></a>
                                                </li>
                                                <li class="page-item">
                                                    <a href="#" class="page-link">1</a>
                                                </li>
                                                <li class="page-item active">
                                                    <a href="#" class="page-link">2</a>
                                                </li>
                                                <li class="page-item">
                                                    <a href="#" class="page-link">3</a>
                                                </li>
                                                <li class="page-item">
                                                    <a href="#" class="page-link">4</a>
                                                </li>
                                                <li class="page-item">
                                                    <a href="#" class="page-link">5</a>
                                                </li>
                                                <li class="page-item">
                                                    <a href="#" class="page-link"><i class="mdi mdi-chevron-right"></i></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end row -->
                </div>
                <!-- container-fluid -->
            </div>
            <!-- End Page-content -->

            <%@include file="/cp/layout/footer.jsp"%>
        </div>
        <!-- end main content-->

    </div>
    <!-- END layout-wrapper -->


    <!-- JAVASCRIPT -->
    <%@include file="/cp/script/javascript.jsp"%>

    <!-- App js -->
    <%@include file="/cp/script/app-js.jsp"%>
</body>

</html>