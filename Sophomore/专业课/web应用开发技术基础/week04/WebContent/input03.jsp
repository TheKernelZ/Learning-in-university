<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 编写jsp页面实现，计算1-1/3+1/5+…-1/n的值。
		要求在input03.jsp页面输入n的具体值，然后在compute_sum03.jsp页面计算并显示结果。 -->
	<form action="compute03.do" method="get">
		n: <input type="text" name="n" />
		<input type="submit" value="compute" />
	</form>
	
	<%
		try {
			Double result = (Double) request.getAttribute("result");
			String print = (String) request.getAttribute("print");
			
			if(print != null && result != null)
				out.print(print + result);
		} catch (Exception e) {
			
		}
	%>

</body>
</html>