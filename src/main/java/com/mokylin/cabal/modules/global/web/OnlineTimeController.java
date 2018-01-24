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

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/global/onlineTime")
public class OnlineTimeController extends BaseController{
    @RequestMapping( value =  "onlineTimeReport")
    public String onlineTimeReport(HttpServletRequest request,HttpServletResponse response, Model model){
    	//表格--每日用户在线时长
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	 //默认查询时间范围为7天
	    setDefaultTimeRange(parameter);
    	//setServerIdList(parameter);
    	List<Map<String,Object>> onlineTime =globalDaoTemplate.selectList("statLevelOnlineTime.findUserOnLineTimeReport",parameter);
    	model.addAttribute("onlineTime", onlineTime);
    	//曲线图--在线时长等级分布
    	List<Map<String,Object>> onlineTimeLevel =globalDaoTemplate.selectList("statLevelOnlineTime.findOnLineTimeLevelReport",parameter);
    	model.addAttribute("onlineTimeLevel", onlineTimeLevel);
    	//曲线图--在线时长区间人数分布(说明,本本处业务完全可以用sql的union all将行转列,但是sql比较长,所以在代码里处理)
    	List<Map<String,Object>> onlineTimeAreaInDb =globalDaoTemplate.selectList("statLevelOnlineDistribution.findOnLineTimeAreaReport",parameter);
    	List<Map<String,Object>> onlineTimeArea = new ArrayList<Map<String,Object>>();
    	Map<String,Object> map=onlineTimeAreaInDb.get(0);
    	if(null!=map){
    		onlineTimeArea.add(createMap(map,"0","in5"));
    		onlineTimeArea.add(createMap(map,"5","in10"));
    		onlineTimeArea.add(createMap(map,"10","in15"));
    		onlineTimeArea.add(createMap(map,"15","in20"));
    		onlineTimeArea.add(createMap(map,"20","in25"));
    		onlineTimeArea.add(createMap(map,"25","in30"));
    		onlineTimeArea.add(createMap(map,"30","in60"));
    		onlineTimeArea.add(createMap(map,"60","in90"));
    		onlineTimeArea.add(createMap(map,"90","in120"));
    		onlineTimeArea.add(createMap(map,"120","in150"));
    		onlineTimeArea.add(createMap(map,"150","in180"));
    		onlineTimeArea.add(createMap(map,"180","in240"));
    		onlineTimeArea.add(createMap(map,"240","in300"));
    		onlineTimeArea.add(createMap(map,"300","in360"));
    		onlineTimeArea.add(createMap(map,"360","big360"));
    		int sumNum=0;
    		sumNum+=MapUtils.getInteger(map, "in5");
    		sumNum+=MapUtils.getInteger(map, "in10");
    		sumNum+=MapUtils.getInteger(map, "in15");
    		sumNum+=MapUtils.getInteger(map, "in20");
    		sumNum+=MapUtils.getInteger(map, "in25");
    		sumNum+=MapUtils.getInteger(map, "in30");
    		sumNum+=MapUtils.getInteger(map, "in30");
    		sumNum+=MapUtils.getInteger(map, "in90");
    		sumNum+=MapUtils.getInteger(map, "in120");
    		sumNum+=MapUtils.getInteger(map, "in150");
    		sumNum+=MapUtils.getInteger(map, "in180");
    		sumNum+=MapUtils.getInteger(map, "in240");
    		sumNum+=MapUtils.getInteger(map, "in300");
    		sumNum+=MapUtils.getInteger(map, "in360");
    		sumNum+=MapUtils.getInteger(map, "big360");
    		model.addAttribute("sumNum",sumNum);
    	}
    	model.addAttribute("onlineTimeArea", onlineTimeArea);
    	//饼图--每日用户在线时长
    	// 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
    	return "modules/global/onlineTimeReport";
    }
    private Map<String, Object> createMap(Map<String, Object> res,Object time,Object key){
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("time",time);
    	map.put("num", MapUtils.getString(res, key));
    	return map;
    }
}

