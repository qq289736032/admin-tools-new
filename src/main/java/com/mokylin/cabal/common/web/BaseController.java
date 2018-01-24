/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.common.web;

import java.beans.PropertyEditorSupport;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.beanvalidator.BeanValidators;
import com.mokylin.cabal.common.cache.EhcacheCacheManager;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisBaseDao;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;

/**
 * 控制器支持类
 *
 * @author 稻草鸟人
 * @version 2014-3-23
 */
public abstract class BaseController extends MybatisBaseDao {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected EhcacheCacheManager ehcacheCacheManager;

    /**
     * 每5万条一个sheet
     */
    public final static int MAX_SHEET_RECORD_NUM = 50000;

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
     */
    protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(model, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
     */
    protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            addMessage(redirectAttributes, list.toArray(new String[]{}));
            return false;
        }
        return true;
    }

    /**
     * 添加Model消息
     *
     * @param messages 消息
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     *
     * @param messages 消息
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }


    /**
     * 导出xls
     *
     * @param dataList 数据集只能是List<Map>形式
     * @param fileName 文件名
     * @param titles   标题
     */
    public ResponseEntity<byte[]> exportXls(List<Map> dataList, String fileName, String... titles) {
        int dataListSize = dataList.size();
        HSSFWorkbook workBook = new HSSFWorkbook();   // 创建Excel文档
        for (int k = 0; k <= dataListSize / MAX_SHEET_RECORD_NUM; k++) {
            int fromIndex = k * MAX_SHEET_RECORD_NUM;
            int toIndex = fromIndex + MAX_SHEET_RECORD_NUM;
            if (toIndex > dataListSize) {
                toIndex = dataListSize;
            }
            List<Map> subList = dataList.subList(fromIndex, toIndex);
            // 新建一个工作页
            HSSFSheet sheet = workBook.createSheet();
            if (titles != null) {
                HSSFRow titleRow = sheet.createRow(0); // 下标为0的行开始
                HSSFCell[] firstcell = new HSSFCell[titles.length];
                for (int i = 0; i < titles.length; i++) {
                    firstcell[i] = titleRow.createCell(i);
                    firstcell[i].setCellValue(new HSSFRichTextString(titles[i]));
                }
            }
            for (int i = 0; i < subList.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);  // 创建一行
                Map recordMap = subList.get(i);
                Set keySet = recordMap.keySet();
                int j = 0;
                for (Object key : keySet) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(MapUtils.getString(recordMap, key));
                    j++;
                }
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            workBook.write(out);
        } catch (IOException e) {
            logger.error(null, e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            fileName = new String((fileName + ".xls").getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
        }
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
    }


    public Page<Map<String, Object>> logPaging(HttpServletRequest request, HttpServletResponse response, String statement) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = logDaoTemplate.paging(statement, parameter);
        return page;
    }

    public Page<Map<String, Object>> gamePaging(HttpServletRequest request, HttpServletResponse response, String statement) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = gameDaoTemplate.paging(statement, parameter);
        return page;
    }

    public Page<Map<String, Object>> globalPaging(HttpServletRequest request, HttpServletResponse response, String statement) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = globalDaoTemplate.paging(statement, parameter);
        return page;
    }

    public Page<Map<String, Object>> toolPaging(HttpServletRequest request, HttpServletResponse response, String statement) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = toolDaoTemplate.paging(statement, parameter);
        return page;
    }

    public String getParameter(HttpServletRequest request, String key) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        return MapUtils.getString(parameter, key);
    }

    public void put(HttpServletRequest request, String key, String value) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put(key, value);
    }


    
    public List<Map<String, Object>> logList(HttpServletRequest request, HttpServletResponse response, String statement){
    	 MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
         parameter.setPage(new Page(request, response));
         List<Map<String, Object>> list = logDaoTemplate.selectList(statement,parameter);
         return list;
    }
    /**
     * 根据查询时间获取表名的后缀
     * 如:goods_flow_log_20150306
     * @param parameter
     */
    public void setDefaultTableSuffix(HashMap parameter){
        String startDate;
        if(parameter.containsKey("createDateStart")){
            startDate= MapUtils.getString(parameter, "createDateStart");
        }else{
            startDate = DateUtils.getDate();
        }
        parameter.put("startDate",StringUtils.replace(startDate,"-",""));
        parameter.put("endDate",StringUtils.replace(startDate, "-", ""));
        parameter.put("suffix", StringUtils.replace(startDate,"-",""));
    }


    public void setTableSuffix(HashMap parameter){
        String logDay;
        if(parameter.containsKey("logDay")){
            logDay= MapUtils.getString(parameter, "logDay");
        }else{
            logDay = DateUtils.getDate();
        }
        parameter.put("logDay",logDay);
        parameter.put("suffix", StringUtils.replace(logDay,"-",""));

    }

    /**
     * 默认设置查询时间范围为最近7天
     *
     * @param parameter
     */
    public void setDefaultTimeRange(HashMap parameter) {
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
        if (StringUtils.isEmpty(createDateStart) && StringUtils.isEmpty(createDateEnd)) {
            parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(new Date(), -7)));
            parameter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(new Date(), -1)));

            //数据库
            parameter.put("startDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(new Date(), -7)),"-",""));
            parameter.put("endDate",StringUtils.replace(DateUtils.formatDate(DateUtils.addDays(new Date(), -1)), "-", ""));
        }else{
        	  //数据库
            parameter.put("startDate",StringUtils.replace(createDateStart,"-",""));
            parameter.put("endDate",StringUtils.replace(createDateEnd, "-", ""));

        }
    }

	public void setAmountInit(HashMap parameter) {
		String startAmount = MapUtils.getString(parameter, "startAmount");
		String endAmount = MapUtils.getString(parameter, "endAmount");
		if ((StringUtils.isEmpty(startAmount)) && (StringUtils.isEmpty(endAmount))) {
			parameter.put("startAmount", Integer.valueOf(8));
			parameter.put("endAmount", Integer.valueOf(10));

			parameter.put("startAmountSql", Integer.valueOf(800));
			parameter.put("endAmountSql", Integer.valueOf(1000));
		} else {
			parameter.put("startAmountSql", Integer.valueOf(Integer.parseInt(startAmount) * 100));
			parameter.put("endAmountSql", Integer.valueOf(Integer.parseInt(endAmount) * 100));
		}
	}

    public void setDefault7DayRange(HashMap parameter) {
        String createDateStart = MapUtils.getString(parameter, "startDate");
        String createDateEnd = MapUtils.getString(parameter, "endDate");
        if (StringUtils.isEmpty(createDateStart) && StringUtils.isEmpty(createDateEnd)) {
            parameter.put("startDate", DateUtils.formatDate(DateUtils.addDays(new Date(), -7)));
            parameter.put("endDate", DateUtils.formatDate(DateUtils.addDays(new Date(), 0)));
        }
    }


    public void setDefaultLogDay(HashMap parameter) {
        String logDay = MapUtils.getString(parameter, "startDate");
        if(StringUtils.isEmpty(logDay)){
            String today = DateUtils.getDate();
            parameter.put("startDate", today);
            parameter.put("logDay", DateUtils.formatDate(new Date(),"yyyyMMdd"));
        }else{
            parameter.put("logDay", DateUtils.formatDate(DateUtils.parseDate(logDay), "yyyyMMdd"));
        }
    }

    public int setDefaultDateRange(HashMap parameter){
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
        if (StringUtils.isEmpty(createDateStart) && StringUtils.isEmpty(createDateEnd)) {
            parameter.put("startDate", DateUtils.formatDate(DateUtils.addDays(new Date(), -15),"yyyyMMdd"));
            parameter.put("endDate", DateUtils.formatDate(DateUtils.addDays(new Date(), 0),"yyyyMMdd"));
            parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(new Date(), -15)));
            parameter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(new Date(), 0)));
        }else{
            parameter.put("startDate", DateUtils.formatDate(DateUtils.parseDate(createDateStart),"yyyyMMdd"));
            parameter.put("endDate", DateUtils.formatDate(DateUtils.parseDate(createDateEnd),"yyyyMMdd"));
        }
        Date date1 = DateUtils.parseDate(MapUtils.getString(parameter, "createDateEnd"));
        Date date2 = DateUtils.parseDate(MapUtils.getString(parameter, "createDateStart"));
        return DateUtils.getDays(date1, date2);

    }

    public void setDefaultWeekRange(HashMap parameter) {
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
        int weekStart = DateUtils.getDayOfWeek(createDateStart);
        int weekEnd = DateUtils.getDayOfWeek(createDateEnd);
        int yearStart = DateUtils.getDayOfYear(createDateStart);
        int yearEnd = DateUtils.getDayOfYear(createDateEnd);
//        parameter.put("weekStart", weekStart);
//        parameter.put("weekEnd", weekEnd);
//        parameter.put("yearStart", yearStart);
//        parameter.put("yearEnd", yearEnd);

        //跨年问题 例如:2014年12月29号-31号 算成2015年的第一周
        if (yearEnd == yearStart && weekEnd <= weekStart) {
//			parameter.put("yearEnd", yearStart + 1);
            yearStart = yearStart + 1;
        }
        String startWeek = "";
        String endWeek = "";
        if (weekStart < 10) {
        	startWeek = yearStart + "0" + weekStart;
        } else {
        	startWeek = yearStart + "" + weekStart;
        }
        if (weekEnd < 10) {
        	endWeek = yearEnd + "0" + weekEnd;
        } else {
        	endWeek = yearEnd + "" + weekEnd;
        }
        parameter.put("startWeek", startWeek);
        parameter.put("endWeek", endWeek);


    }

    /**
     * 默认设置查询时间范围为最近7个月
     *
     * @param parameter
     */
    public void setDefaultMonthlyRange(HashMap parameter) {
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
        if (StringUtils.isEmpty(createDateStart) && StringUtils.isEmpty(createDateEnd)) {
            parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addMonths(new Date(), -8), "yyyy-MM"));
            parameter.put("createDateEnd", DateUtils.formatDate(DateUtils.addMonths(new Date(), 0), "yyyy-MM"));
        }
    }

    /**
     * 默认设置查询时间范围为最近7个月
     *
     * @param parameter
     */
    public void setDefaultDay(HashMap parameter) {
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        if (StringUtils.isEmpty(createDateStart)) {
            parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(new Date(), -1)));
        }
    }

    /**
     * 服务器多选  默认页面不选的话，取当前用户下的所有服
     *
     * @param parameter
     */
    public void setServerIdList(HashMap parameter) {
        List<String> list = new ArrayList<String>();
        if (!parameter.containsKey("serverIdList")) {

            List<GamePlatform> platFormList = UserUtils.getGamePlatformListContainServer();
            for (GamePlatform gamePlatform : platFormList) {
                List<Server> serverList = gamePlatform.getGameServerList();
                for (Server server : serverList) {
                    list.add(String.valueOf(server.getWorldId()));
                }
            }
            parameter.put("serverIdList", list);

//	    	list.add(String.valueOf(parameter.get("currentServerId")));
//	    	parameter.put("serverIdList", list);
//	    	parameter.put("serverIds", parameter.get("currentServerId"));
        }
    }

    /**
     * 处理多平台选择<br>
     * 只选一个的时候，没有list，需要手动加入<br>
     * 全选的时候，list置为空，提高查询效率<br>
     *
     * @param parameter
     */
    public void setMultiplePlatform(HashMap parameter) {
        List<String> list = new ArrayList<String>();
        if (!parameter.containsKey("pidsList") && parameter.containsKey("pids")) {
            list.add(String.valueOf(parameter.get("pids")));
            parameter.put("pidsList", list);
        } else if (parameter.containsKey("pidsList")) {
            List pidsList = (List) parameter.get("pidsList");
            if (pidsList.size() == UserUtils.getGamePlatformList().size()) {
                parameter.put("pidsList", null);
            }
        }
    }

	
	/**
	 * 用户类型
	 */
	public Map<Integer,Object> vipLevelMap(){
	    
		Map<Integer,Object> vipLevelMap =new LinkedHashMap<Integer, Object>() ;
		Map<String, Object> vipMap = Global.getVipLevelMap(); 
		List<Integer>mappingList = new ArrayList<>(); 
		Iterator iter = vipMap.entrySet().iterator(); 
	    while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    int key =Integer.parseInt(entry.getKey().toString()) ; 
		    mappingList.add(key);
		}
		Collections.sort(mappingList);
		for (int i = 0; i < mappingList.size(); i++) {
			vipLevelMap.put(mappingList.get(i), vipMap.get(mappingList.get(i).toString()));
		}
	    return vipLevelMap;
	}
}
