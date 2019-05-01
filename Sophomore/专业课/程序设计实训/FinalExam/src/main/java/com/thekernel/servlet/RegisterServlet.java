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

public class RegisterServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);  
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String again_password = req.getParameter("again_password");
		
		if (!again_password.equals(password)) {
			req.setAttribute("msg", "两次密码不一致!!!");
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}
		
		IUserService service = new UserServiceImpl(); 
		User user = new User(username, password);		
		
		int result = service.register(user);
		
		if(result == 1) {
			session.setAttribute("username", username);
			req.setAttribute("msg", "注册成功");
		} else if(result == -1){
			req.setAttribute("msg", "用户名已存在");
		} else if(result == 0){
			req.setAttribute("msg", "注册失败");
		}
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
		
	}
}
