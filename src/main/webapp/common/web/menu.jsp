<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--header--%>

<%--<div class="mobile-nav">--%>
<%--    <!-- Navbar Brand -->--%>
<%--    <div class="amado-navbar-brand">--%>
<%--        <a href="<c:url value="/home"/>"><img src="<c:url value="/templates/img/core-img/losoxa-logo.png"/>" alt=""></a>--%>
<%--    </div>--%>
<%--    <!-- Navbar Toggler -->--%>
<%--    <div class="amado-navbar-toggler">--%>
<%--        <span></span><span></span><span></span>--%>
<%--    </div>--%>
<%--</div>--%>

<header class="header-area clearfix">
    <!-- Close Icon -->
    <div class="nav-close">
        <i class="fa fa-close" aria-hidden="true"></i>
    </div>
    <!-- Logo -->
    <div class="logo">
        <a href="<c:url value="/home"/> "><img src="<c:url value="/templates/img/core-img/losoxa-logo.png"/>" alt=""></a>
    </div>
    <!-- Amado Nav -->
    <nav class="amado-nav">
        <ul>
            <li class="active"><a href="<c:url value="/home"/> ">Trang chủ</a></li>
            <li><a href="<c:url value="/product"/>">Sản phẩm</a></li>
            <li><a href="#">Thể loại</a>
                <ul>
                    <c:forEach items="${listCategory}" var="category" begin="0" end="9">
                        <li><a href="<c:url value="/category?categoryId=${category.id}"/>">${category.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </nav>
    <!-- Button Group -->
    <div class="amado-btn-group mt-30 mb-100">
        <a href="<c:url value="/product-new"/>" class="btn amado-btn mb-15">SẢN PHẨM MỚI</a>
<%--        <a href="#" class="btn amado-btn active">Sản phẩm mới</a>--%>
    </div>
    <!-- Cart Menu -->
    <div class="cart-fav-search mb-100">
        <a href="<c:url value="/cart"/>" class="cart-nav"><i style="width: 30px;" class="fas fa-shopping-cart"></i> Giỏ hàng <span>(0)</span></a>
        <security:authorize access = "isAuthenticated()">
            <a href="<c:url value="/order-history"/>" class="order-nav"><i style="width: 30px;" class="far fa-file-alt"></i> Đơn hàng</a>
        </security:authorize>
        <a href="<c:url value="/favourite"/>" class="fav-nav"><i style="width: 30px;" class="far fa-heart"></i> Yêu thích</a>
        <a href="#" class="search-nav"><i style="width: 30px;" class="fas fa-search"></i> Tìm kiếm</a>
    </div>
    <!-- Social Button -->
    <div class="social-info d-flex justify-content-between">
        <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
        <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
        <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
        <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
    </div>
</header>

