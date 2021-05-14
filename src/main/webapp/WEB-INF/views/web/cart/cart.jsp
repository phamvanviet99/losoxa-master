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
                <div class="col-12 col-lg-8">
                    <div class="cart-title mt-50">
                        <h2>Giỏ hàng</h2>
                    </div>

                    <div class="cart-table clearfix">
                        <table class="table table-responsive">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Tên</th>
                                <th>Đơn giá</th>
                                <th>Số lượng</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-12 col-lg-4">
                    <div class="cart-summary">
                        <h5>Tính tiền</h5>
                        <ul class="summary-table">
                            <li><span>Tổng:</span> <span>0</span></li>
                        </ul>

                        <div class="cart-btn mt-100">
                            <a onclick="checkLineItem()" class="btn amado-btn w-100">Thanh toán</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="<%=SecurityUtils.getPrinciple().getUsername()%>" id="username" name="username">
<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>


<script>
    var items = shoppingCart.get();
    for(var i=0;i<items.length;i++){
        if ((document.querySelector('input[name="username"]').value)==items[i].username) {
            var html = '<tr>';
            html += '<input type="hidden" name="productId" value="' + items[i].productId + '">';
            html += '<td class="cart_product_img">';
            html += '<a href="#"><img src="' + items[i].fullPathImg + '" alt="Product"></a>';
            html += '</td>';
            html += '<td class="cart_product_desc">';
            html += '<div class="sed">';
            html += '<h5>' + items[i].productName + '</h5>';
            html += '</div>';
            html += '</td>';
            html += '<td class="price">';
            html += '<span>' + items[i].productPrice + '</span>';
            html += '</td>';
            html += '<td class="qty">';
            html += '<div class="qty-btn d-flex">';
            html += '<div class="quantity">';
            html += '<span class="qty-minus"><i class="fa fa-minus" aria-hidden="true"></i></span>';
            html += '<input type="number" class="qty-text" id="qty2" step="1" min="1" max="300" name="quantity" value="' + items[i].quantity + '">';
            html += '<span class="qty-plus" ><i class="fa fa-plus" aria-hidden="true"></i></span>';
            html += '</div>';
            html += '<div class="item_add hvr-skew-backward" >';
            html += '<img src="<c:url value="/templates/img/core-img/trash-cart.PNG"/>" onclick="removeCheckOutWithBtn()">';
            html += '</div>';
            html += '</div>';
            html += '</td>';
            html += '</tr>';
            document.querySelector('table[class="table table-responsive"] tbody').innerHTML += html;
        }
    }



    function caculate(){
        var items = shoppingCart.get();
        var value = 0;
        for(var i=0;i<items.length;i++){
            if((document.querySelector('input[name="username"]').value)==items[i].username){
                value += items[i].quantity * items[i].productPrice;
            }
        }
        return value;
    }

        document.querySelectorAll('span[class="qty-minus"]').forEach(function(btn,index){
        btn.onclick = function () {
            // minus
            var username = document.querySelector('input[name="username"]').value;
            var effect = document.querySelectorAll('input[name="quantity"]')[index];
            var qty = effect.value;
            if (!isNaN(qty) && qty > 1) effect.value--;

            // update
            var productId = parseInt(document.querySelectorAll('table[class="table table-responsive"] input[name="productId"]')[index].value);
            shoppingCart.update({productId :productId, username: username},{ quantity: effect.value });

            // caculate
            document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML = caculate();
        }
    })

    document.querySelectorAll('span[class="qty-plus"]').forEach(function(btn,index){
        btn.onclick = function () {
            // plus
            var username = document.querySelector('input[name="username"]').value;
            var effect = document.querySelectorAll('input[name="quantity"]')[index];
            var qty = effect.value;
            if( !isNaN( qty )) effect.value++;

            // update
            var productId = parseInt(document.querySelectorAll('table[class="table table-responsive"] input[name="productId"]')[index].value);
            shoppingCart.update({productId :productId, username: username},{ quantity: effect.value });

            document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML = caculate();
        }
    })
    document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML = caculate();


    function removeCheckOutWithBtn() {
        document.querySelectorAll('div[class="item_add hvr-skew-backward"]').forEach(function (btn, index) {
            btn.onclick = function () {
                let namecheck = document.querySelectorAll('td[class="cart_product_desc"]')[index].querySelector('div[class="sed"] h5').innerText;
                shoppingCart.remove({productName: namecheck});
                location.reload();
            }
        })

    }


    function checkLineItem() {
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
            lineItemRequests: listInfo
        };
        if (data.lineItemRequests.length > 0)
            window.location.href = "/checkout";
        else
            window.location.href = "/checkout-message?error=true";
    }


</script>


</body>
</html>
