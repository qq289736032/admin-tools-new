package com.mokylin.cabal.modules.welfare.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.WelfareDaoTemplate;
import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import com.mokylin.cabal.modules.welfare.entity.PlatWelfareConfig;
import com.mokylin.cabal.modules.welfare.entity.SingleServerConfig;
import com.mokylin.cabal.modules.welfare.entity.WelfareCommonTemp;
import com.mokylin.cabal.modules.welfare.entity.WelfareSettingLog;

@Transactional(readOnly=true)
@Service
public class WelfareTempService extends BaseService{
    @Resource
    protected WelfareDaoTemplate welfareDaoTemplate;
    @Autowired
    SpecialConfigService specialConfigService;
    
    /**
     * 更新平台配置状态
     * @param config
     * @return
     */
    @Transactional(readOnly=false)
    public Result updatePlatStatus(PlatWelfareConfig config){
    	Map<String,Object> param = Collections3.transBean2Map(config);
    	param.put("updateBy", UserUtils.getUser().getLoginName());
    	welfareDaoTemplate.update("platWelfareConfig.updateStatus", param);
    	welfareDaoTemplate.insert("welfareSettingLog.insert", generateLog(config, WelfareSettingLog.EDIT_TYPE_EDIT,null));
    	return new Result(true);
    }
    
    /**
     * 更新平台配置数据
     * @param config
     * @return
     */
    @Transactional(readOnly=false)
    public Result updatePlatData(PlatWelfareConfig config){
    	Map<String,Object> paramMap = Collections3.transBean2Map(config);
    	paramMap.put("updateBy", UserUtils.getUser().getLoginName());
    	paramMap.put("ids", config.getIds());
    	welfareDaoTemplate.update("platWelfareConfig.updateData", paramMap);
    	List<PlatWelfareConfig> configs = welfareDaoTemplate.selectList("platWelfareConfig.condition", config);
    	List<String> pids = new ArrayList<String>();
    	List<WelfareSettingLog> lstLog = new ArrayList<WelfareSettingLog>();
    	for (PlatWelfareConfig plat : configs) {
    		//事务没提交，数据库值是更新之前的 重新赋值一遍
			plat.setAddTimeLimit(config.getAddTimeLimit());
			plat.setNewServiceGold(config.getNewServiceGold());
			plat.setRResourceAmount(config.getRResourceAmount());
			plat.setRResourceRatio(config.getRResourceRatio());
			plat.setSingleChargeRatio(config.getSingleChargeRatio());
			plat.setTopCharge(config.getTopCharge());
			plat.setTopGoldDay(config.getTopGoldDay());
			plat.setTopHoldGold(config.getTopHoldGold());
			plat.setTopInternalNumber(config.getTopInternalNumber());
			lstLog.add(generateLog(plat, WelfareSettingLog.EDIT_TYPE_EDIT,null));
			pids.add(plat.getPid());
		}
    	welfareDaoTemplate.batchInsert2("welfareSettingLog.batchInsert", lstLog);
    	if(pids.isEmpty()&&StringUtils.isNotBlank(config.getPid())){
    		pids.add(config.getPid());
    	}
    	paramMap = new HashMap<String, Object>();
    	paramMap.put("pids", pids.toArray());
    	paramMap.put("isInfluence", SingleServerConfig.IS_INFLUENCE_TRUE);
    	List<SingleServerConfig> serverConfigs = welfareDaoTemplate.selectList("singleServerConfig.selectAllByPid", paramMap);
    	List<String> ids = new ArrayList<String>();
    	List<String> serverIds = new ArrayList<String>();
    	for (SingleServerConfig singleServerConfig : serverConfigs) {
			ids.add(singleServerConfig.getId());
			serverIds.add(singleServerConfig.getServerId());
		}
    	if(!ids.isEmpty()){
    		specialConfigService.deleteServerConfig(ids.toArray(new String[serverConfigs.size()]), serverConfigs);
    	}
    	
    	//更新福利号特殊配置充值上限
    	specialConfigService.updateWelfareNumTopCharge(serverIds.toArray(), pids.toArray(), config.getTopCharge());
    	
    	return new Result(true);
    }
    
    /**
     * 更新平台性质
     * @param config
     * @return
     */
    @Transactional(readOnly=false)
    public Result updatePlatNature(PlatWelfareConfig config){
    	
    	Map<String,Object> param = Collections3.transBean2Map(config);
    	param.put("updateBy", UserUtils.getUser().getLoginName());
    	//根据pId更新所有需要更新的平台性质
    	welfareDaoTemplate.update("platWelfareConfig.updatePlatNature", param);
    	
    	List<PlatWelfareConfig> configs = welfareDaoTemplate.selectList("platWelfareConfig.condition", config);
    	List<WelfareSettingLog> lstLog = new ArrayList<WelfareSettingLog>();
    	for (PlatWelfareConfig plat : configs) {
			plat.setPlatNature(config.getPlatNature());
			lstLog.add(generateLog(plat,WelfareSettingLog.EDIT_TYPE_EDIT,null));
		}
    	welfareDaoTemplate.batchInsert2("welfareSettingLog.batchInsert", lstLog);
    	
    	return new Result(true);
    }
    
