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
 * 浮游炮统计
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/funnel")
public class FunnelContoller extends BaseController {

	/*浮游炮统计*/
	@RequestMapping("funnelStatisticsList")
	public String funnelStatisticsList(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		model.addAttribute("active", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		model.addAttribute("funnelDistribution", logDaoTemplate.selectList("funnel.funnelDistribution", parameter));		
		return "modules/logs/funnelStatisticsList";
		
	}
	
	/*浮游炮进阶统计*/
	@RequestMapping("funnelStatisticsFormList")
	public String funnelStatisticsFormList(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException{
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		model.addAttribute("active", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		model.addAttribute("funnelAdvance", logDaoTemplate.selectList("funnel.funnelAdvance", parameter));		
		return "modules/logs/funnelStatisticsFormList";
		
	}
}
