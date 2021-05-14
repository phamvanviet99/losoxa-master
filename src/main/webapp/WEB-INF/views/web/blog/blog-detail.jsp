<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Blog</title>
    <%@ include file="/common/web/css.jsp" %>
    <%@ include file="/common/web/blog/css.jsp" %>
</head>
<body>
<%@ include file="/common/web/search.jsp" %>
<%@ include file="/common/web/header.jsp" %>

<div class="single-blog-wrapper">

    <!-- Single Blog Post Thumb -->
    <div class="single-blog-post-thumb" style="margin-top: -30px;">
        <img src="<c:url value="${blog.thumbnail}"/> " style="width: 100%; height: 300px; object-fit: cover;" alt="">
    </div>

    <!-- Single Blog Content Wrap -->
    <div class="single-blog-content-wrapper d-flex">

        <!-- Blog Content -->
        <div class="single-blog--text">
            <h2>${blog.title}</h2>

            <blockquote>
                <h6><i class="fa fa-quote-left" aria-hidden="true"></i> ${blog.shortDescription} </h6>
            </blockquote>

            <p>${blog.content}</p>

            <blockquote style="width: 100%;">
                <span style="margin-left: 0;"><i class="fa fa-eye"></i> Luợt xem: ${blog.views}</></span>
                <span style="margin-left: 60%;">${blog.createdBy}</span>
            </blockquote>
        </div>

        <!-- Related Blog Post -->

        <div class="related-blog-post">
            <h4 style="padding: 20px 0;">Bài viết khác </h4>
            <c:forEach items="${blogTop}" var="blogTop">
                <!-- Single Related Blog Post -->
                <div class="single-related-blog-post">
                    <img src="<c:url value="${blogTop.thumbnail}"/>" alt="">
                    <a href="<c:url value="/blog/${blogTop.id}"/>">
                        <h5>${blogTop.title}</h5>
                    </a>
                </div>
            </c:forEach>
        </div>

    </div>
</div>
<%@ include file="/common/web/js.jsp" %>
<%--<%@ include file="/common/web/blog/js.jsp" %>--%>

<%@ include file="/common/web/footer.jsp" %>
</body>
</html>