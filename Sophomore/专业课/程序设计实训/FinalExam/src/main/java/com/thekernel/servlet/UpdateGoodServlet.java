package com.thekernel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thekernel.entity.Good;
import com.thekernel.service.IGoodService;
import com.thekernel.service.impl.GoodServiceImpl;

public class UpdateGoodServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Content-type", "text/html;charset=UTF-8");  
		req.setCharacterEncoding("UTF-8");

		int result = -1;
		String msg = "";
		int id = 0;
		String name = "";
		double price = 0.00;
		String type = "";
		
		try {
			id = Integer.parseInt(req.getParameter("id"));
			name = req.getParameter("name");
			price = Double.parseDouble(req.getParameter("price"));
			type = req.getParameter("type");
		} catch (Exception e) {
			msg = "商品信息错误!!!";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("show.jsp").forward(req, resp);
		}
		
		Good g = new Good(id, name, price, type);
		IGoodService service = new GoodServiceImpl();
		
		result = service.updateGood(g);
		
		if (result == -1) {
			msg = "修改失败!!!";
		} else if (result == 0) {
			msg = "商品编号或商品名称已存在!!!";
		} else if (result == 1) {
			msg = "修改成功!!!";
		}
		
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("show.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
	
}
