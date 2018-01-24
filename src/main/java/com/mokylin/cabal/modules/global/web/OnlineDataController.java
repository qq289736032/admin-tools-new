package com.mokylin.cabal.modules.global.web;


import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 在线数据
 * @author ln
 */
@Controller
@RequestMapping(value = "${adminPath}/global/onlinedata")
public class OnlineDataController extends BaseController{
    
	/**
	 * 在线数据分布
	 */
	@RequestMapping(value = "onlineDataStatistics")
	  public String onlineDataStatistics(HttpServletRequest request, HttpServletResponse response, Model model){
		
		  MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		  //默认查询时间范围为7天
	      setDefaultTimeRange(parameter);
	      //setServerIdList(parameter);
//	      if(!parameter.containsKey("serverIdList")){
//	    	  list.add(parameter.get("currentServerId"));
//	    	  parameter.put("serverIdList", list);
//	      }
          model.addAttribute("list",globalDaoTemplate.selectList("rizonghe.onlineDataList", parameter));
          // 把serverIdList转成string
          parameter.put("serverIdList", parameter.get("serverIds"));
		  return "modules/global/onlineDataStatistics";
	  }
	
	/**
	 * 在线数据时点分布
	 */
	@RequestMapping(value ="onlinePointDisList")
	public String onlinePointDistributionList(HttpServletRequest request, HttpServletResponse response, Model model){
		
		  MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		  //setServerIdList(parameter);
		  //默认查询时间范围为7天
		  setDefaultTimeRange(parameter);
		  //对比开始时间和结束时间往前推7天
		  parameter.put("beforeStart",DateUtils.addDays(parameter.get("createDateStart").toString(),-7));
		  parameter.put("beforeEnd", DateUtils.addDays(parameter.get("createDateEnd").toString(), -7));
		  
		  //查询原数据
		  List<Map<Object,Object>> list = globalDaoTemplate.selectList("onlineTimeDistribution.onlinePointDis", parameter);
		  Map resultMap = new LinkedHashMap();
		  List<Map<Object,Object>>result = new ArrayList<Map<Object,Object>>();
		  for (Map<Object, Object> map : list) {
			  String day = map.get("logDay").toString();
			  map.remove("logDay");
			  for(Map.Entry<Object,Object> entry : map.entrySet()){
				  resultMap.put("logDay", day);
				  resultMap.put("hour",getHour(entry.getKey().toString()));
				  resultMap.put("value", entry.getValue());
				  result.add(resultMap);
				  resultMap = new LinkedHashMap();
				}
		}
		  
		  //查询7天前的对比数据
		  List<Map<Object,Object>> compare = globalDaoTemplate.selectList("onlineTimeDistribution.onlinePointCompare", parameter);
		 
		  String [] row = {"h0","h1","h2","h3","h4","h5","h6","h7","h8","h9","h10","h11","h12","h13","h14","h15","h16","h17","h18","h19","h20","h21","h22","h23"};
		  String [] col = {parameter.get("beforeStart").toString(),parameter.get("createDateStart").toString() };
		  String [] rep = {"befor","after"};
		  model.addAttribute("createDateStart", parameter.get("createDateStart"));
		  model.addAttribute("createDateEnd", parameter.get("createDateEnd"));
		  model.addAttribute("beforeStart", parameter.get("beforeStart"));
		  model.addAttribute("beforeEnd", parameter.get("beforeEnd"));
		  model.addAttribute("compare", Collections3.invert(compare,row,col,rep,"log_day"));
		  
		  model.addAttribute("result", result);
		  // 把serverIdList转成string
		  
		  parameter.put("serverIdList", parameter.get("serverIds"));
		  return "modules/global/onlinePointDistribution";
	}
	
	/**
	 * 截取小时
	 */
      public String getHour(String time){
    	  String hour= time.length()>2?time.substring(time.length()-2, time.length())+":00" :time.substring(time.length()-1, time.length())+":00";
    	  return hour;
      }
}
