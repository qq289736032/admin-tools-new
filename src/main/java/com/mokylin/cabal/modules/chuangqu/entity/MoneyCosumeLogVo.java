package com.mokylin.cabal.modules.chuangqu.entity;

public class MoneyCosumeLogVo {
	private String sid;
	private String platform;
	private String uid;	
	private String username;
	private String role_name;
	private String role_id;	
	private String update_at;
	private String type;//0金币	2绑元（活动赠送货币，有游戏叫绑定钻石、绑定点券等）3元宝 （游戏充值获得的货币，有的游戏叫钻石、点券等）
	private String num; // 变化值
	private String before_num; // 变化前
	private String after_num; // 变化后
	private String event;// 事件 - 比如出售
	

	

	
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBefore_num() {
		return before_num;
	}

	public void setBefore_num(String before_num) {
		this.before_num = before_num;
	}

	public String getAfter_num() {
		return after_num;
	}

	public void setAfter_num(String after_num) {
		this.after_num = after_num;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
}
