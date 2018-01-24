package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 战阶分布
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/battle")
public class BattleStatisticsContoller extends BaseController {
/**
 * 战阶分布统计
 */
	@RequestMapping("battleStatisticsList")
	public String battleStatistics(HttpServletRequest request, HttpServletResponse response, Model model){
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");		
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter); 	
    	model.addAttribute("battle", logDaoTemplate.selectList("battleStatistics.battleStatistics", parameter));
		return "modules/logs/battleStatisticsList";
		
	}
}
