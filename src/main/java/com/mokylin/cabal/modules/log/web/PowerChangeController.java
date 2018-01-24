package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.cache.ICache;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;

@Controller
@RequestMapping(value = "${adminPath}/log/powerChange")
public class PowerChangeController  extends  BaseController{
	
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		    MybatisParameter parameter= (MybatisParameter) request.getAttribute("paramMap");
	        String roleId = MapUtils.getString(parameter,"roleId");
	        String roleName = MapUtils.getString(parameter,"roleName");
	        setDefaultTableSuffix(parameter);
		    //默认当前时间
			if(!parameter.containsKey("createDateStart")){
				parameter.put("createDateStart", DateUtils.getDate());
			}
				
	        List operaTypeList = new ArrayList();
	        if (!parameter.containsKey("operaTypeList") && parameter.containsKey("operaType")) {
	            operaTypeList.add(parameter.get("operaType").toString());
	            parameter.put("operaTypeList", operaTypeList);
	        } else if (parameter.containsKey("operaTypeList")) {
	            operaTypeList = (List) parameter.get("operaTypeList");
	            if (operaTypeList.size() == OperationTypeService.getOperaTypeMap().size()) {
	                parameter.put("operaTypeList", null);
	            }
	        }
           parameter.setPage(new Page(request, response));
	        Page<Map<String, Object>> page = new Page(request,response);
	        if(StringUtils.isNotEmpty(roleName) || StringUtils.isNotEmpty(roleId)){
	        	       page = logDaoTemplate.paging("powerChange.findPowerChange", parameter);
	        }
	        model.addAttribute("page", page);
	        model.addAttribute("selectedOperas", parameter.get("operaType"));
	        return "modules/logs/powerChangeList";
	}
	
	

}
