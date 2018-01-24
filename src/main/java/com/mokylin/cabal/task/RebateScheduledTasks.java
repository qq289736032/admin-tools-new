package com.mokylin.cabal.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mokylin.cabal.modules.rebate.service.RebateRechargeService;
import com.mokylin.cabal.modules.welfare.service.ReturnResourceService;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/23 11:58
 * 项目: admin-task
 */

@EnableScheduling
@Component
public class RebateScheduledTasks {
	@Autowired
	private RebateRechargeService rebateRechargeService;
	@Autowired
	private ReturnResourceService returnResourceService;

    private Logger logger = LoggerFactory.getLogger(RebateScheduledTasks.class.getName());

   
    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(fixedRate = 5000000)
    public void calculationRebate() {
    	
    	logger.info("每天0点开始计算大R充值返利");
    	rebateRechargeService.calculationRebate();
    	logger.info("结算大R返利成功");
    	logger.info("每天0点开始计算大R累计充值返回资源");
    	returnResourceService.calculation();
    	logger.info("每天0点开始计算大R累计充值返回资源");
    }

}
