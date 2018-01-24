package com.mokylin.cabal.modules.welfare.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.Collections3;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.entity.User;
import com.mokylin.cabal.modules.sys.service.SystemService;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import com.mokylin.cabal.modules.welfare.entity.PlatWelfareConfig;
import com.mokylin.cabal.modules.welfare.entity.WelfareCommonTemp;
import com.mokylin.cabal.modules.welfare.entity.WelfareSettingLog;
import com.mokylin.cabal.modules.welfare.service.WelfareTempService;

@Controller
@RequestMapping(value = "${adminPath}/welfare/common")
public class WelfareCommonTempController extends BaseController{
	private static String INIT_KEY = "INIT_COMMON_CONFIG_KEY";
	@Autowired
	WelfareTempService welfareService;
	
    @Autowired
    private SystemService systemService;
	
    @RequestMapping(value = "/temp")
    public String temp() {
        
        return "modules/welfare/commonTemp";
    }
    
    /**
     * 获取福利通用模板数据
     * @return
     */
    @RequestMapping(value = "/tempData")
    @ResponseBody
    public List<WelfareCommonTemp> getTempData() {
    	
        return welfareDaoTemplate.selectList("welfareCommonTemp.selectAll");
    }
    
    /**
     * 修改福利通用模板数据
     * @param temp
     * @return
     */
    @RequestMapping(value = "/saveTempData")
    @ResponseBody
    public Result saveTempData(WelfareCommonTemp temp) {
    	Map<String,Object> paramMap = Collections3.transBean2Map(temp);
    	User user = UserUtils.getUser();
    	paramMap.put("loginName", user.getLoginName());
        welfareDaoTemplate.update("welfareCommonTemp.update", paramMap);
        return new Result(true);
    }
    
    /**
     * 查询平台配置
     * @param config
     * @return
     */
    @RequestMapping(value = "/platData")
    @ResponseBody
    public List<PlatWelfareConfig> getPlatData(PlatWelfareConfig config) {
    	
        return welfareDaoTemplate.selectList("platWelfareConfig.condition",config);
    }
    
    /**
     * 修改平台配置
     * @param config
     * @return
     */
    @RequestMapping(value = "/updatePlatData")
    @ResponseBody
    public Result updatePlatData(PlatWelfareConfig config) {
    	
        return welfareService.updatePlatData(config);
    }
    
    /**
     * 修改平台配置状态
     * @param config
     * @return
     */
    @RequestMapping(value = "/updatePlatStatus")
    @ResponseBody
    public Result updatePlatStatus(PlatWelfareConfig config) {
    	
    	return welfareService.updatePlatStatus(config);
    }
    
    /**
     * 修改平台性质
     * @param config
     * @return
     */
    @RequestMapping(value = "/updatePlatNature")
    @ResponseBody
    public Result updatePlatNature(PlatWelfareConfig config) {
    	
    	return welfareService.updatePlatNature(config);
    }
    
    /**
     * 新增平台配置
     * @param plat 
     * @return
     */
    @RequestMapping(value = "/insertPlatData")
    @ResponseBody
    public Result insertPlatData(String key) {
    	if(!INIT_KEY.equals(key)){
    		return new Result(false);
    	}
    	List<GamePlatform> list = systemService.findAllGamePlatform();
    	for (GamePlatform plat : list) {
    		welfareService.insertPlatConfig(plat);
		}
    	return new Result(true);
    }
    
    /**
     * 查询福利配置日志
     * @param log
     * @return
     */
    @RequestMapping(value = "/settingLog")
    public String settingLog(WelfareSettingLog log) {
    	
    	return "modules/welfare/commonSettingLog";
    }
    
    /**
     * 查询福利配置日志
     * @param log
     * @param offset 
     * @param limit
     * @param startTime 
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/settingLogData")
    @ResponseBody
    public Map<String,Object> settingLogData(WelfareSettingLog log,int offset,int limit,Date startTime,Date endTime) {
    	Map<String,Object> map = new HashMap<String, Object>();
    	
    	Map<String,Object> param = Collections3.transBean2Map(log);
    	param.put("startTime", startTime);
    	param.put("endTime", endTime);
    	List<WelfareSettingLog> logs = welfareDaoTemplate
    			.selectList("welfareSettingLog.condition",param,new RowBounds(offset, limit));
    	int count = welfareDaoTemplate.selectOne("welfareSettingLog.count", param);
    	map.put("rows", logs);
    	map.put("total", count);
    	return map;
    }
}
