package com.mokylin.cabal.modules.welfare.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.WelfareDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.utils.GoldRmbUtils;
import com.mokylin.cabal.modules.welfare.entity.OperaGoldLog;
import com.mokylin.cabal.modules.welfare.entity.PlatWelfareConfig;
import com.mokylin.cabal.modules.welfare.entity.SingleAccountLog;
import com.mokylin.cabal.modules.welfare.entity.SingleServerConfig;
import com.mokylin.cabal.modules.welfare.entity.SingleServerLog;
import com.mokylin.cabal.modules.welfare.entity.WelfareNum;

@Transactional(readOnly=true)
@Service
public class SpecialConfigService extends BaseService{
	 @Resource
	 protected WelfareDaoTemplate welfareDaoTemplate;
	 @Resource
	 private RebateDaoTemplate rebateDaoTemplate; 
	 
	 /**
	  * 单账号特殊配置
	  * @param topCharge
	  * @param ids
	  * @param isInfluence 是否受批量修改影响
	  * @return
	  */
	 @Transactional(readOnly=false)
	 public Result updateWelfareNumTopCharge(BigInteger topCharge,String []ids,int isInfluence,List<WelfareNum> nums){
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("ids", ids);
		 if(nums==null){
			 nums = welfareDaoTemplate.selectList("welfareNum.paging", param);
		 }
		 
		 if(topCharge==null){
			//topCharge为NULL时执行删除操作
			 return deleteWelfareNumTopCharge(nums);
		 }
		 
		 param.put("topCharge", topCharge);
		 param.put("updateBy", UserUtils.getUser().getLoginName());
		 param.put("isInfluence", isInfluence);
		 welfareDaoTemplate.update("welfareNum.updateTopCharge", param);
		 
		//插入日志
		 welfareDaoTemplate.batchInsert2("singleAccountLog.batchInsert", generateAccountLog(nums,isInfluence,topCharge));
		 return new Result(true);
	 }
	 
	 private Result deleteWelfareNumTopCharge(List<WelfareNum> nums){
		 
		 for (WelfareNum welfareNum : nums) {
			 Map<String,Object> map = new HashMap<String, Object>();
			 map.put("pid", welfareNum.getPid());
			 map.put("serverId", welfareNum.getServerId());
			 SingleServerConfig config = welfareDaoTemplate.selectOne("singleServerConfig.selectOneByServerIdAndPid", map);
			 if(config!=null){
				 
				 setTopCharge(config.getTopCharge(), welfareNum);
				 continue;
			 }
			 
			 map.put("goldPoolCategory", welfareNum.getPassageway()==OperaGoldLog.PASSAGEWAY_PLAT
					 ?PlatWelfareConfig.GOLD_POOL_CATEGORY_PLAT:PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);
			 PlatWelfareConfig platConfig = welfareDaoTemplate.selectOne("platWelfareConfig.selectOneByPid", map);
			 
			 setTopCharge(platConfig.getTopCharge(), welfareNum);
		 }
		 return new Result(true);
	 }
	 
	 private void setTopCharge(BigInteger topCharge,WelfareNum welfareNum){
		 welfareNum.setTopCharge(topCharge);
		 welfareNum.setUpdateBy(UserUtils.getUser().getLoginName());
		 welfareNum.setIsInfluence(WelfareNum.IS_INFLUENCE_TRUE);
		 welfareDaoTemplate.update("welfareNum.updateOneTopCharge", Collections3.transBean2Map(welfareNum));
		 
		 welfareDaoTemplate.insert("singleAccountLog.insert", generateOneAccountLog(welfareNum, WelfareNum.IS_INFLUENCE_TRUE, topCharge));
	 }
	 
	 /**
	  * 单账号配置日志数据
	  * @param pid
	  * @param serverId
	  * @param startTime
	  * @param endTime
	  * @param limit
	  * @param offset
	  * @return
	  */
	 public Map<String,Object> singleAccountLogData(String pid,String serverId,Date startTime,Date endTime,int limit,int offset){
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("pid", pid);
		 param.put("serverId", serverId);
		 param.put("startTime", startTime);
		 param.put("endTime", endTime);
		 Map<String,Object> map = new HashMap<String, Object>();
		 List<SingleAccountLog> logs = welfareDaoTemplate.selectList("singleAccountLog.paging", param, new RowBounds(offset, limit));
		 map.put("rows", setSumCharge(logs));
		 map.put("total", welfareDaoTemplate.selectOne("singleAccountLog.count", param));
		 return map;
	 }
	 
