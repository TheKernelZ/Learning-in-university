package com.thekernel.service.impl;

import java.util.List;

import com.thekernel.dao.IGoodDao;
import com.thekernel.dao.impl.GoodDaoImpl;
import com.thekernel.entity.Good;
import com.thekernel.service.IGoodService;

public class GoodServiceImpl implements IGoodService{
	
	private IGoodDao goodao = new GoodDaoImpl();

	@Override
	public int createGood(Good good) {

		int result = -1;
		List<String> existgoodnames = goodao.selectAllGoodnames();
		List<Integer> existgoodids = goodao.selectAllGoodids();
		
		for(String goodname : existgoodnames) {
			if(good.getName().equals(goodname)) {
				return 0;
			}
		}
		for(int goodid : existgoodids) {
			if(good.getId() == goodid) {
				return 0;
			}
		}
		
		result = goodao.insert(good) ? 1 : -1;
		
		return result;
		
	}

	@Override
	public int updateGood(Good good) {

		int result = -1;
		List<String> existgoodnames = goodao.selectAllGoodnames();
		
		for(String goodname : existgoodnames) {
			if(good.getName().equals(goodname)) {
				result = 0;
			}
		}
		
		result = goodao.updateById(good) ? 1 : -1;
		
		return result;
	}


}
