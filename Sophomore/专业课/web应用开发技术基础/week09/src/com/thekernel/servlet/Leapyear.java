package com.thekernel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Leapyear extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(req.getParameter("n"));
		PrintWriter out = resp.getWriter();
		
		if((n % 4 == 0 && n % 100 != 0) || (n % 400 == 0)) {
			out.write(n + " is a Leap Year");
		} else {
			out.write(n + " is not a Leap Year");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
