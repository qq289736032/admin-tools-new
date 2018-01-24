package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.mokylin.cabal.modules.sys.entity.Dict;
import com.mokylin.cabal.modules.sys.utils.DictUtils;

/**
 * 充值排行统计
 * @author Administrator
 * 
 */

@Controller
@RequestMapping(value = "${adminPath}/log/rechargeRank")
public class RechargeRankController extends BaseController {

	@RequestMapping(value = "rechargeRank")
	public String rechargeRank(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认当前时间
//		if(MapUtils.getString(parameter,"createDate") == null) {
//			parameter.put("createDate", DateUtils.getDate());
//		}
		setDefaultTableSuffix(parameter);
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
		Page<Map<String,Object>> page = logPaging(request, response, "rechargeRank.paging");
        model.addAttribute("page", page);
        
        // 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/logs/rechargeRank";
	}

}
