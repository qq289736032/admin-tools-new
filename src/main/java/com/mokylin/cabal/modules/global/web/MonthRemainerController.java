package com.mokylin.cabal.modules.global.web;

import java.util.Date;
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
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/global/monthRemainer")
public class MonthRemainerController extends BaseController{
    @RequestMapping( value =  "monthRemainerReport")
    public String monthRemainerReport(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	setDefaultTimeRange(parameter);
    	
    	int isLineChart = MapUtils.getIntValue(parameter, "isLineChart");
//    	if(isLineChart == 1) {
//    	 //图表 时间再向前推一个月
//    		String createDateStart = MapUtils.getString(parameter, "createDateStart");
//    		String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
//    		if (StringUtils.isEmpty(createDateStart) && StringUtils.isEmpty(createDateEnd)) {
//    			parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(new Date(), -37)));
//    			parameter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(new Date(), -31)));
//    		}
//    	} else {
//    		setDefaultTimeRange(parameter);
//    	}
    	//setServerIdList(parameter);
    	
    	List<Map<String,Object>> monthRemainer =globalDaoTemplate.selectList("rizonghe.findMonthRemainerReport",parameter);
    	model.addAttribute("monthRemainer", monthRemainer);
    	// 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
    	if(isLineChart == 1) {
    		return "modules/global/monthRemainerLineChart";
    	} else {
    		return "modules/global/monthRemainerReport";
    	}
    }
}
