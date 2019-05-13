<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<%
		String msg = "";

		msg = (String) request.getAttribute("msg");

		
		if (msg != null) {
			out.print("<script>alert('" + msg + "')</script>");
		}
	%>

	<form action="doLogin" method="POST">
		username: <input type="text" name="username" /><br />
		password: <input type="password" name="password" /><br />
		<input type="submit" value="submit" />
		<input type="reset" value="reset" />
	</form>

</body>
</html>