<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Week03_03</title>
</head>
<body>

	<%
		String msg = (String) request.getAttribute("msg");
		
		if(msg != null && !msg.equals("")) {
			out.print("<script>alert('" + msg+ "')</script>");
		}
	%>

	<!-- 实现一个用户登录的页面，
		在一个页面中输入登录信息，在另外一个页面中显示输入的信息，
		并判断时候与自己预先设定的一致。一致登录成功，否则失败。 -->
	<form action="login.do" method="POST">
		username: <input type="text" name="username" />(root)<br /><br />
		password: <input type="password" name="password" />(root)<br /><br />
		<input type="submit" value="submit" />
		<input type="reset" value="reset" />
	</form>

</body>
</html>