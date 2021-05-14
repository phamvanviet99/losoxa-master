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

    <div class="single-product-area section-padding-100 clearfix">
        <div class="container-fluid">

            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb mt-50">
                            <li class="breadcrumb-item"><a href="<c:url value="/home"/>">Trang chủ</a></li>
                            <li class="breadcrumb-item"><a href="<c:url value="/category?categoryId=${product.category.id}"/>">${product.category.name}</a></li>
                            <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
                        </ol>
                    </nav>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-lg-7">
                    <div class="single_product_thumb">
                        <div id="product_details_slider" class="carousel slide" data-ride="carousel">
<%--                            <ol class="carousel-indicators">--%>
<%--                                <li class="active" data-target="#product_details_slider" data-slide-to="0" style="background-image: url(/templates/web/img/product-img/pro-big-1.jpg);">--%>
<%--                                </li>--%>
<%--                                <li data-target="#product_details_slider" data-slide-to="1" style="background-image: url(/templates/web/img/product-img/pro-big-2.jpg);">--%>
<%--                                </li>--%>
<%--                                <li data-target="#product_details_slider" data-slide-to="2" style="background-image: url(/templates/web/img/product-img/pro-big-3.jpg);">--%>
<%--                                </li>--%>
<%--                                <li data-target="#product_details_slider" data-slide-to="3" style="background-image: url(/templates/web/img/product-img/pro-big-4.jpg);">--%>
<%--                                </li>--%>
<%--                            </ol>--%>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <a class="gallery_img" href="<c:url value="${product.productImage}"/>">
                                        <img class="d-block w-100" src="<c:url value="${product.productImage}" />" alt="First slide">
                                    </a>
                                </div>
<%--                                <div class="carousel-item">--%>
<%--                                    <a class="gallery_img" href="<c:url value="/templates/web/img/product-img/pro-big-2.jpg"/>">--%>
<%--                                        <img class="d-block w-100" src="<c:url value="/templates/web/img/product-img/pro-big-2.jpg"/>" alt="Second slide">--%>
<%--                                    </a>--%>
<%--                                </div>--%>
<%--                                <div class="carousel-item">--%>
<%--                                    <a class="gallery_img" href="<c:url value="/templates/web/img/product-img/pro-big-3.jpg"/>">--%>
<%--                                        <img class="d-block w-100" src="<c:url value="/templates/web/img/product-img/pro-big-3.jpg"/>" alt="Third slide">--%>
<%--                                    </a>--%>
<%--                                </div>--%>
<%--                                <div class="carousel-item">--%>
<%--                                    <a class="gallery_img" href="<c:url value="/templates/web/img/product-img/pro-big-4.jpg"/>">--%>
<%--                                        <img class="d-block w-100" src="<c:url value="/templates/web/img/product-img/pro-big-4.jpg"/>" alt="Fourth slide">--%>
<%--                                    </a>--%>
<%--                                </div>--%>
                            </div>
                        </div>
                        <br><p><b>MÔ TẢ SẢN PHẨM</b></p>
                        <p>${product.description}</p>
                    </div>
                </div>
                <div class="col-12 col-lg-5">
                    <div class="single_product_desc">
                        <!-- Product Meta Data -->
                        <div class="product-meta-data">
                            <div class="line"></div>
                            <input type="hidden" value="${product.id}" id="productid" name="productid">

                            <p class="product-price">${product.unitPrice}</p>
                            <a href="#">
                                <h6>${product.name}</h6>
                            </a>
                            <!-- Ratings & Review -->
                            <div class="ratings-review mb-15 d-flex align-items-center justify-content-between">
                                <div class="ratings">
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                    <i class="fa fa-star" aria-hidden="true"></i>
                                </div>
<%--                                <div class="review">--%>
<%--                                    <a href="#">Write A Review</a>--%>
<%--                                </div>--%>
                            </div>
                            <!-- Avaiable -->
                            <c:if test="${product.quantity > 0}">
                                <p class="avaibility"><i class="fa fa-circle"></i> Còn hàng</p>
                            </c:if>
                            <c:if test="${product.quantity == 0}">
                                <p class="avaibility"><i class="fa fa-circle"></i> Còn hàng</p>
                            </c:if>
                        </div>

                        <div class="short_overview my-5">

                        </div>

                        <!-- Add to Cart Form -->
                        <form class="cart clearfix" method="post">
                            <div class="cart-btn d-flex mb-50">
                                <p>Số lượng</p>
                                <div class="quantity">
                                    <span class="qty-minus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i class="fa fa-caret-down" aria-hidden="true"></i></span>
                                    <input type="number" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="1">
                                    <span class="qty-plus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i class="fa fa-caret-up" aria-hidden="true"></i></span>
                                </div>
                            </div>
                            <security:authorize access = "isAnonymous()">
                                <a href="/login" class="btn amado-btn">Thêm vào giỏ</a>
                            </security:authorize>
                            <security:authorize access = "isAuthenticated()">
                                <input type="hidden" value="<%=SecurityUtils.getPrinciple().getUsername()%>" id="username" name="username">
                                <a name="addToCart" class="btn amado-btn">Thêm vào giỏ</a>
                            </security:authorize>

                        </form>
                    </div>
                </div>
            </div>
<%--            <p>Sản phẩm liên quan</p>--%>
            <div class="news-relate">
                <h2 style="width: 300px; border-bottom: solid 1px; padding-bottom: 20px">Sản phẩm liên quan</h2>
                <ul>
                    <c:forEach items="${productRelates}" var="productRelate" begin="0" end="2">
                        <li style="display: inline-block; padding-left: 20px;"><a style="font-size: 20px; " href="<c:url value="/product/${productRelate.id}"/> ">
                                ${productRelate.name}<br>
                                    <span style="font-size: 16px">${productRelate.unitPrice} VNĐ</span>
                            <div><img style="height: 200px;" src="<c:url value="${productRelate.productImage}"/> "></div></a>
                        </li>
                    </c:forEach>

                </ul>
            </div>
        </div>
    </div>
</div>

<script>
    document.querySelector('a[name="addToCart"]').onclick = function () {

        var username = document.querySelector('input[name="username"]').value;
        let fullPathImg = document.querySelector('div[class="carousel-item active"] img').src;
        // let arrPath = fullPathImg.split('localhost')[1].split('/');
        var splitPathName = location.pathname.split('/');
        // var productId = parseInt(splitPathName[splitPathName.length - 1]);
        var productId = parseInt(document.querySelector('input[name="productid"]').value);
        var productName = document.querySelector('div[class="product-meta-data"] h6').innerText;
        var productPrice = parseInt(document.querySelector('div[class="product-meta-data"] p').innerText);
        var quantity = parseInt(document.querySelector('input[id="qty"]').value);
        if (shoppingCart.isExist({productId: productId, username: username})) {
            let oldData = shoppingCart.where({productId: productId});
            shoppingCart.update({productId: productId, username: username}, {quantity: parseInt(oldData.quantity) + quantity});
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
        location.href = location.origin + '/cart';

    }
</script>

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<%@ include file="/common/web/js.jsp" %>
<script src="<c:url value="/templates/web/js/addProduct.js"/>"></script>

</body>
</html>
