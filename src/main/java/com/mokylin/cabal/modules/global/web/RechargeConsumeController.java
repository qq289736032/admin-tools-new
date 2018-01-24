package com.mokylin.cabal.modules.global.web;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.global.service.RechargeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/global/rechargeConsume")
public class RechargeConsumeController extends BaseController{
	
	@Autowired
	private RechargeService rechargeService;
	
	// 日充值统计
	@RequestMapping(value = "dailyRechargeReport")
	public String dailyRechargeReport(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    setDefaultTimeRange(parameter);
	    //setServerIdList(parameter);
	    List<Map<String, Object>> dailyList = globalDaoTemplate.selectList("rechargeConsume.findDailyRechargeData", parameter);
	    model.addAttribute("dailyList", dailyList);
	    
	    List<Map<String, Object>> dailyPlatFormList = globalDaoTemplate.selectList("rechargeConsume.findDailyPlatformRechargeData", parameter);
	    model.addAttribute("dailyPlatFormList", dailyPlatFormList);
	    
	    List<Map<String, Object>> mapList = globalDaoTemplate.selectList("rechargeConsume.findRechargeByPlatform", parameter);
        model.addAttribute("mapList", mapList);
        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
	    return "modules/global/dailyRechargeReport"; 
	}
	
	// 日消费统计
	@RequestMapping(value = "dailyConsumeReport")
	public String dailyConsumeReport(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    setDefaultTimeRange(parameter);
	    //setServerIdList(parameter);
	    List<Map<String, Object>> dailyList = globalDaoTemplate.selectList("rechargeConsume.findDailyConsumeData", parameter);
	    model.addAttribute("dailyList", dailyList);
	    
	    List<Map<String, Object>> dailyPlatFormList = globalDaoTemplate.selectList("rechargeConsume.findDailyPlatformConsumeData", parameter);
	    model.addAttribute("dailyPlatFormList", dailyPlatFormList);
	   
	    List<Map<String, Object>> mapList = globalDaoTemplate.selectList("rechargeConsume.findConsumeByPlatform", parameter);
        model.addAttribute("mapList", mapList);
        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
	    return "modules/global/dailyConsumeReport"; 
	}
	
	// 日消费统计（绑定元宝）
	@RequestMapping(value = "dailyBindConsumeReport")
	public String dailyBindConsumeReport(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    setDefaultTimeRange(parameter);
	    //setServerIdList(parameter);
	    List<Map<String, Object>> dailyList = globalDaoTemplate.selectList("rechargeConsume.findDailyBindConsumeData", parameter);
	    model.addAttribute("dailyList", dailyList);
	    
	    List<Map<String, Object>> dailyPlatFormList = globalDaoTemplate.selectList("rechargeConsume.findDailyPlatformBindConsumeData", parameter);
	    model.addAttribute("dailyPlatFormList", dailyPlatFormList);
	   
	    List<Map<String, Object>> mapList = globalDaoTemplate.selectList("rechargeConsume.findBindConsumeByPlatform", parameter);
        model.addAttribute("mapList", mapList);
        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
	    return "modules/global/dailyBindConsumeReport"; 
	}
	
	// 月消费统计
	@RequestMapping(value = "monthConsumeReport")
	public String monthConsumeReport(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultMonthlyRange(parameter);
		setMultiplePlatform(parameter);
		    
		List<Map<String, Object>> monthList = globalDaoTemplate.selectList("rechargeConsume.findMonthConsumeData", parameter);
		model.addAttribute("monthList", monthList);
		    
		List<Map<String, Object>> monthPlatFormList = globalDaoTemplate.selectList("rechargeConsume.findMonthPlatformConsumeData", parameter);
		model.addAttribute("monthPlatFormList", monthPlatFormList);
		   
		model.addAttribute("selectedPids", parameter.get("pids"));
		
		return "modules/global/monthConsumeReport"; 
	}
	
	// 月消费统计（绑定元宝）
	@RequestMapping(value = "monthBindConsumeReport")
	public String monthBindConsumeReport(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultMonthlyRange(parameter);
		setMultiplePlatform(parameter);
			    
		List<Map<String, Object>> monthList = globalDaoTemplate.selectList("rechargeConsume.findMonthBindConsumeData", parameter);
		model.addAttribute("monthList", monthList);
			    
		List<Map<String, Object>> monthPlatFormList = globalDaoTemplate.selectList("rechargeConsume.findMonthBindPlatformConsumeData", parameter);
		model.addAttribute("monthPlatFormList", monthPlatFormList);
			   
		model.addAttribute("selectedPids", parameter.get("pids"));
		
		return "modules/global/monthBindConsumeReport"; 
		}
	

