package com.mokylin.cabal.init;

import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/13 15:50
 * 项目: admin-tools
 * 日志初始化~设置金币限制~当大于某个值时才需要记录日志
 */
public class LogInitializerImpl implements AdminInitializer {

    private static final Logger logger = LoggerFactory.getLogger(LogInitializerImpl.class);
    private RedisManager redisManager = SpringContextHolder.getBean("redisManager");

    @Override
    public void init() {
        logger.info("---日志金币限制初始化开始---");
        String jinbiLimit = redisManager.getJinbiLimit();
        if (StringUtils.isEmpty(jinbiLimit) || StringUtils.isBlank(jinbiLimit)) {
            redisManager.addJinbiLogLimit(ConfigConstants.JIN_BI_LIMIT);    //redis中不存在则默认为10万
        }
        logger.info("---日志金币限制初始化结束---");
    }

    @Override
    public void destroy() {

    }

}
