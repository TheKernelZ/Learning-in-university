<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam1</title>
</head>
<body>
	<%!
		public boolean daffodils(int n) {
			
			boolean result = false;
			int num = 0;
			int m = n;
			int y = n;
			int sum = 0;
			
			while(m != 0) {
				m /= 10;
				num++;
			}
			
			while(y != 0) {
				sum += Math.pow(y % 10, num);
				y /= 10;
			}
			
			if(sum == n) {
				result = true;
			}
			
			return result;
		
		}
	%>

	<form action="exam1.jsp" method="GET">
		a: <input type="text" name="a"/><br/>
		b: <input type="text" name="b"/><br/>
		<input type="submit" value="Commit"/>
	</form>
	
	<%
		try {
			
			int a = Integer.parseInt(request.getParameter("a"));
			int b = Integer.parseInt(request.getParameter("b"));
			int sum = 0;
			
			out.print("From " + a + " To " + b + " daffodils :");
			for(int i = a; i <= b; i++) {
				if(daffodils(i)) {
					sum += i;
					out.print(i + "&nbsp;");
				}
			}
			out.print("<br/>");
			out.print("Sum Of them is: " + sum);
		}catch(Exception e) {
			
		}
	%>

</body>
</html>