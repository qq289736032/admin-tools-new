package com.mokylin.cabal.modules.log.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.AdminDefaultThreadFactory;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.RefluxTimeUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.log.entity.UserLevel;
import com.mokylin.cabal.modules.log.service.UserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/10
 * admin-tools
 */
@RequestMapping(value = "${adminPath}/log/userStat")
@Controller
public class UserStatController extends BaseController {

    @Autowired
    private UserService userService;

    private ExecutorService exec = Executors.newCachedThreadPool(new AdminDefaultThreadFactory("user-stat"));
    private CompletionService<List<Map<String, Object>>> service = new ExecutorCompletionService(exec);

    /**
     * 流失用户统计
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("churnUser")
    public String churnUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefault7DayRange(parameter);
        List<Map<String, Object>> list = null;
        try {
            list =selectListByMultiTable("roleLogin.churnUserByDay", parameter);
        } catch (Exception e) {
            logger.error("roleLogin.churnUserByDay", e);
        }

        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String date1 = MapUtils.getString(o1, "cdate");
                String date2 = MapUtils.getString(o2, "cdate");
                return DateUtils.parseDate(date2).compareTo(DateUtils.parseDate(date1));
            }
        });

        model.addAttribute("list", list);

        return "modules/logs/churnUserList";
    }

    @RequestMapping("churnUserLevel")
    public String churnUserLevel(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefaultLogDay(parameter);

        List<Map<String, Object>> weekUser = logDaoTemplate.selectList("roleLogin.churnUserLevelWeek", parameter);
        List<Map<String, Object>> doubleWeekUser = logDaoTemplate.selectList("roleLogin.churnUserLevelDoubleWeek", parameter);
        List<Map<String, Object>> monthUser = logDaoTemplate.selectList("roleLogin.churnUserLevelMonth", parameter);
        Map<String, UserLevel> map = Maps.newHashMap();

        userService.process(map, UserService.UserType.TYPE_WEEK, weekUser);
        userService.process(map, UserService.UserType.TYPE_DOUBLE_WEEK, doubleWeekUser);
        userService.process(map, UserService.UserType.TYPE_MONTH, monthUser);

        int week = 0, doubleWeek = 0, month = 0;
        for (Map.Entry<String, UserLevel> entry : map.entrySet()) {
            UserLevel userLevel = entry.getValue();
            week = week + userLevel.getWeekUser();
            doubleWeek = doubleWeek + userLevel.getDoubleWeekUser();
            month = month + userLevel.getMonthUser();
        }


        TreeMap<String, UserLevel> treeMap = new TreeMap<String, UserLevel>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int level1 = Integer.parseInt(StringUtils.split(o1, "-")[0]);
                int level2 = Integer.parseInt(StringUtils.split(o2, "-")[0]);
                return level1 - level2;
            }
        });

        treeMap.putAll(map);


        model.addAttribute("week", week);
        model.addAttribute("doubleWeek", doubleWeek);
        model.addAttribute("month", month);
        model.addAttribute("map", treeMap); //排序

        return "modules/logs/churnUserLevelList";
    }


    /**
     * 回流用户统计
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("refluxUser")
    public String refluxUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefault7DayRange(parameter);
        List<Map<String, Object>> list = null;
        try {
            list =selectListByMultiTable("roleLogin.refluxUser", parameter);
        } catch (Exception e) {
            logger.error("roleLogin.refluxUser", e);

        }


        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String date1 = MapUtils.getString(o1, "cdate");
                String date2 = MapUtils.getString(o2, "cdate");
                return DateUtils.parseDate(date2).compareTo(DateUtils.parseDate(date1));
            }
        });

        model.addAttribute("list", list);

        return "modules/logs/refluxUserList";
    }

    @RequestMapping("refluxUserLevel")
    public String refluxUserLevel(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefaultLogDay(parameter);

        List<Map<String, Object>> weekUser = logDaoTemplate.selectList("roleLogin.refluxUserLevelWeek", parameter);
        List<Map<String, Object>> doubleWeekUser = logDaoTemplate.selectList("roleLogin.refluxUserLevelDoubleWeek", parameter);
        List<Map<String, Object>> monthUser = logDaoTemplate.selectList("roleLogin.refluxUserLevelMonth", parameter);
        Map<String, UserLevel> map = Maps.newHashMap();

        userService.process(map, UserService.UserType.TYPE_WEEK, weekUser);
        userService.process(map, UserService.UserType.TYPE_DOUBLE_WEEK, doubleWeekUser);
        userService.process(map, UserService.UserType.TYPE_MONTH, monthUser);

        int week = 0, doubleWeek = 0, month = 0;
        for (Map.Entry<String, UserLevel> entry : map.entrySet()) {
            UserLevel userLevel = entry.getValue();
            week = week + userLevel.getWeekUser();
            doubleWeek = doubleWeek + userLevel.getDoubleWeekUser();
            month = month + userLevel.getMonthUser();
        }


        TreeMap<String, UserLevel> treeMap = new TreeMap<String, UserLevel>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int level1 = Integer.parseInt(StringUtils.split(o1, "-")[0]);
                int level2 = Integer.parseInt(StringUtils.split(o2, "-")[0]);
                return level1 - level2;
            }
        });

        treeMap.putAll(map);


        model.addAttribute("week", week);
        model.addAttribute("doubleWeek", doubleWeek);
        model.addAttribute("month", month);
        model.addAttribute("map", treeMap); //排序

        return "modules/logs/refluxUserLevelList";
    }

    public List<Map<String, Object>> selectListByMultiTable(final String statement, final Map parameter) throws Exception {
        CompletionService<List<Map<String, Object>>> service = new ExecutorCompletionService(exec);
        List<Map<String, Object>> list = Lists.newArrayList();
        String now = DateUtils.getDate();
        String from = MapUtils.getString(parameter, "startDate", now);
        String to = MapUtils.getString(parameter, "endDate", DateUtils.addDays(now, -7));
        int tableNum = DateUtils.getDays(DateUtils.parseDate(to), DateUtils.parseDate(from));

        CountDownLatch latch = new CountDownLatch(tableNum);
        List<Future<List<Map<String, Object>>>> futures = Lists.newArrayList();
        for (int i = 0; i <= tableNum; i++) {
            final int finalI = i;
            final String date = DateUtils.addDays(from,finalI);
            Callable<List<Map<String, Object>>> callable = new Callable<List<Map<String, Object>>>() {
                @Override
                public List<Map<String, Object>> call() throws Exception {
                    Map newParameter = Maps.newHashMap();
                    String suffix = StringUtils.replace(date,"-","");
                    newParameter.put("logDay", suffix);
                    RefluxTimeUtils.regLogintime(newParameter);
                    return logDaoTemplate.selectList(statement, newParameter);
                }
            };
            futures.add(service.submit(callable));

            latch.countDown();
        }

        latch.await();

        for (Future<List<Map<String, Object>>> future : futures) {
            try {
                list.addAll(future.get());
                //System.out.println("%%%%%=====>>>>"+future.get());
            } catch (Exception e) {
                logger.error("", e);
            }
        }

        return list;
    }

}
