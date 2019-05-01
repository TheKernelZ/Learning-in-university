package com.thekernel.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.thekernel.dao.IUserDao;
import com.thekernel.entity.User;
import com.thekernel.util.MybatisUtils;

public class UserDaoImpl implements IUserDao {

	@Override
	public boolean insert(User user) {

		int affect_row = 0;
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		affect_row = sqlSession.insert("User.insert", user);		
		sqlSession.commit();

		return affect_row == 0 ? false : true;
		
	}

	@Override
	public boolean deleteById(int id) {

		int affect_row = 0;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		affect_row = sqlSession.delete("User.deleteById", id);
		sqlSession.commit();
		
		return affect_row == 0 ? false : true; 
		
	}

	@Override
	public boolean updateById(User user) {
		
		int affect_row = 0;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		affect_row = sqlSession.update("User.updateById", user);
		sqlSession.commit();
		
		return affect_row == 0 ? false : true; 

	}

	@Override
	public User selectByUsername(String username) {
		
		User user = null;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		user = sqlSession.selectOne("User.selectByUsername", username);
		
		return user;
	}

	@Override
	public List<Object> selectAllUsername() {
		
		List<Object> users = null;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		users = sqlSession.selectList("User.selectAllUsername");
		
		return users;
	}

}
