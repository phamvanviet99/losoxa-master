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
<%--            <a id="saveOrder" name="saveOrder">demo</a>--%>
            <button hidden id="saveOrder" name="saveOrder"> Payment with Paypal </button>
        </div>
    </div>
</div>
<%--    <%@ include file="/common/web/js.jsp" %>--%>
<input type="hidden" value="<%=SecurityUtils.getPrinciple().getUsername()%>" id="username" name="username">
<%@ include file="/common/web/js.jsp" %>
<script>
    $(document).ready(function(){
        $('#saveOrder').trigger('click');
    });

    document.querySelector('button[name="saveOrder"]').onclick = function () {
        var info = {
            shippingAddress: sessionStorage.getItem("shippingAddress"),
            note: sessionStorage.getItem("note"),
            totalPrice: sessionStorage.getItem("totalPrice"),
            point: sessionStorage.getItem("point"),
            paymentType: sessionStorage.getItem("paymentType")
        };
        var listInfo = [];
        var lineItem = shoppingCart.get();
        for (let i = 0; i < lineItem.length; i++) {
            var inf = {};
            inf["unitPrice"] = lineItem[i].productPrice;
            inf["quantity"] = lineItem[i].quantity;
            inf["productId"] = lineItem[i].productId;
            listInfo.push(inf);
        }
        var data = {
            orderRequest: info,
            lineItemRequests: listInfo
        };
        ajaxPost(data);
    }




    function ajaxPost(data) {
        $.ajax({
            url: '/checkout',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: success()
        });
    }
    function success() {
        let namecheck = document.querySelector('input[name="username"]').value;
        shoppingCart.remove({username: namecheck});
        window.location.href = "/checkout-message?error=false";
    }

</script>
</body>
</html>
