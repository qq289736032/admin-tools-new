package com.mokylin.cabal.modules.rebate.controller;

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
import com.mokylin.cabal.modules.rebate.entity.RebateOperaDetail;
import com.mokylin.cabal.modules.rebate.entity.RebateOperaLog;
import com.mokylin.cabal.modules.rebate.entity.RebateStatisticRecharge;
import com.mokylin.cabal.modules.rebate.service.OperaLogService;
import com.mokylin.cabal.modules.rebate.service.SelfHelpService;

@Controller
@RequestMapping(value = "${adminPath}/rebate/self")
public class RebateSelfHelpController extends BaseController{
	@Autowired
	private SelfHelpService selfHelpService;
	@Autowired
	private OperaLogService operaLogService;
	/**
	 * 自助返利页面
	 * @return
	 */
    @RequestMapping(value = "/selfHelp")
    public String selfHelp() {
        
        return "modules/rebate/selfHelp";
    }
    
	/**
	 * 申请返利页面
	 * @return
	 */
    @RequestMapping(value = "/apply")
    public String apply() {
        
        return "modules/rebate/apply";
    }
    
	/**
	 * 返利操作日志页面
	 * @return
	 */
    @RequestMapping(value = "/applyLog")
    public String applyLog() {
        
        return "modules/rebate/applyLog";
    }
    
    /**
     * 自助返利数据
     * @return
     */
    @RequestMapping(value = "/selfHelpData")
    @ResponseBody
    public List<Map<String,Object>> selfHelpData(String roleName,String pid,String serverId,BigInteger roleId,String userId) {
    	
    	return selfHelpService.selfHelpData(roleName,pid,serverId,roleId,userId);
    }
    
    /**
     * 最近N日单日充值情况数据
     * @return
     */
    @RequestMapping(value = "/chargeRebateData")
    @ResponseBody
    public List<RebateStatisticRecharge> chargeRebateData(Long roleId,int day) {
    	
    	return selfHelpService.chargeDeatailData(roleId, day);
    }
    
    /**
     * 返利物品数据
     * @return
     */
    @RequestMapping(value = "/goodsData")
    @ResponseBody
    public List<Map<String,Object>> goodsData(String pid,Long roleId) {
    	
    	return selfHelpService.getGoods(pid,roleId);
    }
    
    /**
     * 提交返利物品
     * @param attachments 附件物品组成的字符串(物品id;物品数量;是否绑定:物品id;物品数量;是否绑定...)
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public Result submit(Long roleId,String attachments,String content,String title,String serverId,String pid,String platName,String serverName,String userId) {
    	return selfHelpService.submit(roleId,attachments,content,title,serverId,pid,platName,serverName,userId);
    }
    
    /**
     * 重新发送
     * @param 
     * @return
     */
    @RequestMapping(value = "/reSubmit")
    @ResponseBody
    public Result reSubmit(String attachments,String logId,String content,String title,Long roleId,String serverId) {
    	return selfHelpService.reSubmit(attachments,logId,content,title,roleId,serverId);
    }
    
    /**
     * 返利操作日志
     * @return
     */
    @RequestMapping(value = "/applyLogData")
    @ResponseBody
    public Map<String,Object> applyLogData(int limit,int offset,RebateOperaLog log,Date startTime,Date endTime) {
    	
    	return operaLogService.LogData(limit, offset, log, startTime, endTime);
    }
    
    /**
     * 返利操作详情
     * @return
     */
    @RequestMapping(value = "/logDetailData")
    @ResponseBody
    public List<RebateOperaDetail> logDetailData(String logId) {
    	
    	return operaLogService.LogDetailData(logId);
    }
}
