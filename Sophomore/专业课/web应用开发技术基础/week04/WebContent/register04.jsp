<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register For Fengjie</title>
</head>
<body>

	<!-- 
		编写一个用户注册页面，在register04.jsp页面完成用户注册信息的录入，
		并在receive_register04.jsp完成用户信息的接收，然后判断下输入的用户名是不是“凤姐”，
		如果是的跳转到success04.jsp也是登录成功；若不是，跳转到failure04.jsp页，显示登录失败。分别以get和post方式提交数据。
	 -->

	<h1>Register</h1>
	<!--
	<form action="receive_register04.jsp" method="POST">
		username: <input type="text" name="username" />
		<br />
		<input type="submit" value="submit" />
	</form>
	-->
	<form action="receive_register04.jsp" method="GET">
		username: <input type="text" name="username" />
		<br />
		<input type="submit" value="submit" />
	</form>

</body>
</html>