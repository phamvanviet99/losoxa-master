<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/"/>">LOSOXA</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <nav role="navigation" class="nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a class="nav-link" href="<c:url value="/"/>">TRANG CHỦ
                    <span class="sr-only">(current)</span>
                </a></li>
                <li class="nav-item"><a class="nav-link" href="<c:url value='/blog'/>">BLOG</a></li>
                <security:authorize access = "isAnonymous()">
                    <li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>
                    <li class="nav-item"><a class="nav-link" href="<c:url value='/register'/>">Đăng ký</a></li>
                </security:authorize>
                <security:authorize access = "isAuthenticated()">
                    <li class="nav-item dropdown"><a class="nav-link" href="#"><span><%=SecurityUtils.getPrinciple().getFullName()%></span></a>
                        <nav class="submenu">
                        <ul class="submenu-items">
                            <li class="submenu-item"><a onclick="profile()" class="submenu-link">Tài khoản của tôi</a></li>
                            <li class="submenu-item"><a onclick="redirectOrderHistory()" class="submenu-link">Đơn hàng của tôi</a></li>
                            <li class="submenu-item"><a onclick="logout()" class="submenu-link">Đăng xuất</a></li>
                        </ul>
                        </nav>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="<c:url value='/cart'/>"><i title="Giỏ hàng" data-toggle="tooltip" class="fas fa-shopping-cart"></i></a></li>

                    <li class="nav-item"><a class="nav-link" id="seed" href="#"><%=SecurityUtils.getPrinciple().getPoint()%> <i class="fas fa-seedling"></i></a></li>
                </security:authorize>
            </ul></nav>
        </div>
    </div>
</nav>

