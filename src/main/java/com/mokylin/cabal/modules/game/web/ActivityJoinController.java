package com.mokylin.cabal.modules.game.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 游戏参与度
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/game/activityJoin")
public class ActivityJoinController extends BaseController {
	
	
	
	@RequestMapping(value = "activityJoinList")
	public String activityjoinList(HttpServletRequest request, HttpServletResponse response, Model model){
		
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        
        setDefaultTimeRange(parameter);
        
        parameter.setPage(new Page(request, response));
        
        if(StringUtils.isBlank((CharSequence) parameter.get("activityName"))){
	        Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.activityJoinList", parameter);
	        model.addAttribute("page", page);
			return "modules/game/activityJoinList";
        }else{
        	Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.activityJoinSearchByName", parameter);
	        model.addAttribute("page", page);
			return "modules/game/activityJoinSearch";
        }
	}
	
	//材料副本
	@RequestMapping(value = "caiLiaoFuBenList")
	public String caiLiaoFuBen(HttpServletRequest request, HttpServletResponse response, Model model){
		
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		
		
		setDefaultTimeRange(parameter);
		
		parameter.setPage(new Page(request, response));
		
		if(StringUtils.isBlank((CharSequence) parameter.get("activityName"))){
			Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.caiLiaoFuBenList", parameter);
			model.addAttribute("page", page);
			return "modules/game/caiLiaoFuBenList";
		}else{
			Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.caiLiaoFubenSearchByName", parameter);
			model.addAttribute("page", page);
			return "modules/game/caiLiaoFuBenSearch";
		}
	}
	
	//轩辕副本
	@RequestMapping(value = "xuanYuanFuBenList")
	public String xuanYuanFuBen(HttpServletRequest request, HttpServletResponse response, Model model){
		
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		
		
		/*setDefaultTimeRange(parameter);*/
		setDefault7DayRange(parameter);
		
		parameter.setPage(new Page(request, response));
		
		//统计某天通关总数
		Map<String, String> selectOne = gameDaoTemplate.selectOne("activityJoin.xuanYuanFuBenCount", parameter);
		model.addAttribute("count", selectOne.get("count"));
		
		//统计某天按难度排列通关数
		Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.xuanYuanFuBenByDay", parameter);
		model.addAttribute("page", page);
		return "modules/game/xuanYuanFuBenList";
	}
	
	//十绝阵
	@RequestMapping(value = "shiJueZhenList")
	public String shiJueZhen(HttpServletRequest request, HttpServletResponse response, Model model){
		
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		
		
		setDefaultTimeRange(parameter);
		
		parameter.setPage(new Page(request, response));
		
		if(StringUtils.isBlank((CharSequence) parameter.get("activityName"))){
			Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.shiJueZhenList", parameter);
			model.addAttribute("page", page);
			return "modules/game/shiJueZhenList";
		}else{
			Page<Map<String, Object>> page = gameDaoTemplate.paging("activityJoin.shiJueZhenSearchByName", parameter);
			model.addAttribute("page", page);
			return "modules/game/shiJueZhenSearch";
		}
	}
}
