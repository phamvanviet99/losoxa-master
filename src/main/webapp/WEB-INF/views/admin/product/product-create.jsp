<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Thêm sản phẩm</title>
    <%@ include file="/common/admin/css.jsp" %>
    <script src="<c:url value='/ckeditor/ckeditor.js'/>"></script>
    <script src="<c:url value='/ckfinder/ckfinder.js'/>"></script>

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
                            <form:form class="form-valide" id="formSubmit" action="/admin/product/create" modelAttribute="productRequest">
                                <div class="form-group row">
                                    <label class="col-sm-3 control-label no-padding-right">Hình ảnh
                                        <span class="text-danger">*</span></label>
                                    <div class="col-sm-12">
                                        <div class="avatar">
                                            <img id="productImage"
                                                 src="https://yt3.ggpht.com/-f6NCDKG2Ukw/AAAAAAAAAAI/AAAAAAAAAAA/MqMm3rgmqCY/s48-c-k-no-mo-rj-c0xffffff/photo.jpg"
                                                 class="img-fluid" style="max-width: 300px; max-height: 300px;"/>
                                        </div>
                                        <div class="file-field">
                                            <p>
                                                <strong id="Ithumbnail">Chọn ảnh</strong><br/>
                                                <button
                                                        class="btn btn-primary btn-sm waves-effect waves-light"
                                                        type="button" value="Browse Image"
                                                        onclick="BrowseServer( 'Images:/', 'Ithumbnail' );">Browse Image
                                                </button>
                                            </p>
                                            <input type="hidden" name="productImage" id="image_src"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Thể loại <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <select name="categoryId">
                                            <option disabled selected>Mời chọn...</option>
                                            <c:forEach items="${categories}" var="category">
                                                <option value="${category.id}" >
                                                    ${category.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <form:errors path="categoryId" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Tên sản phẩm <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" name="name"
                                               placeholder="Nhập tên sản phẩm.." value="">
                                        <form:errors path="name" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Đơn giá (VNĐ) <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" name="unitPrice"
                                               placeholder="Nhập đơn giá.." value="">
                                        <form:errors path="unitPrice" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Số lượng <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" name="quantity"
                                               placeholder="Nhập số lượng.." value="">
                                        <form:errors path="quantity" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label">Mô tả sản phẩm
                                    </label>
                                    <div class="col-lg-6">
                                        <textarea class="form-control" name="description" id="description" type="text"
                                                  placeholder="Nhập mô tả.."></textarea>
                                        <form:errors path="description" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-lg-8 ml-auto">
                                        <button type="submit" class="btn btn-primary">Thêm</button>
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
    var editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('description',
            {
                filebrowserBrowseUrl: '/ckfinder/ckfinder.html',
                filebrowserImageBrowseUrl: '/ckfinder/ckfinder.html?type=Images',
                filebrowserFlashBrowseUrl: '/ckfinder/ckfinder.html?type=Flash',
                filebrowserUploadUrl: '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
                filebrowserImageUploadUrl: '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
                filebrowserFlashUploadUrl: '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
            });
    });

    /*Avatar start*/
    function BrowseServer(startupPath, functionData) {
        // You can use the "CKFinder" class to render CKFinder in a page:
        var finder = new CKFinder();

        // The path for the installation of CKFinder (default = "/ckfinder/").
        finder.basePath = '../';

        //Startup path in a form: "Type:/path/to/directory/"
        finder.startupPath = startupPath;

        // Name of a function which is called when a file is selected in CKFinder.
        finder.selectActionFunction = SetFileField;

        // Additional data to be passed to the selectActionFunction in a second argument.
        // We'll use this feature to pass the Id of a field that will be updated.
        finder.selectActionData = functionData;

        // Name of a function which is called when a thumbnail is selected in CKFinder. Preview img
        // finder.selectThumbnailActionFunction = ShowThumbnails;

        // Launch CKFinder
        finder.popup();
    }

    function SetFileField(fileUrl, data) {
        document.getElementById(data["selectActionData"]).innerHTML = this
            .getSelectedFile().name;
        document.getElementById("productImage").src = fileUrl;
        $('#productImage').val(fileUrl);
        $('#image_src').val(fileUrl);
    }

    //call api
    $('#btnCreate').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();
        createNews(data);
    });
    function createNews(data) {
        $.ajax({
            url: '/admin/api/product',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function () {
                window.location.href = "/admin/list-product?message=insert_success";
            }
        });
    }
</script>

</body>

</html>
<!-- end document-->
