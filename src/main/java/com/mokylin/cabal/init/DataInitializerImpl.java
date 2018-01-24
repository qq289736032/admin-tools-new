package com.mokylin.cabal.init;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MyJdbcTemplate;
import com.mokylin.cabal.common.utils.ClassUtil;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.common.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/3/18 14:58
 * 项目: admin-tools
 * <p/>
 * 菜单，数据字段等数据初始化
 */
public class DataInitializerImpl implements AdminInitializer {

    private MyJdbcTemplate jdbcTemplate = SpringContextHolder.getBean("jdbcTemplate");

    @Override
    public void init() {

        logger.info("--------------菜单初始化开始----------------");

       /*   File sqlFile = new File(ClassUtil.getClassPath() + "sql/sys_menu.sql");
		jdbcTemplate.execute("TRUNCATE TABLE sys_menu");
		List<String> sqlList = null;
		sqlList = FileUtils.readLines(sqlFile, "utf-8");
         jdbcTemplate.batchUpdate(sqlList.toArray(new String[sqlList.size()]));*/


		logger.info("--------------菜单初始化结束----------------");


		logger.info("--------------字典初始化开始----------------");
		/*jdbcTemplate.execute("TRUNCATE TABLE sys_dict");
		sqlFile = new File(ClassUtil.getClassPath() + "sql/sys_dict.sql");
		sqlList = FileUtils.readLines(sqlFile, "utf-8");

		jdbcTemplate.batchUpdate(sqlList.toArray(new String[sqlList.size()]));*/

		logger.info("--------------字典初始化结束----------------");


		if(Global.isDemoMode()) {
		    // 注意：此处不能随意为true，否则当前系统中的角色和账号都将被清空
		    logger.info("--------------当前模式为演示模式----------------");
		    logger.info("--------------角色资源初始化开始----------------");
         /*     sqlFile = new File(ClassUtil.getClassPath() + "sql/sys_user.sql");
		    sqlList = FileUtils.readLines(sqlFile, "utf-8");
		    for (String sql : sqlList) {
		        if (StringUtils.isNotBlank(sql) && StringUtils.isNotEmpty(sql)) {
		            jdbcTemplate.execute(sql);
		        }
		    }*/
		    logger.info("--------------角色资源初始化结束----------------");
		}
    }

    @Override
    public void destroy() {

    }
}
