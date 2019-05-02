package com.thekernel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	public static final String URL = "jdbc:mysql://localhost:3306/goods_db?useSSL=false&serverTimezone=Asia/Shanghai";
	public static final String USER = "root";
	public static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}


	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(null!=conn) conn.close();
			if(null!=ps) ps.close();
			if(null!=rs) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
