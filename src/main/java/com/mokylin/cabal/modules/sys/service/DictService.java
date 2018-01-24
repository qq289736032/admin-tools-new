/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.CacheUtils;
import com.mokylin.cabal.modules.sys.dao.DictDao;
import com.mokylin.cabal.modules.sys.dao.MyBatisDictDao;
import com.mokylin.cabal.modules.sys.entity.Dict;
import com.mokylin.cabal.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author 稻草鸟人
 * @version 2014-5-29
 */
@Service
@Transactional(readOnly = true)
public class DictService extends BaseService {

	@Autowired
	private DictDao dictDao;
	
//	@Autowired
//	@SuppressWarnings("unused")
//	private MyBatisDictDao myBatisDictDao;
	
	public Dict get(String id) {
		// MyBatis 查询
//		return myBatisDictDao.get(id);
		// Hibernate 查询
		return dictDao.get(id);
	}
	
	public Page<Dict> find(Page<Dict> page, Dict dict) {
		// MyBatis 查询
//		dict.setPage(page);
//		page.setList(myBatisDictDao.find(dict));
//		return page;
		// Hibernate 查询
		DetachedCriteria dc = dictDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(dict.getType())){
			dc.add(Restrictions.eq("type", dict.getType()));
		}
		if (StringUtils.isNotEmpty(dict.getDescription())){
			dc.add(Restrictions.like("description", "%"+dict.getDescription()+"%"));
		}
		dc.add(Restrictions.eq(Dict.FIELD_DEL_FLAG, Dict.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("type")).addOrder(Order.asc("sort")).addOrder(Order.desc("id"));
		return dictDao.find(page, dc);
	}
	
	public List<String> findTypeList(){
		return dictDao.findTypeList();
	}


	
	@Transactional(readOnly = false)
	public void save(Dict dict) {
		dictDao.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		dictDao.deleteById(id);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
}
