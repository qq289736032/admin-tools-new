/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.global.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.search.aggregator.Count;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.service.BaseService;

/**
 * 充值Service
 * @author maojs
 * @version 2014-12-18
 */
@Service
@Transactional(readOnly = true)
public class RechargeService extends BaseService {
	
	/**
	 * 
	 */
	public List<Map<String, Object>> getDistributionList(List<Map<String, Object>> distributionList, List<Map<String, Object>> logDayList) {
	    
	    List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
	    
	    for(Map<String, Object> distribution : distributionList) {
	    	for(Map<String, Object> logDay : logDayList) {
	    		if(!MapUtils.getString(distribution, "log_day").equals(MapUtils.getString(logDay, "log_day"))) 
	    			continue;
	    		int section = MapUtils.getIntValue(distribution, "section");
	    		logDay.put("num" + section, MapUtils.getIntValue(distribution, "num"));
	    		logDay.put("amount" + section, MapUtils.getIntValue(distribution, "amount"));
	    		logDay.put("section", section);
	    	}
	    }
	    
	    for(int i = 1; i <= 25; i ++) {
	    	Map<String, Object> map = new HashMap<String, Object>();
		    map.put("section", SectionConstant.getContentById(i));
		    int totalNum = 0;
		    int totalAmount = 0;
		    for(Map<String, Object> distribution : distributionList) {
		    	if(MapUtils.getIntValue(distribution, "section") != i)
		    		continue;
		    	totalNum += MapUtils.getIntValue(distribution, "num");
		    	totalAmount += MapUtils.getIntValue(distribution, "amount");
		    }
		    map.put("num", totalNum);
		    map.put("amount", totalAmount);
		    resultList.add(map);
	    }
	    return resultList;
	}
	
	public enum SectionConstant {
		SECTION_1(1, "[0,9]"),
		SECTION_2(2, "[10,19]"),
		SECTION_3(3, "[20,29]"),
		SECTION_4(4, "[30,49]"),
		SECTION_5(5, "[50,69]"),
		SECTION_6(6, "[70,99]"),
		SECTION_7(7, "[100,199]"),
		SECTION_8(8, "[200,299]"),
		SECTION_9(9, "[300,399]"),
		SECTION_10(10, "[400,499]"),
		SECTION_11(11, "[500,699]"),
		SECTION_12(12, "[700,999]"),
		SECTION_13(13, "[1000,1499]"),
		SECTION_14(14, "[1500,1999]"),
		SECTION_15(15, "[2000,2499]"),
		SECTION_16(16, "[2500,2999]"),
		SECTION_17(17, "[3000,3999]"),
		SECTION_18(18, "[4000,4999]"),
		SECTION_19(19, "[5000,6999]"),
		SECTION_20(20, "[7000,9999]"),
		SECTION_21(21, "[10000,19999]"),
		SECTION_22(22, "[20000,49999]"),
		SECTION_23(23, "[50000,99999]"),
		SECTION_24(24, "[100000,199999]"),
		SECTION_25(25, "[200000,∞]"),
		
		;

		private SectionConstant(int id, String content) {
			this.id = id;
			this.content = content;
		}

		private int id;
		private String content;

		public int getId() {
			return id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		public static String getContentById(int id) {
			for(SectionConstant entry : SectionConstant.values()) {
				if(entry.getId() == id) {
					return entry.getContent();
				}
			}
			return "";
		}
	}
}
