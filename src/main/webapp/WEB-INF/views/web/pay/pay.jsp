<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 3/26/2021
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form method="post" action="/pay">
    <input hidden type="text" value="" name="totalPrice" />
    <button hidden id="pay" type="submit"> Payment with Paypal </button>
</form>
<script>
    var totalPrice = sessionStorage.getItem("totalPrice");
    document.querySelector('input[name="totalPrice"]').value = totalPrice;
    document.getElementById('pay').dispatchEvent(new MouseEvent("click"));
</script>
</body>
</html>
