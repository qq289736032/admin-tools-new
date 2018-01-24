package com.mokylin.cabal.modules.global.web;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.DaoLiangConfigService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/global/openServiceIncome")
public class OpenServiceIncomeController extends BaseController {
	@Resource
	 protected DaoLiangConfigService daoLiangConfigService;

	@RequestMapping(value = "openServiceIncomeMany")
	public String openServiceIncomeMany(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		setMultiplePlatform(parameter);
		String createDateStart = MapUtils.getString(parameter, "createDateStart").replace("-", "");
		String createDateEnd = MapUtils.getString(parameter, "createDateEnd").replace("-", "");
		Page<Map<String, Object>> openManyServiceIncome = globalPaging(request, response, "rizonghe.findOpenManyServiceIncome");
		//计算所有和倒量参数有关的数据
		List<Map<String,Object>> allDaoLiang = toolDaoTemplate.selectList("daoLiang.findDaoLiang");
		for (Map<String, Object> mapIncome : openManyServiceIncome.getList()) {
			for (Map<String, Object> mapDaoLiang : allDaoLiang) {
				String incomePid=MapUtils.getString(mapIncome, "pid");
				String daoliangPid=MapUtils.getString(mapDaoLiang, "pid");
				if(StringUtils.isBlank(incomePid)||StringUtils.isBlank(daoliangPid)) continue;
				if(incomePid!=daoliangPid) continue;
				double cpaCost = MapUtils.getInteger(mapDaoLiang, "cpa")*MapUtils.getInteger(mapIncome, "sum_pv");//倒量成本
				if(cpaCost>0){
					mapIncome.put("cpa_cost", cpaCost);
					mapIncome.put("income_back_rate1", MapUtils.getInteger(mapIncome, "income_1")/cpaCost);
					mapIncome.put("income_back_rate3", MapUtils.getInteger(mapIncome, "income_3")/cpaCost);
					mapIncome.put("income_back_rate7", MapUtils.getInteger(mapIncome, "income_7")/cpaCost);
					mapIncome.put("income_back_rate10", MapUtils.getInteger(mapIncome, "income_10")/cpaCost);
					mapIncome.put("income_back_rate14", MapUtils.getInteger(mapIncome, "income_14")/cpaCost);
					mapIncome.put("income_back_rate30", MapUtils.getInteger(mapIncome, "income_30")/cpaCost);
					mapIncome.put("income_back_rate60", MapUtils.getInteger(mapIncome, "income_60")/cpaCost);
					mapIncome.put("income_back_rate90", MapUtils.getInteger(mapIncome, "income_90")/cpaCost);
					break;
				}
			 }
		}
		model.addAttribute("openManyServiceIncome", openManyServiceIncome);
		model.addAttribute("createDateStart", createDateStart);
		model.addAttribute("createDateEnd", createDateEnd);
		model.addAttribute("selectedPids", parameter.get("pids"));

		return "modules/global/openServiceIncomeMany";
	}

	@RequestMapping(value = "openServiceIncomeSingle")
	public String openServiceIncomeSingle(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		String pid = (String) parameter.get("pid");
		if (StringUtils.isNotEmpty(pid)) {
			Page<Map<String, Object>> openSingleServiceIncome = globalPaging(request, response, "rizonghe.findOpenSingleServiceIncome");
			//计算所有和倒量参数有关的数据
			List<Map<String,Object>> allDaoLiang = toolDaoTemplate.selectList("daoLiang.findDaoLiang");
			for (Map<String, Object> mapIncome : openSingleServiceIncome.getList()) {
				for (Map<String, Object> mapDaoLiang : allDaoLiang) {
					String incomePid=MapUtils.getString(mapIncome, "pid");
					String daoliangPid=MapUtils.getString(mapDaoLiang, "pid");
					if(StringUtils.isBlank(incomePid)||StringUtils.isBlank(daoliangPid)) continue;
					if(incomePid.equals(daoliangPid)){
						double cpaCost = MapUtils.getInteger(mapDaoLiang, "cpa")*MapUtils.getInteger(mapIncome, "sum_pv");//倒量成本
						if(cpaCost>0){
							mapIncome.put("cpa_cost", cpaCost);
							mapIncome.put("income_back_rate1", MapUtils.getInteger(mapIncome, "income_1")/cpaCost);
							mapIncome.put("income_back_rate3", MapUtils.getInteger(mapIncome, "income_3")/cpaCost);
							mapIncome.put("income_back_rate7", MapUtils.getInteger(mapIncome, "income_7")/cpaCost);
							mapIncome.put("income_back_rate10", MapUtils.getInteger(mapIncome, "income_10")/cpaCost);
							mapIncome.put("income_back_rate14", MapUtils.getInteger(mapIncome, "income_14")/cpaCost);
							mapIncome.put("income_back_rate30", MapUtils.getInteger(mapIncome, "income_30")/cpaCost);
							mapIncome.put("income_back_rate60", MapUtils.getInteger(mapIncome, "income_60")/cpaCost);
							mapIncome.put("income_back_rate90", MapUtils.getInteger(mapIncome, "income_90")/cpaCost);
							break;
						}
					}
				}
			}
			model.addAttribute("openSingleServiceIncome", openSingleServiceIncome);
			model.addAttribute("pid", pid);
		}
		return "modules/global/openServiceIncomeSingle";
	}
}
