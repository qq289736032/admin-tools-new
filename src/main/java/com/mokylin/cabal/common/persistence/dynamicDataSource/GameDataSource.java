package com.mokylin.cabal.common.persistence.dynamicDataSource;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.DbConnectionUtils;
import com.mokylin.cabal.common.utils.ToolDbUtils;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 游戏库的自定义多数据源
 * @author donghui
 */
public class GameDataSource extends AbstractRoutingDataSource {
	private final Logger log = LoggerFactory.getLogger(getClass()); 
	
	private Map<String,DruidDataSource> dataSources = new ConcurrentHashMap<String, DruidDataSource>();
	private int maxActive = DruidAbstractDataSource.DEFAULT_MAX_ACTIVE_SIZE;
    private int initialSize = DruidAbstractDataSource.DEFAULT_INITIAL_SIZE;
    private int maxWait = DruidAbstractDataSource.DEFAULT_MAX_WAIT;
    private int minIdle = DruidAbstractDataSource.DEFAULT_MIN_IDLE;
    
    private static final String PORT = Global.getConfig("game.jdbc.port");
    private static final String USERNAME = Global.getConfig("game.jdbc.username");
    private static final String PASSWORD = Global.getConfig("game.jdbc.password");
    private static final String DBNAME = Global.getConfig("game.jdbc.dbname");
    
	public void init(){}
	
	/**
	 * 加载一个服务器的数据源
	 */
	public DataSource initDs(String serverId){
		//张磊要求
		if(Integer.parseInt(serverId) < 0){
			serverId = "0";
		}
		//String innerIp = (String) ToolDbUtils.selectObject("SELECT inner_ip FROM game_area WHERE world_id = '" + serverId + "'");
		Server server = RedisUtils.getActualGameServer(serverId);	//防止合服找不到服务器
		serverId = String.valueOf(server.getWorldId());

		// 192.168.1.27:3306/zhanshen;linyu;123456
		String url = "jdbc:mysql://" + server.getInnerIp() + ":" + PORT + "/" + DBNAME + serverId + "?characterEncoding=utf8";
//		//FIXME jassi 临时写成法
//		String url = "jdbc:mysql://10.66.210.212:" + PORT + "/" + DBNAME + serverId + "?characterEncoding=utf8";
		
		if (DbConnectionUtils.testConection(serverId, url, USERNAME, PASSWORD) == false) {
			log.error("加载一个服务器的数据源失败,url:"+url+",username:"+USERNAME+",password:"+PASSWORD);
			return null;
		}
		DruidDataSource ds = new DruidDataSource();
		ds.setMaxActive(maxActive);
		ds.setInitialSize(initialSize);
		ds.setMaxWait(maxWait);
		ds.setMinIdle(minIdle);
		ds.setUrl(url);  
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		dataSources.put(serverId, ds);
		return ds;
	}
	
//	/**
//	 * 加载一个服务器的数据源
//	 */
//	public DataSource initDs(String serverId){
//		String gameDbUrl = (String) ToolDbUtils.selectObject("SELECT game_db_url FROM game_server WHERE server_id = '" + serverId + "'");
//		String[] dbUrlArray = StringUtils.split(gameDbUrl, ";");
//		if (dbUrlArray == null || dbUrlArray.length != 3) {
//			log.warn("serverId:{}的url格式错误:{}", serverId, gameDbUrl);
//			return null;
//		}
//		String url = "jdbc:mysql://" + dbUrlArray[0] + "?characterEncoding=utf8";
//		String username = dbUrlArray[1];
//		String pwd = dbUrlArray[2];
//		if (DbConnectionUtils.testConection(serverId, url, username, pwd) == false) {
//			return null;
//		}
//		DruidDataSource ds = new DruidDataSource();
//		ds.setMaxActive(maxActive);
//		ds.setInitialSize(initialSize);
//		ds.setMaxWait(maxWait);
//		ds.setMinIdle(minIdle);
//		ds.setUrl(url);  
//		ds.setUsername(username);
//		ds.setPassword(pwd);
//		dataSources.put(serverId, ds);
//		return ds;
//	}
	
	@Override
	public Connection getConnection() throws SQLException {
		return this.getConnection(null, null);
	}
	
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		DruidDataSource ds = (DruidDataSource)determineTargetDataSource();
		if (ds == null) {
			String serverId = (String)determineCurrentLookupKey();
			throw new SQLException("数据源初始化失败 serverId:[" + serverId +"]");
		}
		if (username == null && password == null) {
			return ds.getConnection();
		} else{
			return ds.getConnection(username, password);
		}
	}

	@Override
	protected DataSource determineTargetDataSource() {
		String serverId = (String)determineCurrentLookupKey();
		DataSource dataSource = dataSources.get(serverId);
		if (StringUtils.isNotBlank(serverId) && dataSource == null) {
			return initDs(serverId);
		}
		return dataSource;
	}
	
	/**
	 * 在进行DAO操作前，通过上下文环境变量，获得数据源的key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		Server server = RedisUtils.getActualGameServer(LookupContext.getCurrentServerId());
		return String.valueOf(server.getWorldId());
	}

	public int getMaxActive() {return maxActive;}
	public void setMaxActive(int maxActive) {this.maxActive = maxActive;}

	public int getInitialSize() {return initialSize;}
	public void setInitialSize(int initialSize) {this.initialSize = initialSize;}

	public int getMaxWait() {return maxWait;}
	public void setMaxWait(int maxWait) {this.maxWait = maxWait;}

	public int getMinIdle() {return minIdle;}
	public void setMinIdle(int minIdle) {this.minIdle = minIdle;}
}
