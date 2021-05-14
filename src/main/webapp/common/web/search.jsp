<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="search-wrapper section-padding-100">
    <div class="search-close">
        <i class="fa fa-close" aria-hidden="true"></i>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="search-content">
                    <form action="/product" method="get">
                        <input name="search" id="search" placeholder="Tìm sản phẩm, thể loại bạn muốn...">
                        <button type="submit"><img src="<c:url value="/templates/img/core-img/search.png"/>" alt=""></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
