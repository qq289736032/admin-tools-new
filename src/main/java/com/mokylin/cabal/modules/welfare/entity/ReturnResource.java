package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class ReturnResource extends MybatisBaseBean{
	private static final long serialVersionUID = 1L;
	private String id;
	private BigInteger drAmount;//大R充值返回奖金池元宝数量
	private BigInteger singleServerAmount;//单服总充值返回
	private String pid;
	private String serverId;
	private BigInteger roleId;
	private String userId;
	private String orderId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigInteger getDrAmount() {
		return drAmount;
	}
	public void setDrAmount(BigInteger drAmount) {
		this.drAmount = drAmount;
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
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public BigInteger getSingleServerAmount() {
		return singleServerAmount;
	}
	public void setSingleServerAmount(BigInteger singleServerAmount) {
		this.singleServerAmount = singleServerAmount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	
	
}
