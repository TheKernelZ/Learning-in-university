<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WanShu Input</title>
</head>
<body>

	<!-- 编写jsp页面实现，求n之内的所有“完数”，并显示在页面上。
		所谓“完数”是指一个数恰好等于它的所有因子之和。例如，6是完数，因为6=1+2+3。要求在input02.jsp页面输入n的具体值，然后在compute_wansu02.jsp页面计算，
		并返回input02.jsp页面显示结果（输出格式为：n内的完数为XXXX）。 -->
	<form action="wanShu.do" method="GET">
		n: <input type="text" name="n" />
		<input type="submit" value="submit" />
	</form>
	
	<%
		try {
			String result = (String) request.getAttribute("result");
			int n = (Integer) request.getAttribute("n");	
		
			if(result != null && !result.equals("")) {
				out.print("From 1 To " + n + " All WanShu are: ");
				out.print(result);
			}
		} catch (Exception e) {
			
		}
	%>

</body>
</html>