package com.baicai.dao;

import org.apache.ibatis.annotations.Param;

import com.baicai.domain.Member;

public interface MemberDao {
	Member selectMemberByName(@Param("name") String name) throws Exception;
}