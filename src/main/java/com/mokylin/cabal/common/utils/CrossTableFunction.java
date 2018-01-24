package com.mokylin.cabal.common.utils;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 对List map集合进行合并成交叉表
 */
public class CrossTableFunction {

	private List<Map> list;
	private String rowFieldName = "row";
	private String colFieldName = "col";
	private String numFieldName = "num";
	private Object[] orderRowFields;
	private Object[] orderColFields;
	
	public CrossTableFunction(List<Map> list){
		this.list = list;
	}
	
	public CrossTableFunction(List<Map> list, String rowFieldName, String colFieldName, String numFieldName){
		this.list = list;
		this.rowFieldName = rowFieldName;
		this.colFieldName = colFieldName;
		this.numFieldName = numFieldName;
	}
	
	public CrossTableFunction setOrderRowFields(Object[] orderRowFields) {
		this.orderRowFields = orderRowFields;
		return this;
	}
	
	public CrossTableFunction setOrderColFields(Object[] orderColFields) {
		this.orderColFields = orderColFields;
		return this;
	}
	
	public void execute(){
		Map dataMap = new HashMap();
		for (Map map : this.list) {
			String row = MapUtils.getString(map, rowFieldName);
			String col = MapUtils.getString(map, colFieldName);
			double num = MapUtils.getDoubleValue(map, numFieldName);
			dataMap.put(row + "_" + col, num);
		}
		list.clear();
		for (Object orderColFieldName : orderColFields){
			Map recordMap = new LinkedHashMap();
			for (Object orderRowField : orderRowFields) {
				String key = orderRowField + "_" + orderColFieldName;
				double num = MapUtils.getDoubleValue(dataMap, key);
				recordMap.put(key, num);
			}
			list.add(recordMap);
		}
	}
}
