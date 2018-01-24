package com.mokylin.cabal.modules.log.web;

import java.text.SimpleDateFormat;
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


/**
 * 幻魔密境统计
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/log/area")
public class SecretAreaContoller extends BaseController {
/**
 * 幻魔密境统计
 * @throws Exception 
 */
	@RequestMapping("secretAreaList")
	public String battleStatistics(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		@SuppressWarnings("rawtypes")
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");		
		//默认查询时间范围为7天
		setDefaultTimeRange(parameter);
		setDefaultDateRange(parameter);
		model.addAttribute("active", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		int defaultTime = Integer.valueOf(DateUtils.formatDate(new Date(), "yyyyMMdd"));
        final int from = MapUtils.getInteger(parameter, "startDate", defaultTime);
        int to = MapUtils.getInteger(parameter, "endDate", defaultTime);
        List<String> list = new ArrayList<String>();
        if (to < from) {
            throw new Exception("日期结束时间大于开始时间！");
        }
        int tableNum = DateUtils.getDays(DateUtils.parseDate(to),DateUtils.parseDate(from));
        List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();	
        for (int i = 1; i <= tableNum + 1; i++) {
            int finalI = i;
            final String date = DateUtils.addDays(String.valueOf(from),finalI - 1);
            String dates=date+" 16:00";
            String suffix = StringUtils.replace(date,"-","");
            list.add(suffix);
            parameter.put("list", list);
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            Date da = format.parse(date+" 16:00:00");
       	 	parameter.put("timestamps", da.getTime());
       	 	parameter.put("suffix", suffix);
			try {
			List<Map<String, Object>> mapList = logDaoTemplate.selectList("goodsFlowLog.roolNum", parameter);
			lists.addAll(mapList);
			} catch (Exception e) {
			}
        }
        
        Map<String,Map<String,Object>> map = new HashMap<>();
        for(Map<String,Object> innerMap : lists) {
        	String key = MapUtils.getString(innerMap, "item_name");
        	if(map.containsKey(key)) {
        		Map<String,Object> exitsMap = map.get(key);
        		int tenNum = MapUtils.getIntValue(exitsMap, "tenNum") + MapUtils.getIntValue(innerMap, "tenNum"); 
        		int sixteenNum = MapUtils.getIntValue(exitsMap, "sixteenNum") + MapUtils.getIntValue(innerMap, "sixteenNum");
        		exitsMap.put("tenNum", tenNum);
        		exitsMap.put("sixteenNum",sixteenNum);
        	}else {
        		map.put(key, innerMap);
        	}
        }

        model.addAttribute("roolNum", map);
        
		try {
			model.addAttribute("pkDieNum", logDaoTemplate.selectList("secretArea.pkDieNum", parameter));
			model.addAttribute("sixteenPkDieNum", logDaoTemplate.selectList("secretArea.sixteenPkDieNum", parameter));
		} catch (Exception e) {
		}
		try {
			model.addAttribute("tenParticipantNums", logDaoTemplate.selectList("secretArea.tenParticipantNums", parameter));
			model.addAttribute("sixteenParticipantNums", logDaoTemplate.selectList("secretArea.sixteenParticipantNums", parameter));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			model.addAttribute("tenParticipantNum", logDaoTemplate.selectList("moneyFlowLog.tenParticipantNum", parameter));
			model.addAttribute("sixteenParticipantNum", logDaoTemplate.selectList("moneyFlowLog.sixteenParticipantNum", parameter));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return "modules/logs/secretAreaList";
		
	}
}
