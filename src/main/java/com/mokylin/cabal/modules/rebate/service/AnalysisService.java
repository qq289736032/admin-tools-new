package com.mokylin.cabal.modules.rebate.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.RebateDaoTemplate;

@Transactional(readOnly=true)
@Service
public class AnalysisService {
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	
	public List<Map<String,Object>> analysisData(String goodsName,String goodsId,String pid,Date startTime,Date endTime){
		//统计
		Map<String,Object> countAll = rebateDaoTemplate.selectOne("rebateOperaDetailMapper.statisticAll");
		Map<String,Object> countRoleIdMap = rebateDaoTemplate.selectOne("rebateOperaDetailMapper.statisticAllRole");
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("goodsName", goodsName);
		param.put("goodsId", goodsId);
		param.put("pid", pid);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		List<Map<String,Object>> statistic = rebateDaoTemplate.selectList("rebateOperaDetailMapper.statistic",param);
	/*	//key=pid+goodsId  value=兑换总数量
		Map<String,BigDecimal> sumNumMap = new HashMap<String, BigDecimal>();
		//key=pid+goodsId  value=兑换次数
		Map<String,Object> timesMap = new HashMap<String, Object>();
		//key=pid+goodsId  value=兑换人数
		Map<String,Object> countRoleIdMap = new HashMap<String, Object>();
		*/
		for(Map<String,Object> map : statistic){
			
			BigDecimal sumNum = new BigDecimal(map.get("sumNum").toString());
			/*String pid0 = (String) map.get("pid");
			String goodsId0 = (String) map.get("goodsId");
			String goodsName0 = (String) map.get("goodsName");*/
			Long times = (Long) map.get("times");
			Long countRoleId = (Long) map.get("countRoleId");
			BigDecimal allSumNum = new BigDecimal(countAll.get("sumNum").toString());
			BigDecimal allTimes = new BigDecimal(countAll.get("times").toString());
			BigDecimal allCountRoleId = new BigDecimal(countRoleIdMap.get("countRoleId").toString());
			map.put("sumNumRatio", sumNum.divide(allSumNum ,4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			map.put("timesRatio", new BigDecimal(times).divide(allTimes,4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			map.put("countRoleIdRatio", new BigDecimal(countRoleId).divide(allCountRoleId,4, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)));
			
		}
		return statistic;
	}
	
}
