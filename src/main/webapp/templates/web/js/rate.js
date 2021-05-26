jQuery(function ($) {
    $(document).ready(function () {

        getListRate();

        function getListRate() {
            var data = {};
            data['productId'] = parseInt(document.querySelector('input[name="productid"]').value);
            $.ajax({
                type: "POST",
                url: "/api/rate/list",
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (serviceResult) {
                    console.log(serviceResult);
                    loadData(serviceResult);
                }, error: function () {
                    alert("lỗi hiển thị đánh giá")
                }
            })
        }


        function loadData(data) {
            var row = "";
            $.each(data, function (i, v) {
                var date = new Date(v.createdAt);
                var timeCreated = date.getDate() + "/" + (date.getMonth() + 1) + "/" + date.getFullYear();
                row += '<span><b style="font-size: 20px;">'+v.createdBy+'  </b>'+timeCreated+'</span>';
                row += '<div class="ratings">';
                var loop = 0;
                for (loop = 0; loop < v.star; loop++){
                    row += '<i class="fa fa-star" aria-hidden="true"></i>';
                }


                row += '</div>';
                row += '<p>'+v.content+'</p>';
            });

            $('#list-rate').append(row);
        }


        $('#post').click(function () {
            var data = {};
            var star = $("input[name=rating]:checked").val();
            var content = $('#content').val();
            data['star'] = star;
            data['content'] = content;
            data['productId'] = parseInt(document.querySelector('input[name="productid"]').value);
            data['userId'] = parseInt(document.querySelector('input[name="userId"]').value);
            console.log(data);
            if (data.star != null && data.content != "") {
                $.ajax({
                    type: "POST",
                    url: "/api/rate",
                    data: JSON.stringify(data),
                    dataType: "JSON",
                    contentType: "application/json",
                    success: function (response) {
                        alert("Gửi đánh giá thành công");
                        console.log(response);
                        location.reload();
                    },
                    error: function (response) {
                        if($('#userId').val() == '')
                            alert("Bạn cần đăng nhập để viết đánh giá");
                        else alert("Bạn cần mua sản phẩm để đánh giá");
                        console.log(response);
                    },
                });
            }
            else{
                if (data.star == null)
                    alert("Chưa chọn số sao");
                if (data.content == "")
                    alert("Chưa viết đánh giá");
            }
        });
    })
})