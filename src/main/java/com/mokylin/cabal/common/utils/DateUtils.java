/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author 稻草鸟人
 * @version 2014-3-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
			"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	public static String getMonth(Date date) {
		return formatDate(date, "yyyyMM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 得到指定日期是本年度的第几周
	 * 
	 * @param date
	 * @return 本年度的第几周
	 */
	public static int getWeekOfYear(Date date) {
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(date);
		return g.get(Calendar.WEEK_OF_YEAR);// 获得周数 }
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date strToDate(String str) {
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * long类型的日志转化为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param time
	 * @return
	 */
	public static String parseLong(long time) {
		return formatDateTime(new Date(time));
	}

	public static String parseLong(String str) {
		long time = Long.parseLong(str);
		return formatDateTime(new Date(time));
	}

	/**
	 * log_minute日期格式格式化 201503171621 转换为 2015-03-17 16:21:00
	 * 
	 * @param str
	 * @return
	 */
	public static String parseYYYYMMDDHHMM(String str) {
		if (str != null) {
			return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " "
					+ str.substring(8, 10) + ":" + str.substring(10, 12) + ":00";
		}
		return str;
	}

	public static Date parseLong2Date(String str) {
		return parseDate(parseLong(str));
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去天数
	 * 
	 * @param obj
	 * @return
	 */
	public static long pastDays(String obj) {
		long t = new Date().getTime() - Long.parseLong(obj);
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取日期在今年所在的周
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY); // 设置星期一是每周的第一天
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getDayOfWeek(String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(parseDate(date));
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static int getDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getDayOfYear(String date) {
		return getDayOfYear(parseDate(date));
	}

	/**
	 * 根据当前所在周获取周在本年度的日期范围
	 * 
	 * @Param year
	 * @param week
	 *            201509
	 * @return
	 */
	public static String getDayRange(int year, int week) {
		String weekStr = String.valueOf(week);
		weekStr = weekStr.substring(4);
		week = Integer.valueOf(weekStr);
		String[] dates = new String[2];
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		Calendar cal1 = (Calendar) cal.clone();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_WEEK) - dayOfWeek);
		cal.add(Calendar.DATE, 1);
		Date d = cal.getTime();
		dates[0] = sdf.format(d).trim();

		cal1.add(Calendar.DATE, cal1.getActualMaximum(Calendar.DAY_OF_WEEK) - dayOfWeek);
		cal1.add(Calendar.DATE, 1);
		dates[1] = sdf.format(cal1.getTime()).trim();
		;
		return dates[0] + "~" + dates[1];
	}

	/**
	 * 获取两个日期的天数
	 * 
	 * @param start
	 *            <String>
	 * @param end
	 *            <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getDateSpace(String start, String end, String format) throws ParseException {
		return getDateSpace(parseDate(start, format), parseDate(end, format));
	}

	/**
	 * 获取两个日期的相隔的月份数
	 * 
	 * @param start
	 *            <String>
	 * @param end
	 *            <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(Date start, Date end) throws ParseException {
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(end);
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		return result >= 0 ? result : Math.abs(result);
	}

	/**
	 * 获取两个日期的相隔的月份数
	 * 
	 * @param start
	 *            <String>
	 * @param end
	 *            <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(String start, String end, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return getMonthSpace(sdf.parse(start), sdf.parse(end));

	}

	/**
	 * 获取两个日期的天数
	 * 
	 * @param start
	 *            <String>
	 * @param end
	 *            <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getDateSpace(Date start, Date end) throws ParseException {
		Calendar calst = Calendar.getInstance();
		;
		Calendar caled = Calendar.getInstance();
		calst.setTime(start);
		caled.setTime(end);
		// 设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
		return days;
	}

	public static String getDayRange(String year, String week) {
		return getDayRange(Integer.parseInt(year), Integer.parseInt(week));
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据年月 获取该月的天数
	 */
	public static int getDayNum(String date) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM");
		Calendar rightNow = Calendar.getInstance();
		try {

			rightNow.setTime(simpleDate.parse(date.replace("-", "/")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月
																	// 获取月份天数
		return days;
	}

	/**
	 * 间隔天数 end - before
	 * 
	 * @param before
	 * @param end
	 * @return
	 */
	public static long getNumberOfDaysBetween(Date before, Date end) {
		if (before == null || end == null) {
			return -1L;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(before);
			Calendar endCal = Calendar.getInstance();
			endCal.setTime(end);
			return getNumberOfDaysBetween(cal, endCal);
		}
	}

	/**
	 * get day span between two days
	 *
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static int getDays(Date start, Date end) {
		if (start == null || end == null) {
			return 0;
		}

		// reset date time to empty
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			start = sdf.parse(sdf.format(start));
			end = sdf.parse(sdf.format(end));
		} catch (ParseException e) {
			Exceptions.getStackTraceAsString(e);
		}

		// count day span by time long
		long span = (start.getTime() - end.getTime()) / (24 * 60 * 60 * 1000);

		return (int) span;
	}

	public static int getDays(String start, String end) {
		return getDays(parseDate(start), parseDate(end));
	}

	public static long getNumberOfDaysBetween(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			return -1L;
		} else {
			cal1.set(14, 0);
			cal1.set(13, 0);
			cal1.set(12, 0);
			cal1.set(11, 0);
			cal2.set(14, 0);
			cal2.set(13, 0);
			cal2.set(12, 0);
			cal2.set(11, 0);
			long elapsed = cal2.getTime().getTime() - cal1.getTime().getTime();
			return elapsed / 86400000L;
		}
	}

	/**
	 * 根据指定日期 得到指定天数前(后)的日期 指定日期前为- 指定日志后为+
	 */
	public static String addDays(String dateString, int beforeDays) {
		Date date = parseDate(dateString);
		String afterDay = formatDate(addDays(date, beforeDays));
		return afterDay;
	}

	/**
	 * 得到当月一号
	 */
	public static String getFirstDayOfMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		String curDay = sdf.format(calendar.getTime());
		return curDay;
	}

	public static boolean isFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(calendar.DAY_OF_MONTH) == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断当前日期是否是月初
	 */
	public static boolean isBeginMonth(String dateStr) {
		Boolean isN = true;
		String curDay = getFirstDayOfMonth();
		if (!curDay.equals(dateStr)) {
			isN = false;
		}
		return isN;
	}

	/**
	 * 根据年月获取对应的月份 天数
	 */
	public static int getDaysByYearMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到前一天的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		return getBeforeDay(date, 1);
	}

	/**
	 * 得到前N天的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -n);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 得到当月1号
	 * 
	 * @param d
	 * @return
	 */
	public static Date getBeginOfMonth(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static void main(String[] args) {
		System.out.println(strToDate("2017-04-01 00:00:00"));
	}

}
