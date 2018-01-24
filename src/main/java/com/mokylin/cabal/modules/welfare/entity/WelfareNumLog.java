package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class WelfareNumLog extends MybatisBaseBean{
	public static final int EDIT_TYPE_ADD = 0;
	public static final int EDIT_TYPE_STOP = 1;
	public static final int EDIT_TYPE_UNLOCKED = 2;
	public static final int EDIT_TYPE_DEL = 3;
	
	public static final String STATUS_NORMAL = "正常";
	public static final String STATUS_DELETE = "已删除";
	public static final String STATUS_STOP = "封停";
	private static final long serialVersionUID = 1L;
	private String id;
	private String platName;
	private String serverName;
	private String serverId;
	
	private String roleName;
	private BigInteger roleId;
	private String userId;
	private String usePeople;//使用人
	private String usePeoplePost;//使用人职务
	//private String passageway;//通道
	private Integer editType;//操作类型
	private String status;//状态
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
	}

	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public Integer getEditType() {
		return editType;
	}
	public void setEditType(Integer editType) {
		this.editType = editType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}


	
}
