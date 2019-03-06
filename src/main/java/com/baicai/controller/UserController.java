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
	 * ���Կ���
	 */
	@RequestMapping("/api/test")
	public @ResponseBody void test(HttpServletRequest req, HttpServletResponse hsr) {
		logger.info("���Կ���");
	}

	/*
	 * �û���¼
	 */
	@RequestMapping("/api/login")
	public @ResponseBody void login(@RequestParam String account, @RequestParam String password, HttpServletRequest req,
			HttpServletResponse hsr) {
		JSONObject responseData = new JSONObject();
		try {
			int flag = userService.findUserService(account, password);
			if (flag == 0) {
				responseData.put("state", 0);
				responseData.put("msg", "��¼�ɹ�");
			} else if (flag == -1) {

				responseData.put("state", -1);
				responseData.put("msg", "�û�������");
			} else if (flag == -2) {

				responseData.put("state", -2);
				responseData.put("msg", "�������");
			} else if (flag == -2) {

				responseData.put("state", -3);
				responseData.put("msg", "�������������5�Σ��������ٵ�¼");
			}
			responseData.put("data", null);

		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.error(e.toString(), e);
				/* logger.info("����:" + e.toString()); */
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