package com.mokylin.cabal.modules.rebate.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.RebateDaoTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.entity.RebateGoods;
import com.mokylin.cabal.modules.rebate.entity.RebateGoodsLog;
import com.mokylin.cabal.modules.rebate.entity.RebateGoodsSpec;
import com.mokylin.cabal.modules.sys.utils.UserUtils;

@Transactional(readOnly=true)
@Service
public class GoodsSpecService {
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<RebateGoodsSpec> getAll(RebateGoodsSpec spec){
		return rebateDaoTemplate.selectList("reabateGoodsSpecMapper.findAll",spec);
	}
	
	/**
	 * 返利物品特殊配置
	 * @param pids
	 * @param spec
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result batchInsertSpec(String [] pids,RebateGoodsSpec spec){
		for (String pid : pids) {
			
			spec.setPid(pid);
			spec.setPlatName(pid);
			insertSpec(spec);
		}
		return new Result(true);
	}
	
	@Transactional(readOnly=false)
	private void insertSpec(RebateGoodsSpec spec){
		spec.setId(IdGen.uuid());
		spec.setCreateBy(UserUtils.getUser().getId());
		spec.setCreateName(UserUtils.getUser().getLoginName());
		spec.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.insert("reabateGoodsSpecMapper.insert",spec);
		insertLog(spec, "添加");
	}
	
	/**
	 * 更新配置
	 * @param spec
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result updateSepc(RebateGoodsSpec spec){
		
		spec.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.update("reabateGoodsSpecMapper.update",spec);
		insertLog(spec, "修改");
		return new Result(true);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result batchDel(String [] ids){
		for (String id : ids) {
			delete(id);
		}
		return new Result(true);
	}
	/**
	 * 删除 
	 * @param ratio
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result delete(String id){
		RebateGoodsSpec goods = new RebateGoodsSpec();
		goods.setId(id);
		goods.setUpdateBy(UserUtils.getUser().getLoginName());
		goods.setDelFlag(1);
		rebateDaoTemplate.update("reabateGoodsSpecMapper.del",goods);
		goods = rebateDaoTemplate.selectOne("reabateGoodsSpecMapper.findOne", goods);
		insertLog(goods, "删除");
		return new Result(true);
	}
	
	private void insertLog(RebateGoodsSpec goods,String editType){
		RebateGoodsLog log = new RebateGoodsLog();
		log.setId(IdGen.uuid());
		log.setCreateBy(UserUtils.getUser().getId());
		log.setCreateName(UserUtils.getUser().getLoginName());
		log.setEditType(editType);
		log.setExchangeLimit(goods.getExchangeLimit());
		log.setGoodsDesc(goods.getGoodsDesc());
		log.setGoodsId(goods.getGoodsId());
		log.setGoodsName(goods.getGoodsName());
		log.setGoodsPrice(goods.getGoodsPrice());
		log.setGoodsProp(goods.getGoodsProp());
		log.setPid(goods.getPid());
		log.setPlatName(goods.getPlatName());
		log.setTopNum(goods.getTopNum());
		log.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.insert("rebateGoodsLogMapper.insert", log);
	}
	
}
