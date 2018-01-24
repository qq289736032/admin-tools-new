package com.mokylin.cabal.modules.global.web;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mokylin.cabal.common.cache.ICache;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.CacheUtils;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.LevelCross;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.global.service.DashBoardService;
import com.mokylin.cabal.modules.sys.utils.AuthCondition;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.mothRevenue;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/12/1 10:17
 * 项目: cabal-tools
 */

@Controller
@RequestMapping(value = "${adminPath}/global/dashboard")
public class DashboardController extends BaseController {

    public static final String MONTHLY_TOTAL_DATA_LIST = "monthlyTotalData";
    public static final String MONTHLY_TOTAL_DATA_LISTS = "monthlyTotalDatas";
    
    @Autowired
    private   DashBoardService dashBoardService;

    @RequestMapping(value = "baseReport")
    public String baseReport(HttpServletRequest request, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);

        List<Map<String, Object>> list = globalDaoTemplate.selectList("rizonghe.findByTimeRange", parameter);

        List<Map<String, Object>> listPccu = dashBoardService.getPccu(parameter);
		List<Map<String, Object>> targetPCU = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> pcuList = LevelCross.selectPcuu(listPccu);
		
		// 已col开头的字段一共288个，这里取其中最大的值即为pccu
		maxPCCUACU(pcuList, targetPCU);
		dashBoardService.addPccu(list, targetPCU);
	
		
        model.addAttribute("list", list);

        //整体运营报表
        model.addAttribute("hisTotal", globalDaoTemplate.selectList("rizonghe.findHisTotal", parameter));

        //月总数据 ----- 这个数据量较大，后面要做缓存，缓存时间设置为2小时
        ICache<String, Object> cache = ehcacheCacheManager.getCache(MONTHLY_TOTAL_DATA_LIST);
        List<Map<String, Object>> monthlyTotal = (List<Map<String, Object>>) cache.get(MONTHLY_TOTAL_DATA_LIST);
        List<Map<String, Object>> currentMonthTotal = new ArrayList<Map<String, Object>>();
        if (monthlyTotal != null) {
            model.addAttribute("monthlyTotal", monthlyTotal);
        } else {
            String month = DateUtils.getMonth(new Date());    //yyyyMM
            parameter.put("month", month);

            if(DateUtils.isFirstDayOfMonth(new Date())){
            	monthlyTotal = globalDaoTemplate.selectList("monthlyIntegrated.findMonthlyTotalData", parameter);
            }else{
            	monthlyTotal = globalDaoTemplate.selectList("monthlyIntegrated.findMonthlyTotalData", parameter);  //历史月
            }
            if (monthlyTotal.size() > 0) {
                for (Map<String, Object> map : monthlyTotal) {
                	map.put("month", MapUtils.getString(map, "log_month"));
                	map.put("acu",calculateAcu(MapUtils.getString(map, "month")));
                    map.put("pccu", monthPU(MapUtils.getString(map, "month")));
                }
            }

            if (currentMonthTotal != null && currentMonthTotal.size() > 0) {
                for (Map<String, Object> currentMonthMap : currentMonthTotal) {
                    currentMonthMap.put("month_remainer", "0");// 数据0 因为当月还没有过去
                    // 当月的活跃用户从role_login表里面取
                    Map<String, Object> activeNumMap = logDaoTemplate.selectOne("roleLogin.findActiveSumByArea", parameter);
                    currentMonthMap.put("mau", activeNumMap == null ? 0 : MapUtils.getString(activeNumMap, "au"));
                    //calculateCurrentMonthPCCU(currentMonthMap);
                    //calculateCurrentMonthACU(currentMonthMap);
                    currentMonthMap.put("pccu", calculateMonthPU(month));

                    // 付费人数应该去充值记录表查询-  select count(distinct role_id) from role_recharge where 月份 = 3月份
//					Map<String, Object> mpuMap = logDaoTemplate.selectOne("roleRecharge.findMpuByServerId", parameter);
//					currentMonthMap.put("mpu", mpuMap == null ? 0 : MapUtils.getString(mpuMap, "mpu"));
//					// 充值金额去充值记录表查询
//					Map<String, Object> incomeMap = logDaoTemplate.selectOne("roleRecharge.findIncomeByServerId", parameter);
//					currentMonthMap.put("income", incomeMap == null ? 0 : MapUtils.getString(incomeMap, "income"));

                    Map<String, Object> map = logDaoTemplate.selectOne("roleRecharge.findMPUAndIncomeByMonth", parameter);
                    currentMonthMap.put("mpu", MapUtils.getString(map, "mpu") == null ? 0 : MapUtils.getString(map, "mpu"));
                    currentMonthMap.put("income", MapUtils.getString(map, "income") == null ? 0 : MapUtils.getString(map, "income"));
                }
            }
            model.addAttribute("currentMonthTotal", currentMonthTotal);
            model.addAttribute("monthlyTotal", monthlyTotal);
			cache.put(MONTHLY_TOTAL_DATA_LIST, monthlyTotal);
        }

