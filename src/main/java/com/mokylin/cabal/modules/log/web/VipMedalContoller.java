package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;
/**
 * VIP进阶
 * @author Administrator
 *
 */


@Controller
@RequestMapping(value = "${adminPath}/log/medal")
public class VipMedalContoller extends BaseController {

	@RequestMapping("vipMedalList")
	public String roleUpgradeList(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认当前时间
		setDefaultLogDay(parameter);
		// 默认不选服务器的取当前用户有权限访问的服务器
		setServerIdList(parameter);
		model.addAttribute("vipLevelMap", vipLevelMap());
		model.addAttribute("viplevel", parameter.get("viplevel"));
		//坐骑等级分布
		model.addAttribute("medalGrade", logDaoTemplate.selectList("vipMedal.medalGrade", parameter)); 
		//每日坐骑进阶成功人数分布
		model.addAttribute("medalStatistics", logDaoTemplate.selectList("vipMedal.medalStatistics", parameter)); 
		
		// 把serverIdList转成string
		parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/logs/vipMedalList";
		}


}
