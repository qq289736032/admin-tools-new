package com.mokylin.cabal.common.redis;

import java.io.Serializable;

public class ConfigClient implements   Serializable {
	private static final long serialVersionUID = -1321721715111153659L;
    private   String   platformId;
    private   String   name ;
    private   String  serverId;
    private   String  url;
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ConfigClient() {
	}
    

}
