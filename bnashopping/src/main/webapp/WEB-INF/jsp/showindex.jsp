<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Page</title>
</head>
<body>

	<h3>Products Page</h3>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Photo</th>
			<th>Price</th>
			<th>Buy</th>
		</tr>
		<c:forEach var="product" items="${products }"><!-- ProductCartController的modelMap -->
			<tr>
				<td>${product.pid }</td>
				<td>${product.pname }</td>
				<td><img src="${pageContext.request.contextPath }/${product.pimage }" width="150"></td>
				<!-- {pageContext.request.contextPath}是jsp中的EL語法 >> 抓網址路徑 -->
				<td>${product.price }</td>
				<td align="center">
					<a href="${pageContext.request.contextPath }/cart/buy/${product.pid}">Buy Now</a>
					<!-- 連接到Mapping >> cart/buy -->
					<!-- 當游標停留在Buy Now文字上會顯示完整路徑 ex.http..shopping1103/cart/buy/p01 -->
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
