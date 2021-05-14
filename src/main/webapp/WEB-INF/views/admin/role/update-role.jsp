<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Cập nhật vai trò</title>
    <%@ include file="/common/admin/css.jsp" %>
    <link href="<c:url value="/templates/admin/css/tree.css"/>" rel="stylesheet" type="text/css">

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
                                    <label class="col-lg-2 col-form-label">Tên vai trò <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" value="${role.name}" id="name"
                                               name="name">
                                        <form:errors path="name" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-lg-2 col-form-label">Mô tả <span
                                            class="text-danger">*</span>
                                    </label>
                                    <div class="col-lg-10">
                                        <input class="form-control" value="${role.description}" name="description"
                                               id="description">
                                        <form:errors path="description" cssStyle="color: red"></form:errors>
                                    </div>
                                </div>
                                <br>
                                <label>Quyền(có thể chọn nhiều) <span
                                        class="text-danger">*</span>
                                </label>

                                <div class="tree_main">
                                    <ul id="bs_main" class="main_ul">
                                        <myTags:node-update list="${permission}" permissionNames="${permissionNames}"/>

                                    </ul>
                                </div>

                                <div class="form-group row">
                                    <div class="col-lg-8 ml-auto">
                                        <button type="submit" class="btn btn-primary" id="btnUpdate">Cập nhật</button>
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
    $('#btnUpdate').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        var checkboxValues = [];
        $('input[name=permissionIds]:checked').map(function () {
            checkboxValues.push($(this).val());
        });
        data["permissionIds"] = checkboxValues;
        updateUser(data);
    });

    function updateUser(data) {
        $.ajax({
            url: '/api/admin/role/${role.id}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function () {
                window.location.href = "/admin/role?message=update_success";
            },
            error: function () {
                window.location.href = "/admin/role?message=update_error";
            }
        });
    }


    /*tree*/
    $(document).ready(function () {
        $(".plus").click(function () {
            $(this).toggleClass("minus").siblings("ul").toggle();
        })

        $("input[type=checkbox]").click(function () {
            //alert($(this).attr("id"));
            //var sp = $(this).attr("id");
            //if (sp.substring(0, 4) === "c_bs" || sp.substring(0, 4) === "c_bf") {
            $(this).siblings("ul").find("input[type=checkbox]").prop('checked', $(this).prop('checked'));
            //}
        })

        $("input[type=checkbox]").change(function () {
            var sp = $(this).attr("id");
            if (sp.substring(0, 4) === "c_io") {
                var ff = $(this).parents("ul[id^=bf_l]").attr("id");
                if ($('#' + ff + ' > li input[type=checkbox]:checked').length == $('#' + ff + ' > li input[type=checkbox]').length) {
                    $('#' + ff).siblings("input[type=checkbox]").prop('checked', true);
                    check_fst_lvl(ff);
                }
                else {
                    $('#' + ff).siblings("input[type=checkbox]").prop('checked', false);
                    check_fst_lvl(ff);
                }
            }

            if (sp.substring(0, 4) === "c_bf") {
                var ss = $(this).parents("ul[id^=bs_l]").attr("id");
                if ($('#' + ss + ' > li input[type=checkbox]:checked').length == $('#' + ss + ' > li input[type=checkbox]').length) {
                    $('#' + ss).siblings("input[type=checkbox]").prop('checked', true);
                    check_fst_lvl(ss);
                }
                else {
                    $('#' + ss).siblings("input[type=checkbox]").prop('checked', false);
                    check_fst_lvl(ss);
                }
            }
        });

    })

    function check_fst_lvl(dd) {
        //var ss = $('#' + dd).parents("ul[id^=bs_l]").attr("id");
        var ss = $('#' + dd).parent().closest("ul").attr("id");
        if ($('#' + ss + ' > li input[type=checkbox]:checked').length == $('#' + ss + ' > li input[type=checkbox]').length) {
            //$('#' + ss).siblings("input[id^=c_bs]").prop('checked', true);
            $('#' + ss).siblings("input[type=checkbox]").prop('checked', true);
        }
        else {
            //$('#' + ss).siblings("input[id^=c_bs]").prop('checked', false);
            $('#' + ss).siblings("input[type=checkbox]").prop('checked', false);
        }

    }

    function pageLoad() {
        $(".plus").click(function () {
            $(this).toggleClass("minus").siblings("ul").toggle();
        })
    }

</script>
</body>

</html>
