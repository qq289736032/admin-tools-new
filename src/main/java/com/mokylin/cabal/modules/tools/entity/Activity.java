package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

/**
 * 作者: 日期: 2014/11/6 15:40 项目: cabal-tools
 */
public class Activity extends MybatisBaseBean {
	private static final long serialVersionUID = -541576818287671637L;

	private int isGlobal;// 是否全服方式
	private String serverIds;
	private String name;
	private String md5;
	private String activityName;
	private String activityDesc;
	private String originalFile;

	public int getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(int isGlobal) {
		this.isGlobal = isGlobal;
	}

	public String getServerIds() {
		return serverIds;
	}

	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public boolean isGlobal() {
		return this.isGlobal == 1 ? true : false;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getOriginalFile() {
		return originalFile;
	}

	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}

}
