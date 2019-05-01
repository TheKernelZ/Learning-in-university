<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/layui/css/layui.css" />
<link rel="stylesheet" href="resources/js/jquery-3.4.0.min.js" />
<link rel="stylesheet" href="resources/css/good.css" />
<title>Add Good</title>
</head>
<body>

	<h1 style="text-align: center; margin-top: 3%;">添加商品</h1>
	
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>请输入新商品信息</legend>
	</fieldset>
 
	<form method="POST" action="doAddGood" id="payment">
	    <fieldset>
	        <legend>商品信息</legend>
	        <ol>
	            <li>
	                <label>商品编号：</label>
	                <input name="id" type="text" placeholder="请输入商品编号" required autofocus>
	            </li>
	            <li>
	                <label>商品名称：</label>
	                <input name="name" type="text" placeholder="请输入商品名称" required autofocus>
	            </li>
	            <li>
	                <label>商品价格：</label>
	                <input name="price" type="text" placeholder="请输入商品价格" required>
	            </li>
	            <li>
	                <label>商品类型：</label>
	                <select name="type">
	                	<option value ="手机">手机</option>
						<option value ="电脑">电脑</option>
						<option value="平板">平板</option>
						<option value="车">车</option>
	                </select>
	            </li>
	        </ol>
	    </fieldset>
	    <fieldset class="layui-elem-field site-demo-button" >
	    	<button class="layui-btn layui-btn-primary" type="submit" style="display:block;margin:0 auto; margin-top: 2%;">确认添加</button>
	    </fieldset>
	</form>
</body>
</html>