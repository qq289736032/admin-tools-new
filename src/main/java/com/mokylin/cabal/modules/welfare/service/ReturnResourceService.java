package com.mokylin.cabal.modules.welfare.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.GameDaoTemplate;
import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.persistence.WelfareDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.service.RebateRechargeService;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import com.mokylin.cabal.modules.welfare.entity.PlatWelfareConfig;
import com.mokylin.cabal.modules.welfare.entity.SingleServerConfig;

@Transactional(readOnly = true)
@Service
public class ReturnResourceService extends BaseService {
	@Resource
	protected WelfareDaoTemplate welfareDaoTemplate;
	@Resource
	protected ToolDaoTemplate toolDaoTemplate;
	@Resource
	protected GameDaoTemplate gameDaoTemplate;
	@Resource
	private RebateDaoTemplate rebateDaoTemplate; 
	@Resource
	private RebateRechargeService rebateRechargeService;

	@Transactional(readOnly = false)
	public void returnSource(int gold, String serverId, String pid,String orderId,String userId,String roleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", pid);
		param.put("serverId", serverId);
		SingleServerConfig config = welfareDaoTemplate.selectOne(
				"singleServerConfig.selectOneByServerIdAndPid", param);
		PlatWelfareConfig platConfig = null;
		// 单服后续充值比例
		Integer dfPercent = config == null?0:config.getSingleChargeRatio();
		// 2.如果没有特殊配置就查通用配置
		if (config == null) {
			param.put("goldPoolCategory",
					PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);
			platConfig = welfareDaoTemplate.selectOne(
					"platWelfareConfig.selectOneByPid", param);
			dfPercent = platConfig.getSingleChargeRatio();
		}
		BigInteger returnSingleServer = new BigDecimal(gold).multiply(new BigDecimal(dfPercent)).divide(new BigDecimal(100), RoundingMode.DOWN).toBigInteger();
		insertReturnResource(serverId,pid,orderId,userId,roleId,returnSingleServer,BigInteger.ZERO);
	}
	
	private void insertReturnResource(String serverId,String pid,String orderId,String userId,String roleId
			,BigInteger returnSingleServer,BigInteger drAmount){
		
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("id", IdGen.uuid());
		param.put("drAmount", drAmount);
		param.put("roleId", roleId);
		param.put("singleServerAmount", returnSingleServer);
		param.put("pid", pid);
		param.put("serverId", serverId);
		param.put("createBy", UserUtils.getUser().getId());
		param.put("createName", UserUtils.getUser().getLoginName());
		param.put("updateBy", UserUtils.getUser().getLoginName());
		param.put("userId", userId);
		param.put("orderId", orderId);
		welfareDaoTemplate.insert("returnResource.insert", param);
	}
	
	
	@Transactional(readOnly=false)
	public void calculation() {

		Date beforeDate = DateUtils.getBeforeDay(new Date());
		Date endDate = DateUtils.getDateEnd(beforeDate);
		Date startDate = DateUtils.getDateStart(beforeDate);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("startTime", startDate);
		param.put("endTime", endDate);
		List<Map<String,Object>> chargeSum = rebateDaoTemplate.selectList("rebateRechargeMapper.sumByRoleId", param);
		for (Map<String, Object> map : chargeSum) {
			
			if(map.get("pid")==null||map.get("serverId")==null||map.get("orderId")==null
					||map.get("userId")==null||map.get("roleId")==null){
				continue;
			}
			
			String pid = map.get("pid").toString();
			String serverId = map.get("serverId").toString();
			String orderId = map.get("orderId").toString();
			param = new HashMap<String, Object>();
			param.put("pid", pid);
			param.put("serverId", serverId);
			
			SingleServerConfig config = welfareDaoTemplate.selectOne(
					"singleServerConfig.selectOneByServerIdAndPid", param);
			Integer drPercent = config == null?0:config.getRResourceRatio();
			BigInteger drAmount = config == null?BigInteger.ZERO:config.getRResourceAmount();
			PlatWelfareConfig platConfig = null;
			if (config == null) {
				param.put("goldPoolCategory",
						PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);
				platConfig = welfareDaoTemplate.selectOne(
						"platWelfareConfig.selectOneByPid", param);
				drPercent = platConfig.getRResourceRatio();
				drAmount = platConfig.getRResourceAmount();
			}
			
			BigDecimal sumMoney = (BigDecimal) map.get("sumMoney");
			Long roleId = (Long) map.get("roleId");
			String userId = map.get("userId").toString();
			BigInteger returnDrAmount = BigInteger.ZERO;
			if(sumMoney.compareTo(new BigDecimal(drAmount))>=0){
				returnDrAmount = sumMoney.multiply(new BigDecimal(drPercent)).divide(new BigDecimal(100), RoundingMode.DOWN).toBigInteger();
			}
			insertReturnResource(serverId, pid, orderId, userId, roleId.toString(), BigInteger.ZERO, returnDrAmount);
		}
	}
	@Transactional(readOnly=false)
	public Result chargeReturnSource(String orderId,String pid,String serverName,String uid,int gold){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("serverName", serverName);
		param.put("pid", pid);
		Map<String,Object> areaMap = gameDaoTemplate.selectOneGameArea("gameArea.findOneByPidAndWorldName", param);
		if(areaMap==null||areaMap.get("worldId")==null){
			logger.error("查不到服务器ID"+"pid="+pid+",serverName="+serverName+",orderId="+orderId);
			return new Result(false);
		}
		String serverId = areaMap.get("worldId").toString();
		param = new HashMap<String, Object>();
 		param.put("userId", uid);
 		param.put("serverId", serverId);
 		
 		Map<String,Object> role = gameDaoTemplate.selectOneByServerId(serverId, "role.findRoleByUserIdAndServerId", param);
 		if(role==null||role.get("roleId")==null){
 			logger.error("查不到角色ID"+"userId="+uid+",serverId="+serverId+",orderId="+orderId);
 			return new Result(false);
 		}
 		String roleId = role.get("roleId").toString();
 		logger.debug("充值返回资源: roleId = "+roleId+"orderId="+orderId+"gold="+gold);
        returnSource(gold, serverId, pid,orderId,uid,roleId);
       
        rebateRechargeService.insertCharge(roleId, uid, gold, serverId, pid, orderId);
        logger.debug("充值返回资源成功");
        return new Result(true);
	}

}
