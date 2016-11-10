package com.opcVis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;

public class BaseController {

	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}

	protected HttpSession getSession() {
		return getRequest().getSession(true);
	}

	/**
	 * 创建一个简单的成功json串
	 */
	public String createSimpleSuccessJson(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "Y");
		map.put("message", message);

		return JSON.toJSONString(map).toString();
	}

	/**
	 * 创建一个简单的失败json串
	 */
	public String createSimpleFailureJson(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "N");
		map.put("message", message);

		return JSON.toJSONString(map).toString();
	}

	/**
	 * 接口文档返回json串
	 * 
	 * @param resultCode
	 * @param msg
	 * @param obj
	 * @return
	 */
	public String createResultJson(String resultCode, String msg, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result_code", resultCode);
		map.put("msg", msg);
		if (obj != null) {
			map.put("data", JSON.toJSON(obj));
		}
		return JSON.toJSONString(map);

	}

	/**
	 * json解析成Map
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> parseObject(String jsonStr) {

		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		try {
			Map<String, Object> jsonMap = JSON.parseObject(jsonStr, Map.class);
			return jsonMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Json转换成 List<Map<String,Object>>
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> parseList(String jsonStr) {
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		try {
			List<Map<String, Object>> list = JSON.parseObject(jsonStr, List.class);
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 用于处理异常的
	 * 
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	public String exception(Exception e) {
		return "error";
	}
}
