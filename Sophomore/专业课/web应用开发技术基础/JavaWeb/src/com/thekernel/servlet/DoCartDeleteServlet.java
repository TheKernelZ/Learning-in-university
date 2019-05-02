package com.thekernel.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.entity.ShoppingCart;

public class DoCartDeleteServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true); 
		String goodid = req.getParameter("goodid");
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		int gid = 0;
		
		try {
			gid = Integer.parseInt(goodid);
			ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
			Map<Integer, Integer> map = cart.getGoodMap();
			map.remove(gid);
			cart.setGoodCnt(cart.getGoodCnt()-cnt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resp.sendRedirect("cartdetailaction");
//			response.sendRedirect("cartaction.jsp");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
