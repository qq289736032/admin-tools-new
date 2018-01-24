package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
/**
 * 作者: msg
 * 日期: 2016/11/8 9:46
 * 项目: admin-tools-new
 * 单服特殊配置
 */
public class SingleServerLog extends MybatisBaseBean{
	public static final String EDIT_TYPE_DEL = "删除";
	public static final String EDIT_TYPE_ADD = "新增";
	public static final String EDIT_TYPE_UPDATE = "修改";
	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String platName;//平台名
	private String serverId;
	private String serverName;//服务器名
	private BigInteger newServiceGold;//新服资源（元宝）
	private BigInteger RResourceAmount;//大R资源金额(rmb)
	private Integer RResourceRatio;//大R资源比例
	private Integer singleChargeRatio;//单服总充值后续比例
	private BigInteger topCharge;//内部号最高充值限制(rmb)
	private BigInteger topGoldDay;//单角色每日最高限额(元宝)
	private BigInteger topHoldGold;//单角色持有最高额度(元宝)
	private BigInteger topInternalNumber;//单区内部号数量上限(个)
	private Integer addTimeLimit;//内部号添加时间限制(天)
	private String isInfluence;//是否受批量修改影响
	private String editType;//操作类型
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public BigInteger getNewServiceGold() {
		return newServiceGold;
	}
	public void setNewServiceGold(BigInteger newServiceGold) {
		this.newServiceGold = newServiceGold;
	}

	public BigInteger getRResourceAmount() {
		return RResourceAmount;
	}
	public void setRResourceAmount(BigInteger rResourceAmount) {
		RResourceAmount = rResourceAmount;
	}
	public Integer getRResourceRatio() {
		return RResourceRatio;
	}
	public void setRResourceRatio(Integer rResourceRatio) {
		RResourceRatio = rResourceRatio;
	}
	public Integer getSingleChargeRatio() {
		return singleChargeRatio;
	}
	public void setSingleChargeRatio(Integer singleChargeRatio) {
		this.singleChargeRatio = singleChargeRatio;
	}
	public BigInteger getTopCharge() {
		return topCharge;
	}
	public void setTopCharge(BigInteger topCharge) {
		this.topCharge = topCharge;
	}
	public BigInteger getTopGoldDay() {
		return topGoldDay;
	}
	public void setTopGoldDay(BigInteger topGoldDay) {
		this.topGoldDay = topGoldDay;
	}
	public BigInteger getTopHoldGold() {
		return topHoldGold;
	}
	public void setTopHoldGold(BigInteger topHoldGold) {
		this.topHoldGold = topHoldGold;
	}
	public BigInteger getTopInternalNumber() {
		return topInternalNumber;
	}
	public void setTopInternalNumber(BigInteger topInternalNumber) {
		this.topInternalNumber = topInternalNumber;
	}
	public Integer getAddTimeLimit() {
		return addTimeLimit;
	}
	public void setAddTimeLimit(Integer addTimeLimit) {
		this.addTimeLimit = addTimeLimit;
	}
	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
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
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}




	
	
}
