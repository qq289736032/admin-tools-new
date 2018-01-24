/**
 * Copyright &copy; 2014-2015 <a href="https://github.com/mokylin/cabal">cabal</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.mokylin.cabal.common.config;

import com.google.common.collect.Maps;
import com.mokylin.cabal.common.utils.PropertiesLoader;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 全局配置类
 * @author 稻草鸟人
 * @version 2014-03-23
 */
public class Global {
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	public static PropertiesLoader configFileLoader = new PropertiesLoader("properties/config-file.properties");

	public static PropertiesLoader commonLoader = new PropertiesLoader("properties/commons.properties");

	public static PropertiesLoader vipLoader = new PropertiesLoader("properties/vip.properties");

	public static PropertiesLoader profileLoader = new PropertiesLoader("properties/profile.properties");


	public static PropertiesLoader propertiesLoader = new PropertiesLoader("properties/application.properties");

	//public static final Integer MSG_REDIS_INDEX = propertiesLoader.getInteger("msg.redis.index");
	public static final Integer CROSS_REDIS_INDEX = propertiesLoader.getInteger("cross.redis.index");
	public static final String CROSS_REDIS_HOST = propertiesLoader.getProperty("cross.redis.host");
	public static final Integer CROSS_REDIS_PORT = propertiesLoader.getInteger("cross.redis.port");


	public static final Integer GAME_REDIS_INDEX = propertiesLoader.getInteger("game.redis.index");
	public static final Integer CHAT_REDIS_INDEX = propertiesLoader.getInteger("chat.redis.index");
	public static final Integer ROLE_REDIS_INDEX = propertiesLoader.getInteger("role.redis.index");
	public static final Integer REDIS_PORT =   propertiesLoader.getInteger("global.redis.port");
	//redis
    public static final String REDIS_HOST = propertiesLoader.getProperty("global.redis.host");

	public static final String ROLE_REDIS_HOST = propertiesLoader.getProperty("role.redis.host");
	
	public static final String REDIS_PASSWORD = propertiesLoader.getProperty("redis_password");

	public static final String BASE_PACKAGE = profileLoader.getProperty("admin.base_package");

	public static final String CHAT_SIGN_37 = "GA2LXU365t*2@r8N^*831@v(t@f!~s";	//37玩聊天推送key


	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}

	/////////////////////////////////////////////////////////
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}

	/**
	 * 获取CKFinder上传文件的根目录
	 * @return
	 */
	public static String getCkBaseDir() {
		String dir = getConfig("userfiles.basedir");
		Assert.hasText(dir, "配置文件里没有配置userfiles.basedir属性");
		if(!dir.endsWith("/")) {
			dir += "/";
		}
		return dir;
	}


    public static Map<String, Object> getConfigFileMap() {
        return PropertiesLoader.getMap(configFileLoader.getProperties());
    }

    public static Map<String, Object> getCommonMap() {
        return PropertiesLoader.getMap(commonLoader.getProperties());
    }
    
    public static Map<String, Object> getVipLevelMap(){
    	return PropertiesLoader.getMap(vipLoader.getProperties());
    }
    
    
    //用于配置页面显示配置文件类型
    public static String getCommon(String key){
    	return MapUtils.getString(getConfigFileMap(), key);
    }
}
