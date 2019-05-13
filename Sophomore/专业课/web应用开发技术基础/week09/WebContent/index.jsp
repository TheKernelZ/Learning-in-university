<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IFrame</title>
</head>
<body>

	<img src="background.jpg" style="width: 100%"/>	
	
	<form action="MainServlet" method="POST">
		<input style="margin-left: 20%; width: 40%;" type="text" name="keyword"/>&nbsp;<input type="submit" value="search"/>
		<div style="margin-left: 30%;">
			左侧: <select name="left">
					  <option value="baidu">百度</option>
					  <option value="google">谷歌</option>
					  <option value="sougou">搜狗</option>
				  </select>
			右侧: <select name="right">
					  <option value="google">谷歌</option>
					  <option value="baidu">百度</option>
					  <option value="sougou">搜狗</option>
				  </select>
			<label><input name="type" type="radio" value="double" checked/>双搜 </label> 
			<label><input name="type" type="radio" value="one" />单搜 </label> 
		</div>
	</form>
	

</body>
</html>