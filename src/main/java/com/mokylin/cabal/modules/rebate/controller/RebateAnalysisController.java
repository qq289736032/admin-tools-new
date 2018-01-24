package com.mokylin.cabal.modules.rebate.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.rebate.service.AnalysisService;

@Controller
@RequestMapping(value = "${adminPath}/rebate/analysis")
public class RebateAnalysisController extends BaseController{
	@Autowired
	private AnalysisService analysisService;
	
	/**
	 * 返利分析页面
	 * @return
	 */
    @RequestMapping(value = "/analysis")
    public String analysis() {
        
        return "modules/rebate/rebateAnalysis";
    }
    
	/**
	 * 返利分析数据
	 * @return
	 */
    @RequestMapping(value = "/analysisData")
    @ResponseBody
    public List<Map<String,Object>> analysisData(String goodsName,String goodsId,String pid,Date startTime,Date endTime) {
        
        return analysisService.analysisData( goodsName, goodsId, pid, startTime, endTime);
    }
}
