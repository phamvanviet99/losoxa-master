<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                        <h2>Chi tiết đơn hàng</h2>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="order_date">Thời gian đặt hàng</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="order_date" value="${order.orderDate}" name="order_date" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">Địa chỉ nhận hàng</label>
                        <div class="col-sm-10">
                            <p style="border:1px solid gray; word-wrap:break-word; padding: 0 10px;">${order.shippingAddress}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Ghi chú</label>
                        <div class="col-sm-10">
                            <p style="border:1px solid gray; word-wrap:break-word; padding: 0 10px;">${order.note}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="status">Tình trạng</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="status" value="${order.status}" name="status" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-12" for="items_list">Danh sách sản phẩm</label>
                        <div class="col-md-10">
                            <br>
                            <table class="table table-hover table-bordered items_list" id="items_list">
                                <thead>
                                <tr class="info">
                                    <th>Sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th>Đơn giá</th>
                                    <th>Thành tiền</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${order.itemList}" var="item">
                                    <tr>
                                        <td>${item.product.name}</td>
                                        <td>${item.quantity}</td>
                                        <td>${item.unitPrice}</td>
                                        <td>${item.unitPrice * item.quantity}</td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td><b>Khuyến mãi</b></td>
                                    <td></td>
                                    <td></td>
                                    <c:if test="${order.point != null}">
                                        <td>${order.point *1000}</td>
                                    </c:if>
                                    <c:if test="${order.point == null}">
                                        <td>0</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td><b>Tổng</b></td>
                                    <td></td>
                                    <td></td>
                                    <td>${order.totalPrice}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">Phương thức thanh toán</label>

                            <c:if test="${order.paymentType.toString() == '1'}">
                                <b>Thanh toán khi nhận hàng</b>
                            </c:if>
                            <c:if test="${order.paymentType.toString() == '2'}">
                                <b>Đã thanh toán online</b>
                            </c:if>
<%--                            <input type="text" class="form-control" id="paymentType" value="${order.paymentType}" name="status" disabled>--%>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>


</body>
</html>
