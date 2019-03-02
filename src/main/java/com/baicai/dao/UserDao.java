package com.baicai.dao;

import org.apache.ibatis.annotations.Param;

import com.baicai.domain.UserPO;

public interface UserDao {

	public UserPO findUserDao(@Param("account") String account);
}