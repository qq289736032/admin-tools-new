package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 组队副本
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/taskTeam")
public class TaskTeamContoller extends BaseController {
/**
 * 组队副本
 * 
 */
	@RequestMapping("taskTeamList")
	public String taskTeamList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
    	model.addAttribute("teamTask", logDaoTemplate.selectList("taskTeam.teamTask", parameter));
    	model.addAttribute("firstGetNumber", logDaoTemplate.selectList("taskTeam.firstGetNumber", parameter));
    	model.addAttribute("teamNumber", logDaoTemplate.selectList("taskTeam.teamNumber", parameter));
    	model.addAttribute("teamPayNumber", logDaoTemplate.selectList("taskTeam.teamPayNumber", parameter));
    	model.addAttribute("taskTeamBrand", logDaoTemplate.selectList("taskTeam.taskTeamBrand", parameter));
		return "modules/logs/taskTeamList";
		
	}
}
