package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class OperaGoldLog  extends MybatisBaseBean{
	
	public static final String PASSAGEWAY_IND = "独代通道";
	public static final String PASSAGEWAY_PLAT = "平台通道";
	public static final String PASSAGEWAY_SPEC = "特殊通道";
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String passageway;//通道
	private String platName;
	private String pid;
	private String serverId;
	private String serverName;
	private String roleName;
	private BigInteger roleId;
	private String userId;
	private BigInteger gold;//元宝数量
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassageway() {
		return passageway;
	}
	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}

	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigInteger getGold() {
		return gold;
	}
	public void setGold(BigInteger gold) {
		this.gold = gold;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
}
