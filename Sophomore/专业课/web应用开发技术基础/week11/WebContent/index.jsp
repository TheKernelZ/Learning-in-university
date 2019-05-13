<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>

	<%
		try{
			String msg = (String) request.getAttribute("msg");
			if(msg != null) {
				out.print("<script>alert('" + msg + "')</script>");
			}
		} catch (Exception e) {
			
		}
	%>

	<form action="doDatabase" method="POST">
		Table Name: <input type="text" name="table" required /><br /> <br /> Filed1:
		<input type="text" name="filed1" required /> 
		<select name="type1" required>
			<option value="int">Int</option>
			<option value="varchar(255)">Varchar</option>
			<option value="tinyint(1)">Boolean</option>
		</select> <br />
		<br /> Filed2: <input type="text" name="filed2" /> <select
			name="type2">
			<option value="int">Int</option>
			<option value="varchar(255)">Varchar</option>
			<option value="tinyint(1)">Boolean</option>
		</select> <br />
		<br /> Filed3: <input type="text" name="filed3" /> <select
			name="type3">
			<option value="int">Int</option>
			<option value="varchar(255)">Varchar</option>
			<option value="tinyint(1)">Boolean</option>
		</select> <br />
		<br /> Filed4: <input type="text" name="filed4" /> <select
			name="type4">
			<option value="int">Int</option>
			<option value="varchar(255)">Varchar</option>
			<option value="tinyint(1)">Boolean</option>
		</select> <br />
		<br /> Filed5: <input type="text" name="filed5" /> <select
			name="type5">
			<option value="int">Int</option>
			<option value="varchar(255)">Varchar</option>
			<option value="tinyint(1)">Boolean</option>
		</select>
		<br />
		<br />
		<input type="submit" value="submit" />
		<input type="reset" value="reset" />
	</form>
</body>
</html>