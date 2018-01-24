package com.mokylin.cabal.modules.charge.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/charge/platform")
public class PlatformRechargeController extends BaseController {

	@RequestMapping(value = "platformRechargeList")
	public String platformRechargeList(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTimeRange(parameter);
		model.addAttribute("platformRecharge", chargeDaoTemplate.selectList("charge.platformRecharge", parameter));
		model.addAttribute("platformRechargetotal", chargeDaoTemplate.selectList("charge.platformRechargetotal", parameter));
		model.addAttribute("selectedPids", parameter.get("pids"));
		return "modules/charge/platformRechargeList";
	}
}
