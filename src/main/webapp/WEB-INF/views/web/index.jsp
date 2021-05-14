<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <%@ include file="/common/web/css.jsp" %>
    <link href="<c:url value="/templates/index/style.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/templates/index/css/core-style.css"/>" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.min.css"/>
    <style>
        .slick-next::before, .slick-prev::before {
            color: #fbb710;
        }
    </style>
</head>
<body>
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>
<%--<%@ include file="/common/web/header.jsp" %>--%>
<div class="main-content-wrapper d-flex clearfix" style="margin-top: -30px;">

    <div class="col-3" style="padding-left: 0;">
        <%@ include file="/common/web/menu.jsp" %>
    </div>


    <div class="col-9" style="padding-right: 0;">
        <div class="container-fluid">
            <div class="row">
                <!-- ##### Welcome Area Start ##### -->
                <section class="welcome_area bg-img background-overlay"
                         style="background-image: url('/templates/img/bg-img/rung-cay.jpg'); background-size: auto; height: 190px;">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <div class="col-12">
                                <div class="hero-content">
                                    <h4 style="color: #ffffff">LOSOXA - Lối sống xanh</h4>
                                    <a href="/blog" class="btn essence-btn">Ghé thăm blog</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <%--Category popular area--%>
                <div class="top_catagory_area section-padding-80 clearfix">
                    <div class="container">
                        <div class="row justify-content-center">
                            <c:forEach items="${listCategory}" var="category" begin="0" end="5">
                                <!-- Single Catagory -->

                                <div class="col-12 col-sm-6 col-md-4" style="padding-bottom: 15px;">
                                    <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
                                         style="background-image: url(${category.categoryImage});">
                                        <div class="catagory-content">
                                            <a href="<c:url value="/category?categoryId=${category.id}"/>">${category.name}</a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <%--product popular--%>
                <div style="text-align: center; font-family: 'Segoe UI'; width: 100%;">
                    <h2>Phổ biến</h2>
                </div>
                <section class="withOptions">
                    <c:forEach items="${listProduct}" var="product" begin="0" end="7">
                        <input type="hidden" value="${product.id}" id="productid" name="productid">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img" style="height: 250px;">
                                <img src="<c:url value="${product.productImage}"/> " alt="">
<%--                                <!-- Favourite -->--%>
<%--                                <div class="product-favourite">--%>
<%--                                    <a href="#" class="favme fa fa-heart"></a>--%>
<%--                                </div>--%>
                            </div>
                            <!-- Product Description -->
                            <div class="product-description">
                                <span>${product.category.name}</span>
                                <div style="display: flex; ">
                                <a href="<c:url value="/product/${product.id}"/>">
                                    <h6>${product.name}</h6>
                                </a>
                                    <div style="position: absolute; right: 10px;">
                                <security:authorize access="isAuthenticated()">
                                    <c:if test="${productFavourites.contains(product)}">
                                                    <span class="favourite"><div class="favourite-heart"><i
                                                            data-toggle="tooltip" data-placement="left"
                                                            title="Bỏ yêu thích" class="fa fa-heart"></i></div></span>
                                        <input type="hidden" value="${product.id}" id="productFavouriteId"
                                               name="productFavouriteId">
                                    </c:if>
                                    <c:if test="${!productFavourites.contains(product)}">
                                                    <span class="favourite"><div class="un-favourite-heart"><i
                                                            data-toggle="tooltip" data-placement="left"
                                                            title="Thêm vào yêu thích" class="fa fa-heart-o"></i></div></span>
                                        <input type="hidden" value="${product.id}" id="productUnFavouriteId"
                                               name="productUnFavouriteId">
                                    </c:if>
                                </security:authorize>
                                    </div>
                                </div>
                                <p class="product-price">${product.unitPrice}</p>

                                <!-- Hover Content -->
                                <div class="hover-content">
                                    <!-- Add to Cart -->
                                    <div class="add-to-cart-btn">
                                        <security:authorize access="isAnonymous()">
                                            <a href="/login" class="btn essence-btn">Thêm vào giỏ hàng</a>
                                        </security:authorize>
                                        <security:authorize access="isAuthenticated()">
                                            <input type="hidden"
                                                   value="<%=SecurityUtils.getPrinciple().getUsername()%>"
                                                   id="username" name="username">
                                            <input type="hidden" value="<%=SecurityUtils.getPrinciple().getId()%>"
                                                   id="userId" name="userId">
                                            <a name="addToCart" class="btn essence-btn">Thêm vào giỏ hàng</a>
                                        </security:authorize>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </section>

            </div>
        </div>
    </div>
