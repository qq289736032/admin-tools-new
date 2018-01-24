/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mokylin.cabal.modules.sys.service.LogService;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.sys.dao.LogDao;
import com.mokylin.cabal.modules.sys.entity.Log;
import com.mokylin.cabal.modules.sys.entity.User;
import com.mokylin.cabal.modules.sys.utils.UserUtils;


/**
 * 系统拦截器
 * @author 稻草鸟人
 * @version 2014-6-6
 */
public class LogInterceptor extends BaseService implements HandlerInterceptor {

	private static LogService logService = SpringContextHolder.getBean(LogService.class);

	private static final Logger LOGGER = LoggerFactory.getLogger("time_logger");
	private NamedThreadLocal<StopWatch>  startTimeThreadLocal = new NamedThreadLocal<StopWatch>("StopWatch-StartTime");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		//long beginTime = System.currentTimeMillis();//1、开始时间
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		startTimeThreadLocal.set(stopWatch);//线程绑定变量（该数据只有当前请求的线程可见
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		startTimeThreadLocal.set(stopWatch);//线程绑定变量（该数据只有当前请求的线程可见
		if(modelAndView!=null) {
			String viewName = modelAndView.getViewName();
//			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//			if(viewName.startsWith("modules/") && DeviceType.MOBILE.equals(userAgent.getOperatingSystem().getDeviceType())){
//				modelAndView.setViewName(viewName.replaceFirst("modules", "mobile"));
//			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
		String requestRri = request.getRequestURI();
		String uriPrefix = request.getContextPath() + Global.getAdminPath();
		logger.debug("requestRri【{}】,uriPrefix【{}】",requestRri,uriPrefix);

		if ((StringUtils.startsWith(requestRri, uriPrefix) && (StringUtils.endsWith(requestRri, "/save")
				|| StringUtils.endsWith(requestRri, "/delete") || StringUtils.endsWith(requestRri, "/import")
				|| StringUtils.endsWith(requestRri, "/updateSort")))){
		
			User user = UserUtils.getUser();
			if (user!=null && user.getId()!=null){
				
				StringBuilder params = new StringBuilder();
				int index = 0;
				for (Object param : request.getParameterMap().keySet()){ 
					params.append((index++ == 0 ? "" : "&") + param + "=");
					params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase((String)param, "password")
							? "" : request.getParameter((String)param), 100));
				}
				
				Log log = new Log();
				log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
				log.setCreateBy(user);
				log.setCreateDate(new Date());
				log.setRemoteAddr(StringUtils.getRemoteAddr(request));
				log.setUserAgent(request.getHeader("user-agent"));
				log.setRequestUri(request.getRequestURI());
				log.setMethod(request.getMethod());
				log.setParams(params.toString());
				log.setException(ex != null ? ex.toString() : "");
                logService.save(log);
				
				logger.info("save log {type: {}, loginName: {}, uri: {}}, ", log.getType(), user.getLoginName(), log.getRequestUri());
				
			}

		}
		StopWatch stopWatch = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
		LOGGER.info("{} 方法拦截 consume: {} ", request.getRequestURI(), stopWatch.getTime());

		logger.debug("最大内存: {}, 已分配内存: {}, 已分配内存中的剩余空间: {}, 最大可用内存: {}",
				Runtime.getRuntime().maxMemory(), Runtime.getRuntime().totalMemory(), Runtime.getRuntime().freeMemory(),
				Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory());
		
	}

}
