package com.mokylin.cabal.modules.rebate.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.ChargeDaoTemplate;
import com.mokylin.cabal.common.persistence.GameDaoTemplate;
import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.entity.RebateRatio;
import com.mokylin.cabal.modules.rebate.entity.RebateRecharge;
import com.mokylin.cabal.modules.rebate.entity.RebateStatisticRecharge;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.utils.GoldRmbUtils;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import com.mokylin.cabal.modules.welfare.service.ReturnResourceService;

@Transactional(readOnly=true)
@Service
public class RebateRechargeService {
	@Resource
	protected GameDaoTemplate gameDaoTemplate;
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
    @Resource
    protected ToolDaoTemplate toolDaoTemplate;
    @Resource
    protected ChargeDaoTemplate chargeDaoTemplate;
	@Autowired
	private ReturnResourceService returnResourceService;
	
	@Transactional(readOnly=false)
	public void insertCharge(String roleId,String userId,Integer moneyNum,String serverId,String pid,String orderId){
		
			RebateRecharge recharge = new RebateRecharge();
			recharge.setCreateBy(UserUtils.getUser().getId());
			recharge.setCreateDate(new Date());
			recharge.setCreateName(UserUtils.getUser().getLoginName());
			recharge.setId(IdGen.uuid());
			recharge.setMoneyNum(moneyNum);
			recharge.setRoleId(Long.valueOf(roleId));
			recharge.setRoleName(RedisUtils.getRoleNameByRoleId(roleId, ""));
			recharge.setServerId(serverId);
			recharge.setPid(pid);
			recharge.setPlatName(pid);
			recharge.setUpdateDate(new Date());
			recharge.setOrderId(orderId);
			//查询服务器名
			setServerName(serverId, pid, recharge);
			recharge.setUserId(userId);
			
			rebateDaoTemplate.insert("rebateRechargeMapper.insert", recharge);
		
	}
	
	private void setServerName(String serverId,String pid,RebateRecharge recharge){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("serverId", serverId);
		param.put("pid", pid);
		Map<String,Object> result = toolDaoTemplate.selectOne("gameArea.findGameAreaByPid", param);
		recharge.setServerName(result.get("world_name")==null?null:result.get("world_name").toString());
	}
	
	/*private void setUserId(String roleId,String serverId,RebateRecharge recharge){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", roleId);
		Map<String,Object> result = gameDaoTemplate.selectOneByServerId(serverId,"role.findRoleById", param);
		recharge.setUserId(result.get("userId")==null?null:result.get("userId").toString());
	}*/
	
	@Transactional(readOnly=false)
	public void calculationRebate(){
		
		List<RebateRatio> ratios = rebateDaoTemplate.selectList("rebateRatioMapper.find");
		Map<Long,Integer> ratioMap = new HashMap<Long, Integer>();
		List<Long> lstAmount = new ArrayList<Long>();
		for (RebateRatio ratio : ratios) {
			ratioMap.put(Long.valueOf(GoldRmbUtils.RmbChangeToGold(ratio.getDayAmount().intValue())), ratio.getRebateRatio());
			lstAmount.add(Long.valueOf(GoldRmbUtils.RmbChangeToGold(ratio.getDayAmount().intValue())));
		}
		
		
		Date beforeDate = DateUtils.getBeforeDay(new Date());
		Date endDate = DateUtils.getDateEnd(beforeDate);
		Date startDate = DateUtils.getDateStart(beforeDate);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startTime", startDate);
		param.put("endTime", endDate);
		List<Map<String,Object>> result = rebateDaoTemplate.selectList("rebateRechargeMapper.sumCharegByDate", param);
		for (Map<String, Object> map : result) {
			BigDecimal sumMoney = (BigDecimal) map.get("sumMoney");
			Long roleId = (Long) map.get("roleId");
			Long times = (Long) map.get("times");
			String serverId = map.get("serverId").toString();
			String pid = map.get("pid").toString();
			String userId = map.get("userId").toString();
			String platName = map.get("platName").toString();
			String serverName = map.get("serverName").toString();
			String roleName = map.get("roleName").toString();
			//计算充值金额对应的返还比例
			Integer ratio = calculationRatio(lstAmount,sumMoney,ratioMap);
			if(ratio==0){
				continue;
			}
			
			RebateStatisticRecharge statistic = new RebateStatisticRecharge();
			statistic.setCreateBy("系统每日计算");
			statistic.setCreateDate(new Date());
			statistic.setCreateName("系统每日计算");
			statistic.setId(IdGen.uuid());
			statistic.setRebateGold(sumMoney.multiply(new BigDecimal(ratio)).divide(new BigDecimal(100), RoundingMode.DOWN).longValue());
			statistic.setRebateRatio(ratio);
			statistic.setRechargeTimes(times.intValue());
			statistic.setRoleId(roleId);
			statistic.setServerId(serverId);
			statistic.setPid(pid);
			statistic.setStatisticDate(startDate);
			statistic.setSumMoney(sumMoney.longValue());
			statistic.setUpdateBy("系统每日计算");
			statistic.setUpdateDate(new Date());
			statistic.setUserId(userId);
			statistic.setServerName(serverName);
			statistic.setPlatName(platName);
			statistic.setRoleName(roleName);
			rebateDaoTemplate.insert("rebateStatisticRechargeMapper.insert", statistic);
			
		}
	}
	
