package com.thekernel.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.thekernel.entity.User;
import com.thekernel.util.MybatisUtils;

public class UserTest {
	
	@Test
	/**
	 * Test For Insert User
	 */
	public void testInsert() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		User user = new User();
		user.setUsername("tset1");
		user.setPassword("gaga");
		int result = sqlSession.insert("User.insert", user);
		
		sqlSession.commit();
		System.out.println(result);
	    
	}
	
	
	@Test
	/**
	 * Test for Delete User By Id
	 */
	public void testDeleteById() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		int result = sqlSession.delete("User.deleteById", 2);
		
		sqlSession.commit();
		System.out.println(result);
	    
	}
	
	
	@Test
	/**
	 * Test For Update User By Id
	 */
	public void testUpdateById() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		User user = new User();
		user.setId(1);
		user.setUsername("root");
		user.setPassword("root");
		int result = sqlSession.update("User.updateById", user);
		
		sqlSession.commit();
		System.out.println(result);
	
	}
	
	
	@Test
	/**
	 * Test for Select User By Username
	 */
	public void testSelectByUsername() {
	
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		User user = sqlSession.selectOne("User.selectByUsername", "root");
		
		System.out.println(user);
	
	}
	
	
	@Test
	/**
	 * Test for Select All Usernames
	 */
	public void testSelectAllUsername() {
		
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		List<Object> users = sqlSession.selectList("User.selectAllUsername");
		
		for (Object user : users) {
			System.out.println(user);
		}

	}
	
}
