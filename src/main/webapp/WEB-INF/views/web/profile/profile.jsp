<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <%@ include file="/common/web/css.jsp" %>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

</head>
<body>
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>
<div class="main-content-wrapper d-flex clearfix">
    <%@ include file="/common/web/menu.jsp" %>

    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-9">
                    <div class="cart-title mt-50">
                        <h2>Thông tin tài khoản</h2>
                    </div>
                    <form:form class="form-validation" action="/profile" method="POST"
                               modelAttribute="profile">
                        <p>Tên đăng nhập
                        <input type="text" class="input-lg form-control" value="${user.userName}" disabled></p>
                        <p>Email
                        <input type="email" class="input-lg form-control" disabled value="${user.email}"></p>
                        <p>Họ tên
                        <input type="text" class="input-lg form-control" name="fullName"
                               placeholder="Nhập vào tên đầy đủ" value="${user.fullName}">
                        <form:errors path="fullName" cssStyle="color: red"></form:errors></p>
                        <p>Số điện thoại
                        <input type="text" class="input-lg form-control" name="phone"
                               placeholder="Nhập vào số điện thoại" value="${user.phone}">
                        <form:errors path="phone" cssStyle="color: red"></form:errors></p>
                        <p>Địa chỉ
                        <input type="text" class="input-lg form-control" name="address"
                               placeholder="Nhập vào địa chỉ" value="${user.address}">
                        <form:errors path="address" cssStyle="color: red"></form:errors></p>

<%--                        <p><input type="checkbox" id="changePass" name="changePass">--%>
<%--                            Thay đổi mật khẩu</p>--%>
                        <a type="submit" id="changePass" name="changePass" class="btn amado-btn w-25 h-25" onclick="changePass()">Thay đổi mật khẩu</a>


                        <p><span hidden id="pw1">Mật khẩu cũ</span>
                        <input hidden type="password" class="input-lg form-control" name="currentPassword" id="password1"
                               placeholder="Nhập vào mật khẩu cũ" autocomplete="off">
                        <form:errors path="currentPassword" cssStyle="color: red; display: none" id="er1"></form:errors></p>
                        <p><span hidden id="pw2">Mật khẩu mới</span>
                        <input hidden type="password" class="input-lg form-control" name="newPassword" id="password2"
                               placeholder="Nhập vào mật khẩu mới" autocomplete="off">
                        <form:errors path="newPassword" cssStyle="color: red; display: none" id="er2"></form:errors></p>
                        <p><span hidden id="pw3">Nhập lại</span>
                        <input hidden type="password" class="input-lg form-control" name="confirmPassword" id="password3"
                               placeholder="Nhập lại mật khẩu mới" autocomplete="off">
                        <form:errors path="confirmPassword" cssStyle="color: red; display: none" id="er3"></form:errors></p>
                        <div class="row">
                            <div class="col-sm-12">
                                <span id="pwmatch" class="glyphicon" style="color:#FF0004;"></span><span class="notMatch"></span>
                            </div>
                        </div>
                        <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg"
                               data-loading-text="Edit profile..." value="Cập nhật">
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>

<script>
    $("input[type=password]").keyup(function () {
        if ($("#password2").val() == $("#password3").val()) {
            $("#pwmatch").removeClass("glyphicon-remove");
            $("#pwmatch").css("color", "#00A41E");
            document.querySelector('span[class="notMatch"]').innerHTML = "";
        } else {
            $("#pwmatch").addClass("glyphicon-remove");
            $("#pwmatch").css("color", "#FF0004");
            document.querySelector('span[class="notMatch"]').innerHTML = "Mật khẩu không trùng khớp";
        }
    });


    var check = true;
    function changePass() {
        if (check == true){
            $("#password1").removeAttr('hidden');
            $("#password2").removeAttr('hidden');
            $("#password3").removeAttr('hidden');
            $("#pw1").removeAttr('hidden');
            $("#pw2").removeAttr('hidden');
            $("#pw3").removeAttr('hidden');
            if(document.getElementById("er1") != null)
                document.getElementById("er1").style.display = "block";
            if(document.getElementById("er2") != null)
                document.getElementById("er2").style.display = "block";
            if(document.getElementById("er3") != null)
                document.getElementById("er3").style.display = "block";


            check = false;
        }
        else {
            $("#password1").attr("hidden",true);
            $("#password2").attr("hidden",true);
            $("#password3").attr("hidden",true);
            $("#pw1").attr("hidden",true);
            $("#pw2").attr("hidden",true);
            $("#pw3").attr("hidden",true);
            if(document.getElementById("er1") != null)
                document.getElementById("er1").style.display = "none";
            if(document.getElementById("er2") != null)
                document.getElementById("er2").style.display = "none";
            if(document.getElementById("er3") != null)
                document.getElementById("er3").style.display = "none";

            check = true;
        }
    }


</script>



</body>
</html>
