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
			// û�ҵ��û�����-1
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
				// �����¼����5�η���-3
				return -3;
			}
			if (!user.getPassword().equals(password)) {
				// ������󣬷���0�����޽��û��ĵ�¼������1�����µ�¼ʱ��
				this.updateUserService(account, user.getLogin_time() + 1);
				return -2;
			}
		}
		// �ҵ��û���������ȷ ������0���޸��û��ĵ�¼����Ϊ0�����µ�¼ʱ��
		this.updateUserService(account, 0);
		return 0;
	}

	// �����������ͽ����ݿ�������û��ĵ�¼ʧ�ܴ�����1�������ϴε�½ʱ�����
	public void updateUserService(String account, int loginTime) {
		userDao.updateUserLoginDao(account, loginTime);
	}

}
