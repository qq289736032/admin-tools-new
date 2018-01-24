/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.modules.sys.dao;

import java.util.List;

import com.mokylin.cabal.common.persistence.annotation.MyBatisDao;
import com.mokylin.cabal.modules.sys.entity.Dict;

/**
 * MyBatis字典DAO接口
 * @author 稻草鸟人
 * @version 2014-8-23
 */
@MyBatisDao
public interface MyBatisDictDao {
	
    Dict get(String id);
    
    List<Dict> find(Dict dict);
    
}
