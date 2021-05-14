<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Thêm quyền</title>
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
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <form:form class="form-valide" id="formSubmit">
                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Tên quyền <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="name" name="name">
                                        <form:errors path="name" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Mô tả <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <input class="form-control" name="description" id="description">
                                        <form:errors path="description" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Nhóm quyền (quyền mới sẽ thuộc nhóm quyền này)<span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <select name="parentId" style="width: 100%">
                                            <option disabled selected>Mời chọn...</option>
                                            <c:forEach items="${permission}" var="permission">
                                                <option value="${permission.id}">
                                                        ${permission.name} - ${permission.description}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="col-lg-8 ml-auto">
                                        <button type="submit" class="btn btn-primary" id="btnCreate">Thêm</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>

                    <%@ include file="/common/admin/footer.jsp" %>
                </div>
            </div>
        </div>
        <!-- MAIN CONTENT-->

        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<%@ include file="/common/admin/js.jsp" %>
<script>
    $('#btnCreate').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        createPermission(data);
    });

    function createPermission(data) {
        $.ajax({
            url: '/api/admin/permission',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function () {
                window.location.href = "/admin/permission?message=insert_success";
            },
            error: function () {
                window.location.href = "/admin/permission?message=insert_error";
            }
        });
    }


</script>
</body>

</html>
