package com.thekernel.service;

import com.thekernel.entity.User;

public interface IUserService {

	/**
	 * Login Service
	 * @param user
	 * @return Login success or fail
	 */
	boolean login(User user);
	
	
	/**
	 * Register Service
	 * @param user
	 * @return -1 --> insert Error 0 --> User exists 1 --> Insert Success
	 */
	int register(User user);
	
}
