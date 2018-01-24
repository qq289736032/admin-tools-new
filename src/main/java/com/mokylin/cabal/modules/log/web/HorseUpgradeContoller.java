package com.mokylin.cabal.modules.log.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;
/**
 * 坐骑分布
 * @author Administrator
 *
 */


@Controller
@RequestMapping(value = "${adminPath}/log/horseUpgrade")
public class HorseUpgradeContoller extends BaseController {

	@RequestMapping("horseUpgradeList")
	public String roleUpgradeList(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认当前时间
		setDefaultLogDay(parameter);
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
		model.addAttribute("vipLevelMap", vipLevelMap());
		model.addAttribute("viplevel", parameter.get("viplevel"));
		//坐骑等级分布
		model.addAttribute("horse", logDaoTemplate.selectList("horseUpgrade.horseGrade", parameter)); 
		//幻化类型
		model.addAttribute("horseType", logDaoTemplate.selectList("horseUpgrade.horseType", parameter));  
		//每日坐骑进阶成功人数分布
		model.addAttribute("horseStatistics", logDaoTemplate.selectList("horseUpgrade.horseStatistics", parameter)); 
		//每日坐骑进阶清0人数分布
		model.addAttribute("horseEmpty", logDaoTemplate.selectList("horseUpgrade.horseEmpty", parameter)); 
		// 把serverIdList转成string
		parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/logs/horseUpgradeList";
		}


}
