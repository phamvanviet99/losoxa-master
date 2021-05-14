<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="icon" href="<c:url value="/templates/img/core-img/v-logo.png"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/templates/login/vendor/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/templates/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/templates/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/templates/login/vendor/animate/animate.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/templates/login/vendor/css-hamburgers/hamburgers.min.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/templates/login/vendor/animsition/css/animsition.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/templates/login/vendor/select2/select2.min.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/templates/login/vendor/daterangepicker/daterangepicker.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/templates/login/css/util.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/templates/login/css/main.css"/>">
</head>
<body style="background-color: #666666;">
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form validate-form">

                <form:form class="form-valide" action="/register" modelAttribute="userRegister" method="POST">
                    <span class="login100-form-title p-b-43">
						Đăng ký
					</span>
                    <div class="col-md-12 login-do">
                        <div class="form-group row">
                            <label class="col-lg-4 col-form-label">Tên đăng nhập <span
                                    class="text-danger">*</span>
                            </label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" name="userName"
                                       placeholder="Nhập tên đăng nhập.." value="">
                                <form:errors path="userName" cssStyle="color: red"></form:errors>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-4 col-form-label">Tên đầy đủ <span
                                    class="text-danger">*</span>
                            </label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" name="fullName"
                                       placeholder="Nhập tên đầy đủ.." value="">
                                <form:errors path="fullName" cssStyle="color: red"></form:errors>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-4 col-form-label">Email <span
                                    class="text-danger">*</span>
                            </label>
                            <div class="col-lg-6">
                                <input type="email" class="form-control" name="email"
                                       placeholder="Nhập email.." value="">
                                <form:errors path="email" cssStyle="color: red"></form:errors>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-lg-4 col-form-label">Mật khẩu <span
                                    class="text-danger">*</span>
                            </label>
                            <div class="col-lg-6">
                                <input type="password" class="form-control" name="password"
                                       placeholder="Nhập password.." value="">
                                <form:errors path="password" cssStyle="color: red"></form:errors>
                            </div>
                        </div>
<%--                        <div class="login-mail">--%>
<%--                            <input type="text" class="form-control" name="userName" required="true" placeholder="Tên đăng nhập">--%>
<%--                            <i class="glyphicon "></i>--%>
<%--                            <form:errors path="userName" cssStyle="color: red"></form:errors>--%>
<%--                        </div>--%>
<%--                        <div class="login-mail">--%>
<%--                            <input type="text" class="form-control" name="fullName" required="true" placeholder="Tên đầy đủ">--%>
<%--                            <i class="glyphicon "></i>--%>
<%--                            <form:errors path="fullName" cssStyle="color: red"></form:errors>--%>
<%--                        </div>--%>
<%--                        <div class="login-mail">--%>
<%--                            <input type="text" class="form-control" name="email" required="true" placeholder="Email">--%>
<%--                            <i class="glyphicon "></i>--%>
<%--                            <form:errors path="email" cssStyle="color: red"></form:errors>--%>
<%--                        </div>--%>
<%--                        <div class="login-mail">--%>
<%--                            <input type="password" class="form-control" name="password" required="true" placeholder="Mật khẩu">--%>
<%--                            <i class="glyphicon "></i>--%>
<%--                            <form:errors path="password" cssStyle="color: red"></form:errors>--%>
<%--                        </div>--%>
                        <label class="hvr-skew-backward">
                            <input type="submit" class="btn btn-primary btn-block" value="Đăng ký">
                        </label>
                    </div>
                    <div class="col-md-6 login-right">
                            <%--                <h3>Completely Free Account</h3>--%>

                        <p></p>
                        <a href="<c:url value="/login"/>" class="hvr-skew-backward">Đăng nhập</a>

                    </div>

                    <div class="clearfix"></div>
                </form:form>
            </div>

            <div class="login100-more" style="background-image: url('/templates/login/images/bg-01.jpg');">
            </div>
        </div>
    </div>
</div>

<!--===============================================================================================-->

<script src="<c:url value="/templates/login/vendor/jquery/jquery-3.2.1.min.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/animsition/js/animsition.min.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/bootstrap/js/popper.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/select2/select2.min.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/daterangepicker/moment.min.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/daterangepicker/daterangepicker.js"/>"></script>
<script src="<c:url value="/templates/login/vendor/countdowntime/countdowntime.js"/>"></script>
<script src="<c:url value="/templates/login/js/main.js"/>"></script>

</body>
</html>
