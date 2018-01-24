package com.mokylin.cabal.modules.log.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;


/**
 * 宝石镶嵌
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/jewel")
public class JewelUpgradeContoller extends BeautyController {
	/**
	 * 宝石镶嵌统计
	 * 
	 */
	@RequestMapping("jewelUpgradeList")
	public String jewelUpgrade(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		model.addAttribute("jewel", logDaoTemplate.selectList("jewel.jewelUpgrade", parameter));
		parameter.put("serverIdList", parameter.get("serverIds"));


		return "modules/logs/jewelUpgradeList";
		
	} 

}
