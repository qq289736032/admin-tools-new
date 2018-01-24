package com.mokylin.cabal.modules.welfare.web;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.welfare.entity.OperaGoldLog;
import com.mokylin.cabal.modules.welfare.service.GoldPoolService;
@Controller
@RequestMapping(value = "${adminPath}/welfare/goldPool")
public class WelfareGoldPoolController extends BaseController{
	@Autowired
	GoldPoolService goldPoolService;
	/**
	 * 平台通道
	 * @return
	 */
    @RequestMapping(value = "/platGoldPool")
    public String toPlatGoldPool() {
        
        return "modules/welfare/welfarePlatGoldPool";
    }
    
	/**
	 * 独代通道
	 * @return
	 */
    @RequestMapping(value = "/indGoldPool")
    public String toPlatNum() {
        
        return "modules/welfare/welfareIndGoldPool";
    }
    
    /**
     * 资源数据
     * @param 
     * @return
     */
    @RequestMapping(value = "/resourceData")
    @ResponseBody
    public Map<String,Object> resourceData(String pid,String serverId,String goldPoolCategory) {
        
        return goldPoolService.resourceData(pid, serverId,goldPoolCategory);
    }
    
    /**
     * 发放元宝
     * @param 
     * @return
     */
    @RequestMapping(value = "/grantGold")
    @ResponseBody
    public Result grantGold(String[] ids,BigInteger gold,String passageway,String pid,String serverId) {
        
        return goldPoolService.grantGold(ids, gold, passageway,pid,serverId);
    }
    
	/**
	 * 平台操作日志
	 * @return
	 */
    @RequestMapping(value = "/platGoldPoolLog")
    public String platGoldPoolLog() {
        
        return "modules/welfare/platGrantGoldLog";
    }
    
	/**
	 * 独代福利发放日志
	 * @return
	 */
    @RequestMapping(value = "/indGoldPoolLog")
    public String indGoldPoolLog() {
        
        return "modules/welfare/indGrantGoldLog";
    }
    
    /**
     * 福利发放日志数据
     * @param 
     * @return
     */
    @RequestMapping(value = "/grantGoldLogData")
    @ResponseBody
    public Map<String,Object> grantGoldLogData(OperaGoldLog log,Date startTime,Date endTime,int limit,int offset) {
        
        return goldPoolService.getGrantGoldLog(log, startTime, endTime, limit, offset);
    }
    
	/**
	 * 平台发放排名
	 * @return
	 */
    @RequestMapping(value = "/platIssueRank")
    public String platIssueRank() {
        
        return "modules/welfare/rankPlat";
    }
    
	/**
	 * 独代发放排名
	 * @return
	 */
    @RequestMapping(value = "/indIssueRank")
    public String indIssueRank() {
        
        return "modules/welfare/rankInd";
    }
    
	/**
	 * 发放排行数据
	 * @return
	 */
    @RequestMapping(value = "/rankData")
    @ResponseBody
    public List<Map<String,Object>> rankData(OperaGoldLog log,Date startTime,Date endTime,String type) {
        
        return goldPoolService.rankData(log, startTime, endTime,type);
    }
    
    
}
