<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <%@ include file="/common/web/css.jsp" %>

</head>
<body onload="init()">
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>
<div class="main-content-wrapper d-flex clearfix">
    <%@ include file="/common/web/menu.jsp" %>

    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <%--            <form:form class="form-valide" action="/checkout" method="POST" modelAttribute="orderRequest" >--%>
            <form id="checkoutForm">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">
                            <div class="cart-title">
                                <h2>Thanh toán</h2>
                            </div>
                            <%--                            <form action="#" method="post">--%>
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <input type="text" class="form-control" id="fullName"
                                           value="<%=SecurityUtils.getPrinciple().getFullName()%>"
                                           placeholder="Họ tên người nhận *"
                                           required name="fullName">
                                    <%--                                    <form:errors path="fullName" cssStyle="color: red"></form:errors>--%>
                                    <div class="col-sm-12">
                                        <span id="validFullName" hidden
                                              style="color:#FF0004;">Họ tên quá dài</span><br>
                                    </div>
                                </div>

                                <div class="col-md-4 mb-3">
                                    <label class="control-label col-md-3">Tỉnh/TP

                                    </label>
                                    <div class="col-md-12" style="padding: 0;">
                                        <select class="form-control" name="province" id="province">
                                            <option>N/A</option>
                                            <%--                                            <c:forEach items="${provines}" var="provine">--%>
                                            <%--                                                <option value="${provine.id}">--%>
                                            <%--                                                        ${provine.name}--%>
                                            <%--                                                </option>--%>
                                            <%--                                            </c:forEach>--%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label class="control-label col-md-3">Quận/Huyện

                                    </label>
                                    <div class="col-md-12" style="padding: 0;">
                                        <select class="form-control w-100" name="district" id="district">
                                            <option>N/A</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3" style="padding: 0;">
                                    <label class="control-label col-md-3">Xã/Phường

                                    </label>
                                    <div class="col-md-12">
                                        <select class="form-control w-100" name="commune" id="commune">
                                            <option value="">N/A</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col-12 mb-3">
                                    <input type="text" class="form-control" id="shippingAddress"
                                           placeholder="Tòa nhà, tên đường... *"
                                           value=""
                                           name="shippingAddress">
                                    <%--                                    <form:errors path="shippingAddress" cssStyle="color: red"></form:errors>--%>
                                    <div class="col-sm-12">
                                        <span id="validAddress" hidden
                                              style="color:#FF0004;">Địa chỉ quá dài</span><br>
                                    </div>
                                </div>

                                <div class="col-12 mb-3">
                                    <input type="text" class="form-control" id="phone" placeholder="Số điện thoại *"
                                           value="" name="phone">
                                    <%--                                    <form:errors path="phone" cssStyle="color: red"></form:errors>--%>
                                    <div class="col-sm-12">
                                        <span id="validPhoneNumber" hidden
                                              style="color:#FF0004;">Hãy nhập đúng số điện thoại</span><br>
                                    </div>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <input type="text" class="form-control" id="note" value="" placeholder="Ghi chú"
                                           name="note">
                                    <%--                                    <form:errors path="note" cssStyle="color: red"></form:errors>--%>
                                </div>

                                <div class="col-md-12 mb-3" style="margin-top: 10px;">
                                    <div style="border: 1px solid #cabbbb; padding-left: 15px;">
                                        Hình thức thanh toán<br>
                                        <input name="paymentType" type="radio" id="cash" checked value="1"/> Tiền mặt <i
                                            class="fa fa-money"></i><br>
                                        <input name="paymentType" type="radio" id="paypal" value="2"/> Paypal <i
                                            class="fab fa-paypal"></i><br>
                                        <input name="paymentType" type="radio" id="vnpay" value="3"/> VNPAY <i
                                            class="far fa-credit-card"></i>
                                    </div>
                                </div>


                                <div class="col-md-12 mb-3">
                                    <input type="hidden" class="form-control" id="totalPrice" value=""
                                           name="totalPrice">
                                </div>

                                <%--                                <div class="col-md-12 mb-3">--%>
                                <%--                                    <input type="hidden" class="form-control" id="productId" value=""--%>
                                <%--                                           name="productId">--%>
                                <%--                                </div>--%>
                                <%--                                <div class="col-md-12 mb-3">--%>
                                <%--                                    <input type="hidden" class="form-control" id="unitPrice" value=""--%>
                                <%--                                           name="unitPrice">--%>
                                <%--                                </div>--%>
                                <%--                                <div class="col-md-12 mb-3">--%>
                                <%--                                    <input type="hidden" class="form-control" id="quantity" value=""--%>
                                <%--                                           name="quantity">--%>
                                <%--                                </div>--%>
                            </div>
                            <%--                            </form>--%>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                            <h5 class="col-12">Tính tiền</h5>
                            <ul class="summary-table">
                                <li>
                                    <div class="col-12"><input id="point" type="number" pattern="\d+" class="col-12"
                                                               min="0"
                                                               max="<%=SecurityUtils.getPrinciple().getPoint()%>"
                                                               placeholder="Nhập số điểm"
                                                               onchange="checkPoint(this.value)">
                                        <%--                                        <span id="validPoint" hidden--%>
                                        <%--                                              style="color:#FF0004;">Bạn đang có <%=SecurityUtils.getPrinciple().getPoint()%> điểm</span>--%>
                                    </div>
                                </li>
                                <li class="col-12"><span>Tổng:</span> <span>0</span></li>
                            </ul>

                            <div class="cart-btn mt-100">
                                <%--                                    <a name="checkout" type="submit" class="btn amado-btn w-100">Thanh toán</a>--%>
                                <button name="checkout" type="submit" class="btn amado-btn w-100">Thanh toán</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <%--            </form:form>--%>
        </div>
    </div>
    <div class="position-absolute position-cover spin display-none">
        <div class="position-absolute position-center">
            <div class="spinner-border" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="<%=SecurityUtils.getPrinciple().getUsername()%>" id="username" name="username">
