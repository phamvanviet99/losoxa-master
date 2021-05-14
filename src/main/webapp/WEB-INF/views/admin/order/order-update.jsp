<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Cập nhật đơn hàng</title>
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
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <form:form class="form-valide" action="/admin/order/update/${id}" method="POST"
                                       modelAttribute="order">
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" for="val-userName">Người đặt <span
                                            class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-userName" name="userName"
                                               value="${order.username}" disabled>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" for="val-orderDate">Ngày đặt <span
                                            class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-orderDate" name="orderDate"
                                               value="${order.orderDate}" disabled>
                                        <form:errors path="orderDate" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Địa chỉ nhận <span
                                            class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <p style="border:1px solid gray; word-wrap:break-word; padding: 0 10px;">${order.shippingAddress}</p>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" >Lưu ý <span
                                            class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <p style="border:1px solid gray; word-wrap:break-word; padding: 0 10px;">${order.note}</p>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Đơn hàng</label><br>
                                    <table class="table" style="width: 85%; margin-left: 50px;">
                                        <thead class="thead-muted">
                                        <tr>
                                            <th scope="col">STT</th>
                                            <th scope="col">Sản phẩm</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Đơn giá</th>
                                            <th scope="col">Thành tiền</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${order.itemList}" var="item" varStatus="itr">
                                        <tr>
                                            <td>${itr.index +1}</td>
                                            <td>${item.product.name}</td>
                                            <td>${item.quantity}</td>
                                            <td>${item.unitPrice}</td>
                                            <td>${item.unitPrice * item.quantity}</td>
                                        </tr>
                                        </c:forEach>
                                        <thead class="thead-muted">
                                        <tr>
                                            <th scope="col">Khuyến mãi</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <c:if test="${order.point != null}">
                                                <th scope="col">${order.point *1000}</th>
                                            </c:if>
                                            <c:if test="${order.point == null}">
                                                <th scope="col">0</th>
                                            </c:if>
                                        </tr>
                                        <tr>
                                            <th scope="col">Tổng tiền</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <th scope="col">${order.totalPrice}</th>
                                        </tr>
                                        </thead>
                                        </tbody>
                                    </table>
                                </div>

                                <p>Tình trạng</p>
                                <c:forEach items="${listStatus}" var="status">
<%--                                    <c:if test="${order.status == status}">--%>
<%--                                        <p>--%>
<%--                                            <input type="radio" class="check-box" name="status" checked="checked"--%>
<%--                                                   value="${status}"> ${status}--%>
<%--                                        </p>--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${order.status != status}">--%>
<%--                                        <p>--%>
<%--                                            <input type="radio" class="check-box" name="status"--%>
<%--                                                   value="${status}"> ${status}--%>
<%--                                        </p>--%>
<%--                                    </c:if>--%>



                                    <c:if test="${order.status == status}">
                                        <c:if test="${status eq 'Chờ hủy'}">
                                            <p>
                                                <input type="radio" class="check-box" name="status" checked="checked"
                                                       value="${status}" disabled> ${status}
                                            </p>
                                        </c:if>
                                        <c:if test="${status ne 'Chờ hủy'}">
                                            <p>
                                                <input type="radio" class="check-box" name="status" checked="checked"
                                                       value="${status}"> ${status}
                                            </p>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${order.status != status}">
                                        <c:if test="${status eq 'Chờ hủy'}">
                                            <p>
                                                <input type="radio" class="check-box" name="status"
                                                       value="${status}" disabled> ${status}
                                            </p>
                                        </c:if>
                                        <c:if test="${status ne 'Chờ hủy'}">
                                            <p>
                                                <input type="radio" class="check-box" name="status"
                                                       value="${status}"> ${status}
                                            </p>
                                        </c:if>
                                    </c:if>
                                </c:forEach>

                                <div class="form-group row">
                                    <div class="col-lg-8 ml-auto">
                                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>

                    <%@ include file="/common/admin/footer.jsp" %>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT-->

        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<%@ include file="/common/admin/js.jsp" %>

</body>

</html>
<!-- end document-->