</div>
</div>
</div>
<input type="hidden" value="${username}" id="username" name="username">

<!-- Footer -->

<%@ include file="/common/web/footer.jsp" %>

<%@ include file="/common/web/js.jsp" %>

<script src="<c:url value="/templates/web/js/chatbot.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Slick -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.min.js"></script>
<script>
    $('.withOptions').slick({
        slidesToShow: 3,
        dots: true,
        arrows: true,
        autoplay: true,
        autoplayspeed: 1000,
        centerMode: true,
    });
</script>

<script>
    document.querySelectorAll('a[name="addToCart"]').forEach(function (btn, index) {
        btn.onclick = function () {

            var username = document.querySelector('input[name="username"]').value;
            let fullPathImg = document.querySelectorAll('div[class="product-img"] img')[index].src;
            // let arrPath = fullPathImg.split('localhost')[1].split('/');
            var splitPathName = location.pathname.split('/');
            var productId = parseInt(document.querySelectorAll('input[name="productid"]')[index].value);
            var productName = document.querySelectorAll('div[class="product-description"] h6')[index].innerText;
            var productPrice = parseInt(document.querySelectorAll('div[class="product-description"] p')[index].innerText);
            var quantity = 1;
            if (shoppingCart.isExist({productId: productId})) {
                let oldData = shoppingCart.where({productId: productId, username: username});
                shoppingCart.update({
                    productId: productId,
                    username: username
                }, {quantity: parseInt(oldData.quantity) + quantity});
            } else {
                shoppingCart.insert({
                    username,
                    fullPathImg,
                    productId,
                    productName,
                    productPrice,
                    quantity
                })
            }
            alert('Đã thêm sản phẩm vào giỏ hàng.');
            // location.href = location.origin + '/cart';

        }
    })

    function updateFavourite(data) {
        $.ajax({
            url: '/api/favourite',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            // success: function () {
            //     window.location.href = "/product?message=success";
            // },
            // error: function () {
            //     window.location.href = "/product?message=error";
            // }
        });
    }

    document.querySelectorAll('div[class="favourite-heart"]').forEach(function (btn, index) {
        btn.onclick = function (e) {
            if (document.querySelectorAll('div[class="favourite-heart"] i')[index].className == "fa fa-heart") {
                document.querySelectorAll('div[class="favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="favourite-heart"] i')[index].setAttribute("class", "fa fa-heart-o");
            } else {
                document.querySelectorAll('div[class="favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="favourite-heart"] i')[index].setAttribute("class", "fa fa-heart");
            }
            e.preventDefault();
            var data = {};
            data["productId"] = parseInt(document.querySelectorAll('input[name="productFavouriteId"]')[index].value);
            data["userId"] = Number(document.querySelector('input[name="userId"]').value);
            updateFavourite(data);
        }
    })


    document.querySelectorAll('div[class="un-favourite-heart"]').forEach(function (btn, index) {
        btn.onclick = function (e) {
            if (document.querySelectorAll('div[class="un-favourite-heart"] i')[index].className == "fa fa-heart-o") {
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].setAttribute("class", "fa fa-heart");
            } else {
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].setAttribute("class", "fa fa-heart-o");
            }
            e.preventDefault();
            var data = {};
            data["productId"] = parseInt(document.querySelectorAll('input[name="productUnFavouriteId"]')[index].value);
            data["userId"] = Number(document.querySelector('input[name="userId"]').value);
            updateFavourite(data);
        }
    })





</script>
</body>
</html>
