<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="addgoodsaction" method="POST">
		<table  border="1px">
			<tr>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>商品数量</th>
			</tr>
			<tr>
				<td><input type="text" name="goodsname"/></td>
				<td><input type="text" name="price"/></td>
				<td><input type="text" name="num"/></td>
			</tr>
		</table>
		<input type="submit" value="submit"/>
	</form>
	

</body>
</html>