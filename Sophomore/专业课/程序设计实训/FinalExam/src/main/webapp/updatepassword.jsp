<%@page import="com.thekernel.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='resources/bootstrap/js/bootstrap.min.js'>
<link rel='stylesheet' type="text/css" href='resources/css/index.css'>
<title>Change Password</title>
</head>
<body>

	<%
		String username = (String) session.getAttribute("username");
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
	            <form class="form-horizontal" action="doUpdatePassword" method="POST">
	                <span class="heading">修改密码</span>
	                <div class="form-group">
	                    	<input type="text" class="form-control" placeholder="用户名" name="username" value="<%=username%>" readonly />
	                		<i class="fa fa-user"></i>
	                </div>
	                <div class="form-group help">
	                    	<input type="password" class="form-control" placeholder="请输入原密码" name="password" />
	                		<i class="fa fa-lock"></i>
	                </div>
	                <div class="form-group help">
	                    	<input type="password" class="form-control" placeholder="请输入新密码" name="new_password" />
	                		<i class="fa fa-lock"></i>
	                </div>
	                <div class="form-group help">
	                    	<input type="password" class="form-control" placeholder="确认新密码" name="again_new_password" />
	                		<i class="fa fa-lock"></i>
	                </div>
	                <div class="form-group">
	                    <button type="submit" class="btn btn-default">修改密码</button>
	                </div>
	            </form>
	            
	        </div>
	    </div>
	</div>

</body>
</html>