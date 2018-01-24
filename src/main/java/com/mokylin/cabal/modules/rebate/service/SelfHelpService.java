package com.mokylin.cabal.modules.rebate.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.async.AsyncService;
import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.entity.RebateGoods;
import com.mokylin.cabal.modules.rebate.entity.RebateGoodsSpec;
import com.mokylin.cabal.modules.rebate.entity.RebateKeepDay;
import com.mokylin.cabal.modules.rebate.entity.RebateOperaDetail;
import com.mokylin.cabal.modules.rebate.entity.RebateOperaLog;
import com.mokylin.cabal.modules.rebate.entity.RebateStatisticRecharge;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GameEmail;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import com.mokylin.cabal.modules.tools.vo.AttachmentGoods;

@Transactional(readOnly=true)
@Service
public class SelfHelpService {
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	@Autowired
	private KeepDayService keepDayService;
	@Autowired
	private RebateGoodsService rebateGoodsService;
	@Autowired
	private GoodsSpecService goodsSpecService;
    @Resource
    private AsyncService asyncService;
	
	
	public List<Map<String,Object>> selfHelpData(String roleName,String pid,String serverId,BigInteger roleId,String userId){
		//获取返利天数
		RebateKeepDay keepDay = keepDayService.getKeepDay();
		//前N天 00:00:00
		Date beforeDay = DateUtils.getDateStart(DateUtils.getBeforeDay(new Date(), keepDay.getDay()));
		//前一天 23:59:59
		Date endDay = DateUtils.getDateEnd(DateUtils.getBeforeDay(new Date(), 1));
		//前N-1天
		Date beforeNOneDay = DateUtils.getDateStart(DateUtils.getBeforeDay(new Date(), keepDay.getDay()+1));
		
		//获取
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("roleName", roleName);
		param.put("pid", pid);
		param.put("serverId", serverId);
		param.put("userId", userId);
		param.put("roleId", roleId);
		param.put("statisticDate", beforeDay);
		param.put("endDate", endDay);
		List<Map<String,Object>> statistic = rebateDaoTemplate.selectList("rebateStatisticRechargeMapper.sumByRoleId", param);
		//List<Long> roleIds = new ArrayList<Long>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : statistic) {
			BigDecimal sumMoney = (BigDecimal) map.get("sumMoney");
			Long roleId0 = (Long) map.get("roleId");
			
			//roleIds.add(roleId0);
			Long allowGold = caculation(beforeDay,endDay,beforeNOneDay,roleId0,sumMoney.longValue());
			if(allowGold!=0){
				
				Map<String,Object> resultMap = map;
				Long usedGold = sumMoney.longValue()-allowGold;
				resultMap.put("allowGold", allowGold);
				resultMap.put("usedGold", usedGold);
				resultList.add(resultMap);
			}
			
		}
		
