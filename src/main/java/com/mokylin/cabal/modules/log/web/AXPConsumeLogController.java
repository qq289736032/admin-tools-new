package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;

@Controller
@RequestMapping(value = "${adminPath}/log/axpConsumeLog")
public class AXPConsumeLogController extends BaseController {
//	@Resource
//	protected MonitorConfigService monitorConfigService;

	@RequestMapping(value = "axpConsumeLogReport")
	public String moneyGetLogReport(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String roleName = MapUtils.getString(parameter, "roleName");
		setDefaultTableSuffix(parameter);
		Page<Map<String, Object>> axpConsumeLog = logPaging(request,response,"axpLog.findAXPConsumeLog");
		model.addAttribute("axpConsumeLog", axpConsumeLog);
		model.addAttribute("roleName", roleName);
		return "modules/logs/axpConsumeLogReport";
	}
	
	@RequiresPermissions("log.moneyGain.export")
	@RequestMapping(value = "exportXls")
	public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultTableSuffix(parameter);
		List<Map> list=new ArrayList<>();
		List<Map> axpConsumeLog = logDaoTemplate.selectList("axpLog.findAXPConsumeLog",parameter);
		for (Map map : axpConsumeLog) {
			map.put("operate_type", OperationTypeService.getOperationType(map.get("operate_type").toString()));
			list.add(map);
		}
		return super.exportXls(list, "AXP消耗"+System.currentTimeMillis(),"角色名","事件","AXP减少数量","AXP当前数量","时间");
	}
	/**
	 * 创建查询条件，上面的两个操作要求的查询条件是一样的
	 * @param parameter
	 */
	private void createQueryCondition(MybatisParameter parameter) {
		setDefaultTimeRange(parameter);
		String roleName = MapUtils.getString(parameter, "roleName");
		if(null==roleName){
			parameter.put("roleName", "%");
		}else{
			parameter.put("roleName","%"+roleName+"%");
		}
	}
}
