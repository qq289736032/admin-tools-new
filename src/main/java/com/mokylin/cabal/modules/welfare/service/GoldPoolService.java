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
import com.mokylin.cabal.modules.tools.entity.Recharge;
import com.mokylin.cabal.modules.tools.utils.GoldRmbUtils;
import com.mokylin.cabal.modules.welfare.entity.OperaGoldLog;
import com.mokylin.cabal.modules.welfare.entity.PlatWelfareConfig;
import com.mokylin.cabal.modules.welfare.entity.SingleServerConfig;
import com.mokylin.cabal.modules.welfare.entity.WelfareNum;

@Transactional(readOnly = true)
@Service
public class GoldPoolService extends BaseService {
	@Resource
	protected WelfareDaoTemplate welfareDaoTemplate;
	@Resource
	protected GameTemplate gameTemplate;
	@Resource
	protected GameDaoTemplate gameDaoTemplate;
	@Resource
	protected ToolDaoTemplate toolDaoTemplate;
	 @Resource
	 private RebateDaoTemplate rebateDaoTemplate; 

	/**
	 * 发放元宝
	 * 
	 * @param ids
	 *            welfareNumId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public Result grantGold(String[] ids, BigInteger gold, String passageway,
			String pid, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", ids);
		List<WelfareNum> nums = welfareDaoTemplate.selectList(
				"welfareNum.paging", param);
		// 1.判断是否允许发放
		Result result = check(ids, gold, passageway, pid, serverId, nums);
		if (!result.isSuccess()) {
			return result;
		}

		if (result.getData() != null) {
			// result.getData()不为null 说明有的符合发放条件有的不符合 这里筛选出符合条件的
			List<BigInteger> lstRole = new ArrayList<BigInteger>();
			Map<String, Object> map = (Map<String, Object>) result.getData();

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (entry.getValue().toString().equals("0")) {
					lstRole.add(new BigInteger(entry.getKey()));
				}
			}
			if (lstRole.isEmpty()) {
				return result;
			}
			nums = getVaildNums(lstRole, nums);

		}

		// 2.发放元宝
		doGrantGold(gold,nums);

		// 3.插入日志
		insertOperaLog(ids, gold, passageway, nums);

		return result;
	}

	private void doGrantGold(BigInteger gold, List<WelfareNum> nums) {

		for (WelfareNum welfareNum : nums) {
			Recharge charge = new Recharge();
			charge.setServerId(welfareNum.getServerId());
			charge.setMoneyNum(gold.intValue());
			charge.setRoleIds(String.valueOf(welfareNum.getRoleId()));
			charge.setMoneyType("2");
			charge.setRechargeType("1");
			gameTemplate.treasureOperation().recharge(charge);
		}
	}

	/**
	 * 筛选出符合条件的福利号
	 * 
	 * @param lstRole
	 * @param nums
	 * @return
	 */
	private List<WelfareNum> getVaildNums(List<BigInteger> lstRole,
			List<WelfareNum> nums) {
		List<WelfareNum> vaildNums = new ArrayList<WelfareNum>();
		for (WelfareNum welfareNum : nums) {
			if (lstRole.contains(welfareNum.getRoleId())) {
				vaildNums.add(welfareNum);
			}
		}
		return vaildNums;
	}

