<%@page import="java.util.List"%>
<%@page import="com.thekernel.entity.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>

	<%
		List<Student> students = (ArrayList<Student>)session.getAttribute("students");
	%>
	<table style="border: solid 1px;">
		<tr>
			<th>学生编号</th>
			<th>语文成绩</th>
			<th>数学成绩</th>
			<th>外语成绩</th>
		</tr>
	<%
		for(Student student : students) {	
	%>
		
			<tr>
				<td><input type="text" value="<%=student.getId() %>" /></td>
				<td><input type="text" value="<%=student.getChinese() %>" /></td>
				<td><input type="text" value="<%=student.getMath() %>" /></td>
				<td><input type="text" value="<%=student.getEnglish() %>" /></td>
			</tr>
		
	<%
		}
	%>
	</table>
	

</body>
</html>