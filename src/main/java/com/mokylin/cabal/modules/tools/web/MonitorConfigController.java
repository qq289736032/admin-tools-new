package com.mokylin.cabal.modules.tools.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.MonitorConfigService;

@Controller
@RequestMapping(value = "${adminPath}/tools/monitorConfig")
public class MonitorConfigController extends BaseController{
	 @Resource
	 protected MonitorConfigService monitorConfigService;
	
    @RequestMapping( value =  "monitorConfig")
    public String monitorConfig(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	Map<String,Object> monitorConfig =monitorConfigService.selectOne("monitorConfig.findMonitorConfig",parameter);
    	model.addAttribute("monitorConfig", monitorConfig);
    	return "modules/tools/monitorConfig";
    }
    
    @RequestMapping( value =  "addMonitorConfig")
    public String addMonitorConfig(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	String currentId = MapUtils.getString(parameter, "currentId");
    	if(StringUtils.isNotEmpty(currentId)){
    		toolDaoTemplate.update("monitorConfig.update", parameter);
    	}
    	Map<String,Object> monitorConfig =monitorConfigService.selectOne("monitorConfig.findMonitorConfig",parameter);
    	model.addAttribute("monitorConfig", monitorConfig);
    	model.addAttribute("message", "修改成功！");
    	return "modules/tools/monitorConfig";
    }
}
