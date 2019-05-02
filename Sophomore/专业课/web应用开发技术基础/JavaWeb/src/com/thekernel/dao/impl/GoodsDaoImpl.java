package com.thekernel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.thekernel.dao.GoodsDao;
import com.thekernel.entity.Goods;
import com.thekernel.exception.GoodCartException;
import com.thekernel.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao{

	@Override
	public void insert(Goods g) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
		 conn = DBUtil.getConnection();
		String sql ="insert into goods(goodname,goodprice,amount) values(?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, g.getGoodname());
		ps.setInt(2, g.getGoodprice());
		ps.setInt(3, g.getAmount());
		ps.executeUpdate(); 
		} catch(Exception e) {
			e.printStackTrace();
			throw new GoodCartException("商品无法插入");
			
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void delete(int goodid) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
		conn = DBUtil.getConnection();
		String sql ="delete from goods where goodid=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, goodid);
		ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GoodCartException("商品无法删除");
		} finally {
			DBUtil.close(conn, ps, null);
		}
	}

	@Override
	public void update(Goods g) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql ="update goods set goodid=?, goodname=?, goodprice=?,amount=? where goodid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, g.getGoodid());
		ps.setString(2, g.getGoodname());
		ps.setInt(3, g.getGoodprice());
		ps.setInt(4, g.getAmount());
		ps.setInt(5, g.getGoodid());
		ps.executeUpdate();
		DBUtil.close(conn, ps, null);
	}

	@Override
	public Goods find(int goodid) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql ="select goodid,goodname,goodprice,amount from goods where goodid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, goodid);
		ResultSet rs = ps.executeQuery();
		
		Goods g = null;
		if(rs.next()) {
			g = new Goods();
			g.setGoodid(rs.getInt(1));
			g.setGoodname(rs.getString(2));
			g.setGoodprice(rs.getInt(3));
			g.setAmount(rs.getInt(4));
		}
		DBUtil.close(conn, ps, rs);
		return g;
	}

	@Override
	public List<Goods> find() throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql ="select goodid,goodname,goodprice,amount from goods";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Goods> gList = new ArrayList<Goods>();
		Goods g = null;
		while(rs.next()) {
			g = new Goods();
			g.setGoodid(rs.getInt(1));
			g.setGoodname(rs.getString(2));
			g.setGoodprice(rs.getInt(3));
			g.setAmount(rs.getInt(4));
			gList.add(g);
		}
		DBUtil.close(conn, ps, rs);
		return gList;
	}

	@Override
	public Goods search(String goodname) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql ="select goodid,goodname,goodprice,amount from goods where goodname=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, goodname);
		ResultSet rs = ps.executeQuery();
		
		Goods g = null;
		if(rs.next()) {
			g = new Goods();
			g.setGoodid(rs.getInt(1));
			g.setGoodname(rs.getString(2));
			g.setGoodprice(rs.getInt(3));
			g.setAmount(rs.getInt(4));
		}
		DBUtil.close(conn, ps, rs);
		return g;
	}

	@Override
	public List<Goods> order(String ordernum) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql ="select goodid, goodname, goodprice, amount from goods ORDER BY " + ordernum + "";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		List<Goods> gList = new ArrayList<Goods>();
		Goods g = null;
		while(rs.next()) {
			g = new Goods();
			g.setGoodid(rs.getInt(1));
			g.setGoodname(rs.getString(2));
			g.setGoodprice(rs.getInt(3));
			g.setAmount(rs.getInt(4));
			gList.add(g);
		}
		
		DBUtil.close(conn, ps, rs);
		return gList;
	}
	
}
