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

            <form action="j_spring_security_check" id="formLogin" method="post">
                <c:if test="${param.incorrectAccount != null}">
                    <div class="alert alert-danger">
                        Tài khoản hoặc mật khẩu không hợp lệ
                    </div>
                </c:if>
                <c:if test="${param.accessDenied != null}">
                    <div class="alert alert-danger">
                        Bạn không có quyền
                    </div>
                </c:if>
                <span class="login100-form-title p-b-43">
						Đăng nhập để tiếp tục
					</span>


                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Tên đăng nhập">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu">
                </div>

                <div class="flex-sb-m w-full p-t-3 p-b-32">
                    <div class="contact100-form-checkbox">
                        <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                        <label class="label-checkbox100" for="ckb1">
                            Ghi nhớ tài khoản
                        </label>
                    </div>

                    <div>
                        <a href="<c:url value="/forgotPassword"/>" class="txt1">
                            Quên mật khẩu?
                        </a>
                    </div>

                </div>
                <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>

                <div class="text-center p-t-46 p-b-20">
                    <a href="<c:url value="/register"/>" class="txt1">
                        Đăng ký
                    </a>
                </div>
            </form>
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