    /**
     * 生成setting_log对象
     * @param plat 平台配置
     * @param editType 编辑类型
     * @param createName 操作人
     * @return
     */
    private WelfareSettingLog generateLog(PlatWelfareConfig plat,int editType,String createName){
    	WelfareSettingLog log = new WelfareSettingLog();
		log.setId(IdGen.uuid());
		log.setAddTimeLimit(plat.getAddTimeLimit());
		log.setCreateBy(UserUtils.getUser().getId());
		log.setCreateName(StringUtils.isNotBlank(createName) ? createName:UserUtils.getUser().getLoginName());
		log.setEditType(editType);
		log.setNewServiceGold(plat.getNewServiceGold());
		log.setPid(plat.getPid());
		log.setPlatName(plat.getPlatName());
		log.setPlatNature(plat.getPlatNature());
		log.setRResourceAmount(plat.getRResourceAmount());
		log.setRResourceRatio(plat.getRResourceRatio());
		log.setSingleChargeRatio(plat.getSingleChargeRatio());
		log.setStatus(plat.getStatus());
		log.setTopCharge(plat.getTopCharge());
		log.setTopGoldDay(plat.getTopGoldDay());
		log.setTopHoldGold(plat.getTopHoldGold());
		log.setTopInternalNumber(plat.getTopInternalNumber());
		log.setUpdateBy(StringUtils.isNotBlank(createName) ? createName:UserUtils.getUser().getLoginName());
		log.setGoldPoolCategory(plat.getGoldPoolCategory());
		return log;
    }
    
    /**
     * 新增平台根据通用模板自动生成配置数据
     * @param config
     * @return
     */
    @Transactional(readOnly=false)
    public Result insertPlatConfig(GamePlatform plat){
    	if(isExist(plat.getPid(), PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND)){
    		return new Result(false);
    	}
    	List<WelfareSettingLog> lstLog = new ArrayList<WelfareSettingLog>();
    	WelfareCommonTemp temp = welfareDaoTemplate.selectOne("welfareCommonTemp.selectAll");
    	PlatWelfareConfig config = generatePaltConfig(temp, plat, IdGen.uuid(), PlatWelfareConfig.GOLD_POOL_CATEGORY_INDEPEND);
    	
    	//生成独代奖金池配置
    	welfareDaoTemplate.insert("platWelfareConfig.insert", Collections3.transBean2Map(config));
    	lstLog.add(generateLog(config,WelfareSettingLog.EDIT_TYPE_ADD,"模板自动读取"));
    	//生成平台奖金池配置
    	config.setId(IdGen.uuid());
    	config.setGoldPoolCategory(PlatWelfareConfig.GOLD_POOL_CATEGORY_PLAT);
    	welfareDaoTemplate.insert("platWelfareConfig.insert", Collections3.transBean2Map(config));
    	lstLog.add(generateLog(config,WelfareSettingLog.EDIT_TYPE_ADD,"模板自动读取"));
    	//插入日志
    	welfareDaoTemplate.batchInsert2("welfareSettingLog.batchInsert", lstLog);
    	
    	return new Result(true);
    }
    
    private boolean isExist(String pid,String goldPoolCategory){
    	Map<String,Object> param = new HashMap<String, Object>();
    	param.put("pid", pid);
    	param.put("goldPoolCategory", goldPoolCategory);
    	PlatWelfareConfig config = welfareDaoTemplate.selectOne("platWelfareConfig.selectOneByPid", param);
    	return config!=null;
    }
    
    /**
     * 生成plat_welfare_config对象
     * @param temp 模板配置
     * @param plat 平台对象
     * @param id 生成的uuid
     * @param category 奖金池分类
     * @return
     */
    private PlatWelfareConfig generatePaltConfig(WelfareCommonTemp temp,GamePlatform plat,String id,String category){
    	PlatWelfareConfig config = new PlatWelfareConfig();
    	config.setAddTimeLimit(temp.getAddTimeLimit());
    	config.setCreateBy(UserUtils.getUser().getId());
    	config.setCreateName(UserUtils.getUser().getLoginName());
    	config.setId(id);
    	config.setGoldPoolCategory(category);
    	config.setNewServiceGold(temp.getNewServiceGold());
    	config.setPid(plat.getPid());
    	config.setPlatName(plat.getName());
    	config.setPlatNature(temp.getPlatNature());
    	config.setRResourceAmount(temp.getRResourceAmount());
    	config.setRResourceRatio(temp.getRResourceRatio());
    	config.setSingleChargeRatio(temp.getSingleChargeRatio());
    	config.setStatus(PlatWelfareConfig.STATUS_NORMAL);
    	config.setTopCharge(temp.getTopCharge());
    	config.setTopGoldDay(temp.getTopGoldDay());
    	config.setTopHoldGold(temp.getTopHoldGold());
    	config.setTopInternalNumber(temp.getTopInternalNumber());
    	config.setUpdateBy(UserUtils.getUser().getLoginName());
    	return config;
    	
    }
  
}