	 private List<Map<String,Object>> setSumCharge(List<SingleAccountLog> logs){
		 List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		 List<BigInteger> roleIds = new ArrayList<BigInteger>();
		 for (SingleAccountLog singleAccountLog : logs) {
			roleIds.add(singleAccountLog.getRoleId());
		 }
		 if(roleIds.isEmpty()){
			 return result;
		 }
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("roleIds", roleIds.toArray());
		 List<Map<String,Object>> lstChargeSum = rebateDaoTemplate.selectList("rebateRechargeMapper.sumByRoleIds", param);
		 for (SingleAccountLog singleAccountLog : logs) {
			 
			 Map<String,Object> numMap = Collections3.transBean2Map(singleAccountLog);
			 numMap.put("sumCharge", 0);
			 
			 for (Map<String,Object> map : lstChargeSum) {
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(singleAccountLog.getRoleId().compareTo(roleId)==0){
					 numMap.put("sumCharge", GoldRmbUtils.GoldChangeToRmb(Integer.parseInt(map.get("sumMoney").toString())));
				 }
			 }
			 result.add(numMap);
		 }
		 return result;
	 }
	 
	 /**
	  * 单服特殊配置数据
	  * @param pid
	  * @param serverId
	  * @param status
	  * @param limit
	  * @param offset
	  * @return
	  */
	 public Map<String,Object> singleServerData(String pid,String serverId,Integer status,int limit,int offset){
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("pid", pid);
		 param.put("serverId", serverId);
		 param.put("status", status);
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("rows", welfareDaoTemplate.selectList("singleServerConfig.paging", param, new RowBounds(offset, limit)));
		 map.put("total", welfareDaoTemplate.selectOne("singleServerConfig.count", param));
		 return map;
	 }
	 
	 /**
	  * 单服特殊配置
	  * @param config
	  * @return
	  */
	 @Transactional(readOnly=false)
	 public Result insertSingleServerConfig(SingleServerConfig config){
		 String serverIds [] = {config.getServerId()};
		 String pids [] = {config.getPid()};
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("sids", serverIds);
		 param.put("pids", pids);
		 
		 config.setStatus(SingleServerConfig.STATUS_FALSE);
		 
		 Map<String,Object> paramMap = new HashMap<String, Object>();
		 paramMap.put("pid", config.getPid());
		 paramMap.put("goldPoolCategory", PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);
		 PlatWelfareConfig platConfig = welfareDaoTemplate.selectOne("platWelfareConfig.selectOneByPid", paramMap);
		 if(platConfig!=null&&platConfig.getStatus()==PlatWelfareConfig.STATUS_NORMAL){
			 config.setStatus(SingleServerConfig.STATUS_TRUE);
		 }
		 List<SingleServerConfig> configs = welfareDaoTemplate.selectList("singleServerConfig.selectAllByServerIdAndPid", param);
		 String editType = SingleServerLog.EDIT_TYPE_ADD;
		 if(configs.isEmpty()){
			//数据库不存在  插入操作
			 config.setId(IdGen.uuid());
			 config.setCreateBy(UserUtils.getUser().getId());
			 config.setCreateName(UserUtils.getUser().getLoginName());
			 config.setUpdateBy(UserUtils.getUser().getLoginName());
			 welfareDaoTemplate.insert("singleServerConfig.insert", config);
		 }else{
			 editType = SingleServerLog.EDIT_TYPE_UPDATE;
			 //数据库存在数据  更新操作
			 updateServerConfig(config,pids,serverIds);
		 }
		 //更新福利号特殊配置
		 updateWelfareNumTopCharge(serverIds,pids,config.getTopCharge());
		 //插入操作日志
		 insertServerLog(config,editType);
		 return new Result(true);
	 }
	 
	 public void updateWelfareNumTopCharge( Object serverIds [], Object pids [],BigInteger topCharge){
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("pids", pids);
		 param.put("serverIds", serverIds);
		 param.put("isInfluence", SingleServerConfig.IS_INFLUENCE_TRUE);
		 List<WelfareNum> nums = welfareDaoTemplate.selectList("welfareNum.selectAllByPidAndServerId",param);
	    	List<String> numIds = new ArrayList<String>();
	    	for (WelfareNum welfareNum : nums) {
				numIds.add(welfareNum.getId());
			}
	    	if(!numIds.isEmpty()){
	    		updateWelfareNumTopCharge(topCharge, 
	    				numIds.toArray(new String[nums.size()]), WelfareNum.IS_INFLUENCE_TRUE, nums);
	    	}
	 }
	 
	 /**
	  * 更新单服特殊配置
	  * @param config
	  * @param pids
	  * @param serverIds
	  */
	 private void updateServerConfig(SingleServerConfig config,String[] pids,String[] serverIds){
		 config.setUpdateBy(UserUtils.getUser().getLoginName());
		 Map<String,Object> param = Collections3.transBean2Map(config);
		 param.put("pids", pids);
		 param.put("sids", serverIds);
		 welfareDaoTemplate.update("singleServerConfig.updateByServerIdAndPid", param);
	 }
	 
	 /**
	  * 插入日志
	  * @param config
	  */
	 private void insertServerLog(SingleServerConfig config,String editType){
		 List<SingleServerConfig> configs = new ArrayList<SingleServerConfig>();
		 configs.add(config);
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("list", generateServerLog(configs,editType));
		 welfareDaoTemplate.insert("singleServerLog.batchInsert", param);
	 }
	 
