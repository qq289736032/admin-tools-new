package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
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
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;


@Controller
@RequestMapping(value = "${adminPath}/log/treasure")
public class TreasureBoxController extends BaseController {
	@RequestMapping(value = "treasureBox")
	public String treasureBoxLog(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String roleName = MapUtils.getString(parameter, "roleName");
		setDefaultTableSuffix(parameter);
	//	Page<Map<String, Object>> treasureBoxLog = logPaging(request,response,"treasureBoxLog.findTreasureBoxLog");
	    if (!parameter.containsKey("operateType")) {
	    	parameter.put("operateType", "zuoqizhenbaohe");
		}
		List<Map<String, Object>>  list =    logList(request, response, "treasureBoxLog.findTreasureFlowByItemId");
		
		int money = 0;
		int count = list.size();
		for(Map<String, Object> obj : list){
			String eventId = (String) obj.get("event_id");
			     parameter.put("eventId", eventId);
			     List<Map<String, Object>>  moneyList = logList(request, response, "treasureBoxLog.findTreasureBoxLog");
			  for(Map<String, Object> myobj :moneyList){
				  money += (int)myobj.get("value");
			  }
		}
		List<Map<String, Object>> resultlist  = new ArrayList<>();
		
		Map<String, Object> result = new HashMap<>();
		result.put("value", money);
		result.put("num", count);
		resultlist.add(result);
		parameter.getPage().setList(resultlist);
		model.addAttribute("treasureBoxLog", parameter.getPage());

		
		
		return "modules/logs/treasureBox";
	}
	
}
