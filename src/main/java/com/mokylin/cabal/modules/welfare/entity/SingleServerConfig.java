package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
/**
 * 作者: msg
 * 日期: 2016/11/8 9:46
 * 项目: admin-tools-new
 * 单服特殊配置
 */
public class SingleServerConfig extends MybatisBaseBean{
	public static final int IS_INFLUENCE_TRUE = 0;//受批量修改影响
	public static final int IS_INFLUENCE_FALSE = 1;//不受批量修改影响
	public static final int STATUS_TRUE = 0;
	public static final int STATUS_FALSE = 0;
	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String serverId;
	private BigInteger newServiceGold;//新服资源（元宝）
	private BigInteger RResourceAmount;//大R资源金额(rmb)
	private Integer RResourceRatio;//大R资源比例
	private Integer singleChargeRatio;//单服总充值后续比例
	private BigInteger topCharge;//内部号最高充值限制(rmb)
	private BigInteger topGoldDay;//单角色每日最高限额(元宝)
	private BigInteger topHoldGold;//单角色持有最高额度(元宝)
	private BigInteger topInternalNumber;//单区内部号数量上限(个)
	private Integer addTimeLimit;//内部号添加时间限制(天)
	private Integer status;//状态
	private Integer isInfluence;//是否受批量修改影响
	private String platName;//平台名
	private String serverName;//服务器名
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
	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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


	
	
}
