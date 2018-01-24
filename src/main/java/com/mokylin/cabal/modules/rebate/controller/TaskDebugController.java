package com.mokylin.cabal.modules.rebate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.rebate.service.RebateRechargeService;
import com.mokylin.cabal.modules.welfare.service.ReturnResourceService;

@Controller
@RequestMapping(value = "${adminPath}/task/debug")
public class TaskDebugController extends BaseController{
	@Autowired
	private RebateRechargeService rebateRechargeService;
	@Autowired
	private ReturnResourceService returnResourceService;
	 /**
     * 充值返回资源
     * @return
     */
    @RequestMapping(value = "returnResource")
    @ResponseBody
    public Result returnResource() {
    	
    	logger.info("每天0点开始计算大R充值返利");
    	rebateRechargeService.calculationRebate();
    	logger.info("结算大R返利成功");
    	logger.info("每天0点开始计算大R累计充值返回资源");
    	returnResourceService.calculation();
    	logger.info("每天0点开始计算大R累计充值返回资源");
    	return new Result(true);
    }
}
