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
<title>Query Orders Page</title>
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm"
		style="background-image: url(images/background.jpg);background-opacity: 0.1">
  <h4 class="my-0 mr-md-auto font-weight-bold" style="text-shadow:12px 8px 5px #7AB8CC";>Before&After Company</h4>
</div>
<div style="margin-left:5%">
	<h3>Your Orders</h3>
	BnaMember ID : ${himember.username}<br>
	Addressee's name : ${himember.name}<br>
	Tel : ${himember.phone}<br>
	Address : ${himember.address}
	<table border="1" width="70%" class="align-items-center">
      <tr>    
      <th>OrderNo.</th>
      <th>UserId</th>
      <th>OrderDate</th>
      <th>OrderSum</th>
      <th>OrderDetail</th>
      </tr>
        <c:forEach  var="current"  items="${order}" >
        <tr>
          <td><c:out value="${current.orderno}" /></td>
          <td><c:out value="${current.userid}" /></td>
          <td><c:out value="${current.date}" /></td>
          <td><c:out value="${current.ordersum}" /></td>
          <td><a href="${pageContext.request.contextPath}/check/${current.orderno}" style="margin-left:5%">Check</a></td>
        </tr>
      </c:forEach>
    </table>
    </div>
    <br>
	<a href="${pageContext.request.contextPath }/product" style="margin-left:5%">Back to home page</a>
	<!-- 回到選購畫面 -->
</body>
</html>