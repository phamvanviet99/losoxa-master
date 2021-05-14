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

<%--<video autoplay muted loop id="myVideo">--%>
<%--    <source src="<c:url value="/templates/video/Lake.mp4"/> " type="video/mp4">--%>
<%--</video>--%>
<video src="<c:url value="/templates/video/Lake.mp4"/>" loop muted autoplay>
</video>
<%--<div class="breadcumb_area breadcumb-style-two bg-img" style="background-image: url(img/bg-img/breadcumb2.jpg);">--%>
<%--<div class="overlay">--%>
<%--</div>--%>
<h1>BLOG
</h1>
<%--<div class="breadcumb_area breadcumb-style-two bg-img blog-content">--%>
<%--    <div class="container h-100">--%>
<%--        <div class="row h-100 align-items-center">--%>
<%--            <div class="col-12">--%>
<%--                <div class="page-title text-center">--%>
<%--                    <h2>Blog</h2>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Blog Wrapper Area Start ##### -->
<div class="blog-wrapper section-padding-80">
    <div class="container">
        <form action="<c:url value="/blog"/> " id="formSubmit" method="get">
            <div class="row">
                <c:forEach items="${model.listBlog}" var="blog">
                    <!-- Single Blog Area -->
                    <div class="col-12 col-lg-6" style="height: 500px;">

                        <div class="single-blog-area mb-50" >
                            <img src="<c:url value="${blog.thumbnail}" />" style="height: 75%; object-fit: contain;" alt="">
                            <!-- Post Title -->
                            <div class="post-title">
                                <a href="#">${blog.title}</a>
                            </div>
                            <!-- Hover Content -->
                            <div class="hover-content">
                                <!-- Post Title -->
                                <div class="hover-post-title">
                                    <a href="<c:url value="/blog/${blog.id}"/>">${blog.title}</a>
                                </div>
                                <p>${blog.shortDescription}</p>
                                <a href="<c:url value="/blog/${blog.id}"/>">Đọc tiếp <i class="fa fa-angle-right"></i></a>
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


<%@ include file="/common/web/js.jsp" %>
<%--<%@ include file="/common/web/blog/js.jsp" %>--%>

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
<%@ include file="/common/web/footer.jsp" %>
</body>
</html>
