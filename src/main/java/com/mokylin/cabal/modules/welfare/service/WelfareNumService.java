package com.mokylin.cabal.modules.welfare.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.game.api.GameTemplate;
import com.mokylin.cabal.common.persistence.GameDaoTemplate;
import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.persistence.WelfareDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.utils.GoldRmbUtils;
import com.mokylin.cabal.modules.welfare.entity.OperaGoldLog;
import com.mokylin.cabal.modules.welfare.entity.PlatWelfareConfig;
import com.mokylin.cabal.modules.welfare.entity.SingleServerConfig;
import com.mokylin.cabal.modules.welfare.entity.WelfareNum;
import com.mokylin.cabal.modules.welfare.entity.WelfareNumLog;
@Transactional(readOnly=true)
@Service
public class WelfareNumService extends BaseService{
	 @Resource
	 protected WelfareDaoTemplate welfareDaoTemplate;
	 @Resource
	 protected GameDaoTemplate gameDaoTemplate;
	 @Resource
	 protected GameTemplate gameTemplate;
	 @Resource
	 private RebateDaoTemplate rebateDaoTemplate; 
	 @Resource
	 protected ToolDaoTemplate toolDaoTemplate;
	 
	 
	 /**
	  * 添加福利号
	  * @param num
	  */
	 @Transactional(readOnly=false)
	 public Result insertNum(WelfareNum num){
		 
		 Result result = isExist(num);
		 if(!result.isSuccess()){
			 return result;
		 }
		//1.查询特殊配置
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("pid", num.getPid());
		 param.put("serverId", num.getServerId());
		 SingleServerConfig config = welfareDaoTemplate.selectOne("singleServerConfig.selectOneByServerIdAndPid", param);
		 PlatWelfareConfig platConfig = null;
		 //2.如果没有特殊配置就查通用配置
		 if(config==null){
			 param.put("goldPoolCategory", num.getPassageway()==OperaGoldLog.PASSAGEWAY_PLAT
					 ?PlatWelfareConfig.GOLD_POOL_CATEGORY_PLAT:PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND); 
			 platConfig = welfareDaoTemplate.selectOne("platWelfareConfig.selectOneByPid", param);
		 }
		 
		 //3.角色充值上限 判断
		 result = judgeTopCharge(config, platConfig,num);
		 if(!result.isSuccess()){
			 return result;
		 }
		 //特殊通道不受限制
		 if(!num.getPassageway().equals(OperaGoldLog.PASSAGEWAY_SPEC)){
			 
			 if(num.getPassageway().equals(OperaGoldLog.PASSAGEWAY_PLAT)){
				//4.判断开服时间添加内部号限制
				 result = judgeOpenTime(config,platConfig,param);
				 if(!result.isSuccess()){
					 return result;
				 }
			 }

			 //5.内部号数量限制 判断
			 result = judgeTopNum(config, platConfig, param);
			 if(!result.isSuccess()){
				 return result;
			 }
		 }
		
		 num.setId(IdGen.uuid());
		 num.setCreateName(UserUtils.getUser().getLoginName());
		 num.setCreateBy(UserUtils.getUser().getId());
		 num.setUpdateBy(UserUtils.getUser().getLoginName());
		 num.setTopCharge(config==null?platConfig.getTopCharge():config.getTopCharge());
		 num.setIsInfluence(WelfareNum.IS_INFLUENCE_TRUE);
		 welfareDaoTemplate.insert("welfareNum.insert", num);
		 
		 //调用游戏服务器接口标识该角色ID是YY福利号
		 setYYWelfare(num);
		 //插入日志
		 insertLog(num);
		 return new Result(true);
	 }
	 
	 private void setYYWelfare(WelfareNum num){
		 gameTemplate.roleOperation().setYYWelfare(num.getServerId(), num.getRoleId().longValue(), 1);
	 }
	 
