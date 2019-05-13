<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input 01</title>
</head>
<body>

	<!-- 编程实现一个计算器，
		从页面input01.jsp接收输入的数据并选择运算符，
		最后点击“=”号按钮通过compute_show01.jsp页面计算及显示运算结果；
		更进一步，改写上面的程序，仅通过input01.jsp页面去接收及显示数据，上面的过程仅通过一个页面完成。 -->
		
	<%
		String msg = (String) request.getAttribute("msg");
		
		if(msg != null && !msg.equals("")) {
			out.print("<script>alert('" + msg+ "')</script>");
		}
	%>
	
	<h1>Calculator</h1>
	<form action="compute.do" method="POST">
		num1: <input type="text" name="num1" />
		&nbsp; 
		<select name="op">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="x">x</option>
			<option value="/">/</option>
			<option value="%">%</option>
		</select>
		&nbsp;
		num2: <input type="text" name="num2" />
		<input type="submit" value="=" />
	</form>

</body>
</html>