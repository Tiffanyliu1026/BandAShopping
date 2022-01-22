<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Member Form</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-image: url(images/background.jpg);background-opacity: 0.1">
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm"
		style="background-image: url(images/background.jpg);background-opacity: 0.1">
  <h4 class="my-0 mr-md-auto font-weight-bold" style="text-shadow:12px 8px 5px #7AB8CC";>Before&After Company</h4>
  </div>
​
<div class="container" style="background-color:white">
  <h2>BnA's member form</h2>
  <p><p>
  
  <form class="was-validated" id="bnamemberform" onsubmit="return sendform();">
  <div class="form-group">
    <label for="username">Username:</label>
    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="text" class="form-control" id="password" placeholder="Enter password" name="password" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  <div class="form-group">
    <label for="name">Name:</label>
    <input type="text" class="form-control" id="name" placeholder="Enter Firstname&Lastname" name="name" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
 <div class="form-group">
    <label for="phone">Phone:</label>
    <input type="text" class="form-control" id="phone" placeholder="Enter phone number" name="phone" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  <div class="form-group">
    <label for="address">Address:</label>
    <input type="text" class="form-control" id="address" placeholder="Enter address" name="address" required>
    <div class="valid-feedback">Valid.</div>
    <div class="invalid-feedback">Please fill out this field.</div>
  </div>
  
  <div class="form-group form-check">
    <label class="form-check-label">
      <input class="form-check-input" type="checkbox" name="remember" required> I agree on the following of purchase.
      <div class="valid-feedback">Valid.</div>
      <div class="invalid-feedback">Check this checkbox to continue.</div>
    </label>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  <button onclick="resetform()" class="btn btn-secondary">Reset</button>
</form>
  <div style="font-weight:bold">
  <a href="${pageContext.request.contextPath }/loginpage">Click here to return to login</a>
  </div>
</div>
<script type="text/javascript">
	function sendform(){
		$.ajax({
			url:"addmember",
			data:{"username":$("#username").val(),"password":$("#password").val(),"name":$("#name").val(),
				"phone":$("#phone").val(),"address":$("#address").val()},
			type: "GET",
			datatype: 'text',
			success: function (msg) {
	               alert(msg);
	               document.getElementById("bnamemberform").reset();
	        	},
	        error: function (xhr, ajaxOptions, thrownError) {
	            alert(xhr.status);
	            alert(thrownError);
	        	}
			});
			return false;
		}
	function resetform(){
		document.getElementById("bnamemberform").reset();
		}
</script>

</body>
</html>