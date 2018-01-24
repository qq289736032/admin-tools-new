package com.mokylin.cabal.modules.welfare.entity;

import java.math.BigInteger;
import java.util.Date;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

public class WelfareSettingLog extends MybatisBaseBean{
	
	public static final int EDIT_TYPE_ADD = 0;//操作类型：新增
	public static final int EDIT_TYPE_EDIT = 1;//操作类型：修改
	public static final int EDIT_TYPE_DEL = 2;//操作类型：删除
	public static final int EDIT_TYPE_START = 3;//操作类型：启动
	public static final int EDIT_TYPE_STOP = 4;//操作类型：停止
	public static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String platName;
	private String platNature;//平台性质
	private BigInteger newServiceGold;//新服资源（元宝）
	private BigInteger RResourceAmount;//大R资源金额(rmb)
	private Integer RResourceRatio;//大R资源比例
	private Integer singleChargeRatio;//单服总充值后续比例
	private BigInteger topCharge;//内部号最高充值限制(rmb)
	private BigInteger topGoldDay;//单角色每日最高限额(元宝)
	private BigInteger topHoldGold;//单角色持有最高额度(元宝)
	private BigInteger topInternalNumber;//单区内部号数量上限(个)
	private Integer addTimeLimit;//内部号添加时间限制(天)
	private Integer status;
	private Integer editType;
	private String goldPoolCategory;//奖金池类别
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
	public String getPlatName() {
		return platName;
	}
	public void setPlatName(String platName) {
		this.platName = platName;
	}
	public String getPlatNature() {
		return platNature;
	}
	public void setPlatNature(String platNature) {
		this.platNature = platNature;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getEditType() {
		return editType;
	}
	public void setEditType(Integer editType) {
		this.editType = editType;
	}
	public String getGoldPoolCategory() {
		return goldPoolCategory;
	}
	public void setGoldPoolCategory(String goldPoolCategory) {
		this.goldPoolCategory = goldPoolCategory;
	}
	
}