	private Result check(String[] ids, BigInteger gold, String passageway,
			String pid, String serverId, List<WelfareNum> nums) {
		Result result = new Result(true);

		// 特殊通道不受限制
		if (!passageway.equals(OperaGoldLog.PASSAGEWAY_SPEC)) {
			List<BigInteger> roleIds = new ArrayList<BigInteger>();
			Map<BigInteger,BigInteger> roleCharge = new HashMap<BigInteger, BigInteger>();
			for (WelfareNum welfareNum : nums) {
				roleIds.add(welfareNum.getRoleId());
				roleCharge.put(welfareNum.getRoleId(), welfareNum.getTopCharge());
			}
			// 1.判断平台福利有没有启用
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pid", pid);
			param.put(
					"goldPoolCategory",
					passageway.equals(OperaGoldLog.PASSAGEWAY_PLAT) ? PlatWelfareConfig.GOLD_POOL_CATEGORY_PLAT
							: PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);
			PlatWelfareConfig platConfig = welfareDaoTemplate.selectOne(
					"platWelfareConfig.selectOneByPid", param);
			if (platConfig.getStatus() == PlatWelfareConfig.STATUS_STOP) {
				result.setSuccess(false);
				result.setError(0);// 奖金池没有启用
				return result;
			}
			param = new HashMap<String, Object>();
			param.put("pid", pid);
			param.put("serverId", serverId);
			param.put("passageway", OperaGoldLog.PASSAGEWAY_SPEC);
			param.put("passageway0", passageway);
			SingleServerConfig config = welfareDaoTemplate.selectOne(
					"singleServerConfig.selectOneByServerIdAndPid", param);
			// 2.剩余资源判断
			// 查询已本服已发放资源数量
			Integer usedResource = welfareDaoTemplate.selectOne(
					"operaGoldLog.sum", param);
			if (usedResource == null) {
				usedResource = 0;
			}

			// 计算资源总数
			BigInteger allResource = getAllSumResource(platConfig, config,
					param);
			// 本次要发放元宝数量 = gold*要发放账号的数量
			if (gold.multiply(new BigInteger(String.valueOf(ids.length)))
					.compareTo(
							allResource.subtract(new BigInteger(String
									.valueOf(usedResource)))) == 1) {
				result.setSuccess(false);
				result.setError(1);// 剩余资源不足
				return result;
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			for (BigInteger roleId : roleIds) {
				resultMap.put(String.valueOf(roleId), 0);
				// 3.单角色每日限额判断
				param = new HashMap<String, Object>();
				param.put("roleId", roleId);
				param.put("startTime", DateUtils.getDateStart(new Date()));
				param.put("endTime", new Date());
				param.put("passageway", OperaGoldLog.PASSAGEWAY_SPEC);
				int sum = welfareDaoTemplate.selectOne("operaGoldLog.sum",
						param);
				if (config != null
						&& sum + gold.intValue() > config.getTopGoldDay()
								.intValue()) {
					resultMap.put(String.valueOf(roleId), 1);// 1:超出每日限额
					continue;
				}
				// 4.单角色持有上限判断
				
				if(!judgeTopHold(config,platConfig,gold,roleId,serverId)){
					
					resultMap.put(String.valueOf(roleId), 2);//2:超出角色持有上限
					continue; 
				}
				
				//5.角色充值上限判断
				if(!judgeTopCharge(roleCharge.get(roleId),roleId)){
					
					resultMap.put(String.valueOf(roleId), 3);//3:实际充值额度超过充值上限
					continue; 
				}

			}
			result.setSuccess(true);
			result.setData(resultMap.isEmpty() ? null : resultMap);
		}
		return result;
	}
	
	private boolean judgeTopCharge(BigInteger topCharge,BigInteger roleId){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		Map<String,Object> map = rebateDaoTemplate.selectOne("rebateRechargeMapper.sumOneByRoleId", param);
		BigDecimal sumCharge = new BigDecimal(map.get("sumMoney").toString());
		
		return GoldRmbUtils.RmbChangeToGold(topCharge.intValue())>sumCharge.intValue();
	}

	/**
	 * 单角色持有上限判断
	 * 
	 * @param config
	 * @param platConfig
	 * @param gold
	 * @param roleId
	 * @return
	 */
	private boolean judgeTopHold(SingleServerConfig config,
			PlatWelfareConfig platConfig, BigInteger gold, BigInteger roleId,String serverId) {
		Integer yb = gameDaoTemplate.selectOneByServerId(serverId,"role.findRoleYbById", roleId);
		if (yb == null) {
			yb = 0;
		}
		BigInteger topHold = platConfig.getTopHoldGold();
		if (config != null) {
			topHold = config.getTopHoldGold();
		}
		return gold.add(new BigInteger(String.valueOf(yb))).compareTo(topHold) <= 0;
	}

	private void insertOperaLog(String[] ids, BigInteger gold,
			String passageway, List<WelfareNum> nums) {

		List<OperaGoldLog> logs = new ArrayList<OperaGoldLog>();
		for (WelfareNum welfareNum : nums) {
			OperaGoldLog log = new OperaGoldLog();
			log.setCreateBy(UserUtils.getUser().getId());
			log.setCreateName(UserUtils.getUser().getLoginName());
			log.setGold(gold);
			log.setId(IdGen.uuid());
			log.setPassageway(passageway);
			log.setPid(welfareNum.getPid());
			log.setPlatName(welfareNum.getPlatName());
			log.setRoleId(welfareNum.getRoleId());
			log.setRoleName(welfareNum.getRoleName());
			log.setServerId(welfareNum.getServerId());
			log.setServerName(welfareNum.getServerName());
			log.setUpdateBy(UserUtils.getUser().getLoginName());
			log.setUserId(welfareNum.getUserId());
			logs.add(log);
		}
		welfareDaoTemplate.batchInsert2("operaGoldLog.batchInsert", logs);
	}

	/**
	 * 查询资源数据
	 * 
	 * @param pid
	 * @param serverId
	 * @return
	 */
	public Map<String, Object> resourceData(String pid, String serverId,
			String goldPoolCategory) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("serverId", serverId);
		param.put("pid", pid);

		// 有特殊配置就使用特殊配置
		SingleServerConfig config = welfareDaoTemplate.selectOne(
				"singleServerConfig.selectOneByServerIdAndPid", param);
		param = new HashMap<String, Object>();
		param.put("pid", pid);
		param.put("goldPoolCategory", goldPoolCategory);
		PlatWelfareConfig platConfig = welfareDaoTemplate.selectOne(
				"platWelfareConfig.selectOneByPid", param);
		if (null != config) {
			result = Collections3.transBean2Map(config);
		} else {
			result = Collections3.transBean2Map(platConfig);
		}
		param = new HashMap<String, Object>();
		param.put("serverId", serverId);
		param.put("pid", pid);
		param.put("passageway", OperaGoldLog.PASSAGEWAY_SPEC);
		param.put("passageway0", goldPoolCategory.equals(PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND)?OperaGoldLog.PASSAGEWAY_IND:OperaGoldLog.PASSAGEWAY_PLAT);

		// 已发资源
		Integer usedResource = welfareDaoTemplate.selectOne("operaGoldLog.sum",
				param);
		if (usedResource == null) {
			usedResource = 0;
		}
		// 计算资源总数
		BigInteger allResource = getAllSumResource(platConfig, config, param);
		result.put("issuedResource", usedResource);
		result.put("goldSum", allResource);
		result.put("surplus", allResource.subtract(new BigInteger(String
				.valueOf(usedResource))));
		Map<String, Object> map = welfareDaoTemplate.selectOne(
				"returnResource.sum", param);
		result.put("drSum", map.get("drSum"));
		result.put("singleSum", map.get("singleSum"));
		getChargeData(result, serverId);
		return result;
	}

