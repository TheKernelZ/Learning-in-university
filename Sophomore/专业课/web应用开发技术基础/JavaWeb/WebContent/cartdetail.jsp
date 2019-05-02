<%@page import="com.thekernel.entity.Goods"%>
<%@page import="com.thekernel.entity.ShoppingCart"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>购物车详情</h1>
	<table border="1px">
		<tr>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>商品价格</th>
			<th>购买量</th>
			<th>操作</th>
		</tr>
		<%
		//good counts
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		Map<Integer, Integer> map = cart.getGoodMap();
		
		//the details of the good
		Map<Integer, Goods> gmap = (Map<Integer, Goods>)request.getAttribute("gmap");
		Set<Integer> gidset = gmap.keySet();
		Iterator<Integer> giditer = gidset.iterator();
		int gid = 0;
		Goods g = null;
		int cnt = 0;
		int tm = 0;
		while (giditer.hasNext()) {
			gid = giditer.next();
			g = gmap.get(gid); //details
			cnt = map.get(gid); //cnt
			tm += g.getGoodprice()*cnt;
	%>
		<tr>
			<td><%=g.getGoodid()%></td>
			<td><%=g.getGoodname()%></td>
			<td><%=g.getGoodprice()%></td>
			<td><%=cnt%></td>
			<td><a href="cartdelaction?goodid=<%=g.getGoodid()%>&cnt=<%=cnt%>">删除</a>
			</td>
		</tr>
		<%
			}
		%>
		<tr><td colspan="5">总价:&nbsp;&nbsp;<%=tm %></td></tr>
	</table>
	
	<form action="goodaction">
		<input type="submit" value="跳转回首页"/>
	</form>

</body>
</html>