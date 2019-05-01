<%@page import="com.thekernel.entity.Good"%>
<%@page import="java.util.List"%>
<%@page import="com.thekernel.dao.impl.GoodDaoImpl"%>
<%@page import="com.thekernel.dao.IGoodDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="resources/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="resources/js/jquery-3.4.0.min.js" />
<link rel="stylesheet" href="resources/layer/dist/layer.js" />
<script src="resources/layui/layui.js" charset="utf-8"></script>
<title>Manage Goods</title>
<script type="text/javascript">
function del(g) {
	var msg = "您确定要删除该商品吗？\n\n请确认！";
	if (confirm(msg)==true){
		window.location.href='' + g.getId()
		return true;
	} else {
		return false;
	}
}
function loadXMLDoc(url)
{
  var xmlhttp;
  if (window.XMLHttpRequest)
  {
    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
    xmlhttp=new XMLHttpRequest();
  }
  else
  {
    // IE6, IE5 浏览器执行代码
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function()
  {
    if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
      document.getElementById("main").innerHTML=xmlhttp.responseText;
    }
  }
  xmlhttp.open("GET",url,true);
  xmlhttp.send();
}
</script>
</head>
<body>

<body class="layui-layout-body">

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
		
		IGoodDao dao = new GoodDaoImpl();
		List<Good> goods = dao.selectAll();
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
          <dd><a href="updatepassword.jsp">修改密码</a></dd>
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
  	<h1 style="text-align: center; margin-top: 3%;">商品管理</h1>
  	<div class="demoTable" style="text-align: center; margin-top: 5%; margin-bottom: 5%;">
  		<form action="doSearch" action="GET">
		  <div class="layui-inline">
		    <input class="layui-input" name="keyword" id="demoReload" autocomplete="off" placeholder="请输入需要查找的商品信息" />
		  </div>
		  <button class="layui-btn" data-type="reload">搜索</button>
	    </form>
	</div>
    <table class="layui-table">
	    	<tr>
	    		<th>商品编号</th>
	    		<th>商品名称</th>
	    		<th>商品价格</th>
	    		<th>商品类型</th>
	    		<th>操作</th>
	    	</tr>
	    	<%if (goods != null) { 
	    		for(Good g : goods) {%>
	    		<tr>
	    			<td><%=g.getId() %></td>
	    			<td><%=g.getName() %></td>
	    			<td><%=g.getPrice() %></td>
	    			<td><%=g.getType() %></td>
	    			<td>
	    				<div class="layui-btn-group site-demo-button" id="layerDemo">
						    
						    <a href="updategood.jsp?id=<%=g.getId()%>">
						    	<button class="layui-btn layui-btn-sm">
						    		<i class="layui-icon">
						    			</i>
						    	</button>
					    	</a>
						    <a onclick="javascript:return del();" href="doDelete?id=<%=g.getId()%>">
						    	<button class="layui-btn layui-btn-sm"><i class="layui-icon"></i></button>
						    </a>
						  </div>
					</td>
	    		</tr>
	    		<%} %>
	    	<%} else { %>
	    	<%} %>
	    </table> 
	    
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