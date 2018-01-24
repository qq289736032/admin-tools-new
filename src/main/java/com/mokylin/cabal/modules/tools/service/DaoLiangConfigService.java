package com.mokylin.cabal.modules.tools.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;


@Service
public class DaoLiangConfigService extends BaseService {
	 @Resource
	 protected ToolDaoTemplate toolDaoTemplate;

	 /**
	 * 从monitor_Config表中查询配置的预警参数，如果无值，则将预设的值放到数据库中，然后返回改条记录
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public Map<String,Object> selectOneAfterInsertOrUpdate(String statement,MybatisParameter parameter){
		 Map<String,Object> daoLiangConfig =toolDaoTemplate.selectOne(statement,parameter);
		 if(null==daoLiangConfig){
    		parameter.put("cpa", 3);
    		toolDaoTemplate.insert("daoLiang.insert",parameter);
	    }else{
	    	parameter.putAll(daoLiangConfig);
	    	toolDaoTemplate.update("daoLiang.update",parameter);
	    }
		 return toolDaoTemplate.selectOne(statement,parameter);
	 }
}
