<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Cập nhật người dùng</title>
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
                            <form:form class="form-valide" action="/admin/user/update/${id}" method="POST" modelAttribute="userRequest">
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" for="val-userName">Tên đăng nhập <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-userName" name="userName"
                                               value="${user.userName}" disabled>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" for="val-fullName">Tên đầy đủ <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-fullName" name="fullName"
                                               placeholder="Nhập tên đầy đủ.." value="${user.fullName}">
                                        <form:errors path="fullName" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" for="val-email">Email <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-email" name="email"
                                               placeholder="Nhập email.." value="${user.email}">
                                        <form:errors path="email" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
<%--                                <div class="form-group row">--%>
<%--                                    <label class="col-lg-4 col-form-label" for="val-password">Password <span--%>
<%--                                            class="text-danger">*</span>--%>
<%--                                    </label>--%>
<%--                                    <div class="col-lg-6">--%>
<%--                                        <input type="password" class="form-control" id="val-password"--%>
<%--                                               name="password" placeholder="Enter password..">--%>
<%--                                        <form:errors path="password" cssStyle="color: red"></form:errors>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Số điện thoại <span class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-phone" name="phone"
                                               placeholder="Nhập số điện thoại.." value="${user.phone}">
                                        <form:errors path="phone" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Địa chỉ <span class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-address" name="address"
                                               placeholder="Nhập địa chỉ.." value="${user.address}">
                                        <form:errors path="address" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Điểm <span class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-address" name="point"
                                               placeholder="Nhập điểm.." value="${user.point}">
                                        <form:errors path="point" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Vai trò <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <select name="roleId" style="width: 100%">
                                            <c:forEach items="${roles}" var="role">
                                                <c:if test="${user.role.id == role.id}">
                                                    <option selected value="${role.id}">
                                                            ${role.name} - ${role.description}
                                                    </option>
                                                </c:if>
                                                <c:if test="${user.role.id != role.id}">
                                                    <option value="${role.id}">
                                                            ${role.name} - ${role.description}
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
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
