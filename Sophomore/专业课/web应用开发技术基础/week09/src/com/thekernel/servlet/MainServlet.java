package com.thekernel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String keyword = req.getParameter("keyword");
		String left = req.getParameter("left");
		String right = req.getParameter("right");
		String select = req.getParameter("type");
		
		String left_content = "";
		String right_content = "";
		
		if (left.equals("baidu")) {
			left_content = "https://baidu.com/s?wd=";
		} else if (left.equals("google")) {
			left_content = "https://www.google.com/search?q=";
		} else if (left.equals("sougou")) {
			left_content = "https://www.sogou.com/web?query=";
		}
		
		left_content += keyword;
		
		if (select.equals("double")) {
			
			if (right.equals("baidu")) {
				right_content = "https://baidu.com/s?wd=";
			} else if (right.equals("google")) {
				right_content = "https://www.google.com/search?q=";
			} else if (right.equals("sougou")) {
				right_content = "https://www.sogou.com/web?query=";
			}
			
			right_content += keyword;
			
			String left_url = "<iframe src='" + left_content + "' style=\"width: 49%;height: 440px;\"></iframe>";
			String right_url = "<iframe src='" + right_content + "' style=\"width: 49%;height: 440px;\"></iframe>";;
	
			out.write("<html><body>");
			out.write(left_url);
			out.write(right_url);
			out.write("</html></body>");
		} else {
			out.write("<html><body>");
			String left_url = "<iframe src='" + left_content + "' style=\"width: 100%;height: 440px;\"></iframe>";
			out.write(left_url);
			out.write("</html></body>");
		}
	}
}
