package com.mokylin.cabal.modules.chuangqu.entity;

public class LevelLogVo {
	private String sid;
	private String uid;	
	private String username;
	private String role_id;
	private String role_name;
	private String before_level;
	private String after_level;
	private String platform;
	private String update_at;
	
	
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
	public String getBefore_level() {
		return before_level;
	}
	public void setBefore_level(String before_level) {
		this.before_level = before_level;
	}
	public String getAfter_level() {
		return after_level;
	}
	public void setAfter_level(String after_level) {
		this.after_level = after_level;
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
}
