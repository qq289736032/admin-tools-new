package com.mokylin.cabal.modules.global.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.MonitorConfigService;

@Controller
@RequestMapping(value = "${adminPath}/global/platformHistoryMonthData")
public class PlatformHistoryMonthDataController extends BaseController {
//	@Resource
//	protected MonitorConfigService monitorConfigService;

	@RequestMapping(value = "platformHistoryMonthData")
	public String platformHistoryMonthData(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultMonthlyRange(parameter);
		setMultiplePlatform(parameter);
		//设置默认月份
		List<Map<String, Object>> monthData = globalDaoTemplate.selectList("monthlyIntegrated.findMonthData",parameter);
		List<Map<String, Object>> newServerMonthData = globalDaoTemplate.selectList("monthlyIntegrated.findNewServerMonthData",parameter);
		List<Map<String, Object>> oldServerMonthData = globalDaoTemplate.selectList("monthlyIntegrated.findOldServerMonthData",parameter);
		model.addAttribute("monthData", monthData);
		model.addAttribute("newServerMonthData", newServerMonthData);
		model.addAttribute("oldServerMonthData", oldServerMonthData);
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/global/platformHistoryMonthData";
	}
}
