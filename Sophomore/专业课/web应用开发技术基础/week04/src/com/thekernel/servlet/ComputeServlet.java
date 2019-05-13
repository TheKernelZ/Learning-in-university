package com.thekernel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComputeServlet
 */
@WebServlet("/ComputeServlet")
public class ComputeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			double num1 = Double.parseDouble(request.getParameter("num1"));
			String op = request.getParameter("op");
			double num2 = Double.parseDouble(request.getParameter("num2"));
			
			if(op.equals("+")) {
				request.setAttribute("result", num1+num2);
			} else if(op.equals("-")) {
				request.setAttribute("result", num1-num2);
			} else if(op.equals("x")) {
				request.setAttribute("result", num1*num2);
			} else if(op.equals("/")) {
				request.setAttribute("result", num1/num2);
			} else if(op.equals("%")) {
				request.setAttribute("result", num1%num2);
			}
			request.getRequestDispatcher("compute_show01.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "Parameter Error");
			request.getRequestDispatcher("input01.jsp").forward(request, response);
		}
		
	}

}
