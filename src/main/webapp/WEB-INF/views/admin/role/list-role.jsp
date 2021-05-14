<%@ page import="com.phamvanviet.losoxa.util.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Vai trò</title>
    <%@ include file="/common/admin/css.jsp" %>

</head>

<body class="animsition">
<div class="page-wrapper">

    <!-- MENU SIDEBAR-->
    <%@ include file="/common/admin/menu.jsp" %>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <!-- HEADER DESKTOP-->
        <%@ include file="/common/admin/header.jsp" %>
        <!-- HEADER DESKTOP-->

        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${message == 'insert_success'}">
                                <div class="alert alert-success">Thêm mới thành công</div>
                            </c:if>
                            <c:if test="${message == 'insert_error'}">
                                <div class="alert alert-danger">Thêm mới thất bại</div>
                            </c:if>
                            <c:if test="${message == 'update_success'}">
                                <div class="alert alert-success">Cập nhật thành công</div>
                            </c:if>
                            <c:if test="${message == 'update_error'}">
                                <div class="alert alert-danger">Cập nhật thất bại</div>
                            </c:if>
                            <!-- DATA TABLE -->
                            <h3 class="title-5 m-b-35">Quản lý vai trò</h3>
                            <div class="table-data__tool">
                                <a href="<c:url value="/admin/role/create"/>">
                                    <div class="table-data__tool-right">
                                        <c:forEach items="${currentRole}" var="currentRole">
                                            <c:if test="${currentRole == 'CREATE_ROLE'}">
                                                <button class="au-btn au-btn-icon au-btn--green au-btn--small">
                                                    <i class="zmdi zmdi-plus"></i>Thêm
                                                </button>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </a>
                            </div>
                            <form action="<c:url value="/admin/role"/> " id="formSubmit" method="get">
                                <div class="table-responsive table-responsive-data2">
                                    <table class="table table-data2">
                                        <thead>
                                        <tr>
                                            <%--                                            <th>--%>
                                            <%--                                            <label class="au-checkbox">--%>
                                            <%--                                                <input type="checkbox">--%>
                                            <%--                                                <span class="au-checkmark"></span>--%>
                                            <%--                                            </label>--%>
                                            <%--                                            </th>--%>
                                            <th>stt</th>
                                            <th>Tên vai trò</th>
                                            <th>Mô tả</th>
                                            <th>Các quyền</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${role}" var="role" varStatus="itr">
                                            <tr class="tr-shadow">
                                                    <%--                                                <td>--%>
                                                    <%--                                                    <label class="au-checkbox">--%>
                                                    <%--                                                        <input type="checkbox">--%>
                                                    <%--                                                        <span class="au-checkmark"></span>--%>
                                                    <%--                                                    </label>--%>
                                                    <%--                                                </td>--%>
                                                <td>${itr.index +1}</td>
                                                <td>${role.name}</td>
                                                <td>${role.description}</td>
                                                <td><c:forEach items="${role.permission}" var="permission">
                                                    <p>${permission.name} - ${permission.description}</p>
                                                </c:forEach></td>
                                                <td>
                                                    <div class="table-data-feature">
                                                        <c:forEach items="${currentRole}" var="currentRole">
                                                            <c:if test="${currentRole == 'UPDATE_ROLE'}">
                                                                <a href="<c:url value="/admin/role/update/${role.id}"/> ">
                                                                    <i class="item" data-toggle="tooltip"
                                                                       data-placement="top"
                                                                       title="Edit">
                                                                        <i class="zmdi zmdi-edit"></i>
                                                                    </i>
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${currentRole == 'DELETE_ROLE'}">
                                                                <a onclick="return confirm('Bạn có muốn xóa không?');"
                                                                   href="<c:url value="/admin/role/delete/${role.id}"/> ">
                                                                    <i name="delete" class="item"
                                                                       data-toggle="tooltip"
                                                                       data-placement="top" title="Delete">
                                                                        <i class="zmdi zmdi-delete"></i>
                                                                    </i>
                                                                </a>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="spacer"></tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <ul class="pagination" id="pagination"></ul>
                                <input type="hidden" value="" id="page" name="page"/>
                            </form>
                            <!-- END DATA TABLE -->
                        </div>
                    </div>

                    <%@ include file="/common/admin/footer.jsp" %>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<%@ include file="/common/admin/js.jsp" %>
<script type="text/javascript">
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    $(function () {
        var obj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 3,
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

</body>

</html>
<!-- end document-->
