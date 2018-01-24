package com.mokylin.cabal.modules.log.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;
/**
 * 资源线统计
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/log/title")
public class TitleActiveContoller  extends  BaseController {
	/**
	 * 称号激活统计
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("titleActiveList")
	public String  titleActive (HttpServletRequest request, HttpServletResponse response, Model model){

		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		//setServerIdList(parameter);
		parameter.setPage(new Page(request, response));
		Page<Map<String, Object>> page = logDaoTemplate.paging("title.titleStatistics", parameter);
		model.addAttribute("page", page); 
		parameter.put("serverIdList", parameter.get("serverIds"));

		return "modules/logs/titleActiveList";




	}

}
