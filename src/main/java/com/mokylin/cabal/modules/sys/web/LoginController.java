/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.web;

import com.google.common.collect.Maps;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.core.DataContext;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.CacheUtils;
import com.mokylin.cabal.common.utils.CookieUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.entity.User;
import com.mokylin.cabal.modules.sys.security.LogUtils;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * 登录Controller
 * @author 稻草鸟人
 * @version 2014-5-31
 */
@Controller
public class LoginController extends BaseController{
	
	/**
	 * 管理登录
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 如果已经登录，则跳转到管理首页
		if(user.getId() != null){
			return "redirect:"+Global.getAdminPath();
		}
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String login(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 如果已经登录，则跳转到管理首页
		if(user.getId() != null){
			return "redirect:"+Global.getAdminPath();
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresUser
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 未登录，则跳转到登录页
		if(user.getId() == null){
			return "redirect:"+Global.getAdminPath()+"/login";
		}
		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(user.getLoginName(), false, true);
		// 登录成功后，获取上次登录的当前站点ID
		//UserUtils.putCache("siteId", CookieUtils.getCookie(request, "siteId"));
        //登陆成功后，设置默认游戏服

		DataContext dataContext = DataContext.getInstance(user.getId());
		if(dataContext == null){
			Server gameServer = UserUtils.getDefaultGameServer(user);
			if(gameServer != null) {
				String defaultGameServerId = String.valueOf(gameServer.getWorldId());
				String defaultPlatformId = gameServer.getPlatformId();
				model.addAttribute("currentGameServerName",gameServer.getName());
				request.getSession().setAttribute(ConfigConstants.SELECTED_SERVER_KEY, defaultGameServerId);
				request.getSession().setAttribute(ConfigConstants.DEFAULT_PLATFORM_KEY, defaultPlatformId);
				LookupContext.setCurrentServerId(defaultGameServerId);
				LookupContext.setCurrentPlatformId(defaultPlatformId);
				model.addAttribute("defaultPlatformId",defaultPlatformId);
				DataContext.put(user.getId(), defaultPlatformId, defaultGameServerId, gameServer.getName());    //存入缓存
			}
		}else {
			model.addAttribute("currentGameServerName", dataContext.getName());
			request.getSession().setAttribute(ConfigConstants.SELECTED_SERVER_KEY, dataContext.getServerId());
			request.getSession().setAttribute(ConfigConstants.DEFAULT_PLATFORM_KEY, dataContext.getPid());
			LookupContext.setCurrentServerId(dataContext.getServerId());
			LookupContext.setCurrentPlatformId(dataContext.getPid());
			model.addAttribute("defaultPlatformId",dataContext.getPid());
		}

		//记录日志
		LogUtils.logAccess(request);

		
//        if(user.getRoleList().size() > 0 && user.getRoleList().get(0).getGamePlatformList().size() >0
//                && user.getRoleList().get(0).getGamePlatformList().get(0).getGameServerList().size() >0)
//        {
//            Server gameServer = user.getRoleList().get(0).getGamePlatformList().get(0).getGameServerList().get(0);
//            String defaultGameServerId = String.valueOf(gameServer.getWorldId());
//            model.addAttribute("currentGameServerName",gameServer.getWorldName()+"【"+defaultGameServerId+"】");
//            request.getSession().setAttribute(ConfigConstants.SELECTED_SERVER_KEY, defaultGameServerId);
//            LookupContext.setCurrentServerId(defaultGameServerId);
//        }

        //
        //user.setRoleUserIdList(UserUtils.getTheSameRoleUserIds(user));

		return "modules/sys/sysIndex";
	}
	
	/**
	 * 获取主题方案
	 */
	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.isNotBlank(theme)){
			CookieUtils.setCookie(response, "theme", theme);
		}else{
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:"+request.getParameter("url");
	}
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Map<String, Integer> loginFailMap = (Map<String, Integer>)CacheUtils.get("loginFailMap");
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 30;
	}
	

	@RequestMapping("${adminPath}/download")
	public String download(@RequestParam String filePath,HttpServletResponse response) {
		File file = new File(filePath);
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(filePath);
			response.reset();
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			OutputStream outputStream = new BufferedOutputStream(
					response.getOutputStream());
			byte data[] = new byte[1024];
			while (inputStream.read(data, 0, 1024) >= 0) {
				outputStream.write(data);
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
