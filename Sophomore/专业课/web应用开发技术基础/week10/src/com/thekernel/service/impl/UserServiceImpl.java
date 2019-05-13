package com.thekernel.service.impl;

import com.thekernel.dao.IUserDao;
import com.thekernel.dao.impl.UserDaoImpl;
import com.thekernel.entity.UInfo;
import com.thekernel.service.IUserService;

public class UserServiceImpl implements IUserService{

	@Override
	public boolean login(UInfo u) {
		
		boolean result = false;
		IUserDao dao = new UserDaoImpl();
		UInfo dbuser = null;
	
		
		try{
			dbuser = dao.selectByUsername(u.getUsername());
		} catch(Exception e) {
			return result;
		}
		
		
		if(dbuser.getPassword().equals(u.getPassword())) {
			result = true;
		}
		
		return result;
		
	}

	
}
