<%@page import="com.thekernel.dao.IFileDao"%>
<%@page import="com.thekernel.dao.impl.FileDaoImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.thekernel.entity.FileInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>

	<%
		String username = "";
		username = (String) session.getAttribute("username");
		
		
		if (username == null) {
			request.setAttribute("msg", "用户未登陆");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	%>
	
	<form action="doInsertFile">
		<input type="text" name="path" /><br />
		<input type="submit" value="submit" />
		<input type="reset" value="reset" />
	</form>
	<a href="doExit">退出</a>
	
	<%
		IFileDao dao = new FileDaoImpl();
		List<FileInfo> files = dao.selectAll();
		
		if(files != null) {
			out.print("<h1 style='align: center'>Index</h1>");
			out.print("<table border='1px'>");
			out.print("<tr><th>文件编号</th><th>文件名</th><th>文件类型</th><th>文件大小</th><th>创建者Id</th><th>添加时间</th>");
			for(FileInfo file : files) {
				out.print("<tr><td>" + file.getFid() + "</td>" + "<td>" + file.getFname() + "</td>" + "<td>" + file.getFcategory() + "</td>" + "<td>" + file.getFsize() + "</td>" + "<td>" + file.getFuid() + "</td>" + "<td>" + file.getFtime() + "</td>" + "</tr>");
			}
			out.print("</table>");
		}

	%>

</body>
</html>