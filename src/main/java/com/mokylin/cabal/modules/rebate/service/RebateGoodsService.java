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
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.rebate.entity.RebateGoods;
import com.mokylin.cabal.modules.rebate.entity.RebateGoodsLog;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
@Transactional(readOnly=true)
@Service
public class RebateGoodsService extends BaseService{
	@Resource
	private RebateDaoTemplate rebateDaoTemplate;
	/**
	 * 查询所有
	 * @return
	 */
	public List<RebateGoods> getAll(RebateGoods goods){
		
		return rebateDaoTemplate.selectList("rebateGoodsMapper.findAll",goods);
	}
	
	/**
	 * 返利物品配置
	 * @param goods
	 * @return
	 */
	@Transactional(readOnly=false)
	public Result insertGoods(RebateGoods goods){
		
		if(StringUtils.isNotBlank(goods.getId())){
			return doUpdateGoods(goods);
		}
		
		RebateGoods g = rebateDaoTemplate.selectOne("rebateGoodsMapper.findOneByGoodsId", goods);
		//如果数据库已存在该物品则执行更新操作
		if(g!=null){
			goods.setId(g.getId());
			return doUpdateGoods(goods);
		}
		return doInsertGoods(goods);
	}
	
	private Result doUpdateGoods(RebateGoods goods){
		
		goods.setUpdateBy(UserUtils.getUser().getName());
		rebateDaoTemplate.update("rebateGoodsMapper.update",goods);
		insertLog(goods, "修改");
		return new Result(true);
	}
	
	private Result doInsertGoods(RebateGoods goods){
		
		goods.setId(IdGen.uuid());
		goods.setCreateBy(UserUtils.getUser().getId());
		goods.setCreateName(UserUtils.getUser().getName());
		goods.setUpdateBy(UserUtils.getUser().getName());
		rebateDaoTemplate.insert("rebateGoodsMapper.insert",goods);
		insertLog(goods, "添加");
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
		RebateGoods goods = new RebateGoods();
		goods.setId(id);
		goods.setUpdateBy(UserUtils.getUser().getLoginName());
		goods.setDelFlag(1);
		rebateDaoTemplate.update("rebateGoodsMapper.del",goods);
		goods = rebateDaoTemplate.selectOne("rebateGoodsMapper.findOne", goods);
		insertLog(goods, "删除");
		return new Result(true);
	}
	
	private void insertLog(RebateGoods goods,String editType){
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
		log.setPid("");
		log.setPlatName("");
		log.setTopNum(goods.getTopNum());
		log.setUpdateBy(UserUtils.getUser().getLoginName());
		rebateDaoTemplate.insert("rebateGoodsLogMapper.insert", log);
	}
	
	public Map<String,Object> getAllLog(Date startTime,Date endTime,int limit,int offset,RebateGoodsLog log){
		Map<String,Object> param = Collections3.transBean2Map(log);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("rows", rebateDaoTemplate.selectList("rebateGoodsLogMapper.paging", param, new RowBounds(offset, limit)));
		result.put("total", rebateDaoTemplate.selectOne("rebateGoodsLogMapper.count", param));
		return result;
	}
}
