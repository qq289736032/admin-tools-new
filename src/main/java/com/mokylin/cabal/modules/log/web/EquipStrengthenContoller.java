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
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.entity.Dict;
import com.mokylin.cabal.modules.sys.utils.DictUtils;

/**
 * 装备统计
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/equip")
public class EquipStrengthenContoller extends BaseController {
/**
 * 装备强化统计
 */
	@RequestMapping("equipStrengthenList")
	public String equipStrengthen(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String createDateStart = MapUtils.getString(parameter, "createDateStart");
		setDefaultLogDay(parameter);
    	//setServerIdList(parameter);
    	model.addAttribute("equip", logDaoTemplate.selectList("equipStrengthen.equipStatistics", parameter));
		parameter.put("serverIdList", parameter.get("serverIds"));
		
		return "modules/logs/equipStrengthenList";
		
	}
}
