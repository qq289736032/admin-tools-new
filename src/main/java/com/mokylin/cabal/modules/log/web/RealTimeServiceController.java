package com.mokylin.cabal.modules.log.web;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.AuthCondition;


@Controller
@RequestMapping(value = "${adminPath}/log/realTimeService")
public class RealTimeServiceController extends BaseController{
    /**
     * 实时分服统计
     */
    @RequestMapping( value =  "realTimeServiceReport")
    public String realTimeServiceReport(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
//    	String createDateStart = MapUtils.getString(parameter, "createDateStart");
//    	//如果日期为空,则直接获取当前时间的小时段
//    	if (StringUtils.isEmpty(createDateStart)){
//    		parameter.put("createDateStart",DateUtils.getDate());
//    	}

		setDefaultLogDay(parameter);

		AuthCondition.filterPlatform(parameter);	//根据平台显示

    	Page<Map<String,Object>> page = logPaging(request, response, "statRealTimeService.findRealTimeServiceReport");
//    	for (Map<String,Object> map : page.getList()) {
//    		String  log_minute = MapUtils.getString(map, "log_minute");
//			if(null!=log_minute&&log_minute.length()==12){
//				map.put("log_minute", log_minute.substring(0, 4)+"-"+log_minute.substring(4,6)+"-"+log_minute.substring(6, 8)+" "+log_minute.substring(8, 10)+":"+log_minute.substring(10, 12)+":00");
//			}
//		}
    	/*//新注册 
        Map<String,Object>  newRegister=logDaoTemplate.selectOne("roleLogin.getRegister", parameter);
   	    //日登录人数 	
        Map<String,Object>  login=logDaoTemplate.selectOne("roleLogin.getLogin", parameter);
     	//当前在线 	
        Map<String,Object>  online=logDaoTemplate.selectOne("roleOnline.getOnline", parameter);
   	    //充值人数	充值次数 	充值金额
        Map<String,Object>  rechager=logDaoTemplate.selectOne("roleRecharge.getRecharge", parameter);
		model.addAttribute("page", page);
		model.addAttribute("newRegister", newRegister);
		model.addAttribute("login", login);
		model.addAttribute("online", online);
		model.addAttribute("rechager", rechager);*/
		model.addAttribute("page", page);
    	return "modules/logs/realTimeServiceReport";
    }


	@RequestMapping( value =  "realTime")
	public String realTime(HttpServletRequest request,HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String logHour = MapUtils.getString(parameter, "logHour");
		//如果日期为空,则直接获取当前时间的小时段
		if (StringUtils.isEmpty(logHour)){
			Date today = new Date();
			parameter.put("logHour",DateUtils.formatDate(today,"yyyy-MM-dd HH"));
		}

		AuthCondition.filterPlatform(parameter);	//根据平台显示

		Page<Map<String,Object>> page = logPaging(request, response, "statRealTimeService.realTime");

		model.addAttribute("page",page);
		return "modules/logs/realTime";
	}
	
	@RequestMapping( value =  "realFenTime")
	public String realFenTime(HttpServletRequest request,HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultLogDay(parameter);
		AuthCondition.filterPlatform(parameter);	//根据平台显示
    	Page<Map<String,Object>> page = logPaging(request, response, "statRealTimeService.realTimeFen");
    /*	//新注册 
        Map<String,Object>  newRegister=logDaoTemplate.selectOne("roleLogin.getRegister", parameter);
   	//日登录人数 	
        Map<String,Object>  login=logDaoTemplate.selectOne("roleLogin.getLogin", parameter);
   	//当前在线 	
        Map<String,Object>  online=logDaoTemplate.selectOne("roleOnline.getOnline", parameter);
   	//充值人数	充值次数 	充值金额
     Map<String,Object>  rechager=logDaoTemplate.selectOne("roleRecharge.getRecharge", parameter);*/
   	model.addAttribute("page",page);
   	/*model.addAttribute("newRegister",newRegister);
   	model.addAttribute("login",login);
   	model.addAttribute("online",online);
   	model.addAttribute("rechager",rechager);*/
		return "modules/logs/realFenTime";
	}
	
    
	@RequestMapping(value = "export")
    public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultLogDay(parameter);
		AuthCondition.filterPlatform(parameter);	//根据平台显示
		List<Map> realtime = logDaoTemplate.selectList("statRealTimeService.findRealTimeServiceReport", parameter);
		for (Map<String, Object> map : realtime) {
			map.put("log_minute", DateUtils.parseYYYYMMDDHHMM((MapUtils.getString(map, "log_minute"))));
		}
        return super.exportXls(realtime, "实时分服统计" + DateUtils.getDate("yyyyMMddHHmmss"), "统计时间", "平台", "服务器", "新注册", "日登录人数", "当前在线", "充值人数", "充值次数", "充值金额", "付费率", "ARPU");
        
    }



}
