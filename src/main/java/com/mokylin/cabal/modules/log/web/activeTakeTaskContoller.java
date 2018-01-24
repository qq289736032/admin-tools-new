package com.mokylin.cabal.modules.log.web;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 活跃度任务参加情况统计
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/active")
public class activeTakeTaskContoller extends BaseController {
/**
 *活跃度任务参加情况统计
 * @throws ParseException 
 */
	@RequestMapping("activeTakeTaskList")
	public String battleStatistics(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");		
		String createDateStart = MapUtils.getString(parameter, "createDateStart");
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		model.addAttribute("active", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		model.addAttribute("activeTakeTask", logDaoTemplate.selectList("activeTakeTask.activeTakeTask", parameter));		
		return "modules/logs/activeTakeTaskList";
		
	}
}
