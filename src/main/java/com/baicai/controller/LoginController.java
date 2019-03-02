package com.baicai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baicai.domain.Member;
import com.baicai.service.MemberService;

@Controller
public class LoginController {
    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("���յ����� ��Hello");
        return "hi";
    }

    @RequestMapping(value = "/login")
    public String login(String name, String password){
        try {
            Member member = memberService.login(name, password);
            if(member == null){
                System.out.println("��½ʧ��");
            }else {
                System.out.println("��½�ɹ�");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("��¼ʧ��");
        }
        return null;
    }
}