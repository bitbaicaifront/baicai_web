package com.baicai.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baicai.dao.UserDao;
import com.baicai.domain.UserPO;

@Service
public class UserService {
	@Autowired
	UserDao userDao;

	public int findUserService(String account, String password) {
		UserPO user = userDao.findUserDao(account);
		if (user == null) {
			// 没找到用户返回-1
			return -1;
		} else {
			Timestamp today = new Timestamp(System.currentTimeMillis());
			String todayStr = "";
			Timestamp lastLoginDate = user.getLast_login_date();
			String lastLoginDateStr = "";
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				todayStr = sdf.format(today);
				lastLoginDateStr = sdf.format(lastLoginDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (user.getLogin_time() >= 5 && todayStr.equals(lastLoginDateStr)) {
				// 当天登录超过5次返回-3
				return -3;
			}
			if (!user.getPassword().equals(password)) {
				// 密码错误，返回0，并修将用户的登录次数加1，更新登录时间
				this.updateUserService(account, user.getLogin_time() + 1);
				return -2;
			}
		}
		// 找到用户且密码正确 ，返回0，修改用户的登录次数为0，更新登录时间
		this.updateUserService(account, 0);
		return 0;
	}

	// 密码输入错误就将数据库里这个用户的登录失败次数加1，并将上次登陆时间更新
	public void updateUserService(String account, int loginTime) {
		userDao.updateUserLoginDao(account, loginTime);
	}

}
