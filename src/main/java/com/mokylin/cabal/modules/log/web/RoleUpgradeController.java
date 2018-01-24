package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;

import java.util.List;
import java.util.Map;

/**
 * 用户升级时长
 * @author Administrator
 * 
 */

@Controller
@RequestMapping(value = "${adminPath}/log/roleUpgrade")
public class RoleUpgradeController extends BaseController {

	@RequestMapping(value = {"list", ""})
	public String roleUpgradeList(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认当前时间
		if(MapUtils.getString(parameter,"startDate") == null) {
			parameter.put("startDate", DateUtils.getDate());
		}
		setDefaultLogDay(parameter);
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
		
		model.addAttribute("vipLevelMap", vipLevelMap());

        model.addAttribute("list", logDaoTemplate.selectList("roleUpgrade.roleUpgradeTime", parameter));	

        // 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/logs/roleUpgradeList";
	}


	@RequestMapping("levelDistribution")
	public String levelDistribution(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		if(MapUtils.getString(parameter,"startDate") == null) {
			parameter.put("startDate", DateUtils.getDate());
		}
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
		setDefaultLogDay(parameter);
		model.addAttribute("vipLevelMap", vipLevelMap());
		model.addAttribute("viplevel", parameter.get("viplevel"));

		List<Map<String,Object>> list = logDaoTemplate.selectList("roleUpgrade.levelDistribution", parameter);
		int total = 0;
		for(Map<String,Object> map : list){
			total = total + MapUtils.getIntValue(map,"num");
		}
		model.addAttribute("total",total);
		model.addAttribute("list", list);

        // 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/logs/levelDistribution";
	}
	
	/**
	 * 用户升级时长导出
	 */
	@RequestMapping(value ="export", method= RequestMethod.POST)
	 public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
			
	    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    	//默认当前时间
			if(MapUtils.getString(parameter,"createDate") == null) {
				parameter.put("createDate", DateUtils.getDate());
			}
			List<Map> roleUpgrade = logDaoTemplate.selectList("roleUpgrade.roleUpgradeTime", parameter);
			return super.exportXls(roleUpgrade, "用户升级时长"+System.currentTimeMillis(),"等级", "升级时长（分）","平均升级时长（分）");
	 }
	
}
