package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class GoldStatistic extends MybatisBaseBean{
	private static final long serialVersionUID = 1L;
	private String id;
	private BigInteger roleId;
	private BigInteger allGoldCount;//所有通道累积发放元宝数量
	private BigInteger platGoldCount;//平台通道累积发放元宝数量
	private BigInteger insideGoldCount;//内部通道累积发放元宝数量(独代通道+特殊通道)
	private BigInteger chargeCount;//累积充值数量
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigInteger getRoleId() {
		return roleId;
	}
	public void setRoleId(BigInteger roleId) {
		this.roleId = roleId;
	}
	public BigInteger getChargeCount() {
		return chargeCount;
	}
	public void setChargeCount(BigInteger chargeCount) {
		this.chargeCount = chargeCount;
	}
	public BigInteger getAllGoldCount() {
		return allGoldCount;
	}
	public void setAllGoldCount(BigInteger allGoldCount) {
		this.allGoldCount = allGoldCount;
	}
	public BigInteger getPlatGoldCount() {
		return platGoldCount;
	}
	public void setPlatGoldCount(BigInteger platGoldCount) {
		this.platGoldCount = platGoldCount;
	}
	public BigInteger getInsideGoldCount() {
		return insideGoldCount;
	}
	public void setInsideGoldCount(BigInteger insideGoldCount) {
		this.insideGoldCount = insideGoldCount;
	}
	
	
}
