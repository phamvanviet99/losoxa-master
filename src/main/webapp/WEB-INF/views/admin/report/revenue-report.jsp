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
                        <div class="card">
                            <div class="card-header">
                                <div class="caption"><i class="fas fa-layer-group font-green"></i>
                                    <span class="caption-subject font-green sbold uppercase">BÁO CÁO DOANH SỐ BÁN HÀNG</span>
                                </div>
                            </div>
                        </div>

                        <div class="card-header">
                            <div class="container-fluid">

                                <form id="formSubmit">
                                    <div class="d-flex justify-content-end mb-3 ">
                                        <div class="p-3  row">
                                            <label class="col-sm-3 col-form-label label-normal-course">Thời
                                                gian:</label>
                                            <div class="col-sm-9">
                                                <input type="date" id="startDate" name="startDate"
                                                       class="form-control">
                                            </div>
                                        </div>
                                        <div class="p-3  row">
                                            <div class="col-sm-2">
                                                <label class="label-normal-course float-right">Đến:</label>
                                            </div>

                                            <div class="col-sm-10">
                                                <input type="date" id="endDate" name="endDate" class="form-control">
                                            </div>
                                        </div>


                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-2">
                                            <button id="class_see_report_online" style=" border-radius: 27px; "
                                                    class="btn btn-info" type="button">
                                                <i class="fa fa-file-text-o"></i> Tạo báo cáo
                                            </button>
                                        </div>

                                    </div>
                                </form>


                                <!-- /.card-header -->
                                <div class="card-body table-responsive p-0">
                                    <table class="table table-hover  table_competition">
                                        <tr>
                                            <%--<input type='checkbox' class='check-box-element' name="idCoursePlan"   id='checkAllCustomAdd'>--%>
                                            <th>STT</th>
                                            <th>Tên báo cáo</th>
                                            <th>Ngày tạo</th>
                                            <th>Người tạo</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody id="data-list">

                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>


                        <div class="col-sm-12 col-xs-12">
                            <ul id="pagination-test" class="pagination"></ul>
                        </div>


                        <div class="position-absolute loader " style="">
                            <div class="spinner-border "
                                 style="position:fixed;width: 3rem; height: 3rem;top: 50%;left: 50%"
                                 role="status">
                                <span class="sr-only">Loading...</span>
                            </div>
                        </div>

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
<script src="/templates/admin/js/revenueReport.js"></script>

</body>

</html>
<!-- end document-->
