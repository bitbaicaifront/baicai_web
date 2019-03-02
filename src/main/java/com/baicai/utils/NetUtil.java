package com.baicai.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class NetUtil {
	public static void sendJsonDataMap(Object o, HttpServletResponse response)
			throws IOException {
		String result = JSON.toJSONString(o, true);
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"x-requested-with,Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}
}