<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>


<script>
    function caculate() {
        var items = shoppingCart.get();
        var value = 0;
        for (var i = 0; i < items.length; i++) {
            if ((document.querySelector('input[name="username"]').value) == items[i].username) {
                value += items[i].quantity * items[i].productPrice;
            }
        }
        return value;
    }

    document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML = caculate();
    document.querySelector('input[name="totalPrice"]').value = caculate();

    for (var i = 0; i < items.length; i++) {
        if ((document.querySelector('input[name="username"]').value) == items[i].username) {

        }
    }
</script>

<script>
    // document.querySelector('button[name="checkout"]').onclick = function () {
    $(document).ready(function () {
        $("#checkoutForm button").click(function (event) {
            event.preventDefault();

            if (document.getElementById("cash").checked)
                var paymentType = 1;
            else if (document.getElementById("paypal").checked)
                var paymentType = 2;
            else if (document.getElementById("vnpay").checked)
                var paymentType = 3;
            var info = {
                shippingAddress: $("#shippingAddress").val() + ', ' + $("#commune option:selected").text() + ', ' + $("#district option:selected").text() + ', ' + $("#province option:selected").text()
                    + '<br>Người nhận: ' + ($("#fullName").val() === '' ? 'NaN' : $("#fullName").val()) + '. <br>Số điện thoại: ' + ($("#phone").val() === '' ? 'NaN' : $("#phone").val()) + '.',
                note: ($("#note").val() === '' ? '<br>' : ' Lời nhắn: ' + $("#note").val()),
                totalPrice: document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML,
                point: document.querySelector('input[id="point"]').value,
                paymentType: paymentType
            };
            sessionStorage.setItem("shippingAddress", info.shippingAddress);
            sessionStorage.setItem("note", info.note);
            sessionStorage.setItem("totalPrice", info.totalPrice);
            sessionStorage.setItem("point", info.point);
            sessionStorage.setItem("paymentType", info.paymentType);
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
            var isphone1 = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/.test($("#phone").val());
            if (data.lineItemRequests.length > 0 && (($("#fullName").val().length >= 1) && ($("#fullName").val().length <= 50))
                && ($("#shippingAddress").val().length >= 1 && $("#shippingAddress").val().length <= 150)
                && ($("#phone").val().length == 10 && isphone1)) {

                if (data.orderRequest.paymentType == 1)
                    ajaxPost(data);
                else if (data.orderRequest.paymentType == 2) {
                    ajaxPostPaypal(data);
                    // ajaxPost(data);
                }
                else if (data.orderRequest.paymentType == 3) {
                    ajaxPostVNPAY(data);
                    // ajaxPost(data);
                }

                // let namecheck = document.querySelector('input[name="username"]').value;
                // shoppingCart.remove({username: namecheck});
                // window.location.href = "/checkout-message?error=false";

            } else if (!(($("#fullName").val().length >= 1) && ($("#fullName").val().length <= 50))
                || !($("#shippingAddress").val().length >= 1 && $("#shippingAddress").val().length <= 150)
                || !($("#phone").val().length == 10 && isphone1)) {
                window.location.href = '/checkout';
            } else
                window.location.href = "/checkout-message?error=true";
        })
    });


    function ajaxPost(data) {
        if ($("#commune").val() != "") {
            $.ajax({
                url: '/checkout',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(data),
                success: success()
            });
        } else {
            alert("Bạn chưa chọn địa chỉ!")
        }
    }

    function ajaxPostPaypal() {
        if ($("#commune").val() != "") {
            window.location.href = "/pay-view/";
        } else {
            alert("Bạn chưa chọn địa chỉ!")
        }
    }

    function ajaxPostVNPAY() {
        if ($("#commune").val() != "") {
            window.location.href = "/vnpay-index";
        } else {
            alert("Bạn chưa chọn địa chỉ!")
        }
    }

    function success() {
        let namecheck = document.querySelector('input[name="username"]').value;
        shoppingCart.remove({username: namecheck});
        window.location.href = "/checkout-message?error=false";
    }

    // valid
    $("input[type=text]").keyup(function () {
        if ($("#fullName").val().length > 50) {
            $("#validFullName").removeAttr('hidden');
        } else {
            $("#validFullName").attr("hidden", true);
        }

        if ($("#shippingAddress").val().length > 150) {
            $("#validAddress").removeAttr('hidden');
        } else {
            $("#validAddress").attr("hidden", true);
        }

        var isphone1 = /^(1\s|1|)?((\(\d{3}\))|\d{3})(\-|\s)?(\d{3})(\-|\s)?(\d{4})$/.test($("#phone").val());
        // var isphone1 = /((09|03|07|08|05)+([0-9]{8})\b)/g.test($("#phoneNumber").val());
        // var isphone2 = /((09|03|07|08|05)+([0-9]{9})\b)/g.test($("#phoneNumber").val());
        // var isphone3 = /((09|03|07|08|05)+([0-9]{10})\b)/g.test($("#phoneNumber").val());
        if ($("#phone").val().length != 10 || !isphone1) {
            $("#validPhoneNumber").removeAttr('hidden');
        } else {
            $("#validPhoneNumber").attr("hidden", true);
        }
    });


    $(document).ready(function () {
        $('input[type="number"]').on('keyup', function () {
            v = parseInt($(this).val());
            min = parseInt($(this).attr('min'));
            max = parseInt($(this).attr('max'));
            cal = parseInt(document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML) / 1000;

            if (v < min) {
                $(this).val(min);
            } else if (v > max) {
                $(this).val(max);
            } else if (v > cal) {
                $(this).val(cal);
            }
        })
    });

    $("#point").on('keyup', function () {
        $(this).val(parseInt($(this).val()));
    })

    function checkPoint(val) {
        document.querySelectorAll('ul[class="summary-table"] span')[1].innerHTML = caculate() - val * 1000;
    }


    /*shipping address*/

    function init() {
        $("#province").niceSelect('destroy');
        $.ajax({
            url: '/api/admin/province',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            beforeSend: function () {
                $('.spin').removeClass('display-none');
            },
            success: function (result) {
                // console.log(result);
                // mappingResult(result);
                var str = "";

                result.forEach(function (v, i) {
                    str += '<option value="' + v.id + '">' + v.name + '</option>';
                });
                $("#province").append(str);
                $('.spin').addClass('display-none');
            },
            error: function (error) {
                console.log(error);
                $('.spin').addClass('display-none');
                alert('Đã có lỗi xảy ra');
                if (error.status < 300) {
                    // location.reload(true);
                }
            }
        });
    }


    $('#province').change(function () {
        changeProvince($(this).val(), "", "");

    });

    function changeProvince(id, districtId, communeId) {
        $("#district").niceSelect('destroy');
        if (id != '') {
            $.ajax({
                url: '/api/admin/district?province=' + id,
                type: 'GET',
                contentType: 'application/json',
                // data : JSON.stringify($(this).val()),
                dataType: 'json',
                beforeSend: function () {
                    $('.spin').removeClass('display-none');
                },
                success: function (result) {
                    // console.log(result);
                    var str = '';
                    $.each(result, function (i, v) {
                        if (v['id'] == districtId) {
                            str += '<option selected="selected" value="' + v['id'] + '">' + v['prefix'] + ' ' + v['name'] + '</option>';
                        } else {
                            str += '<option value="' + v['id'] + '">' + v['prefix'] + ' ' + v['name'] + '</option>';
                        }

                    });
                    if (districtId == "") {
                        updateCommune(result[0]['id'], communeId)
                    } else {
                        updateCommune(districtId, communeId)
                    }
                    $('#district').html(str);
                    // console.log(result[0]['id']);

                    $('.spin').addClass('display-none');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('The following error occured: ' + textStatus, errorThrown);
                    $('.spin').addClass('display-none');
                    alert('Đã có lỗi xảy ra');
                }
            });
        }
    }

    $('#district').on('change', function () {
        updateCommune($(this).val(), "");
    })

    function updateCommune(districtId, communeId) {
        $("#commune").niceSelect('destroy');
        if (districtId != '') {
            $.ajax({
                url: '/api/admin/commune?district=' + districtId,
                type: 'GET',
                contentType: 'application/json',
                // data : JSON.stringify($(this).val()),
                dataType: 'json',
                beforeSend: function () {
                    $('.spin').removeClass('display-none');
                },
                success: function (result) {
                    // console.log(result);
                    var str = '';
                    $.each(result, function (i, v) {
                        if (communeId == v['id']) {
                            str += '<option selected="selected" value="' + v['id'] + '">' + v['prefix'] + ' ' + v['name'] + '</option>';
                        } else {
                            str += '<option value="' + v['id'] + '">' + v['prefix'] + ' ' + v['name'] + '</option>';
                        }
                    });
                    $('#commune').html(str);
                    $('.spin').addClass('display-none');
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('The following error occured: ' + textStatus, errorThrown);
                    $('.spin').addClass('display-none');
                    alert('Đã có lỗi xảy ra');
                }
            });
        }
    }


</script>
</body>
</html>
