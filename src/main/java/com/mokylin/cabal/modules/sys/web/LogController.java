/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.entity.Log;
import com.mokylin.cabal.modules.sys.service.LogService;

/**
 * 日志Controller
 * @author 稻草鸟人
 * @version 2014-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Log> page = logService.find(new Page<Log>(request, response), paramMap); 
        model.addAttribute("page", page);
        model.addAllAttributes(paramMap);
		return "modules/sys/logList";
	}

}