	 private Result judgeTopCharge(SingleServerConfig config,PlatWelfareConfig platConfig,WelfareNum num){
		 Result result = new Result(true);
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("roleId", num.getRoleId());
		 Map<String,Object> resultMap = rebateDaoTemplate.selectOne("rebateRechargeMapper.sumOneByRoleId", param);
		 int sum = new BigDecimal(resultMap.get("sumMoney").toString()).intValue();
		 BigInteger topCharge = BigInteger.ZERO;
		 if(config!=null){
			 topCharge = config.getTopCharge();
		 }else{
			 topCharge = platConfig.getTopCharge();
		 }
		 if(sum<GoldRmbUtils.RmbChangeToGold(topCharge.intValue())){
			 result.setSuccess(true);
			 return result;
		 }
		 result.setSuccess(false);
		 result.setError(2);//超出角色充值上限
		 return result;
	 }
	 
	 /**
	  * 判断福利号存不存在
	  * @param num
	  * @return
	  */
	 private Result isExist(WelfareNum num){
		 Result result = new Result(true);
		 WelfareNum one = welfareDaoTemplate.selectOne("welfareNum.selectOneByRoleId", num.getRoleId());
		 if(one!=null){
			 result.setSuccess(false);
			 result.setError(3);//该角色已经是内部号
			 return result;
		 }
		 return result;
	 }
	 private Result judgeTopNum(SingleServerConfig config,PlatWelfareConfig platConfig,Map<String,Object> param){
		 Result result = new Result(true);
		 int count = welfareDaoTemplate.selectOne("welfareNum.count", param);
		 
		 if(config!=null){
			 //取特殊配置判断
			 if(count>=config.getTopInternalNumber().intValue()){
				 result.setSuccess(false);
				 result.setError(1);//达到内部号最高数量限制
			 }
		 }else{
			//取平台配置判断
			 if(count>=platConfig.getTopInternalNumber().intValue()){
				 result.setSuccess(false);
				 result.setError(1);//达到内部号最高数量限制
			 }
		 }
		 
		 return result;
	 }
	 
	 private Result judgeOpenTime(SingleServerConfig config,PlatWelfareConfig platConfig,Map<String,Object> param){
		 if(config!=null){
			 return judgeDay(config.getAddTimeLimit(),param);
		 }
		 return judgeDay(platConfig.getAddTimeLimit(),param);
	 }
	 
	 private Result judgeDay(int addTimeLimit,Map<String,Object> param){
		 Result result = new Result(true);
		//查询开服时间
		 Map<String,Object> areaMap = gameDaoTemplate.selectOneGameArea("gameArea.findOneByPidAndWorldId", param);
		 Date openTime = (Date) areaMap.get("openTime");
		 try {
			 	int day = DateUtils.getDateSpace(openTime, new Date());
				//int day = DateUtils.getDateSpace(new Date(), openTime);
				if(day>addTimeLimit){
					result.setSuccess(false);
					result.setError(0);//超过了内部号添加时间
				}
			} catch (ParseException e) {
				
				result.setSuccess(false);
				result.setError(-1);//数据异常
			}
		 return result;
	 }
	 
	 private void insertLog(WelfareNum num){
		 Map<String,Object> param = Collections3.transBean2Map(num);
		 param.put("editType", WelfareNumLog.EDIT_TYPE_ADD);
		 param.put("status", WelfareNumLog.STATUS_NORMAL);
		 welfareDaoTemplate.insert("welfareNumLog.insert", param);
	 }
	 
