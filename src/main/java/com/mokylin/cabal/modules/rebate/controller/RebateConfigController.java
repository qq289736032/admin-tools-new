package com.mokylin.cabal.modules.rebate.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.rebate.entity.RebateGoods;
import com.mokylin.cabal.modules.rebate.entity.RebateGoodsLog;
import com.mokylin.cabal.modules.rebate.entity.RebateGoodsSpec;
import com.mokylin.cabal.modules.rebate.entity.RebateKeepDay;
import com.mokylin.cabal.modules.rebate.entity.RebateRatio;
import com.mokylin.cabal.modules.rebate.service.GoodsSpecService;
import com.mokylin.cabal.modules.rebate.service.KeepDayService;
import com.mokylin.cabal.modules.rebate.service.RebateGoodsService;
import com.mokylin.cabal.modules.rebate.service.RebateRatioService;
import com.mokylin.cabal.modules.tools.service.GoodsAnalyzeService;
import com.mokylin.cabal.modules.tools.vo.Goods;
@Controller
@RequestMapping(value = "${adminPath}/rebate/config")
public class RebateConfigController extends BaseController{
	@Autowired
	private GoodsSpecService goodsSpecService;
	@Autowired
	private KeepDayService keepDayService;
	@Autowired
	private RebateGoodsService rebateGoodsService;
	@Autowired
	private RebateRatioService rebateRatioService;
	
    @Autowired
    private GoodsAnalyzeService goodsAnalyzeService;
	
	/**
	 * 返利配置页面
	 * @return
	 */
    @RequestMapping(value = "/commonConfig")
    public String commonConfig() {
        
        return "modules/rebate/commonConfig";
    }
    
	/**
	 * 返利特殊配置页面
	 * @return
	 */
    @RequestMapping(value = "/specConfig")
    public String specConfig() {
        
        return "modules/rebate/specConfig";
    }
    
	/**
	 * 返利配置日志页面
	 * @return
	 */
    @RequestMapping(value = "/configLog")
    public String configLog() {
        
        return "modules/rebate/configLog";
    }
    
    /**
     * 获取天数配置数据
     * @return
     */
    @RequestMapping(value = "/keepDayData")
    @ResponseBody
    public RebateKeepDay getKeepDayData() {
    	
    	RebateKeepDay day = keepDayService.getKeepDay();
    	return day==null?new RebateKeepDay():day;
    }
    
    /**
     * 修改天数配置数据
     * @return
     */
    @RequestMapping(value = "/updateKeepDay")
    @ResponseBody
    public Result updateKeepDay(RebateKeepDay day) {
    	
    	return keepDayService.insertKeepDay(day);
    }
    
    /**
     * 获取天数配置日志数据
     * @return
     */
    @RequestMapping(value = "/keepDayLogData")
    @ResponseBody
    public Map<String,Object> keepDayLogData(Date startTime,Date endTime,int limit,int offset,String createName) {
    	
    	return keepDayService.getAllLog(startTime, endTime, limit, offset, createName);
    }
    
    /**
     * 根据名字查询物品
     * @return
     */
    @RequestMapping(value = "/searchByGoodsName")
    @ResponseBody
    public Map<String,Object> getRebateGoodsData(String goodsName) {
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("results", goodsAnalyzeService.query(goodsName));
    	return map;
    }
    
    /**
     * 获取返利物品配置数据
     * @return
     */
    @RequestMapping(value = "/rebateGoodsData")
    @ResponseBody
    public List<RebateGoods> getRebateGoodsData(RebateGoods goods) {
    	
    	return rebateGoodsService.getAll(goods);
    }
    
    /**
     * 新增/修改返利物品配置
     * @return
     */
    @RequestMapping(value = "/insertRebateGoods")
    @ResponseBody
    public Result insertRebateGoods(RebateGoods goods) {
    	
    	return rebateGoodsService.insertGoods(goods);
    }
    
    /**
     * 删除返利物品配置
     * @return
     */
    @RequestMapping(value = "/delRebateGoods")
    @ResponseBody
    public Result delRebateGoods(String [] ids) {
    	
    	return rebateGoodsService.batchDel(ids);
    }
    
    /**
     * 获取返利物品配置日志数据
     * @return
     */
    @RequestMapping(value = "/rebateGoodsLogData")
    @ResponseBody
    public Map<String,Object> rebateGoodsLogData(Date startTime,Date endTime,int limit,int offset,RebateGoodsLog log) {
    	
    	return rebateGoodsService.getAllLog(startTime, endTime, limit, offset, log);
    }
    
    /**
     * 获取返利比例配置数据
     * @return
     */
    @RequestMapping(value = "/rebateRatioData")
    @ResponseBody
    public List<RebateRatio> getRebateRatioData() {
    	
    	return rebateRatioService.getAll();
    }
    
    /**
     * 新增/修改返利比例配置
     * @return
     */
    @RequestMapping(value = "/insertRebateRatio")
    @ResponseBody
    public Result insertRebateRatio(RebateRatio ratio) {
    	
    	return rebateRatioService.insertRabateRatio(ratio);
    }
    
    /**
     * 删除返利比例配置
     * @return
     */
    @RequestMapping(value = "/delRebateRatio")
    @ResponseBody
    public Result delRebateRatio(RebateRatio ratio) {
    	
    	return rebateRatioService.delete(ratio);
    }
    
    /**
     * 获取返利比例配置日志数据
     * @return
     */
    @RequestMapping(value = "/rebateRatioLogData")
    @ResponseBody
    public Map<String,Object> rebateRatioLogData(Date startTime,Date endTime,int limit,int offset,String createName,String editType) {
    	
    	return rebateRatioService.getAllLog(startTime, endTime, limit, offset, createName, editType);
    }
    
    /**
     * 获取特殊配置数据
     * @return
     */
    @RequestMapping(value = "/goodsSpecData")
    @ResponseBody
    public List<RebateGoodsSpec> getGoodsSpecData(RebateGoodsSpec spec) {
    	
    	return goodsSpecService.getAll(spec);
    }
    
    /**
     * 新增特殊配置数据
     * @return
     */
    @RequestMapping(value = "/insertGoodsSpec")
    @ResponseBody
    public Result insertGoodsSpec(String[] pids,RebateGoodsSpec spec) {
    	
    	return goodsSpecService.batchInsertSpec(pids, spec);
    }
    
    /**
     * 修改特殊配置数据
     * @return
     */
    @RequestMapping(value = "/updateGoodsSpec")
    @ResponseBody
    public Result updateGoodsSpec(RebateGoodsSpec spec) {
    	
    	return goodsSpecService.updateSepc(spec);
    }
    
    /**
     * 删除特殊配置数据
     * @return
     */
    @RequestMapping(value = "/delGoodsSpec")
    @ResponseBody
    public Result delGoodsSpec(String []ids) {
    	
    	return goodsSpecService.batchDel(ids);
    }
    

}
