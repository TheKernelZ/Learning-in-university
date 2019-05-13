<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// request.setCharacterEncoding("utf-8");  // POST
		// String username = request.getParameter("username");  // POST
	
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		
		if(username.equals("凤姐")) {
			response.sendRedirect("success04.jsp");
		} else {
			response.sendRedirect("failure04.jsp");
		}
	%>

</body>
</html>