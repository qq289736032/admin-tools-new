package com.mokylin.cabal.modules.global.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/global/dayActiveLevel")
public class DayActiveUserLevelController extends BaseController{
    @RequestMapping( value =  "dayActiveUserLevelList")
    public String dayActiveUserLevelList(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);  	
    	model.addAttribute("dayActiveUserLevel", logDaoTemplate.selectList("roleLogin.dayActiveUserLevel",parameter));
    	return "modules/global/dayActiveUserLevelList";

    }
}
