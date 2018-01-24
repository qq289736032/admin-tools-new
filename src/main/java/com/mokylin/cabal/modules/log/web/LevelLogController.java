package com.mokylin.cabal.modules.log.web;

import java.util.Date;
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
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.AuthCondition;

@Controller
@RequestMapping(value = "${adminPath}/log/levelLog")
public class LevelLogController extends BaseController {

	@RequestMapping(value = "levelLogReport")
	public String levelLogReport(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		AuthCondition.filterPlatform(parameter);    //根据平台显示
		//默认查询时间范围为7天
        setDefaultTimeRange(parameter);
		String roleName = MapUtils.getString(parameter, "roleName");
		createQueryCondition(parameter);
		Page<Map<String, Object>> levelLog = logPaging(request,response,"roleUpgrade.findLevelLogReport");
		for (Map<String, Object> map : levelLog.getList()) {
			map.put("upgrade_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "upgrade_time")), "yyyy-MM-dd HH:mm"));
		}
		model.addAttribute("levelLog", levelLog);
		model.addAttribute("roleName", roleName);
		return "modules/logs/levelLogReport";
	}
	
	@RequiresPermissions("log.level.export")
	@RequestMapping(value = "exportXls")
	public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		createQueryCondition(parameter);
		List<Map> levelLogs = logDaoTemplate.selectList("roleUpgrade.findLevelLogReport",parameter);
		for (Map<String, Object> map : levelLogs) {
			map.put("upgrade_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "upgrade_time")), "yyyy-MM-dd HH:mm"));
		}
		return super.exportXls(levelLogs, "等级日志"+System.currentTimeMillis(), "角色ID","角色名","等级","升级时间","日志时间");
	}
	/**
	 * 创建查询条件，上面的两个操作要求的查询条件是一样的
	 * @param parameter
	 */
	private void createQueryCondition(MybatisParameter parameter) {
//		setDefaultTimeRange(parameter);
		String roleName = MapUtils.getString(parameter, "roleName");
		if(null==roleName){
			parameter.put("roleName", "%");
		}else{
			parameter.put("roleName","%"+roleName+"%");
		}
	}
}
