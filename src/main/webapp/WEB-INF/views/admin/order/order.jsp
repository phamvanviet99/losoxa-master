<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Đơn hàng</title>
    <%@ include file="/common/admin/css.jsp" %>

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
                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${message == 'update_success'}">
                                <div class="alert alert-success">Cập nhật thành công</div>
                            </c:if>
                            <c:if test="${message == 'update_error'}">
                                <div class="alert alert-danger">Cập nhật thất bại</div>
                            </c:if>
                            <!-- DATA TABLE -->
                            <h3 class="title-5 m-b-35">Quản lý đơn hàng</h3>

                            <form action="<c:url value="/admin/order"/> " id="formSubmit" method="get">
                                <div class="table-responsive table-responsive-data2">
                                    <table class="table table-data2">
                                        <thead>
                                        <tr>
                                            <th>
                                                <%--                                            <label class="au-checkbox">--%>
                                                <%--                                                <input type="checkbox">--%>
                                                <%--                                                <span class="au-checkmark"></span>--%>
                                                <%--                                            </label>--%>
                                            </th>
                                            <th>stt</th>
                                            <th>tên đăng nhập</th>
                                            <th>thời gian đặt</th>
                                            <th>sản phẩm</th>
                                            <th>Thanh toán</th>
                                            <th>tình trạng</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${orders}" var="orders" varStatus="itr">
                                            <tr class="tr-shadow">
                                                <td>
                                                    <label class="au-checkbox">
                                                        <input type="checkbox">
                                                        <span class="au-checkmark"></span>
                                                    </label>
                                                </td>
                                                <td>${itr.index +1}</td>
                                                <td>${orders.username}</td>
                                                <td>${orders.orderDate}</td>
                                                <td>
                                                    <c:forEach items="${orders.itemList}" var="item">
                                                        <p>Product: ${item.product.name} -
                                                            Quantity: ${item.quantity}</p>
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <c:if test="${orders.paymentType == 1}">
                                                        Tiền mặt
                                                    </c:if>
                                                    <c:if test="${orders.paymentType == 2}">
                                                        Online
                                                    </c:if>
                                                </td>
                                                <td>${orders.status}</td>
                                                <td>
                                                    <div class="table-data-feature">
                                                        <c:forEach items="${currentRole}" var="role">
                                                            <c:if test="${role == 'UPDATE_ORDER'}">
                                                                <a href="<c:url value="/admin/order/update/${orders.id}" />">
                                                                    <i class="item" data-toggle="tooltip"
                                                                       data-placement="top" title="Edit">
                                                                        <i class="zmdi zmdi-edit"></i>
                                                                    </i>
                                                                </a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="spacer"></tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <ul class="pagination" id="pagination"></ul>
                                <input type="hidden" value="" id="page" name="page"/>
                            </form>
                            <!-- END DATA TABLE -->
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
<script type="text/javascript">
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        var obj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 2,
            startPage: currentPage,
            last:'Cuối',
            next:'Tiếp',
            first:'Đầu',
            prev:'Trước',
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    // $('#limit').val(2);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>

<%--<script>--%>
<%--    document.querySelectorAll('i[name="delete"]').forEach(function(btn,index) {--%>
<%--        btn.onclick = function () {--%>

<%--        }--%>
<%--    }--%>
<%--</script>--%>

</body>

</html>
<!-- end document-->
