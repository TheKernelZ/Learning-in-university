package com.thekernel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WanShuServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	
	public boolean getWanShu(int n) {
		boolean result = false;
		int sum = 0;
		
		for (int i = 1; i < n; i++) {
			if(n % i == 0) {
				sum += i;
			}
		}
		
		if(n == sum) {
			result = true;
		}
		
		return result;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int n = 0;
		StringBuffer result = new StringBuffer(" ");
		try {
			n = Integer.parseInt(req.getParameter("n"));
		} catch (Exception e) {
			req.setAttribute("msg", "Parameter Error");
			req.getRequestDispatcher("input02.jsp").forward(req, resp);
		}
		if(n <= 0) {
			req.setAttribute("msg", "n Should Be More Than 0");
			req.getRequestDispatcher("input02.jsp").forward(req, resp);
		}
		
		for(int i = 2; i <= n; i++) {
			if(getWanShu(i)) {
				result.append(i + " ");
			}
		}
		
		req.setAttribute("result", result.toString());
		req.setAttribute("n", n);
		req.getRequestDispatcher("input02.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
		
	}
	
	
}