	// 充值区间分布
	@RequestMapping(value = "rechargeDistribution")
	public String rechargeDistribution(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    setDefaultTimeRange(parameter);
	    //setServerIdList(parameter);
	    List<Map<String, Object>> distributionList = globalDaoTemplate.selectList("rechargeDistribution.findRechargeDistribution", parameter);
	    List<Map<String, Object>> logDayList = globalDaoTemplate.selectList("rechargeDistribution.findRechargeDistributionLogDay", parameter);;
	    
	    List<Map<String, Object>> resultList = rechargeService.getDistributionList(distributionList, logDayList);
	    
	    model.addAttribute("resultList", resultList);
	    
	    // 下面2个表格的数据
	    for(Map<String, Object> distribution : distributionList) {
	    	for(Map<String, Object> logDay : logDayList) {
	    		if(!MapUtils.getString(distribution, "log_day").equals(MapUtils.getString(logDay, "log_day"))) 
	    			continue;
	    		int section = MapUtils.getIntValue(distribution, "section");
	    		logDay.put("num" + section, MapUtils.getIntValue(distribution, "num"));
	    		logDay.put("amount" + section, MapUtils.getIntValue(distribution, "amount"));
	    	}
	    }
	    // 页面总计
	    List<Integer> totalNumList = new ArrayList<Integer>();
	    List<Integer> totalAmountList = new ArrayList<Integer>();
	    for(int i = 1; i <= 25; i ++) {
	    	int totalNum = 0;
	    	int totalAmount = 0;
	    	for(Map<String, Object> distribution : distributionList) {
	    		int section = MapUtils.getIntValue(distribution, "section");
	    		if(section !=i ) 
	    			continue;
	    		totalNum += MapUtils.getIntValue(distribution, "num");
	    		totalAmount += MapUtils.getIntValue(distribution, "amount");
		    }
	    	totalNumList.add(totalNum);
	    	totalAmountList.add(totalAmount);
	    }
	    
	    model.addAttribute("tableList", logDayList);
	    model.addAttribute("totalNumList", totalNumList);
	    model.addAttribute("totalAmountList", totalAmountList);
	   
	    // 把serverIdList转成string
	    parameter.put("serverIdList", parameter.get("serverIds"));
	    return "modules/global/rechargeDistribution"; 
	}
	
	// 月充值数据
	@RequestMapping(value = "monthlyRecharge")
	public String monthlyRecharge(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultMonthlyRange(parameter);
		setMultiplePlatform(parameter);
	    
	    List<Map<String, Object>> rechargeList = globalDaoTemplate.selectList("rechargeDistribution.findMonthlyRecharge", parameter);
	    model.addAttribute("rechargeList", rechargeList);
	    
	    List<Map<String, Object>> newServerRechargeList = globalDaoTemplate.selectList("rechargeDistribution.findNewServerMonthlyRecharge", parameter);
	    List<Map<String, Object>> oldServerRechargeList = globalDaoTemplate.selectList("rechargeDistribution.findOldServerMonthlyRecharge", parameter);
	    
	    List<Map<String, Object>> newOldList = new ArrayList<Map<String, Object>>();
	    for(int i = 0; i < rechargeList.size(); i ++) {
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	int logMonth = MapUtils.getIntValue(rechargeList.get(i), "log_month");
	    	map.put("log_month", logMonth);
	    	map.put("income", MapUtils.getIntValue(rechargeList.get(i), "income"));
	    	for (Map<String, Object> newServerMap: newServerRechargeList) {
	    		if(MapUtils.getIntValue(newServerMap, "log_month")!= logMonth)
	    			continue;
	    		map.put("new_au", MapUtils.getIntValue(newServerMap, "au"));
	    		map.put("new_pa", MapUtils.getIntValue(newServerMap, "pa"));
	    		map.put("new_pay_times", MapUtils.getIntValue(newServerMap, "pay_times"));
	    		map.put("new_income", MapUtils.getIntValue(newServerMap, "income"));
	    		map.put("new_pay_rate", MapUtils.getIntValue(newServerMap, "new_income"));
	    		map.put("new_arpu", MapUtils.getIntValue(newServerMap, "arpu"));
	    	}
	    	for (Map<String, Object> oldServerMap: oldServerRechargeList) {
	    		if(MapUtils.getIntValue(oldServerMap, "log_month")!= logMonth)
	    			continue;
	    		map.put("old_au", MapUtils.getIntValue(oldServerMap, "au"));
	    		map.put("old_pa", MapUtils.getIntValue(oldServerMap, "pa"));
	    		map.put("old_pay_times", MapUtils.getIntValue(oldServerMap, "pay_times"));
	    		map.put("old_income", MapUtils.getIntValue(oldServerMap, "income"));
	    		map.put("old_pay_rate", MapUtils.getIntValue(oldServerMap, "new_income"));
	    		map.put("old_arpu", MapUtils.getIntValue(oldServerMap, "arpu"));
	    	}
	    	newOldList.add(map);
	    }
	    model.addAttribute("newOldList", newOldList);
	    
	    List<Map<String, Object>> platformList = globalDaoTemplate.selectList("rechargeDistribution.findPlatformListRecharge", parameter);
	    model.addAttribute("platformList", platformList);
	    model.addAttribute("selectedPids", parameter.get("pids"));
	    
	    return "modules/global/monthlyRecharge"; 
	}
	
