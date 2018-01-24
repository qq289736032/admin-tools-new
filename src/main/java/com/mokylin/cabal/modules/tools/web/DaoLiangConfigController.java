package com.mokylin.cabal.modules.tools.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.DaoLiangConfigService;

@Controller
@RequestMapping(value = "${adminPath}/tools/daoLiangConfig")
public class DaoLiangConfigController extends BaseController{
	 @Resource
	 protected DaoLiangConfigService daoLiangConfigService;
	
    @RequestMapping( value =  "daoLiangConfig")
    public String daoLiangConfig(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	String pid= (String) parameter.get("pid");
    	if(StringUtils.isNotEmpty(pid)){
	    	Map<String,Object> daoLiangConfig =daoLiangConfigService.selectOneAfterInsertOrUpdate("daoLiang.findDaoLiangByPid",parameter);
	    	model.addAttribute("daoLiangConfig", daoLiangConfig);
    	}
    	List<Map<String,Object>> allDaoLiang = toolDaoTemplate.selectList("daoLiang.findDaoLiang");
    	model.addAttribute("allDaoLiang", allDaoLiang);
    	return "modules/tools/daoLiangConfig";
    }
    
    @RequestMapping( value =  "addDaoLiangConfig")
    public String addDaoLiangConfig(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	daoLiangConfigService.selectOneAfterInsertOrUpdate("daoLiang.findDaoLiangByPid",parameter);
//    	model.addAttribute("daoLiangConfig", daoLiangConfig);
//    	model.addAttribute("pid", MapUtils.getObject(parameter, "pid"));
    	List<Map<String,Object>> allDaoLiang = toolDaoTemplate.selectList("daoLiang.findDaoLiang");
    	model.addAttribute("allDaoLiang", allDaoLiang);
    	model.addAttribute("message", "修改成功！");
    	return "modules/tools/daoLiangConfig";
    }
}
