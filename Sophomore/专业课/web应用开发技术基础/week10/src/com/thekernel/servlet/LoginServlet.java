package com.thekernel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.entity.UInfo;
import com.thekernel.service.IUserService;
import com.thekernel.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		IUserService service = new UserServiceImpl();
		UInfo u = new UInfo();
		boolean result = false;
		HttpSession session = req.getSession();
		
		u.setUsername(username);
		u.setPassword(password);
		
		result = service.login(u);
		
		if (result) {
			session.setAttribute("username", username);
			req.getRequestDispatcher("InsertFile.jsp").forward(req, resp);
		} else {
			req.setAttribute("msg", "µÇÂ½Ê§°Ü!!!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
		
	}
}
