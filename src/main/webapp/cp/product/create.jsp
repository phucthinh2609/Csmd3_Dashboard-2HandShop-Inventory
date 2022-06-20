<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Create A New Product</title>
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
                            <h4 class="mb-0 font-size-18">Create A New Product</h4>

                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboards</a></li>
                                    <li class="breadcrumb-item active">Dashboard</li>
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

                                <form>
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="productname">Product Name</label>
                                                <input id="productname" name="productname" type="text"
                                                       class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <label for="sku">SKU</label>
                                                <input id="sku" name="sku" type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <label for="productdesc">Product Description</label>
                                                <textarea class="form-control" id="productdesc" rows="5"></textarea>
                                            </div>
                                        </div>

                                        <div class="col-sm-12">

                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-primary mr-1 waves-effect waves-light">Save
                                        Changes
                                    </button>
                                    <button type="submit" class="btn btn-secondary waves-effect">Cancel</button>
                                </form>

                            </div>
                            <%--insert image--%>
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-3">Product Images</h4>

                                    <form action="/" method="post" class="dropzone">
                                        <div class="fallback">
                                            <input name="file" type="file" multiple="">
                                        </div>

                                        <div class="dz-message needsclick">
                                            <div class="mb-3">
                                                <i class="display-4 text-muted bx bxs-cloud-upload"></i>
                                            </div>

                                            <h4>Drop files here or click to upload.</h4>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div> <!-- end card-->
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