package com.thekernel.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thekernel.entity.Student;

public class InputServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Student> students = new ArrayList<Student>();
		HttpSession session = req.getSession();
		double chinese = 0.00;
		double math = 0.00;
		double english = 0.00;
		String select = req.getParameter("select");
		
		for(int i = 0; i < 9; i++) {
			try {
				chinese = Double.parseDouble(req.getParameter("chinese" + i));
				math = Double.parseDouble(req.getParameter("math" + i));
				english = Double.parseDouble(req.getParameter("english" + i));
				
				Student student = new Student(i, chinese, math, english);
				students.add(student);
			} catch(Exception e) {
				
			}
		}
		
		if(select.equals("")) {
			
		} else if(select.equals("chinese")){
			students.sort(Comparator.comparing(Student::getChinese));
		} else if(select.equals("math")) {
			students.sort(Comparator.comparing(Student::getMath));
		} else if(select.equals("english")) {
			students.sort(Comparator.comparing(Student::getEnglish));
		}
		
		
		session.setAttribute("students", students);
		req.getRequestDispatcher("result.jsp").forward(req, resp);
		
	}

}
