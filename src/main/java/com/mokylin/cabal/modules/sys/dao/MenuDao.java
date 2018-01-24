/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mokylin.cabal.common.persistence.BaseDao;
import com.mokylin.cabal.common.persistence.Parameter;
import com.mokylin.cabal.modules.sys.entity.Dict;
import com.mokylin.cabal.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * @author 稻草鸟人
 * @version 2014-8-23
 */
@Repository
public class MenuDao extends BaseDao<Menu> {
	
	public List<Menu> findAllActivitiList() {
		return find("from Menu where delFlag=:p1 and isActiviti = :p2 order by sort",new Parameter(Dict.DEL_FLAG_NORMAL,Menu.YES));
	}
	
	public List<Menu> findByParentIdsLike(String parentIds){
		return find("from Menu where parentIds like :p1", new Parameter(parentIds));
	}

	public List<Menu> findAllList(){
		return find("from Menu where delFlag=:p1 order by sort", new Parameter(Dict.DEL_FLAG_NORMAL));
	}
	
	public List<Menu> findByUserId(String userId){
		return find("select distinct m from Menu m, Role r, User u where m in elements (r.menuList) and r in elements (u.roleList)" +
				" and m.delFlag=:p1 and r.delFlag=:p1 and u.delFlag=:p1 and u.id=:p2" + // or (m.user.id=:p2  and m.delFlag=:p1)" + 
				" order by m.sort", new Parameter(Menu.DEL_FLAG_NORMAL, userId));
	}
	public List<Menu> findAllActivitiList(String userId) {
		return find("select distinct m from Menu m, Role r, User u where m in elements (r.menuList) and r in elements (u.roleList)" +
				" and m.delFlag=:p1 and r.delFlag=:p1 and u.delFlag=:p1 and m.isActiviti=:p2 and u.id=:p3 order by m.sort", 
				new Parameter(Menu.DEL_FLAG_NORMAL, Menu.YES,userId));
	}
	
}
