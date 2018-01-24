package com.mokylin.cabal.modules.log.web;

import com.google.common.collect.Lists;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/2
 * admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/log/rank")
public class RankController extends BaseController {


    @RequestMapping("power")
    public String power(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if(!parameter.containsKey("createDateStart")){
            return "modules/logs/powerList";
        }

        setDefaultTableSuffix(parameter);
        Page<Map<String,Object>> page = logPaging(request,response,"powerRank.paging");
        model.addAttribute("page", page);
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/logs/powerList";
    }


    @RequestMapping("singlePower")
    public String singlePower(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if(!parameter.containsKey("createDateStart")){
            return "modules/logs/singlePowerList";
        }

        setDefaultTableSuffix(parameter);
        Page<Map<String,Object>> page = logPaging(request,response,"powerRank.singlePower");
        model.addAttribute("page", page);
        return "modules/logs/singlePowerList";
    }


    @RequestMapping("rechargeRank")
    public String rechargeRank(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if(!parameter.containsKey("createDateStart")){
            return "modules/logs/rechargeRank";
        }

        setDefaultTableSuffix(parameter);
        Page<Map<String,Object>> page = logPaging(request,response,"rechargeRank.paging");
        model.addAttribute("page", page);
        return "modules/logs/rechargeRank";
    }

    @RequestMapping("singleRechargeRank")
    public String singleRechargeRank(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if(!parameter.containsKey("createDateStart")){
            return "modules/logs/singleRechargeRank";
        }

        setDefaultTableSuffix(parameter);
        Page<Map<String,Object>> page = logPaging(request,response,"rechargeRank.singleRechargeRank");
        model.addAttribute("page", page);
        return "modules/logs/singleRechargeRank";
    }

    @RequestMapping("consumeRank")
    public String consumeRank(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if(!parameter.containsKey("createDateStart")){
            return "modules/logs/consumeRank";
        }

        setDefaultTableSuffix(parameter);
        Page<Map<String,Object>> page = logPaging(request,response,"consumeRank.paging");
        model.addAttribute("page", page);
        return "modules/logs/consumeRank";
    }

    @RequestMapping("singleConsumeRank")
    public String singleConsumeRank(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if(!parameter.containsKey("createDateStart")){
            return "modules/logs/singleConsumeRank";
        }

        setDefaultTableSuffix(parameter);
        Page<Map<String,Object>> page = logPaging(request,response,"consumeRank.singleConsumeRank");
        model.addAttribute("page", page);
        return "modules/logs/singleConsumeRank";
    }
    
    @RequestMapping("todayMax")
	public String todayMax(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		if (!parameter.containsKey("createDateStart")) {
			return "modules/logs/todayMaxList";
		}
		setDefaultTableSuffix(parameter);
		List<Map<String, Object>> page = logDaoTemplate.selectList("powerRank.pageMax", parameter);
		model.addAttribute("page", page);

		parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/logs/todayMaxList";
	}
    
    @RequestMapping("pageWorld")
  	public String pageWorld(HttpServletRequest request, HttpServletResponse response, Model model) {
  		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
  		if (!parameter.containsKey("createDateStart")) {
  			return "modules/logs/todayWorldList";
  		}
  		setDefaultTableSuffix(parameter);
  		List<Map<String, Object>> page = logDaoTemplate.selectList("powerRank.pageWorld", parameter);
  		model.addAttribute("page", page);

  		parameter.put("serverIdList", parameter.get("serverIds"));
  		return "modules/logs/todayWorldList";
  	}
    
    

    

}
