package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
/**
 * 作者: msg
 * 日期: 2016/11/8 9:46
 * 项目: admin-tools-new
 * 单服特殊配置
 */
public class SingleAccountLog extends MybatisBaseBean{
	public static final String EDIT_TYPE_ADD = "添加";
	public static final String EDIT_TYPE_EDIT = "修改";
	public static final String EDIT_TYPE_DEL = "删除";
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private BigInteger roleId;
	private String roleName;
	private BigInteger topCharge;//内部号最高充值限制(rmb)
	private String isInfluence;//是否受批量修改影响
	private String editType;//操作类型
	private String platName;//平台名
	private String serverName;//服务器名
	private String pid;
	private String serverId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public BigInteger getTopCharge() {
		return topCharge;
	}
	public void setTopCharge(BigInteger topCharge) {
		this.topCharge = topCharge;
	}

	public String getIsInfluence() {
		return isInfluence;
	}
	public void setIsInfluence(String isInfluence) {
		this.isInfluence = isInfluence;
	}
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
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


}
