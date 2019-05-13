<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Compute Result</title>
</head>
<body>

	<%
		Double result = (Double) request.getAttribute("result");
	 
		out.print(result);
	%>

</body>
</html>