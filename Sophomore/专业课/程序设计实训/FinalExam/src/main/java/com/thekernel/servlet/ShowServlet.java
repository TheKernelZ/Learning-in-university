package com.thekernel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.thekernel.dao.IGoodDao;
import com.thekernel.dao.impl.GoodDaoImpl;
import com.thekernel.entity.Good;

public class ShowServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setHeader("Content-type", "text/html;charset=UTF-8");  
		JSONObject result = new JSONObject();
		IGoodDao dao = new GoodDaoImpl();
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("utf8");
		
		List<Good> goods = dao.selectAll();
		
		result.put("code", 0);
		result.put("msg", "ok");
		result.put("count", goods.size());
		result.put("data", goods);
		
		out.print(result.toJSONString());
		
	}
}
