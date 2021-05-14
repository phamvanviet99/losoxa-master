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


    <%--Sản phẩm--%>
    <div class="amado_product_area section-padding-100">
        <div class="container-fluid">

            <div class="row">
                <div class="col-12">
                    <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                        <!-- Total Products -->
                        <div class="total-products">
                            <p>Hiển thị ${(model.page-1)*model.limit+1} - ${(model.page-1)*model.limit + model.limit} trong ${model.totalItem} sản phẩm</p>
                        </div>

                    </div>
                </div>
            </div>

            <form action="<c:url value="/product"/> " id="formSubmit" method="get">
            <div class="row">
                <c:forEach items="${model.listFavourite}" var="favourite">
                <input type="hidden" value="${favourite.product.id}" id="productid" name="productid">
                <!-- Single Product Area -->
                <div class="col-12 col-sm-12 col-md-12 col-xl-12">
                    <div class="single-product-wrapper">
                        <!-- Product Image -->
                        <div class="product-img" style="display: flex;">
                            <div class="col-3 col-sm-3 col-md-3 col-xl-3">
                                <img style="height: 100px; width: auto;" src="<c:url value="${favourite.product.productImage}"/>"  alt="">
                            </div>
                            <div class="product-description d-flex align-items-center justify-content-between col-9 col-sm-9 col-md-9 col-xl-9">
                                <!-- Product Meta Data -->
                                <div class="product-meta-data">
                                    <div class="line" style="width: 100%;"></div>
                                    <p class="product-price">${favourite.product.unitPrice}</p>
                                    <a href="<c:url value="/product/${favourite.product.id}"/>">
                                        <h6>${favourite.product.name}</h6>
                                    </a>
                                </div>
                                <!-- Ratings & Cart -->
                                <div class="ratings-cart text-right">
                                    <div class="cart">
                                        <security:authorize access = "isAnonymous()">
                                            <a href="/login" data-toggle="tooltip" data-placement="left" title="Thêm vào giỏ"><img src="<c:url value="/templates/img/core-img/cart.png"/> " alt=""></a>
                                        </security:authorize>
                                        <security:authorize access = "isAuthenticated()">
                                            <c:if test="${productFavourites.contains(favourite.product)}">
                                                <span class="favourite"><div class="favourite-heart"><i data-toggle="tooltip" data-placement="left" title="Bỏ yêu thích" class="fa fa-heart"></i></div></span>
                                                <input type="hidden" value="${favourite.product.id}" id="productFavouriteId" name="productFavouriteId">
                                            </c:if>
                                            <c:if test="${!productFavourites.contains(favourite.product)}">
                                                <span class="favourite"><div class="un-favourite-heart"><i data-toggle="tooltip" data-placement="left" title="Thêm vào yêu thích" class="fa fa-heart-o"></i></div></span>
                                                <input type="hidden" value="${favourite.product.id}" id="productUnFavouriteId" name="productUnFavouriteId">
                                            </c:if>
                                            <input type="hidden" value="<%=SecurityUtils.getPrinciple().getUsername()%>" id="username" name="username">
                                            <input type="hidden" value="<%=SecurityUtils.getPrinciple().getId()%>" id="userId" name="userId">
                                            <a name="addToCart" data-toggle="tooltip" data-placement="left" title="Thêm vào giỏ"><img src="<c:url value="/templates/img/core-img/cart.png"/> " alt=""></a>
                                        </security:authorize>

                                    </div>
                                </div>
                            </div>
                            <!-- Hover Thumb -->
                            <%--<img class="hover-img" src="<c:url value="/templates/upload/product/${product.name}.jpg"/>" alt="">--%>
                        </div>

                        <!-- Product Description -->

                    </div>
                </div>
                </c:forEach>
            </div>

            <ul class="pagination" id="pagination"></ul>
            <input type="hidden" value="" id="page" name="page"/>
            </form>


        </div>
    </div>

</div>

<%@ include file="/common/web/js.jsp" %>
<script type="text/javascript">
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        var obj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 2,
            startPage: currentPage,
            last:'Cuối',
            next:'Tiếp',
            first:'Đầu',
            prev:'Trước',
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    // $('#limit').val(2);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>

<script>
    document.querySelectorAll('a[name="addToCart"]').forEach(function(btn,index){
        btn.onclick = function () {

        var username = document.querySelector('input[name="username"]').value;
        let fullPathImg = document.querySelectorAll('div[class="product-img"] img')[index].src;
        // let arrPath = fullPathImg.split('localhost')[1].split('/');
        var splitPathName = location.pathname.split('/');
        var productId = parseInt(document.querySelectorAll('input[name="productid"]')[index].value);
        var productName = document.querySelectorAll('div[class="product-meta-data"] h6')[index].innerText;
        var productPrice = parseInt(document.querySelectorAll('div[class="product-meta-data"] p')[index].innerText);
        var quantity = 1;
        if (shoppingCart.isExist({productId: productId})) {
            let oldData = shoppingCart.where({productId: productId, username: username});
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

    }})


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

    document.querySelectorAll('div[class="favourite-heart"]').forEach(function(btn,index){
        btn.onclick = function (e) {
            if (document.querySelectorAll('div[class="favourite-heart"] i')[index].className == "fa fa-heart"){
                document.querySelectorAll('div[class="favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="favourite-heart"] i')[index].setAttribute("class", "fa fa-heart-o");
            }
            else {
                document.querySelectorAll('div[class="favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="favourite-heart"] i')[index].setAttribute("class", "fa fa-heart");
            }
            e.preventDefault();
            var data = {};
            data["productId"] = parseInt(document.querySelectorAll('input[name="productFavouriteId"]')[index].value);
            data["userId"] = Number(document.querySelector('input[name="userId"]').value);
            updateFavourite(data);
        }})


    document.querySelectorAll('div[class="un-favourite-heart"]').forEach(function(btn,index){
        btn.onclick = function (e) {
            if (document.querySelectorAll('div[class="un-favourite-heart"] i')[index].className == "fa fa-heart-o"){
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].setAttribute("class", "fa fa-heart");
            }
            else {
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].removeAttribute("class");
                document.querySelectorAll('div[class="un-favourite-heart"] i')[index].setAttribute("class", "fa fa-heart-o");
            }
            e.preventDefault();
            var data = {};
            data["productId"] = parseInt(document.querySelectorAll('input[name="productUnFavouriteId"]')[index].value);
            data["userId"] = Number(document.querySelector('input[name="userId"]').value);
            updateFavourite(data);
        }})

</script>

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<script src="<c:url value="/templates/web/js/addProduct.js"/>"></script>



</body>
</html>