	 /**
	  * 删除单服特殊配置
	  * @param ids
	  * @param configs 
	  * @return
	  */
	 @Transactional(readOnly=false)
	 public Result deleteServerConfig(String[] ids,List<SingleServerConfig> configs){
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("ids", ids);
		 if(configs!=null){
			 configs = welfareDaoTemplate.selectList("singleServerConfig.paging", param);
		 }
		 welfareDaoTemplate.update("singleServerConfig.delete", param);
		 
		 Map<String,Object> param1 = new HashMap<String, Object>();
		 param1.put("list", generateServerLog(configs,SingleServerLog.EDIT_TYPE_DEL));
		 welfareDaoTemplate.insert("singleServerLog.batchInsert", param1);
		 return new Result(true);
	 }
	 
	 /**
	  * 单服配置日志数据
	  * @param pid
	  * @param serverId
	  * @param startTime
	  * @param endTime
	  * @param limit
	  * @param offset
	  * @return
	  */
	 public Map<String,Object> singleServerLogData(String pid,String serverId,Date startTime,Date endTime,int limit,int offset){
		 Map<String,Object> param = new HashMap<String,Object>();
		 param.put("pid", pid);
		 param.put("serverId", serverId);
		 param.put("startTime", startTime);
		 param.put("endTime", endTime);
		 Map<String,Object> map = new HashMap<String, Object>();
		 map.put("rows", welfareDaoTemplate.selectList("singleServerLog.paging", param, new RowBounds(offset, limit)));
		 map.put("total", welfareDaoTemplate.selectOne("singleServerLog.count", param));
		 return map;
	 }
	 
	 /**
	  * 生成单账号配置日志list
	  * @param nums
	  * @param isInfluence
	  * @param topCharge
	  * @return
	  */
	 private List<SingleAccountLog> generateAccountLog(List<WelfareNum> nums,int isInfluence,BigInteger topCharge){
		 List<SingleAccountLog> logs = new ArrayList<SingleAccountLog>();
		 for (WelfareNum welfareNum : nums) {
			 
			SingleAccountLog log = generateOneAccountLog(welfareNum, isInfluence, topCharge);
			logs.add(log);
		 }
		 return logs;
	 }
	 
	 /**
	  * 生成单账号配置日志list
	  * @param nums
	  * @param isInfluence
	  * @param topCharge
	  * @return
	  */
	 private SingleAccountLog generateOneAccountLog(WelfareNum welfareNum,int isInfluence,BigInteger topCharge){
		 
			SingleAccountLog log = new SingleAccountLog();
			log.setCreateBy(UserUtils.getUser().getId());
			log.setCreateName(UserUtils.getUser().getLoginName());
			log.setEditType(welfareNum.getTopCharge()==null?SingleAccountLog.EDIT_TYPE_ADD:SingleAccountLog.EDIT_TYPE_EDIT);
			log.setId(IdGen.uuid());
			log.setIsInfluence(isInfluence==WelfareNum.IS_INFLUENCE_TRUE?"是":"否");
			log.setRoleId(welfareNum.getRoleId());
			log.setRoleName(welfareNum.getRoleName());
			log.setTopCharge(topCharge);
			log.setUpdateBy(UserUtils.getUser().getLoginName());
			log.setUserId(welfareNum.getUserId());
			log.setPid(welfareNum.getPid());
			log.setPlatName(welfareNum.getPlatName());
			log.setServerId(welfareNum.getServerId());
			log.setServerName(welfareNum.getServerName());
		 
		 return log;
	 }
	 
	 /**
	  * 生成单服特殊配置日志list
	  * @param configs
	  * @return
	  */
	 private List<SingleServerLog> generateServerLog(List<SingleServerConfig> configs,String editType){
		 List<SingleServerLog> logs = new ArrayList<SingleServerLog>();
		 for (SingleServerConfig config : configs) {
			SingleServerLog log = new SingleServerLog();
			log.setAddTimeLimit(config.getAddTimeLimit());
			log.setCreateBy(UserUtils.getUser().getId());
			log.setCreateName(UserUtils.getUser().getLoginName());
			log.setEditType(editType);
			log.setId(IdGen.uuid());
			log.setIsInfluence(config.getIsInfluence()==SingleServerConfig.IS_INFLUENCE_TRUE?"是":"否");
			log.setNewServiceGold(config.getNewServiceGold());
			log.setPid(config.getPid());
			log.setPlatName(config.getPlatName());
			log.setRResourceAmount(config.getRResourceAmount());
			log.setRResourceRatio(config.getRResourceRatio());
			log.setServerId(config.getServerId());
			log.setSingleChargeRatio(config.getSingleChargeRatio());
			log.setTopCharge(config.getTopCharge());
			log.setTopGoldDay(config.getTopGoldDay());
			log.setTopHoldGold(config.getTopHoldGold());
			log.setTopInternalNumber(config.getTopInternalNumber());
			log.setUpdateBy(UserUtils.getUser().getLoginName());
			log.setServerName(config.getServerName());
			log.setPlatName(config.getPlatName());
			logs.add(log);
		}
		 return logs;
	 }
}
