package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import com.mokylin.cabal.modules.sys.utils.DictUtils;
import com.mokylin.cabal.modules.tools.service.MonitorConfigService;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;

@Controller
@RequestMapping(value = "${adminPath}/log/moneyGetLog")
public class MoneyGetLogController extends BaseController {
//	@Resource
//	protected MonitorConfigService monitorConfigService;

	@RequestMapping(value = "moneyGetLogReport")
	public String moneyGetLogReport(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String roleName = MapUtils.getString(parameter, "roleName");
		int gap = setDefaultDateRange(parameter);
		if(gap > 15){
			addMessage(redirectAttributes, "时间范围必须小于15天");
			return "redirect:"+Global.getAdminPath()+"/log/moneyGetLog/moneyGetLogReport/";
		}
    	Page page = new Page(request,response);
         if(StringUtils.isNoneBlank(roleName) ){
             try {
            	 page = logDaoTemplate.selectPage("moneyFlowLog.findMoneyGetLog", parameter, page, "log_time");
             } catch (Exception e) {
                 logger.error("",e);
             }
         }
		model.addAttribute("page", page);
		model.addAttribute("roleName", roleName);
		return "modules/logs/moneyGetLogReport";
	}
	
	@RequiresPermissions("log.moneyGain.export")
	@RequestMapping(value = "exportXls")
	public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultDateRange(parameter);
		List<Map> list=new ArrayList<>();
		try {
			List<Map<String, Object>>moneyGetLog = logDaoTemplate.selectListByMultiTable("moneyFlowLog.findMoneyGetLog",parameter);
			for (Map map : moneyGetLog) {
				 map.put("money_type", DictUtils.getDictLabel(map.get("money_type").toString(),"money_type","类别错误"));
				 map.put("operate_type", OperationTypeService.getOperationType(map.get("operate_type").toString()));
				 list.add(map);
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		return super.exportXls(list, "货币获取"+System.currentTimeMillis(),"角色名","项目","事件","货币变化数量","货币变化前数量","货币变化后数量","时间");
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
