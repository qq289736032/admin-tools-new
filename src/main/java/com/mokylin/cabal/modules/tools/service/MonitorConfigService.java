package com.mokylin.cabal.modules.tools.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;


@Service
public class MonitorConfigService extends BaseService {
	 @Resource
	 protected ToolDaoTemplate toolDaoTemplate;

	 /**
	 * 从monitor_Config表中查询配置的预警参数，如果无值，则将预设的值放到数据库中，然后返回改条记录
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public Map<String,Object> selectOne(String statement,MybatisParameter parameter){
		 Map<String,Object> monitorConfig =toolDaoTemplate.selectOne(statement);
		 if(null==monitorConfig){
    		parameter.put("warnLogin", 3);
    		parameter.put("warnCharge", 3);
    		toolDaoTemplate.insert("monitorConfig.insert",parameter);
	    }
		 return toolDaoTemplate.selectOne(statement);
	 }
}
