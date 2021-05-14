<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<header class="header-mobile d-block d-lg-none">
    <div class="header-mobile__bar">
        <div class="container-fluid">
            <div class="header-mobile-inner">
                <a class="logo" href="<c:url value="/admin/home"/>">
                    <img src="<c:url value="/templates/img/core-img/losoxa-logo.png"/>" width="70px" alt="Losoxa"/>
                </a>
                <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                </button>
            </div>
        </div>
    </div>
    <nav class="navbar-mobile">
        <div class="container-fluid">
            <ul class="navbar-mobile__list list-unstyled">
                <li class="has-sub">
                    <a class="js-arrow" href="<c:url value="/admin/home"/>">
                        <i class="fas fa-tachometer-alt"></i>Trang chủ</a>
                </li>
                <c:forEach items="${currentRole}" var="role">
                    <c:if test="${role == 'VIEW_USER'}">
                        <li>
                            <a href="<c:url value="/admin/user"/>">
                                <i class="fas fa-chart-bar"></i>Người dùng</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_CATEGORY'}">
                        <li>
                            <a href="<c:url value="/admin/category"/>">
                                <i class="fas fa-list"></i>Thể loại</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_PRODUCT'}">
                        <li>
                            <a href="<c:url value="/admin/product"/>">
                                <i class="fab fa-pagelines"></i>Sản phẩm</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_ORDER'}">
                        <li>
                            <a href="<c:url value="/admin/order"/>">
                                <i class="far fa-file-alt"></i>Đơn hàng</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_ROLE'}">
                        <li>
                            <a href="<c:url value="/admin/role"/>">
                                <i class="fas fa-shield-alt"></i>Vai trò</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_PERMISSION'}">
                        <li>
                            <a href="<c:url value="/admin/permission"/>">
                                <i class="fas fa-unlock-alt"></i>Quyền</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_BLOG'}">
                        <li>
                            <a href="<c:url value="/admin/blog"/>">
                                <i class="fab fa-blogger"></i>Blog</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="<c:url value="/admin/report/list"/>">
                        <i class="fas fa-file-excel"></i>Thống kê báo cáo</a>
                </li>

            </ul>
        </div>
    </nav>
</header>

<aside class="menu-sidebar d-none d-lg-block">
    <div class="logo">
        <a href="<c:url value="/admin/home"/>">
            <img src="<c:url value="/templates/img/core-img/losoxa-logo.png"/>" width="70px" alt="Losoxa"/>
        </a>
    </div>
    <div class="menu-sidebar__content js-scrollbar1">
        <nav class="navbar-sidebar">
            <ul class="list-unstyled navbar__list">
                <li class="active has-sub">
                    <a class="js-arrow" href="<c:url value="/admin/home"/>">
                        <i class="fas fa-tachometer-alt" style="width: 20px;"></i>Trang chủ</a>
                </li>
                <c:forEach items="${currentRole}" var="role">
                    <c:if test="${role == 'VIEW_USER'}">
                        <li>
                            <a href="<c:url value="/admin/user"/>">
                                <i class="far fa-user" style="width: 20px;"></i>Người dùng</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_CATEGORY'}">
                        <li>
                            <a href="<c:url value="/admin/category"/>">
                                <i class="fas fa-list" style="width: 20px;"></i>Thể loại</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_PRODUCT'}">
                        <li>
                            <a href="<c:url value="/admin/product"/>">
                                <i class="fab fa-pagelines" style="width: 20px;"></i>Sản phẩm</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_ORDER'}">
                        <li>
                            <a href="<c:url value="/admin/order"/>">
                                <i class="far fa-file-alt" style="width: 20px;"></i>Đơn hàng</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_ROLE'}">
                        <li>
                            <a href="<c:url value="/admin/role"/>">
                                <i class="fas fa-shield-alt" style="width: 20px;"></i>Vai trò</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_PERMISSION'}">
                        <li>
                            <a href="<c:url value="/admin/permission"/>">
                                <i class="fas fa-unlock-alt" style="width: 20px;"></i>Quyền</a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'VIEW_BLOG'}">
                        <li>
                            <a href="<c:url value="/admin/blog"/>">
                                <i class="fab fa-blogger" style="width: 20px;"></i>Blog</a>
                        </li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="<c:url value="/admin/report/list"/>">
                        <i class="fas fa-file-excel"></i>Thống kê báo cáo</a>
                </li>
            </ul>
        </nav>
    </div>
</aside>