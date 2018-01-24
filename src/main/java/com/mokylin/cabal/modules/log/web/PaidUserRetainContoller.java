package com.mokylin.cabal.modules.log.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;

/***
 * 付费用户留存
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/log/paidUserRetain")
public class PaidUserRetainContoller extends BaseController{

	@RequestMapping("paidView")
	public String paidView(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

		setDefaultTimeRange(parameter);
		
		//set init
		setAmountInit(parameter);
		
    	//setServerIdList(parameter);
		//付费活跃用户统计
        List<Map<String, Object>> apaDayActive = findUserAmountOrderTimeReport(request, response, model);
        List<Map<String, Object>> userList = chargeDaoTemplate.selectList("charge.findUseIdOrderTime", parameter);
        
        //对比当前日期
        parameter.put("currentDate", DateUtils.formatDate(new Date()));
        List<Map<String, Object>> roleLogoutList = logDaoTemplate.selectList("roleLogout.statRoleLoginOutLoginTime", parameter);
        
        for(Map<String,Object> apaDay : apaDayActive){
        	//init()
        	setApaDayMapInit(apaDay);
        	
        	for(Map<String,Object> user : userList){
        		
        		if(apaDay.get("order_time_format").equals(user.get("order_time"))){
        			if(user.get("user_id") == null){
        				continue;
        			}
        			
        			Integer num = 0;
        			for(Map<String,Object> role : roleLogoutList){
            			
            			if(user.get("user_id").equals(role.get("user_id"))){
            				if(Integer.parseInt(role.get("date_login_time").toString()) > Integer.parseInt(apaDay.get("order_time_format").toString())){
            					num+=1;
            				}
            			}
            		}
        			
    				if(num > 0){
        				if(num < 10){
        					apaDay.put("loginDay_"+num, Integer.parseInt(apaDay.get("loginDay_"+num).toString())+1);	
        				}else if(num >=11 && num <= 15){
        					apaDay.put("loginDay_11_15",Integer.parseInt(apaDay.get("loginDay_11_15").toString())+1);
        				}else if(num >=16 && num <= 20){
        					apaDay.put("loginDay_16_20",Integer.parseInt(apaDay.get("loginDay_16_20").toString())+1);
        				}else if(num >=21 && num <= 30){
        					apaDay.put("loginDay_21_30",Integer.parseInt(apaDay.get("loginDay_21_30").toString())+1);
        				}else if(num >=31){
        					apaDay.put("loginDay_31",Integer.parseInt(apaDay.get("loginDay_31").toString())+1);
        				}
    				}
        		}
        	}
        }
        
        model.addAttribute("apaDayActive", apaDayActive);
		return "modules/global/paidUserRetentionReport";
	}
	
	/**
     * 付费活跃用户统计
     */
    private List<Map<String, Object>> findUserAmountOrderTimeReport(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<Map<String, Object>> userAmountList = chargeDaoTemplate.selectList("charge.statUserOrderTimeReport", parameter);
        model.addAttribute("userAmountList", userAmountList);
        return userAmountList;
    }
    
    private void setApaDayMapInit(Map<String, Object> apaDay){
    	apaDay.put("loginDay_1", 0);
    	apaDay.put("loginDay_2", 0);
    	apaDay.put("loginDay_3", 0);
    	apaDay.put("loginDay_4", 0);
    	apaDay.put("loginDay_5", 0);
    	apaDay.put("loginDay_6", 0);
    	apaDay.put("loginDay_7", 0);
    	apaDay.put("loginDay_8", 0);
    	apaDay.put("loginDay_9", 0);
    	apaDay.put("loginDay_10", 0);
    	apaDay.put("loginDay_11_15", 0);
    	apaDay.put("loginDay_16_20", 0);
    	apaDay.put("loginDay_21_30", 0);
    	apaDay.put("loginDay_31", 0);
    }
}
