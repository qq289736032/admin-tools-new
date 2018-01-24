package com.mokylin.cabal.modules.rebate.service;

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
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.entity.RebateKeepDay;
import com.mokylin.cabal.modules.rebate.entity.RebateKeepDayLog;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
@Transactional(readOnly=true)
@Service
public class KeepDayService extends BaseService{
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	
	/**
	 * 查询天数配置
	 * @return
	 */
	public RebateKeepDay getKeepDay(){
		
		return rebateDaoTemplate.selectOne("rebateKeepDayMapper.find");
	}
	
	/**
	 * 配置天数
	 * @param day
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result insertKeepDay(RebateKeepDay day){
		
		if(getKeepDay()!=null){
			return doUpdateKeepDay(day);
		}
		
		return doInsertKeepDay(day);
	}
	
	private Result doUpdateKeepDay(RebateKeepDay day){
		
		day.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.update("rebateKeepDayMapper.update",day);
		insertLog("修改", day);
		return new Result(true);
	}
	
	private Result doInsertKeepDay(RebateKeepDay day){
		
		day.setId(IdGen.uuid());
		day.setCreateBy(UserUtils.getUser().getId());
		day.setCreateName(UserUtils.getUser().getLoginName());
		day.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.insert("rebateKeepDayMapper.insert",day);
		insertLog("新增", day);
		return new Result(true);
	}
	
	/**
	 * 插入日志
	 * @param editType
	 * @param day
	 */
	private void insertLog(String editType,RebateKeepDay day){
		RebateKeepDayLog log = new RebateKeepDayLog();
		log.setDay(day.getDay());
		log.setCreateBy(UserUtils.getUser().getId());
		log.setCreateName(UserUtils.getUser().getLoginName());
		log.setEditType(editType);
		log.setId(IdGen.uuid());
		log.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.insert("rebateKeepDayLogMapper.insert",log);
	}
	
	/**
	 * 查询所有日志
	 * @param startTime
	 * @param endTime
	 * @param limit
	 * @param offset
	 * @param createName
	 * @return
	 */
	public Map<String,Object> getAllLog(Date startTime,Date endTime,int limit,int offset,String createName){
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("createName", createName);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("rows", rebateDaoTemplate.selectList("rebateKeepDayLogMapper.paging", param, new RowBounds(offset, limit)));
		result.put("total", rebateDaoTemplate.selectOne("rebateKeepDayLogMapper.count", param));
		return result;
	}
}
