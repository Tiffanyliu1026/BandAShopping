<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm"
		style="background-image: url(images/background.jpg);background-opacity: 0.1">
  <h4 class="my-0 mr-md-auto font-weight-bold" style="text-shadow:12px 8px 5px #7AB8CC";>Before&After Company</h4>
  <nav class="my-2 my-md-0 mr-md-3">
    <a class="p-2 text-dark" href="#">Features</a>
    <a class="p-2 text-dark" href="#">Enterprise</a>
    <a class="p-2 text-dark" href="#">Support</a>
    <a class="p-2 text-dark" href="#">Pricing</a>
  </nav>
  <div class="btn btn-primary" id="user">
  Hello ! ${_bnaMembers.name}
  </div>
  &ensp;
  <div id="Member">
   <a href="${pageContext.request.contextPath}/memberpage" class="btn btn-primary">Member</a>
  </div>
  &ensp;
   <div id="Cart">
   <a href="${pageContext.request.contextPath}/cart/index" class="btn btn-warning">Cart</a>
  </div>
  &ensp;
  <div id="QueryOrders">
   <a href="${pageContext.request.contextPath}/orderlist" class="btn btn-warning">QueryOrders</a>
  </div>
  &ensp;
  <div id="Logout">
   <a href="${pageContext.request.contextPath}/logoutpage" class="btn btn-warning"
   		onclick="return alert('Thank you!Please come again~')">Logout</a>
  </div>
</div>
<!-- The Modal -->
  <div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">login your account and password</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <form class="form-signin" onsubmit="return check();">                     
               <label for="userName" class="sr-only">Email address</label>
                <input type="text" id="userName" class="form-control" 
                       		placeholder="User Name" required autofocus><br/>
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" class="form-control" 
                             placeholder="Password" required><br/>
               <div class="checkbox">
                   <label>
                       <input type="checkbox" value="remember-me"> 記住我的登入
                  </label>
               </div>
               <button class="btn  btn-primary btn-block" type="submit">登入</button>  
          </form>             
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>
        
      </div>
    </div>
  </div>
  <input type="hidden" value="${pageContext.request.contextPath}/cart/buy/" id="buypath">
<div class="container">
  <div class="card-deck mb-3 text-center">
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal" id="name1">product name1</h4>
      </div>
      <div class="card-body">
      	<img class="card-img-top" src="BB.jpg" id="img1" alt="Card image" style="width:100%">
        <h2 class="card-title pricing-card-title" id="price1">price<small class="text-muted">/ NTD</small></h2>
        <div id="dscip1" class="list-unstyled mt-3 mb-4" style="height:50px"></div>
        <a href="${pageContext.request.contextPath }/cart/buy/" id="buy1" class="btn btn-lg btn-block btn-primary">Add to basket</a>
        <div id="id1" style="display:none;">
        </div>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal" id="name2">product name2</h4>
      </div>
      <div class="card-body">
      	<img class="card-img-top" src="lipstick.jpg" id="img2" alt="Card image" style="width:100%">
        <h2 class="card-title pricing-card-title" id="price2">price <small class="text-muted">/ NTD</small></h2>
        <div id="dscip2" class="list-unstyled mt-3 mb-4" style="height:50px"></div>
        <a href="${pageContext.request.contextPath}/cart/buy/" id="buy2" class="btn btn-lg btn-block btn-primary">Add to basket</a>
        <div id="id2" style="display:none;"></div>
      </div>
    </div>
    <div class="card mb-4 shadow-sm">
      <div class="card-header">
        <h4 class="my-0 font-weight-normal" id="name3">product name3</h4>
      </div>
      <div class="card-body" style="height:20px">
      	<img class="card-img-top" src="powder.jpg" id="img3" alt="Card image" style="width:100%">
        <h2 class="card-title pricing-card-title" id="price3">price <small class="text-muted">/ NTD</small></h2>
        <div id="dscip3" class="list-unstyled mt-3 mb-4" style="height:50px"></div>
        <a href="${pageContext.request.contextPath}/cart/buy/" id="buy3" class="btn btn-lg btn-block btn-primary">Add to basket</a>
        <div id="id3" style="display:none;"></div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	function check(){
	  $.ajax({
        url: "check", //AccountController Mapping
        data:{"userName":$("#userName").val(),"password":$("#password").val()},
        //獲取表單收到的資料 >>JSON資料格式
        cache:false ,
        type: "GET",
        dataType: 'text',
        success: function (msg) {              	
               alert(msg);
               $('#myModal').modal('hide');
        	},
        error: function (xhr, ajaxOptions, thrownError) {
            alert(xhr.status);
            alert(thrownError);
        	}
   		 });
   		 return false;
	}
	
	function show(arry){   
	       var picPage=0;
	       if(arry.length%3==0)
	        picPage= arry.length/3;
	       else
	        picPage= arry.length/3+1;     
	     $('#page-selection').bootpag({
	          maxVisible: 5,
	          total: picPage
	        }).on("page", function(event,  pgnum){
	           display(arry , pgnum);
	        });
	           display(arry,1);//fubction display的參數 >>陣列跟page number >>初始畫面是第一頁
	      }
	function display(arry,num){
	         
	          var index= (num-1)*3
	    	  $("#img1").attr("src", arry[index].pimage);
	          $("#name1").text(arry[index].pname);
	          $("#dscip1").text(arry[index].pdecript);
	          $("#price1").text(arry[index].price);
	          $("#id1").text(arry[index].pid);
	          $("#img2").attr("src", arry[index+1].pimage);
	          $("#name2").text(arry[index+1].pname);
	          $("#dscip2").text(arry[index+1].pdecript);
	          $("#price2").text(arry[index+1].price);
	          $("#id2").text(arry[index+1].pid);
	          $("#img3").attr("src", arry[index+2].pimage);
	          $("#name3").text(arry[index+2].pname);
	          $("#dscip3").text(arry[index+2].pdecript);
	          $("#price3").text(arry[index+2].price);
	          $("#id3").text(arry[index+2].pid);
	          
	          $("#buy1").attr("href",$("#buypath").val()+arry[index].pid);
	          $("#buy2").attr("href",$("#buypath").val()+arry[index+1].pid);
	          $("#buy3").attr("href",$("#buypath").val()+arry[index+2].pid);
	       }
	function start(){
		$.ajax({
			url:"productdisplay",//=ProductController Mapping
			cache:false,
			type:"GET",
			dataType:"json", //回傳的data資料為json格式 >>陣列物件
			success:function(objArry){ //objArry將陣列物件接收
				show(objArry); //傳給show function
				},
				error:function(xhr,ajaxOptions,thrownError){
					alter(xhr.atatus);
					alter(thrownError);
					}
			});
		}
	$(document).ready(start);
</script>
<script type="text/javascript" src="js/bootpag.min.js"></script>
  <div id="page-selection">Pagination goes here</div>
  <script>
  $('#page-selection').bootpag({
      total: 5
  }).on("page", function(event, /* page number here */ num){
       $("#content").html(); // some ajax content loading...
  });
  </script>
</body>
</html>