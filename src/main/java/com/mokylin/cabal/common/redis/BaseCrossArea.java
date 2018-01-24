package com.mokylin.cabal.common.redis;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public abstract class BaseCrossArea {
	/**
	 * 战区名称
	 */
	private String crossAreaName;
	private Server crossServer;
	private int crossType;// 跨服类型
	private String crossAreaId;
	public BaseCrossArea() {
	}

	public BaseCrossArea(String crossAreaName, Server crossServer) {
		this.crossAreaName = crossAreaName;
		this.crossServer = crossServer;
	}

	public String getCrossAreaName() {
		return crossAreaName;
	}

	public void setCrossAreaName(String crossAreaName) {
		this.crossAreaName = crossAreaName;
	}

	public Server getCrossServer() {
		return crossServer;
	}

	public void setCrossServer(Server crossServer) {
		this.crossServer = crossServer;
	}

	/**
	 * 获取战场下的所有服务器
	 * 
	 * @return
	 */
	public abstract List<Server> getAllServers();
	
	public abstract void setAllServers(List<Server> servers);

	/**
	 * 删除所有的server
	 * 
	 * @return
	 */
	public abstract void removeAllServers();

	// /**
	// * 战场类型
	// */
	// public abstract int crossType();

	/**
	 * 判断该服务器是否已经存在此战区中
	 * 
	 * @param server
	 * @return true 存在
	 */
	public abstract boolean isExistServer(Server server);

	public String getServerName() {
		return "国家1,国家2,国家3,国家4";
	}

	public abstract int getCrossType();

	/**
	 * 获取该战区在缓存中/redis中的ID
	 * @return
	 */
	public abstract String getCrossAreaId();
	
	public void setCrossType(int crossType) {
		this.crossType = crossType;
	}
	
	public String toJson(){
		String json = JSONObject.toJSONString(this);
		return json;
	}

	public void setCrossAreaId(String crossAreaId) {
		this.crossAreaId = crossAreaId;
	}
}
