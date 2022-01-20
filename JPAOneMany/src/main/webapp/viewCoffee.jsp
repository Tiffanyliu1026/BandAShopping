<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
		<tr>
			<TH>Coffee Name</th>
			<TH>Coffee Price</th>
			<TH>Supplier ID</th>
			<TH>Supplier Name</th>
		</tr>
		<c:forEach var="current" items="${coffeeList}">
			<tr>
				<td><c:out value="${current.cofName}" /></td>
				<td><c:out value="${current.price}" /></td>
				<td><c:out value="${current.supplier.supId}" /></td>
				<td><c:out value="${current.supplier.supName}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>