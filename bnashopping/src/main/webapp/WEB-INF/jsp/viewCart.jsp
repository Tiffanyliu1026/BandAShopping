<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Cart Page</title>
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm"
		style="background-image: url(images/background.jpg);background-opacity: 0.1">
  <h4 class="my-0 mr-md-auto font-weight-bold" style="text-shadow:12px 8px 5px #7AB8CC";>Before&After Company</h4>
</div>
	<h3 style="margin-left:5%">Cart Page</h3>
	<table cellpadding="2" cellspacing="2" border="1" style="margin-left:5%">
		<tr>
			<th>Option</th>
			<th>Id</th>
			<th>Name</th>
			<th>Photo</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Sub Total</th>
		</tr>
		<c:set var="total" value="0"></c:set>
		<c:forEach var="item" items="${sessionScope.cart }"><!-- 接收session的物件變數cart(arrayList) -->
			<c:set var="total"
				value="${total + item.product.price * item.quantity }"></c:set>
			<tr>
				<td align="center"><a
					href="${pageContext.request.contextPath }/cart/remove/${item.product.pid }"
					onclick="return confirm('Are you sure?')">Remove</a></td>
					<!-- /cart/remove/為cartController的Mapping路徑 -->
					<!-- ${pageContext.request.contextPath }擷取目前網址的根目錄路徑再接到Mapping網址remove處理動作 -->
					<!-- onclick="return confirm('Are you sure?')"為javascript語法 >> 彈跳視窗詢問 -->
				<td>${item.product.pid }</td>
				<td>${item.product.pname }</td>
				<td><img src="${pageContext.request.contextPath }/${item.product.pimage }"
					width="150"></td>
				<td>${item.product.price }</td>
				<td>${item.quantity }</td>
				<td>${item.product.price * item.quantity }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="right" style="font-weight:bold">Sum</td>
			<td>${total }</td>
		</tr>
	</table>
	<br>
	<a href="${pageContext.request.contextPath }/product" style="margin-left:5%">Continue  Shopping</a>
	<!-- 回到選購畫面 -->
	<br>
	<a href="${pageContext.request.contextPath }/order" style="margin-left:5%"
		onclick="return confirm('Place an order?')">checkout</a>

</body>
</html>