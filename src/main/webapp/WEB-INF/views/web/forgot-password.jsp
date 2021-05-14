<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Quên mật khẩu</title>
    <%@ include file="/common/web/css.jsp" %>
</head>
<body>

<div class="container" style="padding-left: 25%; padding-top: 50px">
    <div class="row">
        <div class="col-md-7 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="text-center">
                        <h3><i class="fa fa-lock fa-4x"></i></h3>
                        <h2 class="text-center">Quên mật khẩu?</h2>
                        <p>Vui lòng cung cấp email đăng nhập để lấy lại mật khẩu.</p>
                        <div class="panel-body">

                            <form:form class="form-valide" action="/forgotPassword" modelAttribute="mailRequest" method="POST">

                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                        <input id="email" name="email" placeholder="Nhập email" class="form-control"  type="email">
                                        <form:errors path="email" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                                </div>

                                <input type="hidden" class="hide" name="token" id="token" value="" >
                            </form:form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