	// 充值消费时点分布
	@RequestMapping(value = "rechargeConsumeTimePiont")
	public String rechargeConsumeTimePiont(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    setDefaultTimeRange(parameter);
	    //setServerIdList(parameter);
	    
	    List<Map<String, Object>> rechargeList = logDaoTemplate.selectList("statRealTimeService.findRechargeTimePointDistribution", parameter);
	    List<Map<String, Object>> consumeList = logDaoTemplate.selectList("statRealTimeService.findConsumeTimePointDistribution", parameter);
	    List<Map<String, Object>> consumeBandingList = logDaoTemplate.selectList("statRealTimeService.findConsumeBandingTimePointDistribution", parameter);
	    
	    model.addAttribute("tableList1", transformHour(rechargeList));
	    model.addAttribute("tableList2", transformHour(consumeList));
	    model.addAttribute("tableList3", transformHour(consumeBandingList));
	    
    	// 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
	    return "modules/global/rechargeConsumeTimePiont"; 
	}
	
	// 充值消费排行
	@RequestMapping(value = "rechargeConsumeRanking")
	public String rechargeConsumeRanking(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    setDefaultTimeRange(parameter);
	    //setServerIdList(parameter);
	    
		if (StringUtils.isEmpty(MapUtils.getString(parameter, "jeValue"))) {
			parameter.put("jeValue", "0");
		}
	    
	    List<Map<String, Object>> rechargeList = globalDaoTemplate.selectList("rechargeConsume.findRechargeRanking", parameter);
	    List<Map<String, Object>> rechargePlatformList = globalDaoTemplate.selectList("rechargeConsume.findRechargePlatformRanking", parameter);
	    List<Map<String, Object>> consumeList = globalDaoTemplate.selectList("rechargeConsume.findConsumeRanking", parameter);
	    List<Map<String, Object>> consumePlatformList = globalDaoTemplate.selectList("rechargeConsume.findConsumePlatformRanking", parameter);
	    List<Map<String, Object>> consumeBandingList = globalDaoTemplate.selectList("rechargeConsume.findConsumeBandingRanking", parameter);
	    List<Map<String, Object>> consumeBandingPlatformList = globalDaoTemplate.selectList("rechargeConsume.findConsumePlatformBandingRanking", parameter);
	    
	    model.addAttribute("tableList1", rechargeList);
	    model.addAttribute("tableList2", rechargePlatformList);
	    model.addAttribute("tableList3", consumeList);
	    model.addAttribute("tableList4", consumePlatformList);
	    model.addAttribute("tableList5", consumeBandingList);
	    model.addAttribute("tableList6", consumeBandingPlatformList);
	    
    	// 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
	    return "modules/global/rechargeConsumeRanking"; 
	}
	
	// 把小时转化成 1:00 格式
	private  List<Map<String, Object>> transformHour(List<Map<String, Object>> list) {
		for (Map<String, Object> map: list) {
    		int logHour = MapUtils.getIntValue(map, "log_hour");
    		String hourStr = String.valueOf(logHour).substring(8);
    		if(hourStr.startsWith("0")) {
    			hourStr = hourStr.substring(1);
    		}
    		map.put("log_hour", hourStr + ":00");
    	}
		return list;
	}
}
