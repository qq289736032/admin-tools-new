package com.mokylin.cabal.modules.global.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.entity.Dict;
import com.mokylin.cabal.modules.sys.utils.DictUtils;

/**
 * 职业分布
 * @author Administrator
 * 
 */

@Controller
@RequestMapping(value = "${adminPath}/global/jobDistribution")
public class JobDistributionController extends BaseController {

	@RequestMapping(value = {"list", ""})
	public String levelDistribution(HttpServletRequest request, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认当前时间
		if(MapUtils.getString(parameter,"createDate") == null) {
			parameter.put("createDate", DateUtils.getDate());
		}
		parameter.put("startDate",MapUtils.getString(parameter, "createDate").replace("-", "") );
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
//		model.addAttribute("vipLevelMap", vipLevelMap());

        model.addAttribute("jobList", globalDaoTemplate.selectList("jobDistribution.jobDistribution", parameter));	
        
        List<Dict> jobTypeList = DictUtils.getDictList("job_type");
        
        // 职业等级分布
        List<Map<String, Object>> jobLevelList = new ArrayList<>();
        // 最大等级
        int maxLevel = 0;
        Map<String, String> maxLevelMap = globalDaoTemplate.selectOne("jobDistribution.jobLevelMaxLevel", parameter);
       
        
        if (maxLevelMap != null) {
        	maxLevel = MapUtils.getIntValue(maxLevelMap, "maxLevel");
        	jobLevelList = new ArrayList<>(maxLevel);
        	
        	List<Map<String, Object>> list = globalDaoTemplate.selectList("jobDistribution.jobLevelDistribution", parameter);
        	
        	int[] totalNum = new int[jobTypeList.size()];
        	
            // 从1到最高等级的
        	for (int i = 1; i <= maxLevel; i++) {
        		Map<String, Object> map = new HashMap<>();
        		map.put("level", i);
        		int[] numList = new int[jobTypeList.size()];
        		
        		for (Map<String, Object> jobLevelNumMap : list) {
        			if (MapUtils.getInteger(jobLevelNumMap, "level") != i) {
        				continue;
        			}
        			int jobIndex = 0;
        			for (Dict jobDict : jobTypeList) {
        				String job = jobDict.getValue();
        				if (MapUtils.getString(jobLevelNumMap, "job").equals(job)) {
        					numList[jobIndex] = MapUtils.getInteger(jobLevelNumMap, "num");
        					totalNum[jobIndex] += numList[jobIndex];
        				}
        				jobIndex++;
        			}
        		}
        		map.put("numList", numList);
        		jobLevelList.add(map);
        	}
        	// 总计 
        	model.addAttribute("totalLevelNumList", totalNum);
        }
        
        model.addAttribute("jobLevelList", jobLevelList);
        
        // 职业vip分布
        List<Map<String, Object>> jobVipList = new ArrayList<>();
        // 最大
        int maxVip = 0;
        Map<String, String> maxVipMap = globalDaoTemplate.selectOne("jobDistribution.jobVipMaxLevel", parameter);
        
        if (maxVipMap != null) {
        	maxVip = MapUtils.getIntValue(maxVipMap, "maxLevel");
        	
        	List<Map<String, Object>> jobViplist = globalDaoTemplate.selectList("jobDistribution.jobVipDistribution", parameter);
        	
        	int[] totalNum = new int[jobTypeList.size()];
        	
            // 从1到最高等级的
        	for (int i = -1; i <= maxVip; i++) {
        		Map<String, Object> map = new HashMap<>();
        		map.put("vip_level", i);
//        		if (i == 0) {
//        			map.put("vip_level", "＜V1");
//        		} else {
//        			map.put("vip_level", vipLevelMap().get(i));
//        		}
        		int[] numList = new int[jobTypeList.size()];
        		
        		for (Map<String, Object> jobVipNumMap : jobViplist) {
        			if (MapUtils.getInteger(jobVipNumMap, "vip_level") != i) {
        				continue;
        			}
        			int jobIndex = 0;
        			for (Dict jobDict : jobTypeList) {
        				String job = jobDict.getValue();
        				if (MapUtils.getString(jobVipNumMap, "job").equals(job)) {
        					numList[jobIndex] = MapUtils.getInteger(jobVipNumMap, "num");
        					totalNum[jobIndex] += numList[jobIndex];
        				}
        				jobIndex++;
        			}
        		}
        		map.put("numList", numList);
        		jobVipList.add(map);
        	}
        	// 总计 
        	model.addAttribute("totalVipNumList", totalNum);
        }
        
        model.addAttribute("jobVipList", jobVipList);
        
        // 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/jobDistribution";
	}

}
