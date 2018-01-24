package com.mokylin.cabal.modules.chuangqu.entity;

public class ChuangquRequest {

	private String type;
	private long start_time;
	private long end_time;
	private int cursor;
	private int limit;
	private int gid;
	private int timestamp;
	private String sign;
	private int sid;
	private String log_date;//goods_flow_log_20160615 分日期的表会用到 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}

	public long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getLogDate() {
		return log_date;
	}

	public void setLogDate(String logDate) {
		this.log_date = logDate;
	}

}
