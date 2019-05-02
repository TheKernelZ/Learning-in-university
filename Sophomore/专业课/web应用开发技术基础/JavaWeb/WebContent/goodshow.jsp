<%@page import="com.thekernel.entity.ShoppingCart"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.thekernel.entity.Goods"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有商品</title>
</head>
<body>
<%
	List<Goods> glist = (ArrayList<Goods>)request.getAttribute("glist");
%>



<table border="1px">
	<tr><th>商品编号</th><th>商品名称</th><th>商品价格</th><th>商品数量</th><th>操作</th></tr>
	<%
		Iterator<Goods> iter = glist.iterator();
		Goods g = null;
		while(iter.hasNext()) {
			g = iter.next();
	%>
	<tr><td><%=g.getGoodid()%></td><td><%=g.getGoodname()%></td><td><%=g.getGoodprice()%></td><td><%=g.getAmount()%></td><td><a href="cartaction?goodid=<%=g.getGoodid()%>">加入购物车</a></td></tr>
	<%
		}
		
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		
		if(null!=cart) {
			
		%>
			<tr><td colspan="5" align="center">购物车:&nbsp;&nbsp;&nbsp;&nbsp;<a href="cartdetailaction"><%= cart.getGoodCnt()%></a></td></tr>
		<%
		} 
	%>
</table>

<a href="addgoods.jsp">添加商品</a> 
<a href="search.jsp">搜索商品</a> 

<form method="GET" action="doorder">
	请选择需要排序的列:
	<select name="selected">
	  <option value="goodid">商品编号</option>
	  <option value="goodname">商品名称</option>
	  <option value="goodprice">商品价格</option>
	  <option value="amount">商品数量</option>
	</select><br/>
	<input type="submit" value="order"/>
</form>

</body>
</html>