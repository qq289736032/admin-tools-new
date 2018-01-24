package com.mokylin.cabal.modules.global.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.GlobalDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;

@Service
@Transactional(readOnly = true)
public class DashBoardService  extends BaseService{
	 @Resource
	  protected GlobalDaoTemplate globalDaoTemplate; 
	
	public   List<Map<String, Object>> getPccu(HashMap<String, String> parameter ){
        List<Map<String, Object>> list = globalDaoTemplate.selectList("rizonghe.selectPccu",parameter);
		return list;
	}
	
	public   List<Map<String, Object>> getMoneyPccu(HashMap<String, String> parameter ){
        List<Map<String, Object>> list = globalDaoTemplate.selectList("monthlyIntegrated.selectPccu",parameter);
		return list;
	}
	public   List<Map<String, Object>> selectMonthPccu(HashMap<String, String> parameter ){
        List<Map<String, Object>> list = globalDaoTemplate.selectList("monthlyIntegrated.selectMonthPccu",parameter);
		return list;
	}
	//添加pccu
	@Transactional(readOnly = false)
	public   void  addPccu( List<Map<String, Object>> list,List<Map<String, Object>> targetPCU ){
		for (Map<String, Object> map : list) {
			for (Map<String, Object> innerMap : targetPCU) {
				for (String key : innerMap.keySet()) {
					if(map.containsKey("log_day")){
						//rizonghe里面key-log_day
						if (innerMap.get(key).toString().equals(map.get("log_day").toString())) {
							map.put("pccu", innerMap.get("pccu"));
							map.put("acu", innerMap.get("acu"));
						}
					}
					//monthlyIntegrated里面key-log_month
					if(map.containsKey("log_month")){
						if (innerMap.get(key).toString().equals(map.get("log_month").toString())) {
							map.put("pccu", innerMap.get("pccu"));
							map.put("acu", innerMap.get("acu"));
						}
					}
				}
			}
		}
	}
	
	

}
