<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Week03_02</title>
</head>
<body>
	<!-- 实现一个将 img 中所有图片显示在页面上的程序。更进一步，实现一个随机显示图片的程序（用户每次打开网页显示的图片都可能不一样）。 -->
	<h1>All Images</h1>
	<%
		for(int i = 1; i <= 6; i++) {
			out.print("<img src='./images/ym" + i + ".jpg' />");
		}
	%>
	
	<h1>Random Images</h1>
	<%
		int num = (int)(Math.random()*6+1);
		
		out.print("<img src='./images/ym" + num + ".jpg' alt='The Random Image' />");
	%>

</body>
</html>