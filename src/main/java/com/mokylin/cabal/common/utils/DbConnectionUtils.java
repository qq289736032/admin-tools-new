package com.mokylin.cabal.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.util.concurrent.*;

public class DbConnectionUtils {
	private static final Logger log = LoggerFactory.getLogger(DbConnectionUtils.class);
	
	private static ExecutorService executorService = Executors.newCachedThreadPool(new AdminDefaultThreadFactory("db-connection"));  
	/**
	 * 测试jdbc连接
	 * @param gameServerDbUrl 格式:192.168.1.3:3306/dntg_server_release;username;pwd
	 */
	public static boolean testConection(String serverId, String gameServerDbUrl){
		String[] array = StringUtils.split(gameServerDbUrl, ";");
		if (ArrayUtils.isEmpty(array)) {
			return false;
		}
		String url = "jdbc:mysql://" + array[0] + "?characterEncoding=utf8";
		String userName=array[1];
		String pwd= array[2];
		return DbConnectionUtils.testConection(serverId, url, userName, pwd);
	}
	
	/**
	 * 用线程超时的方法测试连接
	 */
	public static boolean testConection(String serverId, final String url, final String username,final String pwd){
		Future<Boolean> future = executorService.submit(new Callable<Boolean>() {
			 @Override
			 public Boolean call() throws Exception {
				Class.forName("com.mysql.jdbc.Driver");
				DriverManager.getConnection(url , username , pwd);
				return Boolean.TRUE;
			 }  
		 });
		 try {
			 future.get(5000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			future.cancel(true);
			log.error("url:{},用户名:{},密码:{} 服务器id:{} 异常信息:{}", url, username, pwd, serverId, ExceptionUtils.getMessage(e));
			return false;
		}
		 log.info("数据库连接成功 url:{},用户名:{},密码:{} 服务器id:{} ", url, username, pwd, serverId);
		return true;
	}
	
	/**
	 * 测试游戏数据服务器连接
	 */
	public static boolean testGameServer(String serverId){
		return DbConnectionUtils.testServer(serverId, "game_db_url");
	}
	
	/**
	 * 测试游戏log服务器连接
	 */
	public static boolean testLogServer(String serverId){
		return DbConnectionUtils.testServer(serverId, "log_db_url");
	}
	
	public static boolean testServer(String serverId, String dbUrlFieldName){
		String sql = String.format("select %s from game_server WHERE server_id='%s' limit 1", dbUrlFieldName, serverId);
		String dbUrl = (String)ToolDbUtils.selectObject(sql);
		if (StringUtils.isBlank(dbUrl)) {
			log.error("服务器Id:[{}]的dbUrl为空", serverId);
			return false;
		}
		return DbConnectionUtils.testConection(serverId, dbUrl);
	}
	
}
