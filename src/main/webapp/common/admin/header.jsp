<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<header class="header-desktop">
    <div class="section__content section__content--p30">
        <div class="container-fluid">
            <div class="header-wrap">
<%--                <form class="form-header" action="" method="POST">--%>
<%--                    <input class="au-input au-input--xl" type="text" name="search" placeholder="Search for datas &amp; reports..." />--%>
<%--                    <button class="au-btn--submit" type="submit">--%>
<%--                        <i class="zmdi zmdi-search"></i>--%>
<%--                    </button>--%>
<%--                </form>--%>
                <div class="header-button">
                    <div class="account-wrap">
                        <div class="account-item clearfix js-item-menu">
<%--                            <div class="image">--%>
<%--                                <img src="images/icon/avatar-01.jpg" alt="<%=SecurityUtils.getPrinciple().getFullName()%>" />--%>
<%--                            </div>--%>
                            <div class="content">
                                <a class="js-acc-btn" href="#"><%=SecurityUtils.getPrinciple().getFullName()%></a>
                            </div>
                            <div class="account-dropdown js-dropdown">
                                <div class="info clearfix">
<%--                                    <div class="image">--%>
<%--                                        <a href="#">--%>
<%--                                            <img src="images/icon/avatar-01.jpg" alt="<%=SecurityUtils.getPrinciple().getFullName()%>" />--%>
<%--                                        </a>--%>
<%--                                    </div>--%>
                                    <div class="content">
                                        <h5 class="name">
                                            <a href="#"><%=SecurityUtils.getPrinciple().getFullName()%></a>
                                        </h5>
                                        <span class="email"><%=SecurityUtils.getPrinciple().getEmail()%></span>
                                    </div>
                                </div>
<%--                                <div class="account-dropdown__body">--%>
<%--                                    <div class="account-dropdown__item">--%>
<%--                                        <a href="#">--%>
<%--                                            <i class="zmdi zmdi-account"></i>Account</a>--%>
<%--                                    </div>--%>
<%--                                    <div class="account-dropdown__item">--%>
<%--                                        <a href="#">--%>
<%--                                            <i class="zmdi zmdi-settings"></i>Setting</a>--%>
<%--                                    </div>--%>
<%--                                    <div class="account-dropdown__item">--%>
<%--                                        <a href="#">--%>
<%--                                            <i class="zmdi zmdi-money-box"></i>Billing</a>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
                                <div class="account-dropdown__footer">
                                    <a href="<c:url value='/logout'/>">
                                        <i class="zmdi zmdi-power"></i>Đăng xuất</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>