package com.thekernel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thekernel.dao.IGoodDao;
import com.thekernel.dao.impl.GoodDaoImpl;

public class DeleteServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		boolean result = false;
		int id = Integer.parseInt(req.getParameter("id"));
		IGoodDao dao = new GoodDaoImpl();
		
		result = dao.deleteById(id);
		
		if(result) {
			req.setAttribute("msg", "ɾ���ɹ�!!!");
			req.getRequestDispatcher("show.jsp").forward(req, resp);
		} else {
			req.setAttribute("msg", "ɾ��ʧ��!!!");
			req.getRequestDispatcher("managegood.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
}
