package com.mokylin.cabal.init;

import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/13 15:55
 * 项目: admin-tools
 * 道具初始化类
 */
public class GoodsInitializerImpl implements AdminInitializer {

    private static final Logger logger = LoggerFactory.getLogger(GoodsInitializerImpl.class);

    private RedisManager redisManager = SpringContextHolder.getBean("redisManager");

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }
}
