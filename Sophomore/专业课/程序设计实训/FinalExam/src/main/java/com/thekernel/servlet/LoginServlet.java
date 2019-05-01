package com.thekernel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.entity.User;
import com.thekernel.service.IUserService;
import com.thekernel.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);  
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		IUserService service = new UserServiceImpl(); 
		User user = new User(username, password);		
		
		boolean result = service.login(user);
		
		if(result) {
			session.setAttribute("username", username);
			req.setAttribute("msg", "登录成功");
			req.getRequestDispatcher("show.jsp").forward(req, resp);
		} else {
			req.setAttribute("msg", "用户名或密码错误");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
		
	}
}
