package com.mokylin.cabal.modules.welfare.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.welfare.entity.WelfareNum;
import com.mokylin.cabal.modules.welfare.entity.WelfareNumLog;
import com.mokylin.cabal.modules.welfare.service.WelfareNumService;

@Controller
@RequestMapping(value = "${adminPath}/welfare/num")
public class WelfareNumController extends BaseController{
	
	@Autowired
	WelfareNumService welfareNumService;
	
	/**
	 * 平台通道
	 * @return
	 */
    @RequestMapping(value = "/platNum")
    public String toPlatNum() {
        
        return "modules/welfare/welfareNumPlatform";
    }
    
    /**
     * 独代通道
     * @return
     */
    @RequestMapping(value = "/independentNum")
    public String toIndependentNum() {
        
        return "modules/welfare/welfareNumIndependent";
    }
    
    /**
     * 福利号列表数据
     * @param num
     * @return
     */
    @RequestMapping(value = "/numData")
    @ResponseBody
    public Map<String,Object> numData(WelfareNum num,int offset,int limit) {
        
        return welfareNumService.numData(num, offset, limit);
    }
    
    /**
     * 检查roleName
     * @param num
     * @return
     */
    @RequestMapping(value = "/checkRoleName")
    @ResponseBody
    public Map<String,Object> checkRoleName(String roleName,String serverId,String pid) {
    	
    	return welfareNumService.checkRoleName(roleName, serverId);
    	/*String roleId = RedisUtils.getRoleIdByRoleName(pid, roleName, "");
    	String userId = RedisUtils.getUserIdByRoleId(roleId, ""); 
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("roleId", roleId);
    	map.put("userId", userId);
        return map;*/
    }

    /**
     * 添加内部号
     * @param num
     * @return
     */
    @RequestMapping(value = "/insertNum")
    @ResponseBody
    public Result insertNum(WelfareNum num) {
        
        return welfareNumService.insertNum(num);
    }
    
    /**
     * 更新福利号状态
     * @param num
     * @return
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Result updateStatus(WelfareNum num) {
        
        return welfareNumService.updateStatus(num,null,null);
    }
    
    /**
     * 删除福利号
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteNum")
    @ResponseBody
    public Result deleteNum(String[] ids) {
        
        return welfareNumService.deleteNum(ids);
    }
    
    /**
     * 福利号配置LOG页面
     * @return
     */
    @RequestMapping(value = "/numLog")
    public String toNumLog() {
        
        return "modules/welfare/welfareNumLog";
    }
    
    /**
     * 福利号配置记录日志数据
     * @param num
     * @return
     */
    @RequestMapping(value = "/numLogData")
    @ResponseBody
    public Map<String,Object> numLogData(WelfareNumLog log,int offset,int limit,Date startTime,Date endTime) {
    
        return welfareNumService.numLogData(log, offset, limit,startTime,endTime);
    }
    
    /**
     * 福利号高级管理
     * @return
     */
    @RequestMapping(value = "/numSenior")
    public String numSenior() {
        
        return "modules/welfare/welfareNumSenior";
    }
}
