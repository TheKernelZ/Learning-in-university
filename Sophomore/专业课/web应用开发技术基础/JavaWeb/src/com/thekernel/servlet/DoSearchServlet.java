package com.thekernel.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thekernel.dao.DaoFactory;
import com.thekernel.dao.impl.GoodsDaoImpl;
import com.thekernel.entity.Goods;

public class DoSearchServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = "";
		try {
			key = req.getParameter("key");	
		}catch(Exception e) {
			e.printStackTrace();
		}	
	
		Goods g = null;
		
		GoodsDaoImpl gd = DaoFactory.getGoodsDao();
		if(key != "") {
			try {
				g = gd.search(key);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			try {
				List<Goods> gList = new ArrayList<Goods>();
				gList.add(g);
				req.setAttribute("glist",gList);
				req.getRequestDispatcher("goodshow.jsp").forward(req,resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				List<Goods> gList = gd.find();
				req.setAttribute("glist",gList);
				req.getRequestDispatcher("goodshow.jsp").forward(req,resp);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
