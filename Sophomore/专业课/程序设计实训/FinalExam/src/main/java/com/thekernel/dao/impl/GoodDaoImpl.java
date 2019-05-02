package com.thekernel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.thekernel.dao.IGoodDao;
import com.thekernel.entity.Good;
import com.thekernel.util.MybatisUtils;

public class GoodDaoImpl implements IGoodDao {

	@Override
	public List<Good> selectGoodLimtByPage(int page) {
		
		List<Good> result = null;
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		result = sqlSession.selectList("Good.selectGoodLimtByPage", page);		
		sqlSession.commit();

		return result;
	}

	@Override
	public Good selectById(int id) {

		Good good = null;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
		
		good = sqlSession.selectOne("Good.selectById", id);
		
		return good;
		
	}

	@Override
	public boolean insert(Good good) {
		
		int affect_row = 0;
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		affect_row = sqlSession.insert("Good.insert", good);		
		sqlSession.commit();

		return affect_row == 0 ? false : true;
		
	}

	@Override
	public boolean deleteById(int id) {

		int affect_row = 0;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();
			
		affect_row = sqlSession.delete("Good.deleteById", id);
		sqlSession.commit();
		
		return affect_row == 0 ? false : true; 
		
	}

	@Override
	public boolean updateById(Good good) {

		int affect_row = 0;
		SqlSessionFactory factory = null;			
		factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		affect_row = sqlSession.update("Good.updateById", good);
		sqlSession.commit();
		
		return affect_row == 0 ? false : true;
	}

	@Override
	public List<String> selectAllGoodnames() {

		List<String> result = null;
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		result = sqlSession.selectList("Good.selectGoodnames");		
		sqlSession.commit();

		return result;
	}

	@Override
	public List<Integer> selectAllGoodids() {

		List<Integer> result = null;
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		result = sqlSession.selectList("Good.selectGoodids");		
		sqlSession.commit();

		return result;
	}

	@Override
	public List<Good> selectByKeyword(String keyword) {

		List<Good> goods = new ArrayList<Good>();
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		goods = sqlSession.selectList("Good.selectByKeyword", keyword);	
		sqlSession.commit();
		
		return goods;
	}

	@Override
	public List<Good> selectAll() {

		List<Good> result = null;
		
		SqlSessionFactory factory = MybatisUtils.createFactory();
		SqlSession sqlSession = factory.openSession();

		result = sqlSession.selectList("Good.selectAll");		
		sqlSession.commit();

		return result;
	}

}
