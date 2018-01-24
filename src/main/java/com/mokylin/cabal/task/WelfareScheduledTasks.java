package com.mokylin.cabal.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mokylin.cabal.modules.welfare.service.WelfareNumService;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/23 11:58
 * 项目: admin-task
 */

@EnableScheduling
@Component
public class WelfareScheduledTasks {
	@Autowired
	private WelfareNumService welfareNumService;

    private Logger logger = LoggerFactory.getLogger(WelfareScheduledTasks.class.getName());

    //@Scheduled(fixedRate = 5000000)
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateStatus() {
    	
    	logger.info("每天0点开始执行福利号5天未登陆封停操作");
    	welfareNumService.sysUpdateStatus();
    	logger.info("每天0点开始执行福利号5天未登陆封停操作完成");
    }

}
