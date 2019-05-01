package com.thekernel.service.impl;

import com.thekernel.dao.IUserDao;
import com.thekernel.dao.impl.UserDaoImpl;
import com.thekernel.entity.User;
import com.thekernel.service.IUserService;

public class UserServiceImpl implements IUserService{
	
	private IUserDao userdao = new UserDaoImpl();

	@Override
	public boolean login(User user) {
		
		boolean result = false;
		User realuser = userdao.selectByUsername(user.getUsername());


		if(realuser != null){
			if(user.getPassword().equals(realuser.getPassword())){
				result = true;
			}
		} 
		return result;
		
	}

	@Override
	public int register(User user) {
		
		int result = 0;
		User realuser = userdao.selectByUsername(user.getUsername());
		
		if(realuser == null) {
			result = userdao.insert(user) ? 1 : 0;
		} else{
			result = -1;
		}
		
		return result;
		
	}

}
