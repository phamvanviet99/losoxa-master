<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sản phẩm</title>
    <%@ include file="/common/web/css.jsp" %>

</head>
<body>
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>
<div class="main-content-wrapper d-flex clearfix">
    <%@ include file="/common/web/menu.jsp" %>


    <%--Thể loại--%>
    <div class="shop_sidebar_area">

        <!-- ##### Single Widget ##### -->
        <div class="widget catagory mb-50">
            <!-- Widget Title -->
            <h6 class="widget-title mb-30"><b>THỂ LOẠI</b></h6>

            <!--  Catagories  -->
            <div class="catagories-menu">
                <ul>
                    <c:forEach items="${listCategory}" var="category">
                        <li><a href="<c:url value="/category?categoryId=${category.id}"/>">${category.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <%--        <!-- ##### Single Widget ##### -->--%>
        <%--        <div class="widget price mb-50">--%>
        <%--            <!-- Widget Title -->--%>
        <%--            <h6 class="widget-title mb-30">Giá</h6>--%>

        <%--            <div class="widget-desc">--%>
        <%--                <div class="slider-range">--%>
        <%--                    <div data-min="0" data-max="1000000" data-unit="" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="0" data-value-max="1000000" data-label-result="">--%>
        <%--                        <div class="ui-slider-range ui-widget-header ui-corner-all"></div>--%>
        <%--                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>--%>
        <%--                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>--%>
        <%--                    </div>--%>
        <%--                    <div class="range-price"><span id="minPrice">0</span> - <span id="maxPrice">1000000</span></div>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <div class="widget price mb-50">
            <h6 class="widget-title mb-30"><b>GIÁ</b></h6>
            <div class="widget-desc">
                <div class="mb-15">
                    <input type="number" pattern="\d+" id="minPrice" placeholder="Giá từ" class="col-10">
                </div>
                <div class="mb-15">
                    <input type="number" pattern="\d+" id="maxPrice" placeholder="Giá đến" class="col-10">
                </div>
                <div class="mb-15">
                    <input onclick="suggestion1()" type="button" value="Dưới 100,000" style="font-size: 12px">
                    <input onclick="suggestion2()" type="button" value="100,0000-200,000" style="font-size: 12px">
                    <input onclick="suggestion3()" type="button" value="200,0000-500,000" style="font-size: 12px">

                </div>
                <p id="validFilterPrice" hidden>Vui lòng điền khoảng giá phù hợp</p>

            </div>
        </div>
        <a onclick="filterByPrice()" class="btn amado-btn w-100">Lọc</a>

    </div>

    <%--Sản phẩm--%>
    <div class="amado_product_area section-padding-100">
        <div class="container-fluid">

            <div class="row">
                <div class="col-12">
                    <div class="product-topbar d-xl-flex align-items-end justify-content-between">
                        <!-- Total Products -->
                        <div class="total-products">
                            <p>Hiển thị ${(model.page-1)*model.limit+1} - ${(model.page-1)*model.limit + model.limit}
                                trong ${model.totalItem} sản phẩm</p>
                        </div>

                    </div>
                </div>
            </div>

            <form action="<c:url value="/product"/> " id="formSubmit" method="get">
                <div class="row">
                    <c:forEach items="${model.listProduct}" var="product">
                        <input type="hidden" value="${product.id}" id="productid" name="productid">
                        <!-- Single Product Area -->
                        <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                            <div class="single-product-wrapper">
                                <!-- Product Image -->
                                <div class="product-img">
                                    <img class="main-img" src="<c:url value="${product.productImage}" />" alt="">
                                    <!-- Hover Thumb -->
                                        <%--<img class="hover-img" src="<c:url value="/templates/upload/product/${product.name}.jpg"/>" alt="">--%>
                                </div>

                                <!-- Product Description -->
                                <div class="product-description d-flex align-items-center justify-content-between">
                                    <!-- Product Meta Data -->
                                    <div class="product-meta-data">
                                        <div class="line"></div>
                                        <p class="product-price">${product.unitPrice}</p>
                                        <a href="<c:url value="/product/${product.id}"/>">
                                            <h6>${product.name}</h6>
                                        </a>
                                    </div>
                                    <!-- Ratings & Cart -->
                                    <div class="ratings-cart text-right">
                                        <div class="view">
                                            <span class="views">Đã bán: ${product.quantitySold}</span>
                                        </div>
                                        <div class="cart">
                                            <security:authorize access="isAnonymous()">
                                                <a href="/login" data-toggle="tooltip" data-placement="left"
                                                   title="Thêm vào giỏ"><img
                                                        src="<c:url value="/templates/img/core-img/cart.png"/> " alt=""></a>
                                            </security:authorize>
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
                                                <input type="hidden"
                                                       value="<%=SecurityUtils.getPrinciple().getUsername()%>"
                                                       id="username" name="username">
                                                <input type="hidden" value="<%=SecurityUtils.getPrinciple().getId()%>"
                                                       id="userId" name="userId">
                                                <a name="addToCart" data-toggle="tooltip" data-placement="left"
                                                   title="Thêm vào giỏ"><img
                                                        src="<c:url value="/templates/img/core-img/cart.png"/> " alt=""></a>
                                            </security:authorize>

                                        </div>
                                    </div>
                                </div>
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
    document.querySelectorAll('a[name="addToCart"]').forEach(function (btn, index) {
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


    function filterByPrice() {
        var min = document.getElementById('minPrice').value;
        var max = document.getElementById('maxPrice').value;
        if (min == "" || max == "" || (min > max)) {
            $("#validFilterPrice").removeAttr('hidden');
            $("#validFilterPrice").css("color", "#FF0004");
            $("#validFilterPrice").css("font-size", "12px");
        } else {
            window.location.href = "/product/price?min=" + min + "&max=" + max;
        }
    }

    function suggestion1() {
        document.getElementById('minPrice').value = "0";
        document.getElementById('maxPrice').value = "100000";
    }

    function suggestion2() {
        document.getElementById('minPrice').value = "100000";
        document.getElementById('maxPrice').value = "200000";
    }

    function suggestion3() {
        document.getElementById('minPrice').value = "200000";
        document.getElementById('maxPrice').value = "500000";
    }


    $(document).ready(function () {
        $('input[type="number"]').on('keyup', function () {
            v = parseInt($(this).val());
            if (v < 0) {
                $(this).val(0);
            }
        })
    });


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

<!-- Footer -->
<%@ include file="/common/web/footer.jsp" %>
<script src="<c:url value="/templates/web/js/addProduct.js"/>"></script>


</body>
</html>
