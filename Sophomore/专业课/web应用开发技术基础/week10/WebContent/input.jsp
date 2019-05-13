<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Input</title>
</head>
<body>

	<form action="doInput" method="POST">
		<table style="border: solid 1px;">
			<tr>
				<th>学生编号</th>
				<th>语文成绩</th>
				<th>数学成绩</th>
				<th>外语成绩</th>
			</tr>
			<%
				for(int i = 0; i < 9; i++) {
			%>
				<tr>
					<td><input type="text" name="id<%=i %>" value="<%=i%>" readonly/></td>
					<td><input type="text" name="chinese<%=i %>" /></td>
					<td><input type="text" name="math<%=i %>" /></td>
					<td><input type="text" name="english<%=i %>" /></td>
				</tr>
			<%
			}
			%>
		</table>
		sort By:<select name="select">
			<option value="">Id</option>
			<option value="chinese">Chinese</option>
			<option value="math">Math</option>
			<option value="english">English</option>
		</select>
		<input type="submit" value="submit" />
		<input type="reset" value="reset" />
	</form>

</body>
</html>