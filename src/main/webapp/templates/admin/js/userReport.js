jQuery(function ($) {
    $(document).ready(function () {
        var url = "";

        getAllReport(url);

        $('#class_see_report_online').click(function () {
            var data = getDataForm();
            console.log(data);
            if (data.startDate != '-01' && data.endDate != '-20') {

                $.ajax({
                    url: '/api/admin/report/user/loyal-customer',
                    type: 'POST',
                    data: JSON.stringify(data),
                    dataType: 'json',
                    contentType: "application/json",
                    beforeSend: function () {
                        $('.loader').css("display", "block");
                    },
                    success: function (res) {
                        $('.loader').css("display", "none");
                        console.log(res);
                        if (res.code == '200') {
                            if (res.data == "false") {
                                alert("Không có thông tin export")
                            } else {
                                alert("Export thành công")
                                location.reload();
                            }
                        } else {
                            alert("Export thất bại")
                        }

                    },
                    error: function (e) {
                        console.log(e);
                    }
                })
            } else {
                alert("Chưa nhập thời gian bắt đầu hoặc kết thúc")
            }
        });


        function getAllReport(url) {
            if (url == "") {
                url = "/api/admin/report/user/list-loyal-customer";
            }
            var data = {};

            $.ajax({
                url: url,
                type: 'POST',
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json",
                beforeSend: function () {
                    $('.loader').css("display", "block");
                    $('#pagination-test').empty();
                    $('#pagination-test').removeData("twbs-pagination");
                    $('#pagination-test').unbind("page");
                },
                success: function (res) {
                    $('.loader').css("display", "none");
                    if (res.totalPage != 0) {
                        paging(res.totalPage, res.currentPage);
                    }
                    console.log(res);
                    showReport(res.data, res.currentPage);
                },
                error: function (e) {
                    console.log(e);
                }
            })
        }

        function showReport(data, currentPage) {
            var link = window.location.origin;
            var s = '';
            if (data.length === 0) {
                var s1 = `<div class="alert alert-warning text-center w-100 mt-3" style="color: #f6821f;  background-color: #fff3cd; border-color: #ffeeba; margin-top: 200px;">
             <i class="fa fa-exclamation-triangle"></i> Không tìm thấy báo cáo nào ! 
             <a style="color: #f6821f; cursor: pointer" href="/admin/report/list">Quay lại</a>`;
                s += `   <tr style="background-color: white">
                <td colspan="100">` + s1 + `</td>
                </tr> `;
                $("#data-list").html(s1);
            }
            $.each(data, function (i, v) {
                var date = new Date(v.createdDate);
                s += '<tr>';
                s += '<td>' + (((parseInt(currentPage) - 1) * 10) + (parseInt(i) + 1)) + '</td>'
                    + '<td>'
                    + '<a href="https://view.officeapps.live.com/op/embed.aspx?src=' + link + v.path + '">' + v.nameFile + '</a>'
                    + '</td>'
                    + '<td>'
                    + '<span>' + date.toLocaleString('vi') + '</span>'
                    + '</td>'
                    + '<td>'
                    + '<span>' + v.createdBy + '</span>'
                    + '</td>'
                    + '<td>'
                    + '<a href="' + v.url + '" style="font-size: 20px"><i class="fa fa-download" aria-hidden="true"></i></a>'
                    + '<a class="ml-3 " onclick="deleteExcel(this)" data-id="' + v.id + '" style="font-size: 20px"><i class="fa fa-trash" aria-hidden="true"></i></i></a>'
                    + '</td>';
                s += '</tr>';
            });
            $('#data-list').html(s);
        }


        function paging(totalPage, currentPage) {

            $('#pagination-test').twbsPagination({
                totalPages: totalPage,
                startPage: currentPage,
                visiblePages: 10,
                last: 'Cuối cùng',
                next: 'Tiếp theo',
                first: 'Đầu tiên',
                prev: 'Phía trước',
                onPageClick: function (event, page) {
                    if (currentPage != page) {

                        currentPage = page;
                        var url = "/api/admin/report/user/list-loyal-customer";
                        url += "?page=" + page;
                        getAllReport(url);
                    }
                }
            });
        }


        function getDataForm() {
            var data = {};
            var formSearch = $('#formSubmit').serializeArray();

            $.each(formSearch, function (i, v) {
                if (v.name == 'startDate') {
                    var value = $('#startDate').val();
                    data['startDate'] = value;
                }

                if (v.name == 'endDate') {
                    var value = $('#endDate').val();
                    data['endDate'] = value;
                }

            });
            console.log(data);
            return data;
        }
    });

});

function deleteExcel(btn) {
    var id = $(btn).attr("data-id");
    $.ajax({
        url: '/api/admin/report/user/delete-loyal/' + id,
        type: 'DELETE',
        contentType: "application/json",
        beforeSend: function () {
            $('.loader').css("display", "block");
        },
        success: function (res) {
            $('.loader').css("display", "none");
            if (res.data == true) {
                alert("Xóa báo cáo thành công")
                location.reload();
            } else {
                alert("Xóa thất bại")
            }
        },
        error: function (e) {
            console.log(e);
        }
    })
}