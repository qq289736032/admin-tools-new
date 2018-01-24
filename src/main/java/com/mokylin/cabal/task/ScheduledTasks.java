package com.mokylin.cabal.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/23 11:58
 * 项目: admin-task
 */

@EnableScheduling
@Component
public class ScheduledTasks {

    private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class.getName());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Scheduled(fixedRate = 5000000)
    //@Scheduled(cron = "*/10 * * * * *")
    public void reportCurrentTime() {
        logger.info("The time is now " + dateFormat.format(new Date()));
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }

}
