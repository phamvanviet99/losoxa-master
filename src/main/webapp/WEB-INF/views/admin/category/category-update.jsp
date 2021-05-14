<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Cập nhật thể loại</title>
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
                            <form:form class="form-valide" action="/admin/category/update/${id}" method="POST"
                                       modelAttribute="categoryRequest">
                                <div class="form-group row">
                                    <label class="col-sm-3 control-label no-padding-right">Hình ảnh
                                        <span class="text-danger">*</span></label>
                                    <div class="col-sm-12">
                                        <div class="avatar">
                                            <img id="categoryImage"
                                                 src="${category.categoryImage}"
                                                 class="img-fluid" style="max-width: 300px; max-height: 300px;"/>
                                        </div>
                                        <div class="file-field">
                                            <p>
                                                <strong id="Ithumbnail">${category.categoryImage.split("/")[7]}</strong><br/>
                                                <button
                                                        class="btn btn-primary btn-sm waves-effect waves-light"
                                                        type="button" value="Browse Image"
                                                        onclick="BrowseServer( 'Images:/', 'Ithumbnail' );">Browse Image
                                                </button>
                                            </p>
                                            <input type="hidden" name="categoryImage" id="image_src" value="${category.categoryImage}"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-lg-4 col-form-label" for="val-name">Tên thể loại <span
                                            class="text-danger"></span>
                                    </label>
                                    <div class="col-lg-6">
                                        <input type="text" class="form-control" id="val-name" name="name"
                                               placeholder="Nhập tên thể loại.." value="${category.name}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-lg-8 ml-auto">
                                        <button type="submit" class="btn btn-primary">Cập nhật</button>
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
        document.getElementById("categoryImage").src = fileUrl;
        $('#categoryImage').val(fileUrl);
        $('#image_src').val(fileUrl);
    }
</script>
</body>

</html>
<!-- end document-->
