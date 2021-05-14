<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <%@ include file="/common/admin/css.jsp" %>
    <style>
        button:focus {
            border: none;
            outline: none;
        }

        .select2 {
            width: 100% !important;
        }
    </style>
</head>

<body class="animsition">
<div class="page-wrapper">

    <!-- MENU SIDEBAR-->
    <%@ include file="/common/admin/menu.jsp" %>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <!-- HEADER DESKTOP-->
        <%@ include file="/common/admin/header.jsp" %>
        <!-- HEADER DESKTOP-->

        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">

                    <div class="content-wrapper">
                        <!-- Content Header (Page header) -->
                        <div class="content-header">

                        </div>
                        <!-- /.content-header -->

                        <!-- Main content -->
                        <div class="container-fluid">

                            <section class="content">
                                <div class="container-fluid">
                                    <div class="card card-primary card-outline">
                                        <div class="card-header">
                                            <h3 style=" color: #2ab4c0!important;" class="card-title  "><i class="fa fa-user" aria-hidden="true"></i> THỐNG KÊ NGƯỜI DÙNG </h3>
                                        </div> <!-- /.card-body -->
                                        <div class="card-body">
                                            <div class="row flex-wrap">
                                                <a  href="/admin/report/loyal-customer" class="col-md-3 padding_form_report_px">
                                                    <span >1.Thống kê danh sách khách hàng thân thiết</span>
                                                </a>
                                            </div>
                                        </div><!-- /.card-body -->
                                    </div>
                                    <div class="card card-primary card-outline">
                                        <div class="card-header">
                                            <h3 style=" color: #2ab4c0!important;" class="card-title  "><i class="fa fa-file-text" aria-hidden="true"></i> THỐNG KÊ DOANH THU </h3>
                                        </div> <!-- /.card-body -->
                                        <div class="card-body">
                                            <div class="row flex-wrap">
                                                <a  href="/admin/report/revenue" class="col-md-3 padding_form_report_px">
                                                    <span >1.Thống kê doanh số bán hàng</span>
                                                </a>
                                            </div>
                                        </div><!-- /.card-body -->
                                    </div>
                                </div><!-- /.container-fluid -->
                            </section>
                        </div>

                        <!-- /.content -->
                    </div>



                    <%@ include file="/common/admin/footer.jsp" %>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<%@ include file="/common/admin/js.jsp" %>


</body>

</html>
<!-- end document-->