		return resultList;
/*		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("rows", statistic);
		result.put("total", rebateDaoTemplate.selectOne("rebateStatisticRechargeMapper.count",param));
		return result;*/
	}
	
	private Long caculation(Date beforeDay,Date endDay,Date beforeNOneDay,Long roleId,Long sumMoney){
		//查询前N-1天返还的元宝数
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("statisticDate", beforeNOneDay);
		param.put("roleId", roleId);
		RebateStatisticRecharge statistic = rebateDaoTemplate.selectOne("rebateStatisticRechargeMapper.findByRoleIdAndDate", param);
		Long beforeNOnegold = statistic == null?0:statistic.getRebateGold();
		//查询前N天 到前一天兑换了多少元宝
		param = new HashMap<String, Object>();
		param.put("startDate", beforeDay);
		param.put("endDate", endDay);
		param.put("roleId", roleId);
		Map<String,Object> result = rebateDaoTemplate.selectOne("rebateOperaLogMapper.sumByDateAndRoleId", param);
		Long sumGold = new BigDecimal(result.get("sumGold").toString()).longValue();
		//查询今天兑换了多少元宝
		param = new HashMap<String, Object>();
		param.put("startDate", DateUtils.getDateStart(new Date()));
		param.put("endDate", DateUtils.getDateEnd(new Date()));
		param.put("roleId", roleId);
		result = rebateDaoTemplate.selectOne("rebateOperaLogMapper.sumByDateAndRoleId", param);
		Long todayGold = new BigDecimal(result.get("sumGold").toString()).longValue();
		Long m = sumGold-beforeNOnegold<0?0:sumGold-beforeNOnegold;
		return sumMoney-m-todayGold<0?0:sumMoney-m-todayGold;
	}
	
	/**
	 * N天的充值返利明细
	 * @param roleId
	 * @param day
	 * @return
	 */
	public List<RebateStatisticRecharge> chargeDeatailData(Long roleId,int day){
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("statisticDate",DateUtils.getDateStart(DateUtils.getBeforeDay(new Date(), day)));
		return rebateDaoTemplate.selectList("rebateStatisticRechargeMapper.findAll",param);
	}
	
	public List<Map<String,Object>> getGoods(String pid,Long roleId){
		RebateGoodsSpec spec = new RebateGoodsSpec();
		spec.setPid(pid);
		List<RebateGoodsSpec> specs = goodsSpecService.getAll(spec);
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		List<String> lstGoodsIds = new ArrayList<String>();
		for (RebateGoodsSpec rebateGoodsSpec : specs) {
			
			Map<String,Object> resultMap = new HashMap<String, Object>();
			resultMap.put("id", rebateGoodsSpec.getId());
			resultMap.put("goodsName", rebateGoodsSpec.getGoodsName());
			resultMap.put("goodsId", rebateGoodsSpec.getGoodsId());
			resultMap.put("goodsDesc", rebateGoodsSpec.getGoodsDesc());
			resultMap.put("goodsPrice", rebateGoodsSpec.getGoodsPrice());
			resultMap.put("goodsProp", rebateGoodsSpec.getGoodsProp());
			resultMap.put("exchangeLimit", rebateGoodsSpec.getExchangeLimit());
			resultMap.put("topNum", rebateGoodsSpec.getTopNum());
			lstGoodsIds.add(rebateGoodsSpec.getGoodsId());
			queryAlreadyRebateGoods(rebateGoodsSpec.getExchangeLimit(),rebateGoodsSpec.getGoodsId(),roleId,resultMap);
			result.add(resultMap);
		}
		
		List<RebateGoods> goods = rebateGoodsService.getAll(new RebateGoods());
		for (RebateGoods rebateGoods : goods) {
			
			if(!lstGoodsIds.contains(rebateGoods.getGoodsId())){
				
				Map<String,Object> resultMap = new HashMap<String, Object>();
				resultMap.put("id", rebateGoods.getId());
				resultMap.put("goodsName", rebateGoods.getGoodsName());
				resultMap.put("goodsId", rebateGoods.getGoodsId());
				resultMap.put("goodsDesc", rebateGoods.getGoodsDesc());
				resultMap.put("goodsPrice", rebateGoods.getGoodsPrice());
				resultMap.put("goodsProp", rebateGoods.getGoodsProp());
				resultMap.put("exchangeLimit", rebateGoods.getExchangeLimit());
				resultMap.put("topNum", rebateGoods.getTopNum());
				queryAlreadyRebateGoods(rebateGoods.getExchangeLimit(),rebateGoods.getGoodsId(),roleId,resultMap);
				result.add(resultMap);
			}
		}
		
		return result;
	}
	
	private void queryAlreadyRebateGoods(int exchangeLimit,String goodsId,Long roleId,Map<String,Object> resultMap){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);
		param.put("goodsId", goodsId);
		resultMap.put("usedNum", 0);
	
		if(exchangeLimit==RebateGoods.GOODS_MONTH_TOP){
			param.put("startTime", DateUtils.getDateStart(DateUtils.getBeginOfMonth(new Date())));
			param.put("endTime", new Date());
			setResultMap(param,goodsId,resultMap);
			return;
		}
		
		setResultMap(param,goodsId,resultMap);
	}
	
	private void setResultMap(Map<String,Object> param,String goodsId,Map<String,Object> resultMap){
		List<Map<String,Object>> map = rebateDaoTemplate.selectList("rebateOperaDetailMapper.sumNum", param);
		for (Map<String, Object> map2 : map) {
			if(map2.get("goodsId").toString().equals(goodsId)){
				resultMap.put("usedNum", map2.get("sumNum"));
			}
		}
	}
	@Transactional(readOnly=false)
	public Result submit(Long roleId,String attachments,String content,String title,String serverId,String pid,String platName,String serverName,String userId){
		List<AttachmentGoods> list = new ArrayList<AttachmentGoods>();
		String [] attatch = attachments.split(";");
		int sumMoney = 0;
		for (String att : attatch) {
			AttachmentGoods goods = new AttachmentGoods();
			goods.setAttributesInfo("");
			goods.setBinding(att.split(",")[2]);
			goods.setCount(att.split(",")[1]);
			goods.setExpireTime("");
			goods.setId(att.split(",")[0]);
			list.add(goods);
			sumMoney = sumMoney+Integer.parseInt(att.split(",")[1])*Integer.parseInt(att.split(",")[3]);
			
		}
		String logId = insertRebateOperaLog(sumMoney,roleId,serverId,pid,content,title,attachments,platName,serverName,userId);
		for (String att : attatch) {
			int sum = Integer.parseInt(att.split(",")[1])*Integer.parseInt(att.split(",")[3]);
			insertGoodsDetails(att,pid,serverId,roleId,logId,sum,platName);
		}
		//发送邮件
		sendEmail(list, content, roleId, title, serverId);
		
		return new Result(true);
		
	}
	
	/**
	 * 重新发送
	 * @param attachments
	 * @param logId
	 * @param content
	 * @param title
	 * @param roleId
	 * @param serverId
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result reSubmit(String attachments,String logId,String content,String title,Long roleId,String serverId){
		List<AttachmentGoods> list = new ArrayList<AttachmentGoods>();
		String [] attatch = attachments.split(";");
		for (String att : attatch) {
			AttachmentGoods goods = new AttachmentGoods();
			goods.setAttributesInfo("");
			goods.setBinding(att.split(",")[2]);
			goods.setCount(att.split(",")[1]);
			goods.setExpireTime("");
			goods.setId(att.split(",")[0]);
			list.add(goods);
		}
		sendEmail(list, content, roleId, title, serverId);
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("logId", logId);
		param.put("status", 0);
		param.put("updateDate", new Date());
		param.put("updateBy", UserUtils.getUser().getLoginName());
		
		rebateDaoTemplate.update("rebateOperaLogMapper.updateStatus", param);
		
		return new Result(true);
	}
	
	/**
	 * 发送邮件
	 * @param list
	 * @param content
	 * @param roleId
	 * @param title
	 * @param serverId
	 */
	private void sendEmail(List<AttachmentGoods> list,String content,Long roleId,String title,String serverId){
		GameEmail email = new GameEmail();
		//email.setApproveBy(UserUtils.getUser().getId());
		//email.setApproveName(UserUtils.getUser().getLoginName());
		email.setAttachments(JSON.toJSONString(list));
		email.setContent(content);
		//email.setCreateBy(UserUtils.getUser().getId());
		//email.setCreateName(UserUtils.getUser().getLoginName());
		//email.setCreateDate(new Date());
		//email.setCreateTime(new Date());
		email.setDelayHours(0);
		//email.setEmailStatus("1");
		email.setIsGlobal(0);
		email.setId(IdGen.uuid());
		email.setJb(0);
		email.setYb(0);
		email.setReceiverUserIds(roleId.toString());
		email.setReceiverNames(RedisUtils.getRoleNameByRoleId(roleId.toString(), ""));
		email.setTitle(title);
		email.setServerIds(serverId);
		asyncService.sendEmail(email);
	}
	
	private String insertRebateOperaLog(int sumMoney,Long roleId,String serverId,String pid,String content,String title,String attachments
			,String platName,String serverName,String userId){
		RebateOperaLog log = new RebateOperaLog();
		log.setCreateBy(UserUtils.getUser().getId());
		log.setCreateDate(new Date());
		log.setCreateName(UserUtils.getUser().getLoginName());
		log.setEmailText(content);
		log.setEmailTitle(title);
		log.setGold(Long.valueOf(sumMoney));
		log.setId(IdGen.uuid());
		log.setPid(pid);
		log.setPlatName(platName);
		log.setRoleId(roleId);
		log.setRoleName(RedisUtils.getRoleNameByRoleId(roleId.toString(), ""));
		log.setServerId(serverId);
		log.setServerName(serverName);
		log.setStatus(0);
		log.setUpdateBy(UserUtils.getUser().getLoginName());
		log.setUpdateDate(new Date());
		log.setUserId(userId);
		log.setAttachments(attachments);
		rebateDaoTemplate.insert("rebateOperaLogMapper.insert", log);
		return log.getId();
	}
	private void insertGoodsDetails(String att,String pid,String serverId,Long roleId,String logId,int sumMoney
			,String platName){
		RebateOperaDetail detail = new RebateOperaDetail();
		detail.setCreateBy(UserUtils.getUser().getId());
		detail.setCreateName(UserUtils.getUser().getLoginName());
		detail.setCreateDate(new Date());
		detail.setGoodsId(att.split(",")[0]);
		detail.setGoodsName(att.split(",")[4]);
		detail.setGoodsProp(att.split(",")[2].equals("1")?RebateOperaDetail.GOODS_PROP_BIND:RebateOperaDetail.GOODS_PROP_NOT_BIND);
		detail.setId(IdGen.uuid());
		detail.setNum(Integer.parseInt(att.split(",")[1]));
		detail.setOperaLogId(logId);
		detail.setPid(pid);
		detail.setPlatName(platName);
		detail.setRoleId(roleId);
		detail.setRoleName(RedisUtils.getRoleNameByRoleId(roleId.toString(), ""));
		detail.setSumMoney(Long.valueOf(sumMoney));
		detail.setUpdateBy(UserUtils.getUser().getLoginName());
		detail.setUpdateDate(new Date());
		rebateDaoTemplate.insert("rebateOperaDetailMapper.insert", detail);
	}
}
