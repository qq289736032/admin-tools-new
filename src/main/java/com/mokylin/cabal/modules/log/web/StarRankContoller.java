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
 * 星盘等级分布
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/star")
public class StarRankContoller extends BaseController {
/**
 * 星盘等级分布
 */
	@RequestMapping("starRankList")
	public String battleStatistics(HttpServletRequest request, HttpServletResponse response, Model model){
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");		
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
    	model.addAttribute("star", logDaoTemplate.selectList("starRank.findStarStatistics", parameter));
		return "modules/logs/starRankList";
		
	}
}
