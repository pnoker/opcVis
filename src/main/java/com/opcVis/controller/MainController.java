package com.opcVis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/")
public class MainController extends BaseController {
	static final Logger logger = LogManager.getLogger(MainController.class);// 日志

	@RequestMapping("/doGet")
	@ResponseBody
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("resule", "This is a test!");
		return JSON.toJSONString(map);
	}
}