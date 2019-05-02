package com.thekernel.dao;

import com.thekernel.dao.impl.GoodsDaoImpl;

public class DaoFactory {

	public static GoodsDaoImpl getGoodsDao() {
		return new GoodsDaoImpl();
	}
	
}
