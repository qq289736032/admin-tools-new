package com.mokylin.cabal.modules.global.web;

import java.util.ArrayList;
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

import com.mokylin.cabal.common.cache.ICache;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 平台统计
 * 作者: maojs
 * 日期: 2014/12/29 10:17
 * 项目: cabal-tools
 */

@Controller
@RequestMapping(value = "${adminPath}/global/platformStatistics")
public class PlatformStatisticsController extends BaseController {

	// 平台统计
    @RequestMapping(value = "platformStatistics")
    public String platformStatistics(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        setMultiplePlatform(parameter);

        //按平台分组查询，收入，导量，活跃，等
        List<Map<String, Object>> statisticsList = globalDaoTemplate.selectList("platformStatistics.findByPlatform", parameter);
        
        Map<String, Object> sumValue = globalDaoTemplate.selectOne("platformStatistics.findSumValue", parameter);
        
        Date createDateStart = DateUtils.parseDate(parameter.get("createDateStart"));
        Date createDateEnd = DateUtils.parseDate(parameter.get("createDateEnd"));
        
        int dayBetween = (int)DateUtils.getNumberOfDaysBetween(createDateStart, createDateEnd);
        
        for(Map<String, Object> map : statisticsList) {
        	int pId = MapUtils.getIntValue(map, "pid");
        	
        	// 收入
        	int income = MapUtils.getIntValue(map, "income");
        	if(MapUtils.getIntValue(sumValue, "sum_income") != 0) {
        		float p_income = income * 1.0f / MapUtils.getIntValue(sumValue, "sum_income");
            	map.put("p_income", p_income);
        	}
        	
        	// 导量（注册）
        	int dru = MapUtils.getIntValue(map, "dru");
        	if(MapUtils.getIntValue(sumValue, "sum_dru") != 0) {
        		float p_dru = dru * 1.0f / MapUtils.getIntValue(sumValue, "sum_dru");
            	map.put("p_dru", p_dru);
        	}
        	
        	// 开服数
        	int kaifu = MapUtils.getIntValue(map, "kaifu");
        	if(MapUtils.getIntValue(sumValue, "sum_kaifu") != 0) {
        		float p_kaifu = kaifu * 1.0f / MapUtils.getIntValue(sumValue, "sum_kaifu");
            	map.put("p_kaifu", p_kaifu);
        	}
        	
        	// 消费
        	int consume = MapUtils.getIntValue(map, "consume");
        	if(MapUtils.getIntValue(sumValue, "sum_consume") != 0) {
        		float p_consume = consume * 1.0f / MapUtils.getIntValue(sumValue, "sum_consume");
            	map.put("p_consume", p_consume);
        	}
        	
        	// 付费率
        	float payRate = MapUtils.getFloatValue(map, "pay_rate");
        	// arpu
        	float arpu = MapUtils.getFloatValue(map, "arpu");
        	
        	// 环比前 
            MybatisParameter parameterBefore = new MybatisParameter();
            parameterBefore.put("pid", pId);
            parameterBefore.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterBefore.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));
            parameterBefore.put("startDate", StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)), "-", ""));
            parameterBefore.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)), "-", ""));
    		
            Map<String, Object> beforeValue = globalDaoTemplate.selectOne("platformStatistics.findByPid", parameterBefore);
            int beforeIncomeValue = MapUtils.getIntValue(beforeValue, "income");
            float p_income_before = 0;
            if (beforeIncomeValue != 0) {
            	p_income_before = (income - beforeIncomeValue) * 1.0f / beforeIncomeValue;
            }
        	map.put("p_income_before", p_income_before);
        	
        	int beforeDruValue = MapUtils.getIntValue(beforeValue, "dru");
            float p_dru_before = 0;
            if (beforeDruValue != 0) {
            	p_dru_before = (dru - beforeDruValue) * 1.0f / beforeDruValue;
            }
        	map.put("p_dru_before", p_dru_before);
        	
        	int beforeKaifuValue = MapUtils.getIntValue(beforeValue, "kaifu");
            float p_kaifu_before = 0;
            if (beforeKaifuValue != 0) {
            	p_kaifu_before = (kaifu - beforeKaifuValue) * 1.0f / beforeKaifuValue;
            }
        	map.put("p_kaifu_before", p_kaifu_before);
        	
        	int beforeConsumeValue = MapUtils.getIntValue(beforeValue, "consume");
            float p_consume_before = 0;
            if (beforeConsumeValue != 0) {
            	p_consume_before = (consume - beforeConsumeValue) * 1.0f / beforeConsumeValue;
            }
        	map.put("p_consume_before", p_consume_before);
        	
        	float beforePayRateValue = MapUtils.getFloatValue(beforeValue, "pay_rate");
            float p_pay_rate_before = 0;
            if (beforePayRateValue != 0) {
            	p_pay_rate_before = (payRate - beforePayRateValue) * 1.0f / beforePayRateValue;
            }
        	map.put("p_pay_rate_before", p_pay_rate_before);
        	
        	float beforeArpuValue = MapUtils.getFloatValue(beforeValue, "arpu");
            float p_arpu_before = 0;
            if (beforeArpuValue != 0) {
            	p_arpu_before = (arpu - beforeArpuValue) * 1.0f / beforeArpuValue;
            }
        	map.put("p_arpu_before", p_arpu_before);
        	
        	// 环比后
            MybatisParameter parameterAfter = new MybatisParameter();
            parameterAfter.put("pid", pId);
            parameterAfter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterAfter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
            parameterAfter.put("startDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)), "-", ""));
            parameterAfter.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)), "-", ""));
            Map<String, Object> afterValue = globalDaoTemplate.selectOne("platformStatistics.findByPid", parameterAfter);
            int afterIncomeValue = MapUtils.getIntValue(afterValue, "income");
            float p_income_after = 0;
            if (income != 0) {
            	p_income_after = (afterIncomeValue - income)  * 1.0f / income;
            }
        	map.put("p_income_after", p_income_after);
        	
        	int afterDruValue = MapUtils.getIntValue(afterValue, "dru");
            float p_dru_after = 0;
            if (dru != 0) {
            	p_dru_after = (afterDruValue - dru)  * 1.0f / dru;
            }
        	map.put("p_dru_after", p_dru_after);
        	
        	int afterKaifuValue = MapUtils.getIntValue(afterValue, "kaifu");
            float p_kaifu_after = 0;
            if (kaifu != 0) {
            	p_kaifu_after = (afterKaifuValue - kaifu)  * 1.0f / kaifu;
            }
        	map.put("p_kaifu_after", p_kaifu_after);
        	
        	int afterConsumeValue = MapUtils.getIntValue(afterValue, "consume");
            float p_consume_after = 0;
            if (consume != 0) {
            	p_consume_after = (afterConsumeValue - consume)  * 1.0f / consume;
            }
        	map.put("p_consume_after", p_consume_after);
        	
        	float afterPayRateValue = MapUtils.getFloatValue(afterValue, "pay_rate");
            float p_pay_rate_after = 0;
            if (payRate != 0) {
            	p_pay_rate_after = (afterPayRateValue - payRate)  * 1.0f / payRate;
            }
        	map.put("p_pay_rate_after", p_pay_rate_after);
        	
        	float afterArpuValue = MapUtils.getFloatValue(afterValue, "arpu");
            float p_arpu_after = 0;
            if (arpu != 0) {
            	p_arpu_after = (afterArpuValue - arpu)  * 1.0f / arpu;
            }
        	map.put("p_arpu_after", p_arpu_after);
        }
        
        model.addAttribute("statisticsList", statisticsList);
        
    	// 活跃度 去log表里取数据
        List<Map<String, Object>> activeStatisticsList = logDaoTemplate.selectList("roleLogin.findActiveStatistics", parameter);
        
        Map<String, Object> sumActiveValue = logDaoTemplate.selectOne("roleLogin.findSumActive", parameter);
        for(Map<String, Object> map : activeStatisticsList) {
        	int pId = MapUtils.getIntValue(map, "pid");
        	
        	int au = MapUtils.getIntValue(map, "au");
        	if(MapUtils.getIntValue(sumActiveValue, "sum_au") != 0) {
        		float p_au = au * 1.0f / MapUtils.getIntValue(sumActiveValue, "sum_au");
            	map.put("p_au", p_au);
        	}
        	
        	// 环比前 
            MybatisParameter parameterBefore = new MybatisParameter();
            parameterBefore.put("pid", pId);
            parameterBefore.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterBefore.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));

            parameterBefore.put("startDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)), "-", ""));
            parameterBefore.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)), "-", ""));
    		
            Map<String, Object> beforeValue = logDaoTemplate.selectOne("roleLogin.findActiveStatisticsByPid", parameterBefore);
            int beforeActiveValue = MapUtils.getIntValue(beforeValue, "au");
            float p_au_before = 0;
            if (beforeActiveValue != 0) {
            	p_au_before = (au - beforeActiveValue) * 1.0f / beforeActiveValue;
            }
        	map.put("p_au_before", p_au_before);
        	
        	// 环比后
            MybatisParameter parameterAfter = new MybatisParameter();
            parameterAfter.put("pid", pId);
            parameterAfter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterAfter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
            parameterAfter.put("startDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)), "-", ""));
            parameterAfter.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)), "-", ""));
            Map<String, Object> afterValue = logDaoTemplate.selectOne("roleLogin.findActiveStatisticsByPid", parameterAfter);
            int afterActiveValue = MapUtils.getIntValue(afterValue, "au");
            float p_au_after = 0;
            if (au != 0) {
            	p_au_after = (afterActiveValue - au)  * 1.0f / au;
            }
        	map.put("p_au_after", p_au_after);
        }
        model.addAttribute("activeStatisticsList", activeStatisticsList);
        
        // 注册转化率
        List<Map<String, Object>> regConvertionList = globalDaoTemplate.selectList("regConvertion.findRegConvertionStatistics", parameter);
        
        for(Map<String, Object> map : regConvertionList) {
        	int pId = MapUtils.getIntValue(map, "pid");
        	
        	int roleNum = MapUtils.getIntValue(map, "role_num");
        	// 环比前 
            MybatisParameter parameterBefore = new MybatisParameter();
            parameterBefore.put("pid", pId);
            parameterBefore.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterBefore.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));
            parameterBefore.put("startDate", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterBefore.put("endDate", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));
    		
            Map<String, Object> beforeValue = globalDaoTemplate.selectOne("regConvertion.findRegConvertionStatisticsByPid", parameterBefore);
            int beforeRoleNumValue = MapUtils.getIntValue(beforeValue, "role_num");
            float p_role_num_before = 0;
            if (beforeRoleNumValue != 0) {
            	p_role_num_before = (roleNum - beforeRoleNumValue) * 1.0f / beforeRoleNumValue;
            }
        	map.put("p_role_num_before", p_role_num_before);
        	
        	// 环比后
            MybatisParameter parameterAfter = new MybatisParameter();
            parameterAfter.put("pid", pId);
            parameterAfter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterAfter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
            parameterAfter.put("startDate", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterAfter.put("endDate", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
    		
            Map<String, Object> afterValue = globalDaoTemplate.selectOne("regConvertion.findRegConvertionStatisticsByPid", parameterAfter);
            int afterRoleNumValue = MapUtils.getIntValue(afterValue, "role_num");
            float p_role_num_after = 0;
            if (roleNum != 0) {
            	p_role_num_after = (afterRoleNumValue - roleNum)  * 1.0f / roleNum;
            }
            
            int role_num = MapUtils.getIntValue(map, "role_num");
            int visit_times = MapUtils.getIntValue(map, "visit_times");
            if (visit_times == 0) {
            	map.put("create_role_rate", 0);
            } else {
            	map.put("create_role_rate", role_num * 1.f/visit_times * 1.f);
            }
            
        	map.put("p_role_num_after", p_role_num_after);
        }
        model.addAttribute("regConvertionList", regConvertionList);
        
		model.addAttribute("selectedPids", parameter.get("pids"));
        return "modules/global/platformStatistics";
    }

    // 平台在线统计
    @RequestMapping(value = "platformOnlineStatistics")
    public String platformOnlineStatistics(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        setMultiplePlatform(parameter);
        
        // 平台在线时段统计
        List<Map<String, Object>> onlineDistribution = globalDaoTemplate.selectList("platformStatistics.findOnlineDistribution", parameter);
        model.addAttribute("onlineDistribution", onlineDistribution);
        
        // 在线总计
        //已col开头的字段一共288个，这里取其中最大的值即为pccu
        List<Map<String, Object>> onlineTotal = globalDaoTemplate.selectList("rizonghe.findOnlineTotal", parameter);
        calculateMaxPCCU(onlineTotal);
        model.addAttribute("onlineTotal", onlineTotal);
        
        List<Map<String, Object>> onlineTotalPieGraph = globalDaoTemplate.selectList("rizonghe.findOnlineTotalPieGraph", parameter);
        int totalAccu = 0;
        for(Map<String, Object> map : onlineTotalPieGraph) {
        	totalAccu += MapUtils.getIntValue(map, "acu");
        }
        if(totalAccu == 0) { // 分母不能为0
        	totalAccu = 1;
        }
        for(Map<String, Object> map : onlineTotalPieGraph) {
        	map.put("p_acu_percent", MapUtils.getIntValue(map, "acu")*1.0 / totalAccu);
        }
        
        model.addAttribute("onlineTotalPieGraph", onlineTotalPieGraph);
        
        // 平台服务器在线监测
        List<Map<String, Object>> onlineMonitor = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> map : onlineTotal) {
        	Map<String, Object> newMap = new HashMap<String, Object>(); 
        	int pId = MapUtils.getIntValue(map, "pid");
        	int logDay = MapUtils.getIntValue(map, "log_day");
        	int serverTotal = MapUtils.getIntValue(map, "server_total");
        	
        	parameter.put("log_day", logDay);
        	parameter.put("pid", pId);
        	
        	newMap.put("log_day", logDay);
        	newMap.put("pid", pId);
        	newMap.put("server_total", serverTotal);
        	
        	parameter.put("ACCUStart", 0);
			parameter.put("ACCUEnd", 40);
            Map<String, Object> map40less = globalDaoTemplate.selectOne("rizonghe.findACCUDistributionByPid", parameter);
            int accu_40less = MapUtils.getIntValue(map40less, "server_count");
            newMap.put("accu_40less", accu_40less);
            newMap.put("accu_40less_percent", accu_40less*100.0/serverTotal);
            
            parameter.put("ACCUStart", 40);
			parameter.put("ACCUEnd", 100);
            Map<String, Object> map100less = globalDaoTemplate.selectOne("rizonghe.findACCUDistributionByPid", parameter);
            int accu_100less = MapUtils.getIntValue(map100less, "server_count");
            newMap.put("accu_100less", accu_100less);
            newMap.put("accu_100less_percent", accu_100less*100.0/serverTotal);
            
            parameter.put("ACCUStart", 100);
			parameter.put("ACCUEnd", 200);
            Map<String, Object> map200less = globalDaoTemplate.selectOne("rizonghe.findACCUDistributionByPid", parameter);
            int accu_200less = MapUtils.getIntValue(map200less, "server_count");
            newMap.put("accu_200less", accu_200less);
            newMap.put("accu_200less_percent", accu_200less*100.0/serverTotal);
            
            parameter.put("ACCUStart", 200);
			parameter.put("ACCUEnd", 500);
            Map<String, Object> map500less = globalDaoTemplate.selectOne("rizonghe.findACCUDistributionByPid", parameter);
            int accu_500less = MapUtils.getIntValue(map500less, "server_count");
            newMap.put("accu_500less", accu_500less);
            newMap.put("accu_500less_percent", accu_500less*100.0/serverTotal);
            
            parameter.put("ACCUStart", 500);
			parameter.put("ACCUEnd", null);
            Map<String, Object> map500more = globalDaoTemplate.selectOne("rizonghe.findACCUDistributionByPid", parameter);
            int accu_500more = MapUtils.getIntValue(map500more, "server_count");
            newMap.put("accu_500more", accu_500more);
            newMap.put("accu_500more_percent", accu_500more*100.0/serverTotal);
            
            onlineMonitor.add(newMap);
        }
        model.addAttribute("onlineMonitor", onlineMonitor);
        
        model.addAttribute("selectedPids", parameter.get("pids"));
        
        return "modules/global/platformOnlineStatistics";
    }
    
    // 平台注册转化统计
    @RequestMapping(value = "platformRegConvertionStatistics")
    public String platformRegConvertionStatistics(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        setMultiplePlatform(parameter);
        // 注册分布
        List<Map<String, Object>> mapList = globalDaoTemplate.selectList("platformStatistics.findRegDistribution", parameter);
        model.addAttribute("regDistribution", mapList);
        
        List<Map<String, Object>> convertionList = globalDaoTemplate.selectList("regConvertion.findRegConvertionStatisticsByDay", parameter);
        for (Map<String, Object> map : convertionList) {
            int role_num = MapUtils.getIntValue(map, "role_num");
            int visit_times = MapUtils.getIntValue(map, "visit_times");
            if(visit_times == 0) {
            	map.put("create_role_rate", 0);
            	map.put("enter_name_rate", 0);
            } else {
            	map.put("create_role_rate", role_num * 1.f/visit_times * 1.f);
	            int login_times = MapUtils.getIntValue(map, "login_times");
	            map.put("login_rate", login_times * 1.f/visit_times * 1.f);
            }
        }
        model.addAttribute("convertionList", convertionList);
        model.addAttribute("selectedPids", parameter.get("pids"));
        return "modules/global/platformRegConvertionStatistics";
    }
    
    // 平台活跃统计
    @RequestMapping(value = "platformActiveStatistics")
    public String platformActiveStatistics(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        setMultiplePlatform(parameter);
        // 活跃度 去log表里取数据
        List<Map<String, Object>> activeStatisticsList = logDaoTemplate.selectList("roleLogin.findActiveStatistics", parameter);
        
        Date createDateStart = DateUtils.parseDate(parameter.get("createDateStart"));
        Date createDateEnd = DateUtils.parseDate(parameter.get("createDateEnd"));
        
        int dayBetween = (int)DateUtils.getNumberOfDaysBetween(createDateStart, createDateEnd);
        
        Map<String, Object> sumActiveValue = logDaoTemplate.selectOne("roleLogin.findSumActive", parameter);
        Map<String, Object> sumOldActiveValue = logDaoTemplate.selectOne("roleLogin.findSumOldActive", parameter);
        
        for(Map<String, Object> map : activeStatisticsList) {
        	int pId = MapUtils.getIntValue(map, "pid");
        	
        	int au = MapUtils.getIntValue(map, "au");
        	if(MapUtils.getIntValue(sumActiveValue, "sum_au") != 0) {
        		float p_au = au * 1.0f / MapUtils.getIntValue(sumActiveValue, "sum_au");
            	map.put("p_au", p_au);
        	}
        	
        	// 环比前 
            MybatisParameter parameterBefore = new MybatisParameter();
            parameterBefore.put("pid", pId);
            parameterBefore.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterBefore.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));
    		
            Map<String, Object> beforeValue = logDaoTemplate.selectOne("roleLogin.findActiveStatisticsByPid", parameterBefore);
            int beforeActiveValue = MapUtils.getIntValue(beforeValue, "au");
            float p_au_before = 0;
            if (beforeActiveValue != 0) {
            	p_au_before = (au - beforeActiveValue) * 1.0f / beforeActiveValue;
            }
        	map.put("p_au_before", p_au_before);
        	
        	// 环比后
            MybatisParameter parameterAfter = new MybatisParameter();
            parameterAfter.put("pid", pId);
            parameterAfter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterAfter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
    		
            Map<String, Object> afterValue = logDaoTemplate.selectOne("roleLogin.findActiveStatisticsByPid", parameterAfter);
            int afterActiveValue = MapUtils.getIntValue(afterValue, "au");
            float p_au_after = 0;
            if (au != 0) {
            	p_au_after = (afterActiveValue - au)  * 1.0f / au;
            }
        	map.put("p_au_after", p_au_after);
        	
        	// 去新活跃
        	parameter.put("pid", pId);
        	Map<String, Object> oldActiveValue = logDaoTemplate.selectOne("roleLogin.findOldActiveStatisticsByPid", parameter);
        	int oldAu = MapUtils.getIntValue(oldActiveValue, "old_au");
        	int sumOldAu = MapUtils.getIntValue(sumOldActiveValue, "sum_old_au");
        	
        	map.put("old_au", oldAu);
        	float p_old_au = 0;
        	if(sumOldAu != 0) {
        		p_old_au = oldAu * 1.0f / sumOldAu;
        	}
        	map.put("p_old_au", p_old_au);
        	
        	// 环比前 
            MybatisParameter parameterOldBefore = new MybatisParameter();
            parameterOldBefore.put("pid", pId);
            parameterOldBefore.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterOldBefore.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));
    		
            Map<String, Object> beforeOldValue = logDaoTemplate.selectOne("roleLogin.findActiveStatisticsByPid", parameterOldBefore);
            int beforeOldActiveValue = MapUtils.getIntValue(beforeOldValue, "old_au");
            float p_old_au_before = 0;
            if (beforeOldActiveValue != 0) {
            	p_old_au_before = (oldAu - beforeOldActiveValue) * 1.0f / beforeOldActiveValue;
            }
        	map.put("p_old_au_before", p_old_au_before);
        	
        	// 环比后
            MybatisParameter parameterOldAfter = new MybatisParameter();
            parameterOldAfter.put("pid", pId);
            parameterOldAfter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterOldAfter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
            
            Map<String, Object> afterOldValue = logDaoTemplate.selectOne("roleLogin.findActiveStatisticsByPid", parameterOldAfter);
	        int afterOldActiveValue = MapUtils.getIntValue(afterOldValue, "old_au");
	        float p_old_au_after = 0;
	        if (oldAu != 0) {
	        	p_old_au_after = (afterOldActiveValue - oldAu)  * 1.0f / oldAu;
	        }
	      	map.put("p_old_au_after", p_old_au_after);
	      	
        }
        model.addAttribute("activeStatisticsList", activeStatisticsList);
        
        model.addAttribute("selectedPids", parameter.get("pids"));
        return "modules/global/platformActiveStatistics";
    }
    
 // 平台活跃统计（去新活跃）
    @RequestMapping(value = "platformActiveStatisticsList")
    public String platformActiveStatisticsList(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        setMultiplePlatform(parameter);
        // 活跃度 去log表里取数据
        List<Map<String, Object>> activeStatisticsList = logDaoTemplate.selectList("roleLogin.findActiveStatistics", parameter);
        Map<String, Object> sumOldActiveValue = logDaoTemplate.selectOne("roleLogin.findSumOldActive", parameter);
        
        for(Map<String, Object> map : activeStatisticsList) {
        	int pId = MapUtils.getIntValue(map, "pid");
        	// 去新活跃
        	parameter.put("pid", pId);
        	Map<String, Object> oldActiveValue = logDaoTemplate.selectOne("roleLogin.findOldActiveStatisticsByPid", parameter);
        	int oldAu = MapUtils.getIntValue(oldActiveValue, "old_au");
        	int sumOldAu = MapUtils.getIntValue(sumOldActiveValue, "sum_old_au");
        	
        	map.put("old_au", oldAu);
        	float p_old_au = 0;
        	if(sumOldAu != 0) {
        		p_old_au = oldAu * 1.0f / sumOldAu;
        	}
        	map.put("p_old_au", p_old_au);
        	
	      	// 去新活跃用户构成 免费 【0,100】	【100,1000】	【1000，10000】	【10000+】
	      	parameter.put("amountStart", 0);
	      	parameter.put("amountEnd", 100);
	      	Map<String, Object> recharge100_lessMap = logDaoTemplate.selectOne("roleRecharge.findRechargeOldActiveDistributionByPid", parameter);
	      	int recharge100_less = MapUtils.getIntValue(recharge100_lessMap, "oau_recharge_count");
	      	map.put("recharge100_less", recharge100_less);
	      	
	      	parameter.put("amountStart", 100);
	      	parameter.put("amountEnd", 1000);
	      	Map<String, Object> recharge1000_lessMap = logDaoTemplate.selectOne("roleRecharge.findRechargeOldActiveDistributionByPid", parameter);
	    	int recharge1000_less = MapUtils.getIntValue(recharge1000_lessMap, "oau_recharge_count");
	      	map.put("recharge1000_less", recharge1000_less);
	      	
	      	parameter.put("amountStart", 1000);
	      	parameter.put("amountEnd", 10000);
	      	Map<String, Object> recharge10000_lessMap = logDaoTemplate.selectOne("roleRecharge.findRechargeOldActiveDistributionByPid", parameter);
	    	int recharge10000_less = MapUtils.getIntValue(recharge10000_lessMap, "oau_recharge_count");
	      	map.put("recharge10000_less", recharge10000_less);
	      	
	      	parameter.put("amountStart", 10000);
	      	parameter.put("amountEnd", null);
	      	Map<String, Object> recharge10000_moreMap = logDaoTemplate.selectOne("roleRecharge.findRechargeOldActiveDistributionByPid", parameter);
	    	int recharge10000_more = MapUtils.getIntValue(recharge10000_moreMap, "oau_recharge_count");
	      	map.put("recharge10000_more", recharge10000_more);
	      	
	      	int recharge0 = oldAu - recharge10000_more - recharge10000_less - recharge1000_less- recharge100_less;
	      	map.put("recharge0", recharge0);
        }
        model.addAttribute("activeStatisticsList", activeStatisticsList);
        
        model.addAttribute("selectedPids", parameter.get("pids"));
        return "modules/global/platformActiveStatisticsList";
    }
    
    /**
     * 计算PCCU值-即取的所有已col开头的字段的最大值
     *
     * @param list
     */
    public void calculateMaxPCCU(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            int max = 0;
            for (int i = 1; i <= 288; i++) {
                max = max > MapUtils.getIntValue(map, "col" + i, 0) ? max : MapUtils.getIntValue(map, "col" + i, 0);
            }
            map.put("pccu", max);
        }
    }
    
	// 平台统计
    @RequestMapping(value = "platformStatisticsTwo")
    public String platformStatisticsTwo(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        setMultiplePlatform(parameter);

        //按平台分组查询，收入，导量，活跃，等
        List<Map<String, Object>> statisticsList = globalDaoTemplate.selectList("platformStatistics.findByPlatform", parameter);
        
        Map<String, Object> sumValue = globalDaoTemplate.selectOne("platformStatistics.findSumValue", parameter);
        
        Date createDateStart = DateUtils.parseDate(parameter.get("createDateStart"));
        Date createDateEnd = DateUtils.parseDate(parameter.get("createDateEnd"));
        
        int dayBetween = (int)DateUtils.getNumberOfDaysBetween(createDateStart, createDateEnd);
        
        for(Map<String, Object> map : statisticsList) {
        	int pId = MapUtils.getIntValue(map, "pid");
        	
        	// 收入
        	int income = MapUtils.getIntValue(map, "income");
        	if(MapUtils.getIntValue(sumValue, "sum_income") != 0) {
        		float p_income = income * 1.0f / MapUtils.getIntValue(sumValue, "sum_income");
            	map.put("p_income", p_income);
        	}
        	
        	// 导量（注册）
        	int dru = MapUtils.getIntValue(map, "dru");
        	if(MapUtils.getIntValue(sumValue, "sum_dru") != 0) {
        		float p_dru = dru * 1.0f / MapUtils.getIntValue(sumValue, "sum_dru");
            	map.put("p_dru", p_dru);
        	}
        	
        	// 开服数
        	int kaifu = MapUtils.getIntValue(map, "kaifu");
        	if(MapUtils.getIntValue(sumValue, "sum_kaifu") != 0) {
        		float p_kaifu = kaifu * 1.0f / MapUtils.getIntValue(sumValue, "sum_kaifu");
            	map.put("p_kaifu", p_kaifu);
        	}
        	
        	// 消费
        	int consume = MapUtils.getIntValue(map, "consume");
        	if(MapUtils.getIntValue(sumValue, "sum_consume") != 0) {
        		float p_consume = consume * 1.0f / MapUtils.getIntValue(sumValue, "sum_consume");
            	map.put("p_consume", p_consume);
        	}
        	
        	// 付费率
        	float payRate = MapUtils.getFloatValue(map, "pay_rate");
        	// arpu
        	float arpu = MapUtils.getFloatValue(map, "arpu");
        	
        	// 环比前 
            MybatisParameter parameterBefore = new MybatisParameter();
            parameterBefore.put("pid", pId);
            parameterBefore.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)));
            parameterBefore.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)));
            parameterBefore.put("startDate", StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateStart, -1-dayBetween)), "-", ""));
            parameterBefore.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateEnd, -1-dayBetween)), "-", ""));
            Map<String, Object> beforeValue = globalDaoTemplate.selectOne("platformStatistics.findByPid", parameterBefore);
            int beforeIncomeValue = MapUtils.getIntValue(beforeValue, "income");
            float p_income_before = 0;
            if (beforeIncomeValue != 0) {
            	p_income_before = (income - beforeIncomeValue) * 1.0f / beforeIncomeValue;
            }
        	map.put("p_income_before", p_income_before);
        	
        	int beforeDruValue = MapUtils.getIntValue(beforeValue, "dru");
            float p_dru_before = 0;
            if (beforeDruValue != 0) {
            	p_dru_before = (dru - beforeDruValue) * 1.0f / beforeDruValue;
            }
        	map.put("p_dru_before", p_dru_before);
        	
        	int beforeKaifuValue = MapUtils.getIntValue(beforeValue, "kaifu");
            float p_kaifu_before = 0;
            if (beforeKaifuValue != 0) {
            	p_kaifu_before = (kaifu - beforeKaifuValue) * 1.0f / beforeKaifuValue;
            }
        	map.put("p_kaifu_before", p_kaifu_before);
        	
        	int beforeConsumeValue = MapUtils.getIntValue(beforeValue, "consume");
            float p_consume_before = 0;
            if (beforeConsumeValue != 0) {
            	p_consume_before = (consume - beforeConsumeValue) * 1.0f / beforeConsumeValue;
            }
        	map.put("p_consume_before", p_consume_before);
        	
        	float beforePayRateValue = MapUtils.getFloatValue(beforeValue, "pay_rate");
            float p_pay_rate_before = 0;
            if (beforePayRateValue != 0) {
            	p_pay_rate_before = (payRate - beforePayRateValue) * 1.0f / beforePayRateValue;
            }
        	map.put("p_pay_rate_before", p_pay_rate_before);
        	
        	float beforeArpuValue = MapUtils.getFloatValue(beforeValue, "arpu");
            float p_arpu_before = 0;
            if (beforeArpuValue != 0) {
            	p_arpu_before = (arpu - beforeArpuValue) * 1.0f / beforeArpuValue;
            }
        	map.put("p_arpu_before", p_arpu_before);
        	
        	// 环比后
            MybatisParameter parameterAfter = new MybatisParameter();
            parameterAfter.put("pid", pId);
            parameterAfter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)));
            parameterAfter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)));
            parameterAfter.put("startDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateStart, +1+dayBetween)), "-", ""));
            parameterAfter.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(createDateEnd, +1+dayBetween)), "-", ""));
            Map<String, Object> afterValue = globalDaoTemplate.selectOne("platformStatistics.findByPid", parameterAfter);
            int afterIncomeValue = MapUtils.getIntValue(afterValue, "income");
            float p_income_after = 0;
            if (income != 0) {
            	p_income_after = (afterIncomeValue - income)  * 1.0f / income;
            }
        	map.put("p_income_after", p_income_after);
        	
        	int afterDruValue = MapUtils.getIntValue(afterValue, "dru");
            float p_dru_after = 0;
            if (dru != 0) {
            	p_dru_after = (afterDruValue - dru)  * 1.0f / dru;
            }
        	map.put("p_dru_after", p_dru_after);
        	
        	int afterKaifuValue = MapUtils.getIntValue(afterValue, "kaifu");
            float p_kaifu_after = 0;
            if (kaifu != 0) {
            	p_kaifu_after = (afterKaifuValue - kaifu)  * 1.0f / kaifu;
            }
        	map.put("p_kaifu_after", p_kaifu_after);
        	
        	int afterConsumeValue = MapUtils.getIntValue(afterValue, "consume");
            float p_consume_after = 0;
            if (consume != 0) {
            	p_consume_after = (afterConsumeValue - consume)  * 1.0f / consume;
            }
        	map.put("p_consume_after", p_consume_after);
        	
        	float afterPayRateValue = MapUtils.getFloatValue(afterValue, "pay_rate");
            float p_pay_rate_after = 0;
            if (payRate != 0) {
            	p_pay_rate_after = (afterPayRateValue - payRate)  * 1.0f / payRate;
            }
        	map.put("p_pay_rate_after", p_pay_rate_after);
        	
        	float afterArpuValue = MapUtils.getFloatValue(afterValue, "arpu");
            float p_arpu_after = 0;
            if (arpu != 0) {
            	p_arpu_after = (afterArpuValue - arpu)  * 1.0f / arpu;
            }
        	map.put("p_arpu_after", p_arpu_after);
        }
        
        model.addAttribute("statisticsList", statisticsList);
		model.addAttribute("selectedPids", parameter.get("pids"));
        return "modules/global/platformStatisticsTwo";

    }
}
