package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 军阶统计
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/rank")
public class RankStatisticsContoller extends BaseController {
/**
 * 军阶分布统计
 */
	@RequestMapping("rankStatisticsList")
	public String battleStatistics(HttpServletRequest request, HttpServletResponse response, Model model){
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");		
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
    	model.addAttribute("rank", logDaoTemplate.selectList("rankStatistics.findRankStatistics", parameter));
		return "modules/logs/rankStatisticsList";
		
	}
}