	 /**
	  * 更新福利号状态
	  * @param num
	  * @return
	  */
	 @Transactional(readOnly=false)
	 public Result updateStatus(WelfareNum num,String editName,String editId){
		 String editName0  = StringUtils.isNotBlank(editName)?editName:UserUtils.getUser().getLoginName();
		 String editId0 = StringUtils.isNotBlank(editId)?editId:UserUtils.getUser().getId();
		 num.setUpdateBy(editName0);
		 welfareDaoTemplate.update("welfareNum.updateStatus", num);
		 
		 //插入日志
		 num.setCreateName(editName0);
		 num.setCreateBy(editId0);
		 num.setUpdateBy(editName0);
		 Map<String,Object> param = Collections3.transBean2Map(num);
		 param.put("id", IdGen.uuid());
		 param.put("editType", WelfareNum.STATUS_STOP == num.getStatus()
				 ?WelfareNumLog.EDIT_TYPE_STOP:WelfareNumLog.EDIT_TYPE_UNLOCKED);
		 param.put("status", WelfareNum.STATUS_STOP == num.getStatus()
				 ?WelfareNumLog.STATUS_STOP:WelfareNumLog.STATUS_NORMAL);
		 
		 welfareDaoTemplate.insert("welfareNumLog.insert", param);
		 
		 if(WelfareNum.STATUS_STOP == num.getStatus()){
			 
			 doDeleteNum(num);
		 }else{
			 
			 setYYWelfare(num);
		 }
		 return new Result(true);
	 }
	 
	 /**
	  * 删除福利号
	  * @param ids
	  * @return
	  */
	 @Transactional(readOnly=false)
	 public Result deleteNum(String[] ids){
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("ids", ids);
		 //查询这些ID对应的记录（用于插入日志）
		 List<WelfareNum> nums = welfareDaoTemplate.selectList("welfareNum.paging", param);
		 //删除
		 param.put("delFlag", 1);
		 welfareDaoTemplate.update("welfareNum.delete", param);
		 //插入日志
		 welfareDaoTemplate.batchInsert2("welfareNumLog.batchInsert", generateLog(nums));
		
		 //调用游戏服务器接口
		 doDeleteNums(nums);
		 
		 return new Result(true);
	 }
	 
	 /**
	  * 调用游戏服务器接口  标识角色从福利号系统删除了
	  * @param nums
	  */
	 private void doDeleteNums(List<WelfareNum> nums){
		 for (WelfareNum welfareNum : nums) {
			 
			 doDeleteNum(welfareNum);
		}
	 }
	 
	 private void doDeleteNum(WelfareNum welfareNum){
		 
		 gameTemplate.roleOperation().setYYWelfare(welfareNum.getServerId(), welfareNum.getRoleId().longValue(), 0);
	 }
	 
	 /**
	  * 福利号列表数据
	  * @param num
	  * @param offset
	  * @param limit
	  * @return
	  */
	 public Map<String,Object> numData(WelfareNum num,int offset,int limit){
		 Map<String,Object> map = new HashMap<String, Object>();
		 Map<String,Object> param = Collections3.transBean2Map(num);
		 List<WelfareNum> list = welfareDaoTemplate.selectList("welfareNum.paging", param,new RowBounds(offset, limit));
		 //查询特殊配置放入list
		 /*setSpecialConfig(list,num.getPassageway()==OperaGoldLog.PASSAGEWAY_PLAT
				 ?PlatWelfareConfig.GOLD_POOL_CATEGORY_PLAT:PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);*/
		
		 map.put("rows", setRoleInfo(list));
		 map.put("total", welfareDaoTemplate.selectOne("welfareNum.count", num));
		 return map;
	 }
	 
