package com.thekernel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Compute3Servelt
 */
@WebServlet("/Compute3Servelt")
public class Compute3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Compute3Servlet() {
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

		String n = request.getParameter("n");
		int num = 0;
		double result = 0.00;
		StringBuffer print = new StringBuffer("");
		
		try {
			num = Integer.parseInt(n);
			
			for(int i = 1; i <= num; i+=2) {
				result += 1.0/i * Math.pow(-1, i/2.0-0.5);
				
				if(i == 1) {
					print.append("1");
					continue;
				}
				
				if((i/2.0-0.5) % 2 == 0) {
					print.append("+1/" + i);
				} else {
					print.append("-1/" + i);
				}
			}
			
			print.append("=");
			request.setAttribute("result", result);
			request.setAttribute("print", print.toString());
			request.getRequestDispatcher("input03.jsp").forward(request, response);
		} catch (Exception e) {
			 	
		}
		
		
	}

}
