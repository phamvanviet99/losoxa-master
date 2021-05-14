<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Thêm bài viết</title>
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
                            <form:form class="form-valide" id="formSubmit" modelAttribute="blogRequest">
                                <div class="form-group row">
                                    <label class="col-sm-3 control-label no-padding-right">Hình đại diện
                                        <span class="text-danger">*</span></label>
                                    <div class="col-sm-12">
                                        <div class="avatar">
                                            <img id="thumbnail"
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
                                            <input type="hidden" name="thumbnail" id="image_src"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Tiêu đề <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <textarea type="text" class="form-control" id="title" name="title"></textarea>
                                        <form:errors path="title" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Mô tả ngắn <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <textarea class="form-control" name="shortDescription"
                                                  id="shortDescription"></textarea>
                                        <form:errors path="shortDescription" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Nội dung <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <textarea type="text" id="content" name="content"></textarea>
                                        <form:errors path="content" cssStyle="color: red"></form:errors>
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
    var editor = '';
    $(document).ready(function () {
        editor = CKEDITOR.replace('content',
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
        document.getElementById("thumbnail").src = fileUrl;
        $('#thumbnail').val(fileUrl);
        $('#image_src').val(fileUrl);
    }

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
            url: '/api/admin/blog',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function () {
                window.location.href = "/admin/blog?message=insert_success";
            },
            error: function () {
                window.location.href = "/admin/blog?message=insert_error";
            }
        });
    }

</script>
</body>

</html>
<!-- end document-->
