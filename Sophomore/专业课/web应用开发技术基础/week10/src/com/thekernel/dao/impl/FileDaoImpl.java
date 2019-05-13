package com.thekernel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thekernel.dao.IFileDao;
import com.thekernel.entity.FileInfo;
import com.thekernel.util.JdbcUtils;

public class FileDaoImpl implements IFileDao{

	@Override
	public int insertList(List<FileInfo> files) {
		
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		JdbcUtils util = new JdbcUtils();
		String sql = "INSERT INTO `fileinfo` VALUES(?, ?, ?, ?, ?, ?)";
		
		conn = util.getConnection();
		
		for(FileInfo file : files) {
			
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, file.getFid());
				ps.setString(2, file.getFname());
				ps.setString(3, file.getFcategory());
				ps.setString(4, file.getFsize());
				ps.setInt(5, file.getFuid());
				ps.setString(6, file.getFtime());
				
				result += ps.executeUpdate(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		return result;
	}

	@Override
	public List<FileInfo> selectAll() {

		FileInfo f = null;
		JdbcUtils util = new JdbcUtils();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FileInfo> files = new ArrayList<FileInfo>();
		
		conn = util.getConnection();
		String sql = "SELECT * FROM `fileinfo`";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				f = new FileInfo();
				
				f.setFid(rs.getInt(1));
				f.setFname(rs.getString(2));
				f.setFcategory(rs.getString(3));
				f.setFsize(rs.getString(4));
				f.setFuid(rs.getInt(5));
				f.setFtime(rs.getString(6));
				
				files.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return files;
	}

}
