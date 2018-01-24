package com.mokylin.cabal.modules.log.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.MonitorConfigService;

@Controller
@RequestMapping(value = "${adminPath}/log/upgradeLog")
public class UpgradeLogController extends BaseController {
	// @Resource
	// protected MonitorConfigService monitorConfigService;

	@RequestMapping(value = "upgradeLogReport")
	public String upgradeLogReport(HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes) {
		// MybatisParameter parameter = (MybatisParameter)
		// request.getAttribute("paramMap");
		// String roleName = MapUtils.getString(parameter, "roleName");
		// createQueryCondition(parameter);
		// Page<Map<String, Object>> upgradeLog =
		// logPaging(request,response,"upgradeLog.findUpgradeLogReport");
		// model.addAttribute("upgradeLog", upgradeLog);
		// model.addAttribute("roleName", roleName);
		// return "modules/logs/upgradeLogReport";

		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String roleName = MapUtils.getString(parameter, "roleName");
		model.addAttribute("roleName", roleName);
		createQueryCondition(parameter);
	
		int gap = setDefaultDateRange(parameter);
		if (gap > 15) {
			addMessage(redirectAttributes, "时间范围必须小于15天");
			return "redirect:" + Global.getAdminPath() + "/log/upgradeLog/upgradeLogReport/";
		}

//		 setDefaultTableSuffix(parameter);
		Page page = new Page(request, response);
		if (StringUtils.isNoneBlank(roleName)) {
			try {
				page = logDaoTemplate.selectPage2("upgradeLog.findUpgradeLogReport", parameter, page, "log_time");
			} catch (Exception e) {
				logger.error("", e);
			}
		}
//		model.addAttribute("selectedOperas", parameter.get("operaType"));
//		model.addAttribute("page", page);
//		model.addAttribute("logTime",
//				DateUtils.formatDate(new Date(MapUtils.getLongValue(parameter, "logTime")), "yyyy-MM-dd HH:mm"));
//		return "modules/logs/upgradeLogReport";
		
//		Page<Map<String, Object>> upgradeLog = logPaging(request, response, "upgradeLog.findUpgradeLogReport");
		model.addAttribute("upgradeLog", page);
		model.addAttribute("roleName", roleName);
		return "modules/logs/upgradeLogReport";
	}

	@RequiresPermissions("log.upgrade.export")
	@RequestMapping(value = "exportXls")
	public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		createQueryCondition(parameter);
		List<Map> moneyConsumeLog = logDaoTemplate.selectList("upgradeLog.findUpgradeLogReport", parameter);
		return super.exportXls(moneyConsumeLog, "进阶日志" + System.currentTimeMillis(), "角色名", "项目", "装备名称", "消耗道具",
				"消耗数量", "操作前等级", "操作后等级", "时间");
	}

	/**
	 * 创建查询条件，上面的两个操作要求的查询条件是一样的
	 * 
	 * @param parameter
	 */
	private void createQueryCondition(MybatisParameter parameter) {
		// setDefaultTimeRange(parameter);
		String roleName = MapUtils.getString(parameter, "roleName");
		if (null == roleName) {
			parameter.put("roleName", "%");
		} else {
			parameter.put("roleName", "%" + roleName + "%");
		}
	}
}
