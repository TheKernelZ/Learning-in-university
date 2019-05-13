package com.thekernel.dao;

import com.thekernel.entity.UInfo;

public interface IUserDao {

	UInfo selectByUsername(String username);
	
}
