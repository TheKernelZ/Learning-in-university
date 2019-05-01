package com.thekernel.service;

import com.thekernel.entity.Good;

public interface IGoodService {

	/**
	 * Create Good Service
	 * @param good
	 * @return
	 */
	int createGood(Good good);
	
	
	/**
	 * Update Good Service
	 * @param good
	 * @return
	 */
	int updateGood(Good good);
	
}
