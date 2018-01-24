/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.emory.mathcs.backport.java.util.Arrays;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Collections工具集.
 * 在JDK的Collections和Guava的Collections2后, 命名为Collections3.
 * @author calvin
 * @version 2014-01-15
 */
@SuppressWarnings("rawtypes")
public class Collections3 {

	/**
	 * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
	 * 
	 * @param collection 来源集合.
	 * @param keyPropertyName 要提取为Map中的Key值的属性名.
	 * @param valuePropertyName 要提取为Map中的Value值的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static Map extractToMap(final Collection collection, final String keyPropertyName,
			final String valuePropertyName) {
		Map map = new HashMap(collection.size());

		try {
			for (Object obj : collection) {
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),
						PropertyUtils.getProperty(obj, valuePropertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return map;
	}

	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List extractToList(final Collection collection, final String propertyName) {
		List list = new ArrayList(collection.size());

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw Reflections.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的一个属性(通过Getter函数), 组合成由分割符分隔的字符串.
	 * 
	 * @param collection 来源集合.
	 * @param propertyName 要提取的属性名.
	 * @param separator 分隔符.
	 */
	public static String extractToString(final Collection collection, final String propertyName, final String separator) {
		List list = extractToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
	 */
	public static String convertToString(final Collection collection, final String separator) {
		return StringUtils.join(collection, separator);
	}

	/**
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 */
	public static String convertToString(final Collection collection, final String prefix, final String postfix) {
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}

	/**
	 * 判断是否为空.
	 */
	public static boolean isEmpty(Collection collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 取得Collection的第一个元素，如果collection为空返回null.
	 */
	public static <T> T getFirst(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		return collection.iterator().next();
	}

	/**
	 * 获取Collection的最后一个元素 ，如果collection为空返回null.
	 */
	public static <T> T getLast(Collection<T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		//当类型为List时，直接取得最后一个元素 。
		if (collection instanceof List) {
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		//其他类型通过iterator滚动到最后一个元素.
		Iterator<T> iterator = collection.iterator();
		while (true) {
			T current = iterator.next();
			if (!iterator.hasNext()) {
				return current;
			}
		}
	}

	/**
	 * 返回a+b的新List.
	 */
	public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
		List<T> result = new ArrayList<T>(a);
		result.addAll(b);
		return result;
	}

	/**
	 * 返回a-b的新List.
	 */
	public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
		List<T> list = new ArrayList<T>(a);
		for (T element : b) {
			list.remove(element);
		}

		return list;
	}

	/**
	 * 返回a与b的交集的新List.
	 */
	public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
		List<T> list = new ArrayList<T>();

		for (T element : a) {
			if (b.contains(element)) {
				list.add(element);
			}
		}
		return list;
	}


	/**
	 * 行转列
	 * @param source
	 * @param <T>
	 * @return
	 */
	public static <T> List<Map<Object,Object>> invert(List<Map<Object,Object>> source, String[] rows, String[] cols, String ext){
		List<Map<Object,Object>> target = Lists.newArrayList();
		Map<Object, Object> tmp = Maps.newHashMap();
		for(Map<Object, Object> map : source){
			if(map!=null){
			Object key = map.keySet().iterator().next();
			Object value = map.get(key);
			map.remove(key);
			tmp.put(key+"_"+value, value);
			for(Map.Entry<Object,Object> entry : map.entrySet()){
				tmp.put(entry.getKey()+"_"+value, entry.getValue());
			}
			}
		}

		for(String row : rows){
			Map<Object, Object> inner = Maps.newHashMap();
			inner.put(row, row);
			for(String col : cols){
				inner.put(col, tmp.get(row+"_"+col));
				
			}
			target.add(inner);
		}
		
		
		

		return target;
	}
	
	/**
	 * cols长度和rep保持一致,定值key（rep）替换动态 key
	 * @param source
	 * @param rows
	 * @param cols
	 * @param rep
	 * @return
	 */
	public static <T> List<Map<Object,Object>> invert(List<Map<Object,Object>> source, String[] rows, String[] cols, String[] rep_col,String ext){
		List<Map<Object,Object>> result = invert(source,rows,cols,ext);
//		if(cols.length != rep_col.length) 
//			return null;
		int size = cols.length;
		for(Map<Object,Object> map : result){
			for(int i = 0; i < size; i ++){
				map.put(rep_col[i], MapUtils.getObject(map, cols[i]));
			}	
		}
		int i = 0;
		for(Map<Object,Object> map : result){
			map.put(ext, rows[i]);
			i ++;
		}
	
			
		return result;
	}


	public static Map<Object,Object> exists(List<Map<Object,Object>> list, String key){
		for(Map<Object,Object> map : list){
			if(map.containsKey(key)){
				return map;
			}
		}
		return Maps.newHashMap();
	}
	
	public static Map<String, Object> transBean2Map(Object obj) {  
	    if (obj == null) {  
	        return null;  
	    }  
	    Map<String, Object> map = new HashMap<String, Object>();  
	    try {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	        for (PropertyDescriptor property : propertyDescriptors) {  
	            String key = property.getName();  
	            // 过滤class属性  
	            if (!key.equals("class")) {  
	                // 得到property对应的getter方法  
	                Method getter = property.getReadMethod();  
	                Object value = getter.invoke(obj);  
	  
	                map.put(key, value);  
	            }  
	  
	        }  
	    } catch (Exception e) {  
	      return map;
	    }  
	    return map;  
	  
	}  

	public static void main(String[] args) {
		List<Map<Object,Object>> list = new ArrayList<Map<Object, Object>>();
		for(int i =0; i<=1; i++){
			Map<Object,Object> map = Maps.newLinkedHashMap();
			map.put("log_day","2014111"+i);
			map.put("h0",1+i);
			map.put("h1",2+i);
			map.put("h2",3+i);
			list.add(map);
		}

		String[] rows = {"h0","h1","h2"};
		String[] cols = {"20141110","20141111"};
		String[] rep = {"a","b"};
		
		List<Map<Object,Object>> aa = invert(list,rows,cols,rep,"log_day");
		System.out.println(JSON.toJSONString(aa));
	}

}
