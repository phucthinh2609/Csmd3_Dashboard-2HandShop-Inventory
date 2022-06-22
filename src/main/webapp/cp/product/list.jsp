<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Products List</title>
    <!-- Meta -->
    <%@include file="/cp/head/meta.jsp"%>

    <!-- App favicon -->
    <%@include file="/cp/head/app-favicon.jsp"%>

    <!-- Bootstrap Css --> <!-- Icons Css --> <!-- App Css-->
    <%@include file="/cp/head/main.jsp"%>
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

                <%--                start page title--%>
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box d-flex align-items-center justify-content-between">
                            <h4 class="mb-0 font-size-18">List of Products</h4>

                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Product</a></li>
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
                                                <form>
                                                    <div class="row">

                                                        <div class="col-sm-7">
                                                            <input type="text" name="search" value=""
                                                                   class="form-control mb-3" placeholder="Search...">
                                                            <i class="bx bx-search-alt search-icon"></i>
                                                        </div>
                                                        <div class="col-sm-2">
                                                            <button type="submit"
                                                                    class="btn btn-outline-primary mr-1 waves-effect waves-light">
                                                                Search
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-8">
                                        <div class="text-sm-right">
                                            <button type="button"
                                                    class="btn btn-success btn-rounded waves-effect waves-light mb-2 mr-2">
                                                <a href="products?action=create" class="text-white"><i
                                                        class="mdi mdi-plus mr-1"></i> Add New Product</a></button>
                                        </div>
                                    </div><!-- end col-->
                                </div>
                                <div class="table-responsive">
                                    <table id="datatable" class="table table-centered table-nowrap table-hover"
                                           role="grid" aria-describedby="datatable_info">
                                        <%--                                            --%>
                                        <thead class="thead-light">
                                        <tr>
                                            <th style="width: 20px;">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                                    <label class="custom-control-label" for="customCheck1">&nbsp;</label>
                                                </div>
                                            </th>
                                            <th scope="col">Title</th>
                                            <th scope="col">Image</th>
                                            <th scope="col">Created At</th>
                                            <th scope="col">Created By</th>
                                            <th scope="col">Updated At</th>
                                            <th scope="col">Updated By</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${products}" var="product">

                                            <tr>
                                                <td>
                                                    <div class="custom-control custom-checkbox">
                                                        <input type="checkbox" class="custom-control-input" id="customCheck2">
                                                        <label class="custom-control-label" for="customCheck2">&nbsp;</label>
                                                    </div>
                                                </td>
                                                <td>
                                                    <h5 class="font-size-14 mb-1"><a href="/products?action=edit&id=${product.getId()}" class="text-dark">${product.getTitle()}</a>
                                                    </h5>
                                                </td>
                                                <td><img src="/images/${product.getImage()}" width="50px" height="50px"
                                                          alt=""></td>
                                                <td>${product.getCreatedAt()}</td>
                                                <td>${product.getCreatedBy()}</td>
                                                <td>${product.getUpdatedAt()}</td>
                                                <td>${product.getUpdatedBy()}</td>
                                                <td>
                                                    <ul class="list-inline font-size-20 contact-links mb-0">
                                                        <li class="list-inline-item px-2">
                                                            <a href="/products?action=edit&id=${product.getId()}"
                                                               data-toggle="tooltip" data-placement="top"
                                                               title="Edit"><i class="bx bx-task"></i></a>
                                                        </li>
                                                    </ul>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
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

        <%@include file="/cp/layout/footer.jsp" %>
    </div>
    <!-- end main content-->

</div>
<!-- END layout-wrapper -->


<!-- JAVASCRIPT -->
<%@include file="/cp/script/javascript.jsp" %>

<!-- Required datatable js -->
<%@include file="/cp/script/required-datatable-js.jsp" %>

<!-- Buttons examples -->
<%@include file="/cp/script/buttons-examples.jsp" %>

<!-- Responsive examples -->
<%@include file="/cp/script/responsive-examples.jsp" %>

<!-- Datatable init js -->
<%@include file="/cp/script/datatable-init-js.jsp" %>

<!-- App js -->
<%@include file="/cp/script/app-js.jsp" %>

<script>
    $( document ).ready(function() {
        document.querySelector(".dataTables_filter").style.display = 'none';
    });
</script>
</body>

</html>