package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * 增幅器
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/amplifier")
public class AmplifierDistributionContoller extends BaseController {

	/*增幅器统计*/
	@RequestMapping("amplifierDistributionList")
	public String amplifierDistributionList(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		model.addAttribute("active", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		int defaultTime = Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd"));
		final int from = MapUtils.getInteger(parameter, "startDate", defaultTime);
		int to = MapUtils.getInteger(parameter, "endDate", defaultTime);
		List<String> list = new ArrayList<String>();
		if (to < from) {
			throw new Exception("日期结束时间大于开始时间！");
		}
		int tableNum = DateUtils.getDays(DateUtils.parseDate(to),DateUtils.parseDate(from));
		for (int i = 1; i <= tableNum + 1; i++) {
			int finalI = i;
			final String date = DateUtils.addDays(String.valueOf(from),finalI - 1);
			String suffix = StringUtils.replace(date,"-","");
			list.add(suffix);
			parameter.put("list", list);
		}
		try {
			model.addAttribute("funnelPrimary", logDaoTemplate.selectList("goodsFlowLog.funnelPrimary", parameter));
		} catch (Exception e) {
		}		
		return "modules/logs/amplifierDistributionList";
		
	}
}
