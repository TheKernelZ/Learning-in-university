package com.thekernel.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.entity.ShoppingCart;

public class DoCartServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true); 
		String goodid= req.getParameter("goodid");
		int gid = 0;
		try {
			gid = Integer.parseInt(goodid);
			ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
			if(null==cart) {
				cart = new ShoppingCart();
				cart.getGoodMap().put(gid,1);
				cart.setGoodIncrement();
				session.setAttribute("cart",cart);
				cart.setGoodCnt(0);
			} else {
				Map<Integer,Integer> map = cart.getGoodMap();
				if(map.containsKey(gid)) {
					int cnt = cart.getGoodMap().get(gid);
					cart.getGoodMap().put(gid,++cnt);
					cart.setGoodIncrement();
					cart.setGoodCnt(cart.getGoodMap().size());
				} else {
					cart.getGoodMap().put(gid,1);
					cart.setGoodIncrement();
					cart.setGoodCnt(cart.getGoodMap().size());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			resp.sendRedirect("goodaction");
		}

	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
