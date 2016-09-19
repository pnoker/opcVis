package com.opcVis.service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.annotation.Log;
import com.opcVis.model.OperateLog;

@Aspect
@Component
public class LogAspectService {
	@Autowired
	private OperateLogService operateLogService;

	OperateLog operateLog = new OperateLog();

	private static final Logger logger = LoggerFactory.getLogger(LogAspectService.class);

	// AOP切点
	@Pointcut("@annotation(com.annotation.Log)")
	public void logAspect() {
	}

	// 前置通知，记录用户的操作
	@Before("logAspect()")
	public void beforeExec(JoinPoint joinPoint) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 获取用户名，记录操作用户
		String username = (String) session.getAttribute("username");
		String siid = (String) session.getAttribute("siid");
		String roleid = (String) session.getAttribute("role");
		// 获取IP地址，并判断是否有代理服务器，获取其真实的地址
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 主机用localhost访问时，ip为0:0:0:0:0:0:0:1
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "localhost";
		}
		String model = joinPoint.getTarget().getClass().getName();
		String type = joinPoint.getSignature().getName() + "()";
		String description = getControllerMethodDescription(joinPoint);
		String url = request.getRequestURI();
		String method = request.getMethod();
		operateLog.setId(UUID.randomUUID().toString());
		operateLog.setIp(ip);
		operateLog.setModel(model);
		operateLog.setType(type);
		operateLog.setDescription(description);
		operateLog.setUrl(url);
		operateLog.setMethod(method);
	}

	@AfterThrowing(pointcut = "logAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		String message = "异常信息：" + e.getMessage();
		String method = "异常方法：" + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName()
				+ "()";
		String code = "异常代码：" + e.getClass().getName();
		String exception = message + method + code;
		operateLog.setException(exception);
		logger.error("异常{}", exception);

	}

	@After("logAspect()")
	public void afterExec(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		// 获取用户名，记录操作用户
		String code = "";
		try {
			code = (String) session.getAttribute("code");
		} catch (Exception e) {
		}
		if (code.equals("000")) {
			String username = (String) session.getAttribute("username");
			String siid = (String) session.getAttribute("siid");
			String roleid = (String) session.getAttribute("role");
			operateLog.setUsername(username);
			operateLog.setCreatetime(new Date());
			if (roleid.equals("SI_ADMIN")) {
				operateLog.setRole("YD" + username + roleid);
			} else if (roleid.equals("SI_OPERATOR")) {
				operateLog.setRole("YD" + roleid);
			} else {
				operateLog.setRole(roleid);
			}
			operateLog.setException("无异常");
			try {
				operateLogService.insert(operateLog);
			} catch (Exception e) {

			}

		}
	}

	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(Log.class).description();
					break;
				}
			}
		}
		return description;
	}
}
