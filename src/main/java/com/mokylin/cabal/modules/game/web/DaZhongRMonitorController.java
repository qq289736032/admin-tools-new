package com.mokylin.cabal.modules.game.web;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.MonitorConfigService;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/game/daZhongRMonitor")
public class DaZhongRMonitorController extends BaseController {
	@Resource
	protected MonitorConfigService monitorConfigService;

	@RequestMapping(value = "daZhongRMonitorReport")
	public String daZhongRMonitorReport(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		Integer amount = MapUtils.getInteger(parameter, "amount");
		if(null==amount){
			parameter.put("amount", 0);
		}
		//setServerIdList(parameter);
		Page<Map<String, Object>> daZhongRMonitor = gamePaging(request, response, "role.findDaZhongRMonitorReport");
		model.addAttribute("daZhongRMonitor", daZhongRMonitor);

		Map<String, Object> monitorConfig = monitorConfigService.selectOne("monitorConfig.findMonitorConfig",parameter);
		model.addAttribute("warnLogin",  MapUtils.getInteger(monitorConfig, "warn_login"));
		model.addAttribute("warnCharge", MapUtils.getInteger(monitorConfig, "warn_charge"));
    	// 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/game/daZhongRMonitorReport";
	}
}
