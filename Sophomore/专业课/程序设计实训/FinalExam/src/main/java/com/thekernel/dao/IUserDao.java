package com.thekernel.dao;

import java.util.List;

import com.thekernel.entity.User;

public interface IUserDao {

	/**
	 * Create User
	 * @param user
	 * @return success or fail
	 */
	boolean insert(User user);
	
	/**
	 * Delete By Id
	 * @param id
	 * @return success or fail
	 */
	boolean deleteById(int id);
	
	/**
	 * Update By Id
	 * @param user
	 * @return success or fail
	 */
	boolean updateById(User user);
	
	/**
	 * Select By Username
	 * @param username
	 * @return User->success or None->fail
	 */
	User selectByUsername(String username);
	
	/**
	 * Select all Usernames 
	 * @return usernames
	 */
	List<Object> selectAllUsername();
}
