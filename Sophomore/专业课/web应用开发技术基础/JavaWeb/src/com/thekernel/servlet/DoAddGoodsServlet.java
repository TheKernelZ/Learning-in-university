package com.thekernel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thekernel.dao.DaoFactory;
import com.thekernel.dao.impl.GoodsDaoImpl;
import com.thekernel.entity.Goods;

public class DoAddGoodsServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("goodsname");
		int price = Integer.parseInt(req.getParameter("price"));
		int num = Integer.parseInt(req.getParameter("num"));
		
		Goods g = new Goods(name, price, num);
		
		GoodsDaoImpl gd = DaoFactory.getGoodsDao();
		try {
			gd.insert(g);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			List<Goods> gList = gd.find();
			req.setAttribute("glist",gList);
			req.getRequestDispatcher("goodshow.jsp").forward(req,resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