        //按平台分组查询，收入，导量，活跃，等
        //List<Map<String, Object>> mapList = globalDaoTemplate.selectList("rizonghe.findByPlatform", parameter);
        List<Map<String, Object>> mapList = logDaoTemplate.selectList("roleLogin.findPlatformData", parameter);
        model.addAttribute("mapList", mapList);

        //按平台分组查询，收入，导量，活跃，等
        //Map<String, Object> totalMap = globalDaoTemplate.selectOne("rizonghe.findByPlatformTotal", parameter);
		Map<String, Object> totalMap = Maps.newHashMap();
		for(Map<String, Object> map : mapList){
			totalMap.put("income", MapUtils.getIntValue(map, "income") + MapUtils.getIntValue(totalMap, "income"));
			totalMap.put("au", MapUtils.getIntValue(map, "au") + MapUtils.getIntValue(totalMap, "au"));
			totalMap.put("ru", MapUtils.getIntValue(map, "ru") + MapUtils.getIntValue(totalMap, "ru"));
		}

        model.addAttribute("totalMap", totalMap);

        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/baseReport";
    }

    //日综合报表
    @RequestMapping(value = "dailyComprehensiveReport")
    public String dailyComprehensiveReport(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefaultTimeRange(parameter);
        if (StringUtils.isEmpty(MapUtils.getString(parameter, "serverIds"))) {
            addMessage(model, "请先选择服务器");
            return "modules/global/dailyComprehensiveReport";
        }

        //setServerIdList(parameter);
        List<Map<String, Object>> list = globalDaoTemplate.selectList("rizonghe.findDailyReport", parameter);
        //得出列five_minutes_count中最大值即为pccu
        calculateMaxPCCU(list);
        model.addAttribute("list", list);
        //根据查询条件中的开始时间、结束时间计算出开始时间、结束时间所在的周,作为查询条件
        setDefaultWeekRange(parameter);
        List<Map<String, Object>> weekReport = globalDaoTemplate.selectList("rizonghe.findWeekReport", parameter);
        model.addAttribute("weekReport", weekReport);

        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/dailyComprehensiveReport";
    }

    @RequestMapping(value = "serverDailyReport")
    public String serverDailyReport(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultTimeRange(parameter);
        //setServerIdList(parameter); //
        //parameter.put("areaId", LookupContext.getCurrentServerId());
        Page<Map<String, Object>> page = globalPaging(request, response, "rizonghe.findServerDailyReport");
        calculateMaxPCCU(page.getList());

        model.addAttribute("page", page);
        return "modules/global/serverDailyReport";
    }

    @RequestMapping(value = "gunfuUserStatReport")
    public String gunfuUserStatReport(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultTimeRange(parameter);
        //setServerIdList(parameter);
       /*globalPaging(request, response, "rizonghe.findGunfuUserStatReport");*/
        model.addAttribute("list", globalDaoTemplate.selectList("rizonghe.findGunfuUserStatReport", parameter));
        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/gunfuUserStatReport";
    }

    /**
     * 日活跃用户统计
     *
     * @throws ParseException
     */
    @RequestMapping(value = "dayActiveUserReport")
    public String dayActiveUserReport(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultTimeRange(parameter);
        //setServerIdList(parameter);
        //日活跃用户统计
        List<Map<String, Object>> dayActive = findDayActiveUserReport(request, response, model);
        //日查询统计
        statisticsDayActive(request, response, model);
        //服务器日活跃数统计
        dayServerActive(request, response, model);
        //周活跃对比图,获取日活跃对应的前一周的数据
        weekComparison(request, response, model, dayActive);
        //weekComparison 改变了日期的值  重置parameter中的日期值
        parameter.put("createDateStart", MapUtils.getString(model.asMap(), "createDateStart"));
        parameter.put("createDateEnd", MapUtils.getString(model.asMap(), "createDateEnd"));
        //月活跃对比图,获取日活跃对应的前30天的数据
        monthComparison(request, response, model, dayActive);
        //重置parameter中的日期值,因为要提供给jsp页面的日期控件显示
        parameter.put("createDateStart", MapUtils.getString(model.asMap(), "createDateStart"));
        parameter.put("createDateEnd", MapUtils.getString(model.asMap(), "createDateEnd"));
        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/dayActiveUserReport";
    }

    /**
     * 月活跃对比图
     */
	private void monthComparison(HttpServletRequest request, HttpServletResponse response, Model model,List<Map<String, Object>> dayActive) throws ParseException {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String createDateStart = MapUtils.getString(parameter, "createDateStart");
    	String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
    	String begin =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateStart), -30));
    	String end =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateEnd), -30));
    	parameter.put("startDate",StringUtils.replace(begin,"-",""));
        parameter.put("endDate",StringUtils.replace(end, "-", ""));
    	List<Map<String,Object>> monthComparison = globalDaoTemplate.selectList("rizonghe.findMonthComparison",parameter);
    	
    	List<Map<String,Object>> monthComparisonList = new ArrayList<Map<String,Object>>();
    	
    	int dateRange = DateUtils.getDateSpace(createDateStart, createDateEnd, "yyyy-MM-dd");
    	
    	for (int i = 0; i <= dateRange; i++) {
    		Map<String,Object> monthComparisonMap = new HashMap<String,Object>();
    		
    		String logDay = DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateStart), +i), "yyyyMMdd");
    		
    		monthComparisonMap.put("log_day", logDay);
    		
    		for (Map<String,Object> mapBef : monthComparison) {
        		String beforDay =  MapUtils.getString(mapBef, "log_day");
        		if (DateUtils.getDateSpace(beforDay, logDay, "yyyyMMdd")==30) {
        			monthComparisonMap.put("bef_sum_dau", MapUtils.getInteger(mapBef, "sum_dau"));
        			break;
        		}
    		}
    		if (MapUtils.getInteger(monthComparisonMap, "bef_sum_dau") == null) {
    			monthComparisonMap.put("bef_sum_dau", 0);
			}
    		
    		for (Map<String,Object> mapNow : dayActive) {
    			String dayNow = MapUtils.getString(mapNow,"log_day");
        		if (dayNow.equals(logDay)) {
        			monthComparisonMap.put("now_sum_dau", MapUtils.getInteger(mapNow, "sum_dau"));
        			break;
        		}
    		}
    		if (MapUtils.getInteger(monthComparisonMap, "now_sum_dau") == null) {
    			monthComparisonMap.put("now_sum_dau", 0);
			}
    		monthComparisonList.add(monthComparisonMap);
    	}
    	
    	 Collections.sort(monthComparisonList, new Comparator<Map<String, Object>>() {
             @Override
             public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                 String date1 = MapUtils.getString(o1, "log_day");
                 String date2 = MapUtils.getString(o2, "log_day");
                 return DateUtils.parseDate(date2).compareTo(DateUtils.parseDate(date1));
             }
         });
    	model.addAttribute("monthComparison", monthComparisonList);
    	
