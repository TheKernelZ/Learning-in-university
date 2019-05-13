package com.thekernel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thekernel.dao.IUserDao;
import com.thekernel.entity.UInfo;
import com.thekernel.util.JdbcUtils;

public class UserDaoImpl implements IUserDao{

	@Override
	public UInfo selectByUsername(String username) {
		
		UInfo u = new UInfo();
		JdbcUtils util = new JdbcUtils();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		conn = util.getConnection();
		String sql = "SELECT * FROM `uinfo` WHERE `username`=?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				u.setUserid(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return u;
	}

	
}
