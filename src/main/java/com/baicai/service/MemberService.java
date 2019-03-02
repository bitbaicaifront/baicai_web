package com.baicai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baicai.dao.MemberDao;
import com.baicai.domain.Member;
import com.baicai.utils.StringUtil;

@Service("iMemberService")
public class MemberService {
    @Autowired
    MemberDao mDao;

    public Member login(String name, String passsword) throws Exception {
        System.out.println(name + passsword);
        if(StringUtil.isNullOrZero(name)){
            System.out.println("用戶名不能為空");
            return null;
        }
        if(StringUtil.isNullOrZero(passsword)){
            System.out.println("密碼不能為空");
            return null;
        }
        Member member = mDao.selectMemberByName(name);
        return member;
    }

}
