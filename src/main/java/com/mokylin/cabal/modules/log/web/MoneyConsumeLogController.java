package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.DictUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/log/moneyConsumeLog")
public class MoneyConsumeLogController extends BaseController {
    //	@Resource
    //	protected MonitorConfigService monitorConfigService;

    @RequestMapping(value = "moneyConsumeLogReport")
    public String moneyConsumeReport(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String roleName = MapUtils.getString(parameter, "roleName");
        model.addAttribute("roleName", roleName);
        int gap = setDefaultDateRange(parameter);
        if (gap > 15) {
            addMessage(redirectAttributes, "时间范围必须小于15天");
            return "redirect:" + Global.getAdminPath() + "/log/moneyConsumeLog/moneyConsumeLogReport/";
        }

//		setDefaultTableSuffix(parameter);
        Page page = new Page(request, response);
        if (StringUtils.isNoneBlank(roleName)) {
            try {
                page = logDaoTemplate.selectPage("moneyFlowLog.findMoneyConsumeLog", parameter, page, "log_time");
            } catch (Exception e) {
                logger.error("", e);
            }
        }

        model.addAttribute("selectedOperas", parameter.get("operaType"));
        model.addAttribute("page", page);
        model.addAttribute("logTime", DateUtils.formatDate(new Date(MapUtils.getLongValue(parameter, "logTime")), "yyyy-MM-dd HH:mm"));
        return "modules/logs/moneyConsumeLogReport";
    }


    @RequiresPermissions("log.moneyConsume.export")
    @RequestMapping(value = "exportXls")
    public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultDateRange(parameter);
   	    List<Map> list=new ArrayList<>();
		try {
			   List<Map<String, Object>>  moneyConsumeLog = logDaoTemplate.selectListByMultiTable("moneyFlowLog.findMoneyConsumeLog", parameter);
		        for (Map map : moneyConsumeLog) {
		            map.put("money_type", DictUtils.getDictLabel(map.get("money_type").toString(), "money_type", "类别错误"));
//					map.put("operate_type", OperationTypeService.getOperationType(map.get("operate_type").toString()));
		            map.put("log_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "log_time")), "yyyy-MM-dd HH:mm"));
		            list.add(map);
		        }
		} catch (Exception e) {
			logger.error("",e);
		}
       

        return super.exportXls(list, "货币消耗" + System.currentTimeMillis(), "角色名", "项目", "事件", "货币变化数量", "货币变化前数量", "货币变化后数量", "时间");
    }


    @RequestMapping("dailyOperationConsumeReport")
    public String dailyOperationConsumeReport(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
//		setTableSuffix(parameter);
        String roleName = MapUtils.getString(parameter, "roleName");
        model.addAttribute("roleName", roleName);
        int gap = setDefaultDateRange(parameter);

        if (gap > 15) {
            addMessage(redirectAttributes, "时间范围必须小于15天");
            return "redirect:" + Global.getAdminPath() + "/log/moneyConsumeLog/dailyOperationConsumeReport/";
        }

        List operaTypeList = new ArrayList();
//		setDefaultTableSuffix(parameter);
        Page page = new Page(request, response);
        try {
            page = logDaoTemplate.selectPage("moneyFlowLog.findDailyOperationConsumeReport", parameter, page, "log_time");
        } catch (Exception e) {
            logger.error("", e);
        }

        model.addAttribute("selectedOperas", parameter.get("operaType"));
        model.addAttribute("page", page);
        return "modules/logs/operationConsumeReport";
    }

    /**
     * 创建查询条件，上面的两个操作要求的查询条件是一样的
     *
     * @param parameter
     */
    private void createQueryCondition(MybatisParameter parameter) {
        //	setDefaultTimeRange(parameter);
        String roleName = MapUtils.getString(parameter, "roleName");
        if (null == roleName) {
            parameter.put("roleName", "%");
        } else {
            parameter.put("roleName", "%" + roleName + "%");
        }
    }
}
