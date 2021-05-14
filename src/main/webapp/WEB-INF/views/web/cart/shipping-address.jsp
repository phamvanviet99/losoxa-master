<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Địa chỉ nhận</title>
    <%@ include file="/common/web/css.jsp" %>

</head>
<body>
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>
<div class="main-content-wrapper d-flex clearfix">
    <%@ include file="/common/web/menu.jsp" %>

    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <%--            <form:form class="form-valide" action="/checkout" method="POST" modelAttribute="orderRequest" >--%>
            <form id="shippingAddressForm">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">
                            <div class="cart-title">
                                <h2>Địa chỉ nhận hàng</h2>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <label class="control-label col-md-3">Địa chỉ

                                    </label>
                                    <div class="col-md-8">
                                        <input class="w-100 form-control" name="place" value="${user.place}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Tỉnh/TP

                                    </label>
                                    <div class="col-md-8">
                                        <select class="form-control w-100" name="provine" id="provine">
                                            <option>N/A</option>
                                            <c:forEach items="${provines}" var="provine">
                                                <option value="${provine.id}">
                                                        ${provine.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Quận/Huyện

                                    </label>
                                    <div class="col-md-8">
                                        <select class="form-control w-100" name="district" id="district">
                                            <option>N/A</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">Xã/Phường

                                    </label>
                                    <div class="col-md-8">
                                        <select class="form-control w-100" name="idCommuneVnpost" id="ward">
                                            <option value="null">N/A</option>

                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </form>

        </div>
    </div>
</div>
<input type="hidden" value="<%=SecurityUtils.getPrinciple().getUsername()%>" id="username" name="username">
<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>