	private void getChargeData(Map<String, Object> result, String serverId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("serverId", serverId);
		Map<String,Object> resultMap = rebateDaoTemplate.selectOne("rebateRechargeMapper.sumBy", param);
		result.put("chargeSum", GoldRmbUtils.GoldChangeToRmb(new BigDecimal(resultMap.get("sumMoney").toString()).intValue()));
		param.put("startTime", DateUtils.getDateStart(new Date()));
		param.put("endTime", new Date());
		resultMap = rebateDaoTemplate.selectOne("rebateRechargeMapper.sumBy",
				param);
		result.put("chargeSumOfDay", GoldRmbUtils.GoldChangeToRmb(new BigDecimal(resultMap.get("sumMoney").toString()).intValue()));
	}

	private BigInteger getAllSumResource(PlatWelfareConfig platConfig,
			SingleServerConfig config, Map<String, Object> param) {
		Map<String, Object> map = welfareDaoTemplate.selectOne(
				"returnResource.sum", param);
		BigInteger drSum = new BigDecimal(map.get("drSum").toString())
				.toBigInteger();
		BigInteger singleSum = new BigDecimal(map.get("singleSum").toString())
				.toBigInteger();
		if (config != null) {

			return config.getNewServiceGold().add(drSum).add(singleSum);
		}

		return platConfig.getNewServiceGold().add(drSum).add(singleSum);
	}

