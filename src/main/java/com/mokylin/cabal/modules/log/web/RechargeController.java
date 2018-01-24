package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;

import com.mokylin.cabal.modules.sys.utils.AuthCondition;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/24 15:05
 * 项目: cabal-tools
 */
@Controller(value = "roleRecharge")
@RequestMapping(value = "${adminPath}/log/recharge")
public class RechargeController extends BaseController {


    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultDateRange(parameter);

        AuthCondition.filterPlatform(parameter);	//根据平台显示

        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = logDaoTemplate.paging("roleRecharge.paging", parameter);

        model.addAttribute("page", page);
        return "modules/logs/rechargeList";
    }

    @RequestMapping(value = "chargeStatistics")
    public String chargeStatistic(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultDateRange(parameter);

        AuthCondition.filterPlatform(parameter);	//根据平台显示

        parameter.setPage(new Page(request,response));
        Page<Map<String,Object>> page = logDaoTemplate.paging("roleRecharge.chargeStatistics",parameter);

        model.addAttribute("page",page);
        return "modules/logs/chargeStatistics";
    }

    @RequestMapping(value = "singleChargeStatistics")
    public String singleChargeStatistics(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultDateRange(parameter);
        parameter.setPage(new Page(request,response));
        Page<Map<String,Object>> page = logDaoTemplate.paging("roleRecharge.singleChargeStatistics",parameter);

        model.addAttribute("page",page);
        return "modules/logs/singleChargeStatistics";
    }
    
    /**
     * 充值列表导出excel
     *
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	AuthCondition.filterPlatform(parameter);	//根据平台显示
    	setDefaultDateRange(parameter);
		List<Map> roleRank = logDaoTemplate.selectList("roleRecharge.export",parameter);
		for (Map<String, Object> map : roleRank) {
			map.put("recharge_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "recharge_time")), "yyyy-MM-dd HH:mm"));
			/*map.put("rechargeType", value);
			map.put("moneyType", value);*/
			
		}
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request); 
    	WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    	String wj= ac.getMessage("str57", null,"玩家", locale);
    	String str138= ac.getMessage("str138", null,"角色", locale);
    	String str7= ac.getMessage("str7", null,"角色名", locale);
    	String str969= ac.getMessage("str969", null,"订单号", locale);
    	String str970= ac.getMessage("str970", null,"充值类型", locale);
    	String str888= ac.getMessage("str888", null,"货币类型", locale);
    	String str253= ac.getMessage("str253", null,"充值金额", locale);
    	String str971= ac.getMessage("str971", null,"充值元宝", locale);
    	String str972= ac.getMessage("str972", null,"充值状态", locale);
    	String str973= ac.getMessage("str973", null,"充值时间", locale);
    	String str967=ac.getMessage("str967", null,"充值时间", locale);
		return super.exportXls(roleRank, str967+System.currentTimeMillis(),wj+"ID",str138+"ID",str7,str969,str970,str888,str253, str971,str972,	str973);

	}
    
    /**
     * 全服充值统计	chargeExport
     * 
     * 
     */
    @RequestMapping(value = "chargeExport", method= RequestMethod.POST)
    public ResponseEntity<byte[]> chargeExport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws ParseException {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	 setDefaultDateRange(parameter);
    	 AuthCondition.filterPlatform(parameter);	//根据平台显示
		List<Map> roleRank = logDaoTemplate.selectList("roleRecharge.chargeExport",parameter);
		for (Map<String, Object> map : roleRank) {
			map.put("lastChargeTime", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "lastChargeTime")), "yyyy-MM-dd HH:mm"));
			map.put("last_login_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "last_login_time")), "yyyy-MM-dd HH:mm"));
			//获取"充值流失","登陆流失"
			map.put("lastChargeTime2", DateUtils.pastDays(new Date(MapUtils.getLongValue(map, "lastChargeTime2"))));
			map.put("lastLoginTime2", DateUtils.pastDays(new Date(MapUtils.getLongValue(map, "lastLoginTime2"))));
			 
		}
		
		return super.exportXls(roleRank, "全服充值统计"+System.currentTimeMillis(),"用户ID","	角色ID","角色名","等级","总充值金额","充值次数","最大充值金额","目前剩余元宝","最后充值时间","最后登陆时间","充值流失","登陆流失");
    }
    /**
     *
     * 单服充值统计	singleChargeExport
     * 
     * 
     */
    @RequestMapping(value = "singleChargeExport", method= RequestMethod.POST)
    public ResponseEntity<byte[]> singleChargeExport(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws ParseException {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		List<Map> roleRank = logDaoTemplate.selectList("roleRecharge.singleChargeExport",parameter);
		for (Map<String, Object> map : roleRank) {
			map.put("lastChargeTime", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "lastChargeTime")), "yyyy-MM-dd HH:mm"));
			map.put("last_login_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "last_login_time")), "yyyy-MM-dd HH:mm"));
			//获取"充值流失","登陆流失"
			map.put("lastChargeTime2", DateUtils.pastDays(new Date(MapUtils.getLongValue(map, "lastChargeTime2"))));
			map.put("lastLoginTime2", DateUtils.pastDays(new Date(MapUtils.getLongValue(map, "lastLoginTime2"))));
			
		}
		
		return super.exportXls(roleRank, "单服充值统计"+System.currentTimeMillis(),"用户ID","	角色ID","角色名","等级","总充值金额","充值次数","最大充值金额","目前剩余元宝","最后充值时间","最后登陆时间","充值流失","登陆流失");
    }
    
    
}
