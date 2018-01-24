package com.mokylin.cabal.modules.welfare.web;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.welfare.entity.SingleServerConfig;
import com.mokylin.cabal.modules.welfare.service.SpecialConfigService;
@Controller
@RequestMapping(value = "${adminPath}/welfare/specConfig")
public class WelfareSpecialConfigController extends BaseController{
	@Autowired
	SpecialConfigService configService;
	/**
	 * 特殊配置页面
	 * @return
	 */
    @RequestMapping(value = "/specConfig")
    public String specConfig() {
        
        return "modules/welfare/specConfig";
    }
    
	/**
	 * 特殊配置日志页面
	 * @return
	 */
    @RequestMapping(value = "/specConfigLog")
    public String specConfigLog() {
        
        return "modules/welfare/specConfigLog";
    }
    
    /**
     * 更新/删除 单账号topCharge
     * @param 
     * @return
     */
    @RequestMapping(value = "/updateTopCharge")
    @ResponseBody
    public Result updateTopCharge(BigInteger topCharge,String[]ids,int isInfluence) {
        
        return configService.updateWelfareNumTopCharge(topCharge, ids, isInfluence,null);
    }
    
    /**
     * 单账号特殊配置日志数据
     * @param 
     * @return
     */
    @RequestMapping(value = "/singleAccountLogData")
    @ResponseBody
    public Map<String,Object> singleAccountLogData(String pid,String serverId,Date startTime,Date endTime,int limit,int offset) {
        
        return configService.singleAccountLogData(pid, serverId, startTime, endTime, limit, offset);
    }
    
    /**
     * 单服特殊配置数据
     * @param 
     * @return
     */
    @RequestMapping(value = "/serverConfigData")
    @ResponseBody
    public Map<String,Object> serverConfigData(String pid,String serverId,Integer status,int limit,int offset) {
        
        return configService.singleServerData(pid, serverId, status, limit, offset);
    }
    
    /**
     * 删除单服特殊配置
     * @param 
     * @return
     */
    @RequestMapping(value = "/deleteServerConfig")
    @ResponseBody
    public Result deleteServerConfig(String ids[]) {
        
        return configService.deleteServerConfig(ids,null);
    }
    
    /**
     * 单服特殊配置
     * @param 
     * @return
     */
    @RequestMapping(value = "/insertSingleServerConfig")
    @ResponseBody
    public Result insertSingleServerConfig(SingleServerConfig config) {
        
        return configService.insertSingleServerConfig(config);
    }
    
    /**
     * 单服特殊配置日志数据
     * @param 
     * @return
     */
    @RequestMapping(value = "/singleServerLogData")
    @ResponseBody
    public Map<String,Object> singleServerLogData(String pid,String serverId,Date startTime,Date endTime,int limit,int offset) {
        
        return configService.singleServerLogData(pid, serverId, startTime, endTime, limit, offset);
    }
    
	
}
