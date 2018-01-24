package com.mokylin.cabal.modules.global.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/global/platformRechargeConsume")
public class PlatformRechargeConsumeController extends BaseController {

	@RequestMapping(value = "platformRechargeConsume")
	public String platformRechargeConsume(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		setMultiplePlatform(parameter);

		Page<Map<String, Object>> platformRecharge = globalPaging(request, response, "rizonghe.findPlatformRecharge");
		model.addAttribute("platformRecharge", platformRecharge);
		
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/global/platformRechargeConsume";
	}
	@RequestMapping(value = "platformRechargeTimePeriod")
	public String platformRechargeTimePeriod(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		setMultiplePlatform(parameter);
		Page<Map<String, Object>> platformRechargeTimePeriod =  globalPaging(request, response, "rechargeTimeDistribution.findPlatformRechargeTimePeriod");
		model.addAttribute("platformRechargeTimePeriod", platformRechargeTimePeriod);
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/global/platformRechargeTimePeriod";
	}
	@RequestMapping(value = "platformConsume")
	public String platformConsume(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		setMultiplePlatform(parameter);
		Page<Map<String, Object>> platformConsume =  globalPaging(request, response, "rechargeConsume.findPlatformConsume");
		model.addAttribute("platformConsume", platformConsume);
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/global/platformConsume";
	}
	@RequestMapping(value = "platformConsumeTimePeriod")
	public String platformConsumeTimePeriod(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		setMultiplePlatform(parameter);
		Page<Map<String, Object>> platformConsumeTimePeriod =  globalPaging(request, response, "consumeTimeDistribution.findPlatformConsumeTimePeriod");
		model.addAttribute("platformConsumeTimePeriod", platformConsumeTimePeriod);
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/global/platformConsumeTimePeriod";
	}
	
	@RequestMapping(value = "platformConsumeBandingTimePeriod")
	public String platformConsumeBandingTimePeriod(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		setMultiplePlatform(parameter);
		Page<Map<String, Object>> platformConsumeBandingTimePeriod =  globalPaging(request, response, "consumeTimeDistribution.findPlatformConsumeBandingTimePeriod");
		model.addAttribute("platformConsumeTimePeriod", platformConsumeBandingTimePeriod);
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/global/platformConsumeBandingTimePeriod";
	}
}
