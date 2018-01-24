package com.mokylin.cabal.modules.log.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.DictUtils;
	
@Controller
@RequestMapping(value = "${adminPath}/log/beauty")
public class BeautyController extends BaseController {

	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) throws ParseException {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultLogDay(parameter);
	    int sumCount=0;
        List<Map>  beauty=logDaoTemplate.selectList("beauty.findBeauty", parameter);
		model.addAttribute("beauty", beauty);
		model.addAttribute("sumCount", sumCount);
    	int sumCountOne=0;
		List<Map>  beautyActive=logDaoTemplate.selectList("beauty.findBeautyActive", parameter);
		model.addAttribute("beautyActive", beautyActive);
		model.addAttribute("sumCountOne", sumCountOne);
     	parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/logs/beautyList";
	}
	 
    /**
     * 星图分布
     * 等级分布
     */
	@RequestMapping("starDistribution")
	public String starDistribution(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultLogDay(parameter);
		model.addAttribute("vipLevelMap", vipLevelMap());
		model.addAttribute("viplevel", parameter.get("viplevel"));
		//所有equipType
		List<Map<String,Object>> equipType=logDaoTemplate.selectList("beauty.equipType");
		model.addAttribute("equipType", equipType);
		//星级等级
	    List<Map<String, Object>> starList = new ArrayList<>();
	    // 最大等级
		Map<Integer, Integer> ret = Maps.newHashMap();	//key -> level  value- > num
		//星级等级
	    int maxLevel = 0;
		Map<String, String> maxLevelMap = logDaoTemplate.selectOne("beauty.levelMax", parameter);
		if (maxLevelMap != null) {
		    maxLevel = MapUtils.getIntValue(maxLevelMap, "maxLevel");
		    List<Map<String, Object>> list = logDaoTemplate.selectList("beauty.starlevelDistribution", parameter);
	        //总行数
	        int[] totalNum = new int[equipType.size()];
			// 从1到最高等级的
			for (Map<String, Object> huaLevelMap : list) {
				Integer key = MapUtils.getInteger(huaLevelMap, "level");
				Integer value = MapUtils.getInteger(ret, key, 0);
				ret.put(key, value + MapUtils.getInteger(huaLevelMap, "num"));
			}
	        // 从1到最高等级的
		    for (int i = 1; i <= maxLevel; i++) {
        		Map<String, Object> map = new HashMap<>();
        		map.put("level", i);
        		int[] numList = new int[equipType.size()];
	        	for (Map<String, Object> huaLevelMap : list) {
        			if (MapUtils.getInteger(huaLevelMap, "level") != i) {
        				continue;
        			}
	        		int jobIndex = 0;
        			for (int f=0; f< equipType.size();f++) {
        				String job = String.valueOf( equipType.get(f).get("equip_type"));
        				if (MapUtils.getString(huaLevelMap, "equip_type").equals(job)) {
        					numList[jobIndex] = MapUtils.getInteger(huaLevelMap, "num");
        					totalNum[jobIndex] += numList[jobIndex];
        				}
        				jobIndex++;
        			}
	        	}
	        	map.put("numList", numList);
	        	starList.add(map);
	           }
			 }
		     model.addAttribute("starList", ret);
		     model.addAttribute("starList1", starList);
	    	 parameter.put("serverIdList", parameter.get("serverIds"));
			 return "modules/logs/starlevelDistribution";
		}
	    
	    

}
