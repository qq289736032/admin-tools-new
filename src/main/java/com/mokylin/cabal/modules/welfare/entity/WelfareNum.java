package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class WelfareNum extends MybatisBaseBean{
	public static final int DEFAULT_STOP_DAY = 5;//系统默认5天不登录自动封停
	public static final int STATUS_STOP = 1;//封停
	public static final int STATUS_NORMAL = 0;//正常
	public static final int IS_INFLUENCE_TRUE = 0;//受批量修改影响
	public static final int IS_INFLUENCE_FALSE = 1;//不受批量修改影响
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleName;
	private BigInteger roleId;
	private String userId;
	private String usePeople;//使用人
	private String usePeoplePost;//使用人职务
	private String passageway;//通道
	private Integer status;//状态
	private String pid;
	private String serverId;
	private String serverName;
	private String platName;
	private BigInteger topCharge;//内部号最高充值限制(rmb)
	private Integer isInfluence;//是否受批量修改影响
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUsePeople() {
		return usePeople;
	}
	public void setUsePeople(String usePeople) {
		this.usePeople = usePeople;
	}
	public String getUsePeoplePost() {
		return usePeoplePost;
	}
	public void setUsePeoplePost(String usePeoplePost) {
		this.usePeoplePost = usePeoplePost;
	}
	public String getPassageway() {
		return passageway;
	}
	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
	}
	public BigInteger getTopCharge() {
		return topCharge;
	}
	public void setTopCharge(BigInteger topCharge) {
		this.topCharge = topCharge;
	}
	public Integer getIsInfluence() {
		return isInfluence;
	}
	public void setIsInfluence(Integer isInfluence) {
		this.isInfluence = isInfluence;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
