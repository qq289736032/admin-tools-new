package com.mokylin.cabal.common.utils;

import java.util.HashMap;
import java.util.Map;

public class RefluxTimeUtils {
	public static  void   regLogintime(Map parameter ) {
		//StringBuffer sb=new StringBuffer();
		String time=	DateUtils.addDays(parameter.get("logDay").toString(), -14).replaceAll("-", "");
		String six=	DateUtils.addDays(parameter.get("logDay").toString(), -6).replaceAll("-", "");
		String thirteen=DateUtils.addDays(parameter.get("logDay").toString(), -13).replaceAll("-", "");
		String fourWeek=DateUtils.addDays(parameter.get("logDay").toString(), -28).replaceAll("-", "");
		String oneWeek=DateUtils.addDays(parameter.get("logDay").toString(), -7).replaceAll("-", "");
		String sixty=DateUtils.addDays(parameter.get("logDay").toString(), -60).replaceAll("-", "");
		String twentyNine=DateUtils.addDays(parameter.get("logDay").toString(), -29).replaceAll("-", "");
		String thirty=DateUtils.addDays(parameter.get("logDay").toString(), -30).replaceAll("-", "");
		parameter.put("twoWeek", time);
		parameter.put("six", six);
		parameter.put("thirteen", thirteen);
		parameter.put("fourWeek", fourWeek);
		parameter.put("oneWeek", oneWeek);
		parameter.put("sixty", sixty);
		parameter.put("twentyNine", twentyNine);
		parameter.put("thirty", thirty);
}
	

}
