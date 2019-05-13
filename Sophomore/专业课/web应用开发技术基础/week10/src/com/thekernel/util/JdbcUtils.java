package com.thekernel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils {

	private static  String user;
    public  static String password;
    public  static String className;
    public  static String url;
    Connection con = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    static{
    	ResourceBundle bundle=ResourceBundle.getBundle("db-config");
        user = bundle.getString("jdbc.username");
        password = bundle.getString("jdbc.password");
        className = bundle.getString("jdbc.driver");
        url = bundle.getString("jdbc.url");
    }
    
    public JdbcUtils() {
    	try{
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
    
    public Connection getConnection() {
         try {
             con=(Connection) DriverManager.getConnection(url, user, password);
         } catch (SQLException e) {
             con=null;
             e.printStackTrace();
         }
         
         return con;
     }
 
    	 
}