//    	for (Map<String,Object> mapBef : monthComparison) {
//    		String day =  MapUtils.getString(mapBef,"log_day");
//    		for (Map<String,Object> mapNow : dayActive) {
//    			String dayNow = MapUtils.getString(mapNow,"log_day");
//    			if(DateUtils.getDateSpace(day, dayNow, "yyyyMMdd")==30){
//    				mapBef.put("now_sum_dau", MapUtils.getInteger(mapNow, "sum_dau"));
//    			}
//    		}
//    		if (MapUtils.getInteger(mapBef, "now_sum_dau") == null) {
//				mapBef.put("now_sum_dau", 0);
//			}
//    	}
//    	model.addAttribute("monthComparison",monthComparison);
    }


	/**
	 * 周活跃对比图
	 */
	private void weekComparison(HttpServletRequest request, HttpServletResponse response, Model model,List<Map<String, Object>> dayActive) throws ParseException {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String createDateStart = MapUtils.getString(parameter, "createDateStart");
    	String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
    	String begin =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateStart), -7));
    	String end =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateEnd), -7));
    	parameter.put("startDate",StringUtils.replace(begin,"-",""));
        parameter.put("endDate",StringUtils.replace(end, "-", ""));
    	List<Map<String,Object>> weekComparison = globalDaoTemplate.selectList("rizonghe.findWeekComparison",parameter);
    	
    	List<Map<String,Object>> weekComparisonList = new ArrayList<Map<String,Object>>();
    	int dateRange = DateUtils.getDateSpace(createDateStart, createDateEnd, "yyyy-MM-dd");
    	for (int i = 0; i <= dateRange; i++) {
    		Map<String,Object> weekComparisonMap = new HashMap<String,Object>();
    		String logDay = DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(createDateStart), +i), "yyyyMMdd");
             weekComparisonMap.put("log_day", logDay);
    		for (Map<String,Object> mapBef : weekComparison) {
        		String beforDay =  MapUtils.getString(mapBef, "log_day"); //26 - 4
        		if (DateUtils.getDateSpace(beforDay, logDay, "yyyyMMdd")==7) {
        			weekComparisonMap.put("bef_sum_dau", MapUtils.getInteger(mapBef, "sum_dau"));
        			break;
        		}
    		}
    		if (MapUtils.getInteger(weekComparisonMap, "bef_sum_dau") == null) {
    			weekComparisonMap.put("bef_sum_dau", 0);
			}
    		for (Map<String,Object> mapNow : dayActive) {
    			String dayNow = MapUtils.getString(mapNow,"log_day");
        		if (dayNow.equals(logDay)) {
        			weekComparisonMap.put("now_sum_dau", MapUtils.getInteger(mapNow, "sum_dau"));
        			break;
        		}
    		}
    		if (MapUtils.getInteger(weekComparisonMap, "now_sum_dau") == null) {
    			weekComparisonMap.put("now_sum_dau", 0);
			}
    		weekComparisonList.add(weekComparisonMap);
    	}
    	 Collections.sort(weekComparisonList, new Comparator<Map<String, Object>>() {
             @Override
             public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                 String date1 = MapUtils.getString(o1, "log_day");
                 String date2 = MapUtils.getString(o2, "log_day");
                 return DateUtils.parseDate(date2).compareTo(DateUtils.parseDate(date1));
             }
         });

        model.addAttribute("weekComparison", weekComparisonList);
    }

    /**
     * 服务器日活跃统计
     */
    private void dayServerActive(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<Map<String, Object>> dayServerActive = globalDaoTemplate.selectList("rizonghe.findDayServerActiveReport", parameter);
        model.addAttribute("dayServerActive", dayServerActive);
    }

    /**
     * 日查询统计
     */
    private void statisticsDayActive(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
        model.addAttribute("createDateStart",createDateStart);
    	model.addAttribute("createDateEnd",createDateEnd);
        model.addAttribute("betweenDays", DateUtils.getDateSpace(createDateStart, createDateEnd, "yyyy-MM-dd") + 1); // 相减差一天，实际上是2天
        model.addAttribute("serverNum", ((HashMap<String, Object>) globalDaoTemplate.selectList("rizonghe.findServerNum", parameter).get(0)).get("server_num"));
        Map<String, Object> activeNumMap = logDaoTemplate.selectOne("roleLogin.findActiveSumByArea", parameter);
        model.addAttribute("activeNum", activeNumMap == null ? 0 : MapUtils.getString(activeNumMap, "au"));
    }

    /**
     * 日活跃用户统计
     */
    private List<Map<String, Object>> findDayActiveUserReport(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<Map<String, Object>> dayActive = globalDaoTemplate.selectList("rizonghe.findDayActiveUserReport", parameter);
        model.addAttribute("dayActive", dayActive);
        return dayActive;
    }

    /**
     * 月数据报表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "monthlyReport")
    public String monthlyReport(HttpServletRequest request, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        //默认查询时间范围为7天
        setDefaultMonthlyRange(parameter);

        if (StringUtils.isEmpty(MapUtils.getString(parameter, "serverIds"))) {
            addMessage(model, "请先选择服务器");
            return "modules/global/monthlyReport";
        }

        //setServerIdList(parameter);
        List<Map<String, Object>> list = globalDaoTemplate.selectList("monthlyIntegrated.findMonthlyReport", parameter);

//        //已col开头的字段一共288个，这里取其中最大的值即为pccu
       // calculateMaxPCCU(list);
        parameter.put("createDateEnd", MapUtils.getString(parameter, "createDateEnd").replace("-", ""));
        parameter.put("createDateStart", MapUtils.getString(parameter, "createDateStart").replace("-", ""));
        List<Map<String, Object>> monthlyTotalPccu = dashBoardService.selectMonthPccu(parameter);
		List<Map<String, Object>> targetmoneyPCU = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> moneyList = LevelCross.selectPcuu(monthlyTotalPccu);
		maxPCCU(moneyList, targetmoneyPCU);
		dashBoardService.addPccu(list, targetmoneyPCU);
        model.addAttribute("list", list);

        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/monthlyReport";
    }


    /**
     * 计算PCCU值-即取的所有已col开头的字段的最大值
     *
     * @param list
     */
    public void calculateMaxPCCU(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            int max = 0;
            int  acu=0;
            for (int i = 1; i <= 288; i++) {
                max = max > MapUtils.getIntValue(map, "col" + i, 0) ? max : MapUtils.getIntValue(map, "col" + i, 0);
                acu=acu+MapUtils.getIntValue(map, "col" + i, 0);
            }
            map.put("pccu", max);
            map.put("acu", acu * 1.0f / 288);
        }
    }


    /**
     * 每月的PCCU计算方法
     * 首先取得每日的PCCU，然后取其中的最大值
     *
     * @param source source 是日数据
     * @param target target 是月数据
     */
    public void calculateMonthPCCU(List<Map<String, Object>> source, List<Map<String, Object>> target) {
        for (Map<String, Object> map : target) {
            String month = MapUtils.getString(map, "log_month");
            int pccu = 0;
            for (Map<String, Object> dayData : source) {
                String day = MapUtils.getString(dayData, "log_day");    //day:20150501 month:201505
                int dayPcu = MapUtils.getIntValue(dayData, "pccu");
                if (day.startsWith(month) && dayPcu > pccu) {
                    pccu = dayPcu;
                }
            }
            map.put("pccu", pccu);
        }
    }

    /**
     * 获取某个月的PCCU
     *
     * @param month
     */
    @Deprecated
    public Integer calculateMonthPCCU(String month) {
        Integer monthPCCU = (Integer) CacheUtils.get("pccu_" + month);    //key:201505 value:PCCU
        int max = 0;
        if (monthPCCU == null) {
            List<Map<String, Object>> monthList = globalDaoTemplate.selectList("rizonghe.findFiveMinuteCount", month);
            calculateMaxPCCU(monthList);
            for (Map<String, Object> map : monthList) {
                max = max > MapUtils.getIntValue(map, "pccu") ? max : MapUtils.getIntValue(map, "pccu");
            }
            CacheUtils.put("pccu_" + month, max);
        }
        logger.info("month【{}】,pccu【{}】", month, max);
        return max;
    }


    /**
     * 当月多服计算PCCU值-每个5分钟的时间点纵向相加，然后取其中的最大值
     *
     * @param map
     */
    public void calculateCurrentMonthPCCU(Map<String, Object> map) {
        int max = 0;
        for (int i = 1; i <= 288; i++) {
            max = max > MapUtils.getIntValue(map, "col" + i, 0) ? max : MapUtils.getIntValue(map, "col" + i, 0);
        }
        map.put("pccu", max);
    }

    /**
     * 当月多服计算ACCU值-每个5分钟的时间点纵向相加，然后再除以288
     * 方法错误，如果开服时间不是凌晨，那么被除数讲小于288
     *
     * @param map
     */
    @Deprecated
    public void calculateCurrentMonthACU(Map<String, Object> map) {
        int total = 0;
        for (int i = 1; i <= 288; i++) {
            total += MapUtils.getIntValue(map, "col" + i, 0);
        }
        map.put("acu", total * 1.0f / 288);
    }

    /**
     * 月营指标模型分析
     */
   /* @ResponseBody*/
    @RequestMapping(value = "monthSalesReport")
    public String monthSalesReport(mothRevenue mothRevenue, HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) throws IOException {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if (!parameter.containsKey("month")) {
            parameter.put("month", DateUtils.getYear() + "-" + DateUtils.getMonth());
        }
        PrintWriter out = response.getWriter();
        int days = DateUtils.getDayNum(parameter.get("month").toString()); //当月天数
        int now = Integer.valueOf(DateUtils.getDay()); //当前天数
        model.addAttribute("month", parameter.get("month"));
        mothRevenue revenue = toolDaoTemplate.selectOne("monthRevenue.selectMonth", parameter);
        if (revenue == null) {
            addMessage(model, "请到 游戏配置-预估配置中 设置 月营收预估");
        } else {
            model.addAttribute("revenue", revenue);
            parameter.put("months", parameter.get("month").toString().replace("-", ""));
            mothRevenue real = globalDaoTemplate.selectOne("rizonghe.monthRevenus", parameter);
            if (real != null) {
                double arpu = real.getChargeNum() > 0 ? real.getIncome() / real.getChargeNum() : 0;
                double payrate = real.getActive() > 0 ? real.getChargeNum() * 1.0f / real.getActive() : 0;
                real.setArpu(arpu);
                real.setPayrate(payrate);
            }
            int income = Integer.valueOf(revenue == null ? "0" : revenue.getIncome().toString());
            int newUser = Integer.valueOf(revenue == null ? "0" : revenue.getNewUser().toString());
            model.addAttribute("real", real);
            model.addAttribute("speed", now * 100.f / days); //时间进度
            model.addAttribute("surplus", days - now);   //后续每日需完成
            model.addAttribute("redayincome", income * 1.f / days);  //预估日收入
            model.addAttribute("redayuser", newUser * 1.f / days);  //预估日新注册
            String operator = Global.getCommonMap().get("operator").toString();
            String[] opera = operator.split(",");
            for (int i = 0; i < opera.length; i++) {
                parameter.put("pid" + (i + 1), opera[i]);
            }
            model.addAttribute("realDayList", globalDaoTemplate.selectList("rizonghe.dateTarget", parameter)); //实际日收入、日注册
        }
        return "modules/global/monthSalesReport";
    }

    @RequestMapping(value = "export")
    public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws ParseException {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        AuthCondition.filterPlatform(parameter);    //根据平台显示
        setDefaultDateRange(parameter);
        List<Map> dayRank = globalDaoTemplate.selectList("rizonghe.export", parameter);
        for (int i = 0; i < dayRank.size(); i++) {
            String areaId = dayRank.get(i).get("area_id").toString();
            Server server = RedisUtils.getGameServer(areaId);
            dayRank.get(i).put("area_id", server.getName());
        }
        return super.exportXls(dayRank, "日运营详细数据" + System.currentTimeMillis(), "平台", "区服", "日期", "收入", "新增注册数", "活跃用户", "老用户", "ACCU", "PCCU", "DT时长(分)",
                "充值人数", "充值次数", "付费率", "ARPU", "活跃ARPU", "首充人数", "首充次数", "首充金额", "新注册次日留存",
                "新注册3日留存", "新注册7日留存", "新注册双周留存", "新注册月留存");
    }

    @RequestMapping("getIncome")
    @ResponseBody
    public Result getIncome(HttpServletRequest request, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        setDefaultDateRange(parameter);
        List<Map<String, Object>> mapList = logDaoTemplate.selectList("roleLogin.findPlatformData", parameter);
        model.addAttribute("mapList", mapList);
        //按平台分组查询，收入，导量，活跃，等
        //Map<String, Object> totalMap = globalDaoTemplate.selectOne("rizonghe.findByPlatformTotal", parameter);
        Map<String, Object> totalMap = Maps.newHashMap();
        for (Map<String, Object> map : mapList) {
            totalMap.put("income", MapUtils.getIntValue(map, "income") + MapUtils.getIntValue(totalMap, "income"));
            totalMap.put("au", MapUtils.getIntValue(map, "au") + MapUtils.getIntValue(totalMap, "au"));
            totalMap.put("ru", MapUtils.getIntValue(map, "ru") + MapUtils.getIntValue(totalMap, "ru"));
        }
        for (Map<String, Object> inner : mapList) {
            double income = Double.parseDouble(inner.get("income").toString()) / Integer.parseInt(totalMap.get("income").toString());
            inner.put("incomePercent", UserUtils.fourFiveData(income));
            double au = Double.parseDouble(inner.get("au").toString()) / Integer.parseInt(totalMap.get("au").toString());
            inner.put("auPercent", UserUtils.fourFiveData(au));
            double ru = Integer.parseInt(inner.get("ru").toString()) / Integer.parseInt(totalMap.get("ru").toString());
            inner.put("ruPercent", UserUtils.fourFiveData(ru));
        }
        model.addAttribute("totalMap", totalMap);
        return new Result(true).data(mapList);
    }

    public void maxPCCU(List<Map<String, Object>> list, List<Map<String,Object>>   target) {
    	   for (Map<String, Object> map : list) {
    		   int max = 0;
    		   int  acu=0;
    			Map<String, Object> inner = new HashMap<>();
    			inner.put("logDay", MapUtils.getString(map, "logDay"));
    			int[] pcu = (int[]) MapUtils.getObject(map, "RealTime");
    			for(int i=0;i<pcu.length;i++){
    				 acu=acu+pcu[i];
	    			if(pcu[i]>max){
	    				max=pcu[i];
	    			}
    			}
    			inner.put("pccu", max);
    			inner.put("acu", acu * 1.0f / 288);
    			target.add(inner);
    		}
    	}
    
    @Deprecated
    public Integer calculateMonthPU(String month) {
      	   Integer monthPCCU = (Integer) CacheUtils.get("pccu_" + month);    //key:201505 value:PCCU
           if (monthPCCU == null) {
        	   monthPCCU=0;
               List<Map<String, Object>> monthList = globalDaoTemplate.selectList("monthlyIntegrated.selectPccu", month);
               //calculateMaxPCCU(monthList);
               List<Map<String, Object>> targetPCU = new ArrayList<Map<String, Object>>();
       		   List<Map<String, Object>> pcuList = LevelCross.selectPcuu(monthList);
       		     // 已col开头的字段一共288个，这里取其中最大的值即为pccu
	       		maxPCCU(pcuList, targetPCU);
	       		for(Map<String,Object> map : targetPCU){
	       			monthPCCU = monthPCCU > MapUtils.getIntValue(map, "pccu") ? monthPCCU : MapUtils.getIntValue(map, "pccu");
	   			}
	               CacheUtils.put("pccu_" + month, monthPCCU);
	         }
           logger.info("month【{}】,pccu【{}】", month, monthPCCU);
           return monthPCCU;
      }
    //月总数据acu
    public float calculateAcu(String month) {
              List<Map<String, Object>> monthList =globalDaoTemplate.selectList("monthlyIntegrated.selectPccu",month);
    		   List<Map<String, Object>> pcuList = LevelCross.selectPcuu(monthList);
    		   int acu=0;
    		   for(Map<String,Object> map:pcuList){
    			      int[] tmpRealTime = (int[]) map.get("RealTime");
    			    for (int i = 0; i < 288; i++) {
    			    	acu = acu + tmpRealTime[i];
				  }
    		   }
    	int day=DateUtils.getDaysByYearMonth(Integer.parseInt(month.substring(0, month.length()-2)), Integer.parseInt(month.substring(month.length()-2, month.length())));
        return acu * 1.0f / 288/day;
   }
    //基础信息-每日运营数据acu
    public void maxPCCUACU(List<Map<String, Object>> list, List<Map<String,Object>>   target) {
   	   for (Map<String, Object> map : list) {
   		 int max = 0;
   			Map<String, Object> inner = new HashMap<>();
   			inner.put("logDay", MapUtils.getString(map, "logDay"));
   			int[] pcu = (int[]) MapUtils.getObject(map, "RealTime");
   			int acu=0;
   			for(int i=0;i<pcu.length;i++){
      			      acu=acu+pcu[i];
	    			if(pcu[i]>max){
	    				max=pcu[i];
	    			}
   			}
   			inner.put("pccu", max);
   			inner.put("acu", acu * 1.0f / 288);
   			target.add(inner);
   		}
   	}
    /**
     * 基础信息月总数据
     * 获取数据是实时分服表
     * 当月时间点求和，取其中最大的值即为pccu
     */
    	public Integer monthPU(String month) {
    		Integer monthPCCU = (Integer) CacheUtils.get("pccu_" + month); // key:201505
    		if (monthPCCU == null) {
    			monthPCCU = 0;
    			List<Map<String, Object>> monthList = logDaoTemplate.selectList("statRealTimeService.month", month);
    			for (Map<String, Object> map : monthList) {
    				monthPCCU = monthPCCU > MapUtils.getIntValue(map, "pccu") ? monthPCCU: MapUtils.getIntValue(map, "pccu");
    			}
    			CacheUtils.put("pccu_" + month, monthPCCU);
    		}
    		logger.info("month【{}】,pccu【{}】", month, monthPCCU);
    		return monthPCCU;
    	}
    
}
