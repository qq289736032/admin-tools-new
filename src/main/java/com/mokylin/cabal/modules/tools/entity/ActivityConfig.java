package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

/**
 * 作者: 日期: 2014/11/6 15:40 项目: cabal-tools
 */
public class ActivityConfig extends MybatisBaseBean {
	private static final long serialVersionUID = -541576818287671637L;

	private String name;
	private String alias;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
