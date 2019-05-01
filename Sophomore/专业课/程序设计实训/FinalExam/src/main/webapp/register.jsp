<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='resources/bootstrap/js/bootstrap.min.js'>
<link rel='stylesheet' type="text/css" href='resources/css/index.css'>
<title>Register</title>
</head>
<body>

	<%
		try{
			String msg = (String) request.getAttribute("msg");
			if(msg != null) {
				out.print("<script>alert('" + msg + "')</script>");
			}
		} catch (Exception e) {
			
		}
	%>

	<div class="container">
	    <div class="row">
	        <div class="col-md-offset-3 col-md-6">
	            <form class="form-horizontal" action="doRegister" method="POST">
	                <span class="heading">用户注册</span>
	                <div class="form-group">
	                    	<input type="text" class="form-control" id="inputEmail3" placeholder="用户名" name="username" />
	                		<i class="fa fa-user"></i>
	                </div>
	                <div class="form-group help">
	                    	<input type="password" class="form-control" id="inputPassword3" placeholder="密　码" name="password" />
	                		<i class="fa fa-lock"></i>
	                    	<a href="#" class="fa fa-question-circle"></a>
	                </div>
	                <div class="form-group help">
	                    	<input type="password" class="form-control" id="inputPassword3" placeholder="确认密码" name="again_password" />
	                		<i class="fa fa-lock"></i>
	                    	<a href="#" class="fa fa-question-circle"></a>
	                </div>
	                <div class="form-group">
	                    <button type="submit" class="btn btn-default">注册</button>
	                </div>
	            </form>
	            
	        </div>
	    </div>
	</div>

</body>
</html>