package com.thekernel.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thekernel.util.DbUtils;

/**
 * Servlet implementation class DatabaseServlet
 */
@WebServlet("/DatabaseServlet")
public class DatabaseServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String table = request.getParameter("table");
		String field1 = request.getParameter("filed1");
		String type1 = request.getParameter("type1");
		
		String field2 = request.getParameter("filed2");
		String type2 = request.getParameter("type2");
		String field3 = request.getParameter("filed3");
		String type3 = request.getParameter("type3");
		String field4 = request.getParameter("filed4");
		String type4 = request.getParameter("type4");
		String field5 = request.getParameter("filed5");
		String type5 = request.getParameter("type5");
		
		Connection conn = DbUtils.getConn();
		PreparedStatement ps = null;
		String sql = "CREATE TABLE " + table + "(`" + field1 + "` " + type1 + " PRIMARY KEY, ";

		
		if(!field2.equals("")) {
			sql += "`" + field2 + "` " + type2 + ", ";
		}
		if(!field3.equals("")) {
			sql += "`" + field3 + "` " + type3 + ", ";
		}
		if(!field4.equals("")) {
			sql += "`" + field4 + "` " + type4 + ", ";
		}
		if(!field5.equals("")) {
			sql += "`" + field5 + "` " + type5 + ", ";
		}
		
		sql = sql.substring(0, sql.length()-2) + ");";
//		System.out.println(sql);
		
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			request.setAttribute("msg", "添加数据表失敗!!!");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		try {
			conn.close();
		} catch (SQLException e) {

		}

		request.setAttribute("msg", "添加数据表成功!!!");
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
