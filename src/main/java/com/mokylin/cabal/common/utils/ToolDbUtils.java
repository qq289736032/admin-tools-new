package com.mokylin.cabal.common.utils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.mokylin.cabal.common.config.Global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * jdbc连接tool数据库
 * @author donghui
 */
public class ToolDbUtils {  
	private static final Logger log = LoggerFactory.getLogger(ToolDbUtils.class);
	

    public static Connection getConnection() {
//    	String url = applicationConfig.getString("jdbc.url");
//		String name = applicationConfig.getString("jdbc.username");
//		String pwd = applicationConfig.getString("jdbc.password");
    	String url = Global.getConfig("jdbc.url");
    	String name = Global.getConfig("jdbc.username");
    	String pwd = Global.getConfig("jdbc.password");
		String driver = "com.mysql.jdbc.Driver";
        Connection conn = null;  
        DbUtils.loadDriver(driver);  
        try {  
            conn = DriverManager.getConnection(url, name, pwd);  
        } catch (SQLException e) {
        	log.error(null, e);
        }
        return conn;  
    }
    
    public static List<Map> selectList(String sql) {
    	Connection conn = getConnection();  
    	QueryRunner qr = new QueryRunner();
    	List<Map> results = null;
    	try {
    		results = (List)qr.query(conn,sql,  new MapListHandler());  
    	} catch (SQLException e) {  
    		e.printStackTrace();  
    	} finally {  
    		DbUtils.closeQuietly(conn);  
    	} 
    	return results;
    }
    
    public static int update(String sql) throws SQLException {
        Connection conn = getConnection();  
        QueryRunner qr = new QueryRunner();
        int num = 0;
        try {
        	num = qr.update(conn, sql);  
        }  catch (SQLException e) {  
    		e.printStackTrace();  
    	} finally {  
            DbUtils.closeQuietly(conn);  
        } 
        return num;
    }
    
    public static Object selectObject(String sql) {
    	if (ToolDbUtils.selectList(sql).size() > 0) {
    		Map map = ToolDbUtils.selectList(sql).get(0);
    		return map.values().iterator().next();
		}
    	return null;
    }
    
    public static Map selectMap(String sql) {
    	List<Map> list = ToolDbUtils.selectList(sql);
    	if (CollectionUtils.isEmpty(list)) {
			return new HashMap();
		}
    	return list.get(0);
    }  
  
}  
