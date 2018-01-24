package com.mokylin.cabal.modules.log.web;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;

/**
 * 翅膀分布
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/cloak")
public class CloakController extends BeautyController {
	
	@RequestMapping("cloakList")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultLogDay(parameter);
    	//setServerIdList(parameter);
    	model.addAttribute("cloak", logDaoTemplate.selectList("cloak.cloakUser", parameter));  
    	model.addAttribute("cloakType", logDaoTemplate.selectList("cloak.cloakType", parameter)); 
    	
    	model.addAttribute("cloakActive", logDaoTemplate.selectList("cloak.cloakStatistics", parameter)); 
    	model.addAttribute("cloakNumber", logDaoTemplate.selectList("cloak.cloakDayStatistics", parameter)); 
    	parameter.put("serverIdList", parameter.get("serverIds"));
    	return "modules/logs/cloakList";
	}

}
