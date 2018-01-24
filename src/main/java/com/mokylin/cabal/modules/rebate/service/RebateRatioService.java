package com.mokylin.cabal.modules.rebate.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.entity.RebateRatio;
import com.mokylin.cabal.modules.rebate.entity.RebateRatioLog;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
@Transactional(readOnly=true)
@Service
public class RebateRatioService extends BaseService{
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	/**
	 * 查询所有
	 * @return
	 */
	public List<RebateRatio> getAll(){
		
		return rebateDaoTemplate.selectList("rebateRatioMapper.find");
	}
	
	/**
	 * 返利比例配置
	 * @param day
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result insertRabateRatio(RebateRatio ratio){
		
		if(StringUtils.isNotBlank(ratio.getId())){
			return doUpdateRebateRatio(ratio);
		}
		
		return doInsertRebateRatio(ratio);
	}
	
	private Result doUpdateRebateRatio(RebateRatio r){
		
		r.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.update("rebateRatioMapper.update",r);
		insertLog("修改", r.getRebateRatio(), r.getDayAmount());
		return new Result(true);
	}
	
	private Result doInsertRebateRatio(RebateRatio ratio){
		
		ratio.setId(IdGen.uuid());
		ratio.setCreateBy(UserUtils.getUser().getId());
		ratio.setCreateName(UserUtils.getUser().getLoginName());
		ratio.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.insert("rebateRatioMapper.insert",ratio);
		insertLog("添加", ratio.getRebateRatio(), ratio.getDayAmount());
		return new Result(true);
	}
	/**
	 * 删除 
	 * @param ratio
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result delete(RebateRatio ratio){
		ratio.setUpdateBy(UserUtils.getUser().getLoginName());
		ratio.setDelFlag(1);
		rebateDaoTemplate.update("rebateRatioMapper.del",ratio);
		insertLog("删除", ratio.getRebateRatio(), ratio.getDayAmount());
		return new Result(true);
	}
	
	/**
	 * 插入日志
	 * @param editType
	 * @param rebateRatio
	 * @param dayAmount
	 */
	private void insertLog(String editType,int rebateRatio,long dayAmount){
		
		RebateRatioLog log = new RebateRatioLog();
		log.setCreateBy(UserUtils.getUser().getId());
		log.setCreateName(UserUtils.getUser().getLoginName());
		log.setUpdateBy(UserUtils.getUser().getLoginName());
		log.setDayAmount(dayAmount);
		log.setEditType(editType);
		log.setId(IdGen.uuid());
		log.setRebateRatio(rebateRatio);
		rebateDaoTemplate.insert("rebateRatioLogMapper.insert", log);
	}
	
	public Map<String,Object> getAllLog(Date startTime,Date endTime,int limit,int offset,String createName,String editType){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		param.put("createName", createName);
		param.put("editType", editType);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("rows", rebateDaoTemplate.selectList("rebateRatioLogMapper.paging", param, new RowBounds(offset, limit)));
		result.put("total", rebateDaoTemplate.selectOne("rebateRatioLogMapper.count",param));
		return result;
	}
}
