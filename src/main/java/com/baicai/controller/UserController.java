package com.baicai.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baicai.service.UserService;
import com.baicai.utils.NetUtil;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /*
	 * 测试跨域
	 */
	@RequestMapping("/api/test")
	public @ResponseBody void test(HttpServletRequest req, HttpServletResponse hsr) {
		logger.info("测试跨域");
	}
    
    /*
	 * 用户登录
	 */
	@RequestMapping("/api/login")
	public @ResponseBody void login(@RequestParam String account,
			@RequestParam String password, HttpServletRequest req, HttpServletResponse hsr) {
		JSONObject responseData = new JSONObject();
		logger.info("test info");
		logger.error("test error");
		logger.debug("test debug");
		try {
			boolean flag = userService.findUserService(account, password);
			if(flag == true) {
				responseData.put("state", 0);
				responseData.put("msg", "登录成功");
			} else {
				// 密码输入错误就将数据库里这个用户的登录失败次数加
				userService.updateUserService(account);
				responseData.put("state", 1);
				responseData.put("msg", "登录失败");
			}
			responseData.put("data", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.error(e.toString(), e);
				/* logger.info("错误:" + e.toString()); */
			}
		} finally {
			try {
				NetUtil.sendJsonDataMap(responseData, hsr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}