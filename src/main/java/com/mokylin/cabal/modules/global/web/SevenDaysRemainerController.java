package com.mokylin.cabal.modules.global.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/global/sevenDaysRemainer")
public class SevenDaysRemainerController extends BaseController{
    @RequestMapping( value =  "sevenDaysRemainerReport")
    public String sevenDaysRemainerReport(HttpServletRequest request,HttpServletResponse response, Model model){
    	//新注册总量时点分布
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	setDefaultTimeRange(parameter);
    	//setServerIdList(parameter);
    	List<Map<String,Object>> sevenDaysRemainer =globalDaoTemplate.selectList("rizonghe.findSevenDaysRemainerReport",parameter);
    	List<Map<String,Object>> newSS7 =globalDaoTemplate.selectList("rizonghe.findSevenDaysRemainerInNewServerReport",parameter);
    	List<Map<String,Object>> oldSS7 =globalDaoTemplate.selectList("rizonghe.findSevenDaysRemainerInOldServerReport",parameter);
    	for (Map<String,Object> mapSum : sevenDaysRemainer) {
    		//合并新服的数据到结果集中
    		for (Map<String,Object> mapNew : newSS7) {
    			if(MapUtils.getInteger(mapSum, "log_day").intValue()==MapUtils.getInteger(mapNew, "log_day").intValue()){
					mapSum.put("sum_dru_new", MapUtils.getString(mapNew, "sum_dru"));
					mapSum.put("sum_2_dru_new", MapUtils.getString(mapNew, "sum_2_dru"));
					mapSum.put("sum_3_dru_new", MapUtils.getString(mapNew, "sum_3_dru"));
					mapSum.put("sum_4_dru_new", MapUtils.getString(mapNew, "sum_4_dru"));
					mapSum.put("sum_5_dru_new", MapUtils.getString(mapNew, "sum_5_dru"));
					mapSum.put("sum_6_dru_new", MapUtils.getString(mapNew, "sum_6_dru"));
					mapSum.put("sum_7_dru_new", MapUtils.getString(mapNew, "sum_7_dru"));
					break;
				}else{
					mapSum.put("sum_dru_new","0");
					mapSum.put("sum_2_dru_new", "0");
					mapSum.put("sum_3_dru_new", "0");
					mapSum.put("sum_4_dru_new", "0");
					mapSum.put("sum_5_dru_new", "0");
					mapSum.put("sum_6_dru_new", "0");
					mapSum.put("sum_7_dru_new", "0");
				}
    		}
    		//合并老服的数据到结果集中
    		for (Map<String,Object> mapOld : oldSS7) {
				if(MapUtils.getInteger(mapSum, "log_day").intValue()==MapUtils.getInteger(mapOld, "log_day").intValue()){
					mapSum.put("sum_dru_old", MapUtils.getString(mapOld, "sum_dru"));
					mapSum.put("sum_2_dru_old", MapUtils.getString(mapOld, "sum_2_dru"));
					mapSum.put("sum_3_dru_old", MapUtils.getString(mapOld, "sum_3_dru"));
					mapSum.put("sum_4_dru_old", MapUtils.getString(mapOld, "sum_4_dru"));
					mapSum.put("sum_5_dru_old", MapUtils.getString(mapOld, "sum_5_dru"));
					mapSum.put("sum_6_dru_old", MapUtils.getString(mapOld, "sum_6_dru"));
					mapSum.put("sum_7_dru_old", MapUtils.getString(mapOld, "sum_7_dru"));
					break;
				}else{
					mapSum.put("sum_dru_old","0");
					mapSum.put("sum_2_dru_old","0");
					mapSum.put("sum_3_dru_old","0");
					mapSum.put("sum_4_dru_old","0");
					mapSum.put("sum_5_dru_old","0");
					mapSum.put("sum_6_dru_old","0");
					mapSum.put("sum_7_dru_old","0");
				}
						
			}
		}
    	model.addAttribute("sevenDaysRemainer", sevenDaysRemainer);
    	// 把serverIdList转成string
	    parameter.put("serverIdList", parameter.get("serverIds"));
    	return "modules/global/sevenDaysRemainerReport";
    }
}
