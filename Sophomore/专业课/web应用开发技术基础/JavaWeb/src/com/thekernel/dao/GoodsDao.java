package com.thekernel.dao;

import java.util.List;

import com.thekernel.entity.Goods;

public interface GoodsDao {

	
	public void insert(Goods g) throws Exception;
	
	public void delete(int goodid) throws Exception;
	
	public void update(Goods g) throws Exception;
	
	public Goods find(int goodid) throws Exception;
	
	public List<Goods> find() throws Exception;
	
	public Goods search(String goodname) throws Exception;
	
	public List<Goods> order(String ordernum) throws Exception;
	
}
