package com.baicai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baicai.dao.UserDao;
import com.baicai.domain.UserPO;
import com.baicai.utils.StringUtil;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

	public boolean findUserService(String account, String password) {
		UserPO user = userDao.findUserDao(account);
		System.out.println(user.getAccount());
		return true;
	}

	public void updateUserService(String account) {
		// TODO Auto-generated method stub
		
	}

}
