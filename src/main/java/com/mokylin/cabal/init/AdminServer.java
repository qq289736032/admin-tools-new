package com.mokylin.cabal.init;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mokylin.cabal.common.game.api.impl.AbstractGameOperations;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.support.ClassHelper;
import com.mokylin.cabal.common.utils.SpringContextHolder;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/13 18:08
 * 项目: admin-tools
 */
@WebListener
public class AdminServer implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(AdminServer.class);

    private Set<AdminInitializer> initObjSet = new HashSet<>();
    
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("-----容器启动-----");
        List<Class<?>> classes = ClassHelper.getClassListBySuper(AdminInitializer.class);
        for(Class<?> initClass : classes){
            try {
                AdminInitializer initializer = (AdminInitializer) initClass.newInstance();
                initializer.init();
                
                initObjSet.add(initializer);
            } catch (Exception e) {
                logger.error("",e);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    	//销毁对象
    	for( AdminInitializer initializer : initObjSet ){
    		initializer.destroy();
    	}
    	
    	//销毁Manager订阅
    	RedisManager redisManager = SpringContextHolder.getApplicationContext().getBean(RedisManager.class);
    	if( redisManager != null ){
    		redisManager.destory();
    	}
    	
    	//销毁Executors
    	Map<String, AbstractGameOperations> executorBeans = SpringContextHolder.getApplicationContext().getBeansOfType(AbstractGameOperations.class);
    	for( Map.Entry<String, AbstractGameOperations> entry : executorBeans.entrySet() ){
    		entry.getValue().destroy();
    	}
    	
        logger.info("-----容器已销毁-----");
    }
}
