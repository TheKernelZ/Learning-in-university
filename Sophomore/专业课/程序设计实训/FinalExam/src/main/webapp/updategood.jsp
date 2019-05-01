<%@page import="com.thekernel.entity.Good"%>
<%@page import="com.thekernel.dao.IGoodDao"%>
<%@page import="com.thekernel.dao.impl.GoodDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/layui/css/layui.css" />
<link rel="stylesheet" href="resources/js/jquery-3.4.0.min.js" />
<link rel="stylesheet" href="resources/css/good.css" />
<title>Update Good</title>
</head>
<body>

	<%
	
		String username = (String) session.getAttribute("username");
		
		if(username == null) {
			String msg = "账户已过期，请重新登录";
			session.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		try{
			String msg = (String) request.getAttribute("msg");
			if(msg != null) {
				out.print("<script>alert('" + msg + "')</script>");
			}
		} catch (Exception e) {
			
		}
	
		int id = Integer.parseInt(request.getParameter("id"));
		IGoodDao dao = new GoodDaoImpl();
		Good g = null;
		
		g = dao.selectById(id);
		
		if(g == null) {
			request.setAttribute("msg", "该商品不存在!!!");
			request.getRequestDispatcher("managegood.jsp").forward(request, response);
		}
	%>
	
	<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">电商后台管理系统</div>
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="show.jsp">首页</a></li>
      <li class="layui-nav-item"><a href="managegood.jsp">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="updatepassword.jsp">
          <img src="resources/images/avatar.jpg" class="layui-nav-img"><%=username %>
        </a>
        <dl class="layui-nav-child">
          <dd><a href="#">基本资料</a></dd>
          <dd><a href="updatePassword.jsp">修改密码</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="doExit">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <ul class="layui-nav layui-nav-tree">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">所有商品</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:void(0);" onclick="loadXMLDoc('addgood.jsp')">添加商品</a></dd>
            <dd><a href="managegood.jsp">商品管理</a></dd>
            <dd><a href="">更新中...</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">更新中...</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">更新中...</a></dd>
            <dd><a href="javascript:;">更新中...</a></dd>
            <dd><a href="">更新中...</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="#">更新中...</a></li>
        <li class="layui-nav-item"><a href="#">更新中...</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body" id="main">
    <h1 style="text-align: center; margin-top: 3%;">修改商品</h1>
	
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>请输入新商品信息</legend>
	</fieldset>
 
	<form method="POST" action="doUpdateGood" id="payment">
	    <fieldset>
	        <legend>商品信息</legend>
	        <ol>
	            <li>
	                <label>商品编号：</label>
	                <input name="id" type="text" value="<%=g.getId() %>" readonly />
	            </li>
	            <li>
	                <label>商品名称：</label>
	                <input name="name" type="text" value="<%=g.getName() %>" required autofocus>
	            </li>
	            <li>
	                <label>商品价格：</label>
	                <input name="price" type="text" value="<%=g.getPrice() %>" required>
	            </li>
	            <li>
	                <label>商品类型：</label>
	                <select name="type">
	                <%
	                	if(g.getType().equals("手机")) {
	                %>
	                	<option value ="手机" selected="selected">手机</option>
	                <%} else { %>
	                	<option value ="手机">手机</option>
	                <%} %>
					<%
	                	if(g.getType().equals("电脑")) {
	                %>
	                	<option value ="电脑" selected="selected">电脑</option>
	                <%} else { %>
	                	<option value ="电脑">电脑</option>
	                <%} %>
	               	<%
	                	if(g.getType().equals("平板")) {
	                %>
	                	<option value ="平板" selected="selected">平板</option>
	                <%} else { %>
	                	<option value ="平板">平板</option>
	                <%} %>
	                <%
	                	if(g.getType().equals("车")) {
	                %>
	                	<option value ="车" selected="selected">车</option>
	                <%} else { %>
	                	<option value ="车">车</option>
	                <%} %>
	                </select>
	            </li>
	        </ol>
	    </fieldset>
	    <fieldset class="layui-elem-field site-demo-button" >
	    	<button class="layui-btn layui-btn-primary" type="submit" style="display:block;margin:0 auto; margin-top: 2%;">确认修改</button>
	    </fieldset>
	</form>
  </div>
  
  <div class="layui-footer">
  	<div style="margin-left: 40%;">
	    TheKernel © 2019 Power By 
	    <a href="https://www.github.com/TheKernelZ" style="color:#00f; text-decoration:underline;">Github</a>
    </div>
  </div>
</div>

</body>
</html>