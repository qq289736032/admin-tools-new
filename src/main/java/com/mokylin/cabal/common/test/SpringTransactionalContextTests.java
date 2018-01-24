package com.mokylin.cabal.common.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Spring 单元测试基类
 * @author 稻草鸟人
 * @version 2014-05-15
 */
@ContextConfiguration(locations = {"classpath:spring-context.xml","classpath:spring-context-shiro.xml","classpath:spring-context-mybatis.xml","classpath:spring-mvc.xml"})
public class SpringTransactionalContextTests extends AbstractTransactionalJUnit4SpringContextTests {

	protected DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
	
}