	 private List<Map<String,Object>> setRoleInfo(List<WelfareNum> list){
		 List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		 List<BigInteger> roleIds = new ArrayList<BigInteger>();
		 for (WelfareNum welfareNum : list) {
			 roleIds.add(welfareNum.getRoleId());
		 }
		 if(roleIds.isEmpty()){
			 return result;
		 }
		 //查询角色对应的发放元宝总额
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("roleIds", roleIds.toArray());
		 List<Map<String,Object>> lstSum = welfareDaoTemplate.selectList("operaGoldLog.sumByRoleIds", param);
		 List<Map<String,Object>> lstRole = gameDaoTemplate.selectListAllWorlds("role.findAllRoleByIds",param);
		 List<Map<String,Object>> lstChargeSum = rebateDaoTemplate.selectList("rebateRechargeMapper.sumByRoleIds", param);
		// List<Map<String,Object>> lstRole = new ArrayList<Map<String,Object>>();
		 for (WelfareNum welfareNum : list) {
			 Map<String,Object> numMap = Collections3.transBean2Map(welfareNum);
			 numMap.put("sumYb", 0);
			 numMap.put("sumCharge", 0);
			 for (Map<String, Object> map : lstSum) {
				 //提取角色对应的发放元宝总额
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(welfareNum.getRoleId().compareTo(roleId)==0){
					 numMap.put("sumYb", map.get("sumYb"));
				 }
			 }
			 
			 for (Map<String, Object> map : lstChargeSum) {
				 //提取角色对应的实际充值总额
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(welfareNum.getRoleId().compareTo(roleId)==0){
					 numMap.put("sumCharge", GoldRmbUtils.GoldChangeToRmb(Integer.parseInt(map.get("sumMoney").toString())));
				 }
			 }
			 
			 
			 for (Map<String, Object> map : lstRole) {
				
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(welfareNum.getRoleId().compareTo(roleId)==0){
					 numMap.put("vipLevel", map.get("vipLevel"));
					 Date logoutTime = (Date) map.get("lastLogoutTime");
					 int day;
					 try {
						day = DateUtils.getDateSpace(logoutTime, new Date());
					 } catch (ParseException e) {
						day = 0;
					 }
					 numMap.put("numOfDays", day);
					 numMap.put("level", map.get("level"));
					 numMap.put("combat", map.get("power"));
					 numMap.put("surplusYb", map.get("diamond"));
					 
				 }
			 }
			result.add(numMap);
		 }
		 return result;
		 
	 }
	 
	 /**
	  * 查询特殊配置放入list中
	  * @param list
	  */
	/* private void setSpecialConfig(List<WelfareNum> list,String goldPoolCategory){
		 List<String> serverIds = new ArrayList<String>();
		 List<String> pids = new ArrayList<String>();
		 for (WelfareNum welfareNum : list) {
			if(welfareNum.getTopCharge()!=null){
				continue;
			}
			serverIds.add(welfareNum.getServerId());
			pids.add(welfareNum.getPid());
		}
		if(serverIds.isEmpty()||pids.isEmpty()){
			return;
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("sids", serverIds.toArray());
		param.put("pids", pids.toArray());
		List<SingleServerConfig> configs = welfareDaoTemplate.selectList("singleServerConfig.selectAllByServerIdAndPid", param);
		Map<String,BigInteger> map = new HashMap<String, BigInteger>();
		for (SingleServerConfig config : configs) {
			map.put(config.getPid()+config.getServerId(),config.getTopCharge());
		}
		pids = new ArrayList<String>();
		for (WelfareNum welfareNum : list) {
			//如果福利号充值上限为NULL 说明此福利号没有特殊配置充值上限
			if(welfareNum.getTopCharge()==null){
				//取单服特殊配置的充值上限
				welfareNum.setTopCharge(map.get(welfareNum.getPid()+welfareNum.getServerId()));
				//如果还是为NULL  取通用平台配置
				if(welfareNum.getTopCharge()==null){
					pids.add(welfareNum.getPid());
				}
			}
		}
		if(!pids.isEmpty()){
			
			for (String pid : pids) {
				param = new HashMap<String, Object>();
				param.put("pid", pid);
				param.put("goldPoolCategory", goldPoolCategory);
				PlatWelfareConfig config = welfareDaoTemplate.selectOne("platWelfareConfig.selectOneByPid", param);
				if(config!=null){
					map = new HashMap<String, BigInteger>();
					map.put(pid, config.getTopCharge());
				}
			}
			for (WelfareNum welfareNum : list) {
				
				if(welfareNum.getTopCharge()==null){
					welfareNum.setTopCharge(map.get(welfareNum.getPid()));
				}
			}
		}
	 }*/
	 
