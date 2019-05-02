package com.thekernel.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.dao.GoodsDao;
import com.thekernel.dao.impl.GoodsDaoImpl;
import com.thekernel.entity.Goods;
import com.thekernel.entity.ShoppingCart;

public class DoCartDetailServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);  
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");

		if (null != cart) {
			Map<Integer, Integer> map = cart.getGoodMap();

			Set<Integer> gidset = map.keySet();
			Iterator<Integer> giditer = gidset.iterator();
			int gid = 0;
			GoodsDao igd = new GoodsDaoImpl();
			Goods g = null;
			Map<Integer, Goods> gmap = new HashMap<Integer, Goods>();
			while (giditer.hasNext()) {
				gid = giditer.next();
				try {
					g = igd.find(gid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gmap.put(gid, g);
			}
			req.setAttribute("gmap", gmap);
			req.getRequestDispatcher("cartdetail.jsp").forward(req,resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
