package com.thekernel.dao;

import java.util.List;

import com.thekernel.entity.Good;

public interface IGoodDao {

	/**
	 * Select Goods With Paging, Five Goods For a Page 
	 * @param page
	 * @return
	 */
	List<Good> selectGoodLimtByPage(int page);
	
	
	/**
	 * Select Good By id
	 * @param id
	 * @return
	 */
	Good selectById(int id);
	
	
	/**
	 * Select All Goodnames
	 * @param id
	 * @return
	 */
	List<String> selectAllGoodnames();
	
	
	/**
	 * Select All Goodnames
	 * @param id
	 * @return
	 */
	List<Integer> selectAllGoodids();
	
	
	/**
	 * Insert Good
	 * @param good
	 * @return
	 */
	boolean insert(Good good);
	
	
	/**
	 * Delete Good By Id
	 * @param id
	 * @return
	 */
	boolean deleteById(int id);
	
	
	/**
	 * Update Good By Id
	 * @param good
	 * @return
	 */
	boolean updateById(Good good);
	
	
	/**
	 * Select Good By Keyword
	 * @param keyword
	 * @return
	 */
	List<Good> selectByKeyword(String keyword);
	
	
	/**
	 * Select All Goods
	 * @return
	 */
	List<Good> selectAll();
}
