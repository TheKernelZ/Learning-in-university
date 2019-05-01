package com.thekernel.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.thekernel.entity.Good;
import com.thekernel.util.MybatisUtils;

public class GoodTest {

	@Test
	/**
	 * Test For Insert Good
	 */
	public void testInsert() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		Good good = new Good(2, "华为P30", 700.23, "手机");
		
		int result = sqlSession.insert("Good.insert", good);
		
		sqlSession.commit();
		System.out.println(result);
	    
	}
	
	
	@Test
	/**
	 * Test for Delete Good By Id
	 */
	public void testDeleteById() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		int result = sqlSession.delete("Good.deleteById", 1);
		
		sqlSession.commit();
		System.out.println(result);
	    
	}
	
	
	@Test
	/**
	 * Test For Select Good Limit By Page
	 */
	public void testSelectGoodLimtByPage() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		List<Good> goods = sqlSession.selectList("Good.selectGoodLimtByPage", 1);
		
		for (Good good : goods) {
			System.out.println(good);
		}
	}
	
	
	@Test
	/**
	 * Test for Select Good By Id
	 */
	public void testSelectById() {
	
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		Good good = sqlSession.selectOne("Good.selectById", 2);
		
		System.out.println(good);
	
	}
	
	
	@Test
	/**
	 * Test For Update Good By Id
	 */
	public void testUpdateById() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		Good good = new Good(1, "华为P20", 15.2, "手机");
		
		int result = sqlSession.update("Good.updateById", good);
		
		sqlSession.commit();
		System.out.println(result);
	
	}
	
	
	@Test
	/**
	 * Test For Insert Good
	 */
	public void testSelectByKeyword() {
		
		List<Good> goods = null;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		goods = sqlSession.selectList("Good.selectByKeyword", "%手机%");
		
		sqlSession.commit();
		System.out.println(goods);
	    
	}
}