	 /**
	  * 福利号日志列表数据
	  * @param log
	  * @param offset
	  * @param limit
	  * @param startTime
	  * @param endTime
	  * @return
	  */
	 public Map<String,Object> numLogData(WelfareNumLog log,int offset,int limit,Date startTime,Date endTime){
		 Map<String,Object> map = new HashMap<String, Object>();
		 Map<String,Object> param = Collections3.transBean2Map(log);
		 param.put("startTime", startTime);
		 param.put("endTime", endTime);
		 List<WelfareNumLog> logs = welfareDaoTemplate.selectList("welfareNumLog.paging", param, new RowBounds(offset,limit));
		 map.put("rows", setRoleLogInfo(logs));
		 map.put("total", welfareDaoTemplate.selectOne("welfareNumLog.count", param));
		 return map;
	 }
	 
	 private List<Map<String,Object>> setRoleLogInfo(List<WelfareNumLog> list){
		 List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		 List<BigInteger> roleIds = new ArrayList<BigInteger>();
		 for (WelfareNumLog log : list) {
			 roleIds.add(log.getRoleId());
		 }
		 if(roleIds.isEmpty()){
			 return result;
		 }
		 //查询角色对应的发放元宝总额
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("roleIds", roleIds.toArray());
		 List<WelfareNum> nums = welfareDaoTemplate.selectList("welfareNum.selectOneByRoleIds", param);
		 List<Map<String,Object>> lstSum = welfareDaoTemplate.selectList("operaGoldLog.sumByRoleIds", param);
		 List<Map<String,Object>> lstRole = gameDaoTemplate.selectListAllWorlds("role.findAllRoleByIds",param);
		 List<Map<String,Object>> lstChargeSum = rebateDaoTemplate.selectList("rebateRechargeMapper.sumByRoleIds", param);
		// List<Map<String,Object>> lstRole = new ArrayList<Map<String,Object>>();
		 for (WelfareNumLog log : list) {
			 
			 Map<String,Object> numMap = Collections3.transBean2Map(log);
			 numMap.put("sumYb", 0);
			 numMap.put("sumCharge", 0);
			 for (Map<String, Object> map : lstSum) {
				 //提取角色对应的发放元宝总额
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(log.getRoleId().compareTo(roleId)==0){
					 numMap.put("sumYb", map.get("sumYb"));
				 }
			 }
			 
			 for (Map<String, Object> map : lstChargeSum) {
				 //提取角色对应的发放元宝总额
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(log.getRoleId().compareTo(roleId)==0){
					 numMap.put("sumCharge", GoldRmbUtils.GoldChangeToRmb(Integer.parseInt(map.get("sumMoney").toString())));
				 }
			 }
			 
			 
			 for (Map<String, Object> map : lstRole) {
				
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(log.getRoleId().compareTo(roleId)==0){
					 numMap.put("vipLevel", map.get("vipLevel"));
					 Date logoutTime = (Date) map.get("lastLogoutTime");
					 int day;
					 try {
						day = DateUtils.getDateSpace(logoutTime, new Date());
					 } catch (ParseException e) {
						day = 0;
					 }
					 numMap.put("numOfDays", day);
					 numMap.put("level", map.get("level"));
					 numMap.put("combat", map.get("power"));
					 numMap.put("surplusYb", map.get("diamond"));
					 
				 }
			 }
			 
			 for (WelfareNum num : nums) {
				if(log.getRoleId().compareTo(num.getRoleId())==0){
					numMap.put("topCharge", num.getTopCharge());
				}
			 }
			result.add(numMap);
		 }
		 return result;
		 
	 }
	 