	/**
	 * 计算充值金额对应的返利比例
	 * @param lstAmount
	 * @param sumMoney
	 * @param ratioMap
	 * @return
	 */
	private Integer calculationRatio(List<Long> lstAmount,BigDecimal sumMoney,Map<Long,Integer> ratioMap){
		
		if(lstAmount.isEmpty()||sumMoney==null||ratioMap.isEmpty()){
			
			return 0;
		}
		
		if(lstAmount.contains(sumMoney.longValue())){
			
			return ratioMap.get(sumMoney.longValue());
		}
		
		List<Long> lstAmount0 = new ArrayList<Long>();
		lstAmount0.addAll(lstAmount);
		lstAmount0.add(sumMoney.longValue());
		Object [] amountArr = lstAmount0.toArray();
		Arrays.sort(amountArr);
		int index = 0 ;
		
		for (Object amount : amountArr) {
			
			if(Long.valueOf(amount.toString())==sumMoney.longValue()){
				
				break;
			}
			index++;
		}
		
		if(index!=0){
			
			Long a = Long.valueOf(amountArr[index-1].toString());
			return ratioMap.get(a);
		}
		
		return 0;
	}
	
	@Transactional(readOnly=false)
	public void compare(){
		List<Map<String,Object>> list = chargeDaoTemplate.selectList("charge.selectAllOrderId");
		List<String> orderIds = new ArrayList<String>();
		Map<String,String> pidMap = new HashMap<String, String>();
		Map<String,String> userIdMap = new HashMap<String, String>();
		Map<String,Integer> goldMap = new HashMap<String, Integer>();
		Map<String,String> serverMap = new HashMap<String, String>();
		for (Map<String, Object> map : list) {
			if(map==null||map.get("orderId")==null||map.get("pid")==null||
					map.get("userId")==null||map.get("gold")==null||
					map.get("serverId")==null){
				continue;
			}
			orderIds.add(map.get("orderId").toString());
			pidMap.put(map.get("orderId").toString(), map.get("pid").toString());
			userIdMap.put(map.get("orderId").toString(), map.get("userId").toString());
			goldMap.put(map.get("orderId").toString(), Integer.parseInt(map.get("gold").toString()));
			serverMap.put(map.get("orderId").toString(), map.get("serverId").toString());
		}
		
		list = rebateDaoTemplate.selectList("rebateRechargeMapper.selectAllOrderId");
		List<String> rebateOrderIds = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			if(map==null||map.get("orderId")==null){
				continue;
			}
			rebateOrderIds.add(map.get("orderId").toString());
		}
		
		for (String orderId : orderIds) {
			if(!rebateOrderIds.contains(orderId)){
				returnResourceService.chargeReturnSource(orderId, pidMap.get(orderId), 
						serverMap.get(orderId), userIdMap.get(orderId), goldMap.get(orderId));
			}
		}
	}
}