	public Map<String, Object> getGrantGoldLog(OperaGoldLog log,
			Date startTime, Date endTime, int limit, int offset) {
		Map<String, Object> param = Collections3.transBean2Map(log);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", welfareDaoTemplate.selectList("operaGoldLog.paging",
				param, new RowBounds(offset, limit)));
		result.put("total",
				welfareDaoTemplate.selectOne("operaGoldLog.count", param));
		return result;

	}

	public List<Map<String, Object>> rankData(OperaGoldLog log, Date startTime,
			Date endTime, String type) {

		Map<String, Object> param = Collections3.transBean2Map(log);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		return getResult(param, type);
	}

	private List<Map<String, Object>> getResult(Map<String, Object> param,
			String type) {
		if ("ind".equals(type)) {

			return getIndRankResult(param);
		}

		return getPlatRankResult(param);

	}

	private List<Map<String,Object>> getPlatRankResult(Map<String,Object> param){
		List<Map<String,Object>> logs = welfareDaoTemplate.selectList("operaGoldLog.sumRankPlat", param);
		
		return setRoleInfo(logs);
	}

	private List<Map<String, Object>> getIndRankResult(Map<String, Object> param) {
		// 查询各个通道的发放元宝总额
		List<Map<String, Object>> list = welfareDaoTemplate.selectList(
				"operaGoldLog.sumRankInd", param);
		Map<Object, BigDecimal> platMap = new HashMap<Object, BigDecimal>();
		Map<Object, BigDecimal> indMap = new HashMap<Object, BigDecimal>();
		Map<Object, Map<String, Object>> roleMap = new HashMap<Object, Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Object roleId = map.get("roleId");
			BigDecimal sumYb = (BigDecimal) map.get("sumYb");
			if (map.get("passageway").equals(OperaGoldLog.PASSAGEWAY_PLAT)) {
				// 计算平台通道发放元宝总额
				if (platMap.get(roleId) == null) {
					platMap.put(roleId, sumYb);
				} else {
					platMap.put(roleId, platMap.get(roleId).add(sumYb));
				}
			} else {
				// 计算特殊通道+独代通道发放元宝总额
				if (indMap.get(roleId) == null) {
					indMap.put(roleId, sumYb);
				} else {
					indMap.put(roleId, indMap.get(roleId).add(sumYb));
				}
			}
			// 根据roleId取任意一条角色基本信息
			roleMap.put(roleId, map);
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 遍历角色基本信息roleMap 根据roleId去找前面计算的通道元宝
		for (Map.Entry<Object, Map<String, Object>> entry : roleMap.entrySet()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", entry.getKey());
			map.put("roleName", entry.getValue().get("roleName"));
			map.put("userId", entry.getValue().get("userId"));
			map.put("pid", entry.getValue().get("pid"));
			map.put("platName", entry.getValue().get("platName"));
			map.put("serverName", entry.getValue().get("serverName"));
			map.put("serverId", entry.getValue().get("serverId"));
			map.put("usePeople", entry.getValue().get("usePeople"));
			map.put("usePeoplePost", entry.getValue().get("usePeoplePost"));
			map.put("status", entry.getValue().get("status"));
			map.put("platSum", platMap.get(entry.getKey()) == null ? 0
					: platMap.get(entry.getKey()));
			map.put("indSum",
					indMap.get(entry.getKey()) == null ? 0 : indMap.get(entry
							.getKey()));
			map.put("sumYb",Integer.valueOf(map.get("platSum").toString())+Integer.valueOf(map.get("indSum").toString()));
					
			result.add(map);
		}
		return setRoleInfo(result);
	}

	private List<Map<String,Object>> setRoleInfo(List<Map<String, Object>> logs) {
		List<BigInteger> roleIds = new ArrayList<BigInteger>();
		for (Map<String, Object> logMap : logs) {
			if (logMap.get("roleId") == null) {
				continue;
			}
			roleIds.add(new BigInteger(logMap.get("roleId").toString()));
		}
		if (roleIds.isEmpty()) {
			return logs;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleIds", roleIds.toArray());

		List<Map<String, Object>> lstSum = welfareDaoTemplate.selectList(
				"operaGoldLog.sumByRoleIds", param);
		List<Map<String, Object>> lstRole = gameDaoTemplate
				.selectListAllWorlds("role.findAllRoleByIds", param);
		List<Map<String, Object>> lstChargeSum = rebateDaoTemplate.selectList(
				"rebateRechargeMapper.sumByRoleIds", param);
		List<WelfareNum> nums = welfareDaoTemplate.selectList("welfareNum.selectOneByRoleIds", param);
		// List<Map<String,Object>> lstRole = new
		// ArrayList<Map<String,Object>>();
		for (Map<String, Object> logMap : logs) {
			if(logMap.get("roleId")==null){
				continue;
			}
			BigInteger logRoleId = new BigInteger(logMap.get("roleId").toString());
			logMap.put("sumYb", 0);
			logMap.put("sumCharge", 0);
			for (Map<String, Object> map : lstSum) {
				// 提取角色对应的发放元宝总额
				BigInteger roleId = new BigInteger(String.valueOf(map
						.get("roleId")));
				if (logRoleId.compareTo(roleId) == 0) {
					logMap.put("sumYb", map.get("sumYb"));
				}
			}

			for (Map<String, Object> map : lstChargeSum) {
				// 提取角色对应的充值元宝总额
				BigInteger roleId = new BigInteger(String.valueOf(map
						.get("roleId")));
				if (logRoleId.compareTo(roleId) == 0) {
					logMap.put("sumCharge", GoldRmbUtils.GoldChangeToRmb(new BigDecimal(map.get("sumMoney").toString()).intValue()));
				}
			}
			
			for(WelfareNum num : nums){
				if(num.getRoleId().compareTo(logRoleId)==0){
					logMap.put("topCharge", num.getTopCharge());
				}
			}

			for (Map<String, Object> map : lstRole) {

				BigInteger roleId = new BigInteger(String.valueOf(map
						.get("roleId")));
				if (logRoleId.compareTo(roleId) == 0) {
					logMap.put("vipLevel", map.get("vipLevel"));
					Date logoutTime = (Date) map.get("lastLogoutTime");
					int day;
					try {
						day = DateUtils.getDateSpace(logoutTime,new Date());
					} catch (ParseException e) {
						day = 0;
					}
					logMap.put("numOfDays", day);
					logMap.put("level", map.get("level"));
					logMap.put("combat", map.get("power"));
					logMap.put("surplusYb", map.get("diamond"));

				}
			}
			
		}
		return logs;
	}

}
