package com.thekernel.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {

	static SqlSessionFactory factory = null;
	
	public static SqlSessionFactory createFactory() {
		if(factory != null) {
			return factory;
		}
		
		Reader reader;
		String resource = "mybatis/Configuration.xml";
		
		try {
			reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return factory;
		
	}
}
