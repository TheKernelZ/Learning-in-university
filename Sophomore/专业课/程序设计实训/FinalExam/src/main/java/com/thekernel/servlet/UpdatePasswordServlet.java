package com.thekernel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.dao.IUserDao;
import com.thekernel.dao.impl.UserDaoImpl;
import com.thekernel.entity.User;

public class UpdatePasswordServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String password = req.getParameter("password");
		boolean result = false;
		
		HttpSession session = req.getSession();
		IUserDao dao = new UserDaoImpl();
		
		String username = (String) session.getAttribute("username");
		User user = dao.selectByUsername(username);
		
		if(!password.equals(user.getPassword())) {
			String msg = "原密码错误!!!";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("updatepassword.jsp").forward(req, resp);
		}
		
		String new_password = req.getParameter("new_password");
		String again_new_password = req.getParameter("again_new_password");
		
		if(!new_password.equals(again_new_password)) {
			String msg = "两次密码输入不一致";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("updatepassword.jsp").forward(req, resp);
		}
		
		user.setPassword(new_password);
		result = dao.updateById(user);
		
		if(result) {
			String msg = "修改密码成功";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} else {
			String msg = "修改密码失败!!!";
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("updatepassword.jsp").forward(req, resp);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
}