	 /**
	  * 生成日志集合（用于批量插入）
	  * @param nums
	  * @return
	  */
	 private List<WelfareNumLog> generateLog(List<WelfareNum> nums){
		 List<WelfareNumLog> logs = new ArrayList<WelfareNumLog>();
		 for (WelfareNum welfareNum : nums) {
			 WelfareNumLog log = new WelfareNumLog();
			 log.setCreateBy(UserUtils.getUser().getId());
			 log.setCreateName(UserUtils.getUser().getLoginName());
			 log.setEditType(WelfareNumLog.EDIT_TYPE_DEL);
			 log.setId(IdGen.uuid());
			 log.setPlatName(welfareNum.getPlatName());
			 log.setRoleId(welfareNum.getRoleId());
			 log.setRoleName(welfareNum.getRoleName());
			 log.setServerName(welfareNum.getServerName());
			 log.setServerId(welfareNum.getServerId());
			 log.setStatus(WelfareNumLog.STATUS_DELETE);
			 log.setUpdateBy(UserUtils.getUser().getLoginName());
			 log.setUsePeople(welfareNum.getUsePeople());
			 log.setUsePeoplePost(welfareNum.getUsePeoplePost());
			 log.setUserId(welfareNum.getUserId());
			 logs.add(log);
		 }
		 return logs;
	 }
	 
	 /**
	  * 根据角色名查询角色信息
	  * @param num
	  */
	 public Map<String,Object> checkRoleName(String roleName,String serverId){
		 Map<String,Object> param = new HashMap<String, Object>(); 
		 List<String> roleNameList = new ArrayList<String>();
		 roleNameList.add(roleName);
		 param.put("roleNameList", roleNameList);
		 List<Map<String,Object>> result = gameDaoTemplate.selectListByServerId(serverId, "role.findRoleByRoleNameList", param);
		 Map<String,Object> map = new HashMap<String, Object>();
		 if(result.isEmpty()){
	    	map.put("userId", "");
	    	map.put("roleId", "");
	    	return map;
		 }
		 map.put("userId", result.get(0).get("user_id"));
		 map.put("roleId", result.get(0).get("id"));
		 return map;
	 }
	 
	 /**
	  * 封停5天未登陆的福利号
	  */
	 @Transactional(readOnly=false)
	 public void sysUpdateStatus(){
		 Map<String,Object> param = new HashMap<String, Object>();
		 param.put("status", WelfareNum.STATUS_NORMAL);
		 List<WelfareNum> list = welfareDaoTemplate.selectList("welfareNum.findAll", param);
		 List<BigInteger> roleIds = new ArrayList<BigInteger>();
		 for (WelfareNum welfareNum : list) {
			 roleIds.add(welfareNum.getRoleId());
		 }
		 
		 param = new HashMap<String, Object>();
		 param.put("roleIds", roleIds.toArray());
		 List<Map<String,Object>> lstRole = gameDaoTemplate.selectListAllWorlds("role.findAllRoleByIds",param);
		 List<WelfareNum> finalNums = new ArrayList<WelfareNum>();
		 for (WelfareNum welfareNum : list) {
			 for (Map<String, Object> map : lstRole) {
					
				 BigInteger roleId = new BigInteger(String.valueOf(map.get("roleId")));
				 if(welfareNum.getRoleId().compareTo(roleId)==0){
					 Date logoutTime = (Date) map.get("lastLogoutTime");
					 int day;
					 try {
						day = DateUtils.getDateSpace(logoutTime, new Date());
					 } catch (ParseException e) {
						logger.error("查询福利号logoutTime转换出错"+logoutTime);
						day = 0;
					 }
					 
					 if(day>=WelfareNum.DEFAULT_STOP_DAY){
						 finalNums.add(welfareNum);
					 }
				 }
			 }
		 }
		 logger.info("要封停的福利号有"+finalNums.size()+"个");
		 for (WelfareNum welfareNum : finalNums) {
			 logger.info(new Date()+"封停"+welfareNum.getRoleName()+":"+welfareNum.getRoleId());
			 welfareNum.setStatus(WelfareNum.STATUS_STOP);
			 updateStatus(welfareNum,"系统自动封停","0");
		 }

	 }
}
