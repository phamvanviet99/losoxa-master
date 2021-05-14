<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <%@ include file="/common/web/css.jsp" %>

</head>
<body>
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>
<div class="main-content-wrapper d-flex clearfix">
    <%@ include file="/common/web/menu.jsp" %>

    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="cart-title mt-50">
                        <h2>Lịch sử đặt hàng của bạn</h2>
                    </div>
                    <form action="<c:url value="/order-history"/> " id="formSubmit" method="get">
                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                <tr>
                                    <th>Thông tin chung</th>
                                    <th>Địa chỉ giao</th>
                                    <th>Tình trạng</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orders}" var="order">
                                    <tr>
                                        <td>
                                                ${order.orderDate}<br>
                                            Tổng số tiền: ${order.totalPrice} VNĐ<br>
                                            <c:if test="${order.paymentType.toString() == '1'}">
                                                Thanh toán khi nhận hàng
                                            </c:if>
                                            <c:if test="${order.paymentType.toString() == '2'}">
                                                Đã thanh toán online
                                            </c:if>
                                        </td>
                                        <td>${order.shippingAddress}</td>

                                        <td>${order.status}</td>
                                        <td>
                                            <div style="background-color: #fbb710; height: 30px; width: 45%; margin: 10px 0; text-align: center;">
                                                <a style="color: white; font-weight: bold;"
                                                   href="<c:url value="/order/${order.id}"/>">Xem chi tiết</a>
                                            </div>
                                            <c:if test="${order.status eq 'Chờ xác nhận' || order.status eq 'Chờ lấy hàng'}">
                                                <div style="background-color: black; height: 30px; width: 45%; margin: 10px 0; text-align: center;">
                                                    <a style="font-weight: bold; color: white;"
                                                       onclick="return confirm('Bạn có chắc muốn hủy đơn hàng này không?');"
                                                       href="<c:url value="/cancel/${order.id}"/>">Hủy đơn</a>
                                                </div>
                                            </c:if>
<%--                                            <c:if test="${order.status eq 'Chờ hủy'}">--%>
<%--                                                <div style="background-color: #008050; height: 30px; width: 45%; margin: 10px 0; text-align: center;">--%>
<%--                                                    <a style="font-weight: bold; color: white;"--%>
<%--                                                       onclick="return confirm('Bạn có chắc muốn hủy đơn hàng này không?');"--%>
<%--                                                       href="<c:url value="/product/${order.}"/>">Đặt lại</a>--%>
<%--                                                </div>--%>
<%--                                            </c:if>--%>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <ul class="pagination" id="pagination"></ul>
                        <input type="hidden" value="" id="page" name="page"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>


<script type="text/javascript">
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        var obj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 2,
            startPage: currentPage,
            last: 'Cuối',
            next: 'Tiếp',
            first: 'Đầu',
            prev: 'Trước',
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


</body>
</html>
