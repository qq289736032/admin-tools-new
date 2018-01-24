package com.mokylin.cabal.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/13 17:58
 * 项目: admin-tools
 */
public interface AdminInitializer {

    Logger logger = LoggerFactory.getLogger(AdminInitializer.class.getName());

    void init();

    void destroy();
}
