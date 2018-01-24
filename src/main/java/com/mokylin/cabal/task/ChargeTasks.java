package com.mokylin.cabal.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mokylin.cabal.modules.rebate.service.RebateRechargeService;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/23 11:58
 * 项目: admin-task
 */

@EnableScheduling
@Component
public class ChargeTasks {
	@Autowired
	private RebateRechargeService rebateRechargeService;
    private Logger logger = LoggerFactory.getLogger(ChargeTasks.class.getName());
    /** 两小时对比一次
     @Scheduled(fixedRate = 20000)
    public void compare() {
    	logger.info("开始对比充值数据");
    	rebateRechargeService.compare();
    	logger.info("对比完成");
    } */

}
