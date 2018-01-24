package com.mokylin.cabal.common.redis;


import com.alibaba.fastjson.annotation.JSONField;
import com.mokylin.cabal.common.utils.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

public class Server implements Serializable {

	private static final long serialVersionUID = 1875900293607911390L;

	@JSONField(name="serverId")
	private int worldId;//world_id
	@JSONField(name="platSid")
	private String worldName;//world_name
	private String name;//area_name
	private int id;//area_id
	private int type;//area_type
	private int subType;
	@JSONField(name="ipIn")
	private String innerIp;//ip
	@JSONField(name="ipOut")
	private String externalIp;//external_ip
	private int innerPort;//inner_port
	private int tcpPort;//tcp_port
	private int webPort;//web_port
	@JSONField(name="targetId")
	private int followerId;//follower_id
	private String key;// 服务器的公钥
	private int status;//status

	@JSONField(name="platform")
	private String platformId;//pid

	private Date openTime;
	
	private Date hefuTime;
	
	@JSONField(name="startTime")
	private Date updateTime;
	
	private String pName;

	private String serverVersion="";
	private String dataVersion="";

	public Server() {

	}

	public Date getHefuTime() {
		return hefuTime;
	}

	public void setHefuTime(Date hefuTime) {
		this.hefuTime = hefuTime;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public void setServerVersion(String serverVersion) {
		this.serverVersion = serverVersion;
	}

	public String getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(String dataVersion) {
		this.dataVersion = dataVersion;
	}

	public int getWorldId() {
		return worldId;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public void setWorldId(int worldId) {
		this.worldId = worldId;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
		this.name = worldName;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public int getWebPort() {
		return webPort;
	}

	public void setWebPort(int webPort) {
		this.webPort = webPort;
	}

	public int getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(int tcpPort) {
		this.tcpPort = tcpPort;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInnerPort() {
		return innerPort;
	}

	public void setInnerPort(int innerPort) {
		this.innerPort = innerPort;
	}

	public String getInnerIp() {
		return innerIp;
	}

	public void setInnerIp(String innerIp) {
		this.innerIp = innerIp;
	}

	public String getExternalIp() {
		return externalIp;
	}

	public void setExternalIp(String externalIp) {
		this.externalIp = externalIp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public boolean isValid(){

		return status != SystemConstant.SERVER_STATUS_STOPED;
	}


	public Object[] toAreaVO() {
		int areaId = id;
		String areaName = name;
		return new Object[] { worldId, name, worldId, name, };
	}

	/**
	 * 生成内部完整的链接
	 * @return
	 */
	public String createUrl(){
		return "http://"+innerIp+":"+webPort;
	}

	public String createGateUrl(){
		return "http://" + externalIp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((externalIp == null) ? 0 : externalIp.hashCode());
		result = prime * result + followerId;
		result = prime * result + ((innerIp == null) ? 0 : innerIp.hashCode());
		result = prime * result + innerPort;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((platformId == null) ? 0 : platformId.hashCode());
		result = prime * result + status;
		result = prime * result + tcpPort;
		result = prime * result + type;
		result = prime * result + webPort;
		result = prime * result + ((worldName == null) ? 0 : worldName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Server server = (Server) o;

		if (followerId != server.followerId) return false;
		if (id != server.id) return false;
		if (innerPort != server.innerPort) return false;
		if (status != server.status) return false;
		if (subType != server.subType) return false;
		if (tcpPort != server.tcpPort) return false;
		if (type != server.type) return false;
		if (webPort != server.webPort) return false;
		if (worldId != server.worldId) return false;
		if (externalIp != null ? !externalIp.equals(server.externalIp) : server.externalIp != null) return false;
		if (innerIp != null ? !innerIp.equals(server.innerIp) : server.innerIp != null) return false;
		if (key != null ? !key.equals(server.key) : server.key != null) return false;
		if (name != null ? !name.equals(server.name) : server.name != null) return false;
		if (openTime != null ? !openTime.equals(server.openTime) : server.openTime != null) return false;
		if (pName != null ? !pName.equals(server.pName) : server.pName != null) return false;
		if (platformId != null ? !platformId.equals(server.platformId) : server.platformId != null) return false;
		if (worldName != null ? !worldName.equals(server.worldName) : server.worldName != null) return false;

		return true;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getVersion(){
		if(StringUtils.isNotEmpty(serverVersion) || StringUtils.isNotEmpty(dataVersion)){
			return serverVersion+"\t\n"+dataVersion;
		}
		return "";
	}
}
