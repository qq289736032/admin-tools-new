package com.mokylin.cabal.modules.global.web;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/global/coinProduceConsume")
public class CoinProduceConsumeController extends BaseController{
    @RequestMapping( value =  "coinProduceConsumeReport")
    public String monthRemainerReport(HttpServletRequest request,HttpServletResponse response, Model model){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	//setServerIdList(parameter);
    	setDefaultTimeRange(parameter);
    	Page<Map<String,Object>> coinProduceConsume =globalPaging(request, response, "rizonghe.findCoinProduceConsumeReport");
    	model.addAttribute("coinProduceConsume", coinProduceConsume);
    	// 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
    	return "modules/global/coinProduceConsumeReport";
    }
}
