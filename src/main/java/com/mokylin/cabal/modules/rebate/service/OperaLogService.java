package com.mokylin.cabal.modules.rebate.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.modules.rebate.entity.RebateOperaDetail;
import com.mokylin.cabal.modules.rebate.entity.RebateOperaLog;

@Transactional(readOnly=true)
@Service
public class OperaLogService {
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	
	public Map<String,Object> LogData(int limit,int offset,RebateOperaLog log,Date startTime,Date endTime){
		Map<String,Object> param = Collections3.transBean2Map(log);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		List<RebateOperaLog> logs = rebateDaoTemplate.selectList("rebateOperaLogMapper.paging", param);
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("rows", logs);
		result.put("total", rebateDaoTemplate.selectList("rebateOperaLogMapper.count", param));
		return result;
	}
	
	public List<RebateOperaDetail> LogDetailData(String logId){
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("logId", logId);
		return rebateDaoTemplate.selectList("rebateOperaDetailMapper.findAll", param);
	}
	
}
