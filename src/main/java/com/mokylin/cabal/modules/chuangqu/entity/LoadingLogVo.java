package com.mokylin.cabal.modules.chuangqu.entity;

public class LoadingLogVo {
	private String sid;
	private String platform;
	private String type;//1：平台注册数 	2：进入游戏数 3：达创角页数 	4：创建成功数
	private String openid;
	private String desc;
	
	
	public String getSid() {
		return sid;
	}
	
	public void setSid(String sid) {
		this.sid = sid;
	}
	

	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
