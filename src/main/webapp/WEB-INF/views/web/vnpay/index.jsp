<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="post" action="/vnpay">
    <input hidden type="text" value="" name="total" />
    <button hidden id="pay" type="submit"> Payment with VNPAY </button>
</form>
<script>
    var totalPrice = sessionStorage.getItem("totalPrice");
    document.querySelector('input[name="total"]').value = totalPrice;
    document.getElementById('pay').dispatchEvent(new MouseEvent("click"));
</script>
</body>
</html>