package com.thekernel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thekernel.dao.IGoodDao;
import com.thekernel.dao.impl.GoodDaoImpl;
import com.thekernel.entity.Good;

public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String keyword = new String(req.getParameter("keyword").getBytes("ISO8859-1"), "UTF-8");
		List<Good> goods = null;
		IGoodDao dao = new GoodDaoImpl();
		
		goods = dao.selectByKeyword("%" + keyword + "%");

		req.setAttribute("goods", goods);
		
		req.getRequestDispatcher("searchresult.jsp").forward(req, resp);

	}
}
