<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Kết quả đặt hàng</title>
    <%@ include file="/common/web/css.jsp" %>
</head>
<body>
    <div class="container" align="center">
        <div class="four">
            <br>
            <h3 style="font-size: 30px">${message}</h3>
            <br>
            <div class="amado-btn-group mt-30 mb-100">
                <a href="<c:url value="/"/>" class="btn amado-btn mb-15">Trang chủ</a>
                <i class="fal fa-tree-alt"></i>
            </div>
        </div>
    </div>
<%--    <%@ include file="/common/web/js.jsp" %>--%>
</body>
</html>
