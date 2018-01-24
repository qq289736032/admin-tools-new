package com.mokylin.cabal.modules.global.web;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/global/newRegisterStatistics")
public class NewRegisterStatisticsController extends BaseController {


    @RequestMapping(value = "newRegisterStatisticsReport")
    public String statNewReg(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultTimeRange(parameter);
        setDefaultDateRange(parameter);
        List<Map<String, Object>> tempNewRegister = globalDaoTemplate.selectList("regDistribution.findNewRegNumByDay", parameter);
        if (tempNewRegister != null) {
            List<Map<String, Object>> newRegister = Lists.newLinkedList();
            for (Map<String, Object> map : tempNewRegister) {
                for (int s = 0; s <= 23; s++) {
                    Map<String, Object> innerMap = Maps.newHashMap();
                    innerMap.put("day", MapUtils.getString(map, "log_day"));
                    innerMap.put("hour", s + ":00");
                    innerMap.put("num", map.get("section_" + (s+1)));
                    newRegister.add(innerMap);
                }
            }
            model.addAttribute("newRegister", newRegister);    //新注册总量时点分布
        }
        List<Map<String, Object>> serverNewRegister = Lists.newLinkedList();
        List<Map<String, Object>> temp = globalDaoTemplate.selectList("regDistribution.findNewRegNumByServer", parameter);
        if (temp != null) {
            int k = 0;
            for (Map<String, Object> obj : temp) {
                Map<String, Object> innerMap = Maps.newHashMap();
                innerMap.put("day", MapUtils.getString(obj, "log_day"));
                innerMap.put("pid", MapUtils.getString(obj, "pid"));
                innerMap.put("serverId", MapUtils.getString(obj, "area_id"));
                int num = 0;
                for (int j = 1; j <= 24; j++) {
                    num = num + MapUtils.getIntValue(obj, "section_" + j);
                }
                innerMap.put("num", num);
                serverNewRegister.add(innerMap);
                k = k + num;
            }
            model.addAttribute("serverNewRegister", serverNewRegister);    //服务器新注册统计
            model.addAttribute("serverNum", temp.size());
            model.addAttribute("totalNewRegNum", k);
        }

          //对比开始时间和结束时间往前推7天
          parameter.put("beforeStart",StringUtils.replace(DateUtils.addDays(parameter.get("createDateStart").toString(),-7),"-",""));
		  parameter.put("beforeEnd", StringUtils.replace(DateUtils.addDays(parameter.get("createDateEnd").toString(), -7),"-",""));
		  //查询7天前的对比数据
		  List<Map<Object,Object>> compare = globalDaoTemplate.selectList("regDistribution.newRegNumByDayCompare", parameter);
		  String [] row = {"h0","h1","h2","h3","h4","h5","h6","h7","h8","h9","h10","h11","h12","h13","h14","h15","h16","h17","h18","h19","h20","h21","h22","h23"};
		  String [] col = {parameter.get("beforeStart").toString(),parameter.get("createDateStart").toString() };
		  String [] rep = {"before","after"};
		  model.addAttribute("createDateStart", parameter.get("createDateStart"));
		  model.addAttribute("createDateEnd", parameter.get("createDateEnd"));
		  model.addAttribute("beforeStart", parameter.get("beforeStart"));
		  model.addAttribute("beforeEnd", parameter.get("beforeEnd"));
		  model.addAttribute("compare", Collections3.invert(compare,row,col,rep,"log_day"));
		  
          return "modules/global/statNewReg";
    }

//    @RequestMapping( value =  "newRegisterStatisticsReport")
//    public String newRegisterStatisticsReport(HttpServletRequest request,HttpServletResponse response, Model model){
//    	//新注册总量时点分布
//    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
//    	String createDateStart = MapUtils.getString(parameter, "createDateStart");
//    	if (StringUtils.isEmpty(createDateStart)) {
//    		parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(new Date(), -1)));
//    	}
//    	setServerIdList(parameter);
//    	List<Map<String,Object>> newRegister = logDaoTemplate.selectList("statRealTimeService.findNewRegister",parameter);
//    	model.addAttribute("newRegister", newRegister);
//    	//服务器新注册统计
//    	List<Map<String,Object>> serverNewRegister = logDaoTemplate.selectList("statRealTimeService.findServerNewRegister", parameter);
//    	model.addAttribute("serverNewRegister", serverNewRegister);
//    	//服务器新注册统计汇总
//    	Set pidNum=new HashSet();
//    	Set serverNum=new HashSet();
//    	int sumRu=0;
//    	for (Map<String,Object> map : serverNewRegister) {
//    		pidNum.add(MapUtils.getString(map, "pid"));
//    		serverNum.add(MapUtils.getString(map, "area_id"));
//    		sumRu+=MapUtils.getIntValue(map, "sum_register");
//		}
//    	model.addAttribute("pidNum", pidNum.size());
//    	model.addAttribute("serverNum", serverNum.size());
//    	model.addAttribute("sumRu", sumRu);
//    	//周对比
//    	createDateStart = MapUtils.getString(parameter, "createDateStart");
//    	parameter.put("createDateStart",  DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateStart), -7)));
//    	Page<Map<String,Object>> newRegisterWeekBefore = logPaging(request, response, "statRealTimeService.findNewRegister");
//    	for (Map<String,Object> map : newRegister) {
//    		for (Map<String,Object> mapWeekBefore : newRegisterWeekBefore.getList()) {
//    			if(MapUtils.getInteger(map, "log_hour").intValue()==MapUtils.getInteger(mapWeekBefore, "log_hour").intValue()){
//    				map.put("sum_register_weekbefore", MapUtils.getInteger(mapWeekBefore, "sum_register"));
//    				break;
//    			}else{
//    				map.put("sum_register_weekbefore", "0");
//    			}
//    		}
//		}
//    	//重置时间
//    	parameter.put("createDateStart",  createDateStart);
//    	// 把serverIdList转成string
//    	parameter.put("serverIdList", parameter.get("serverIds"));
//    	return "modules/global/statNewReg";
//    }
}
