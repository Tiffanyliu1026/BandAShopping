<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
   body {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 10%;
  padding-left: 40%;
  padding-right: 40%;
  background-image: url(images/background1.jpg);
  background-opacity: 0.5;
  background-origin: content-box;
  
  }
  
  .form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
  }
  .form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
  }
  .sr-only{
  font-weight: bold;
  }
  button{
  	width:290px;
	height: 50px;
	background-color: #008CBA;
	font-weight: bold;
  }
  h1{
  text-align: center;
  }
  h2{
  text-align: center;
  }
</style>
</head>
<body>
<div>
  <h1 style="text-shadow:12px 8px 5px #7AB8CC";>Before & After</h1>
  <h2 class="h3 mb-3 font-weight-normal">Please login</h2>

<form class="form-signin" onsubmit="return check();">
  <label for="inputUsername" class="sr-only">Username：</label>
  <input type="text" id="username" class="form-control" placeholder="Username" required autofocus>
  <p>
  <label for="inputPassword" class="sr-only">Password：</label>
  <input type="password" id="password" class="form-control" placeholder="Password" required>
  <div class="checkbox mb-3">
    <label style="font-weight:600">
      <input type="checkbox" value="remember-me"/> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button><p><p>
</form>
	<div style="font-weight:bold;text-align:center">
  	Create a new account! <p>Please <a href="${pageContext.request.contextPath }/register">Click here!</a>
  	<p class="text-muted" style="text-align: center;">&copy; 2021-2022</p>
  	</div>
</div>
<script type="text/javascript">
	function check(){
	$.ajax({
	        url: "check", //LoginController Mapping
	        data:{"username":$("#username").val(),"password":$("#password").val()},
	        //獲取表單收到的資料 >>JSON資料格式
	        cache:false ,
	        type: "GET",
	        dataType: 'text',
	        success: function (msg) {
		        	alert(msg);
		        	location.href="${pageContext.request.contextPath }/product";
	        	},
	        error: function (xhr, ajaxOptions, thrownError) {
	            alert(xhr.status);
	            alert(thrownError);
	        	}
	   		 });
	   		 return false;
	}
	
</script>
</body>
</html>