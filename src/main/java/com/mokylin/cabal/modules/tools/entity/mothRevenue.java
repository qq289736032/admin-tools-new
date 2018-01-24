package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

/**
 * 月营收数据分析视实体类
 * @author ln
 *
 */
public class mothRevenue  extends MybatisBaseBean {

	private static final long serialVersionUID = 1L;
	
	private  String  month;     //月份
	private  Integer newUser;   //新用户
	private  Integer oldUser;   //老用户
	private  Integer active;    //活跃用户
	private  Double  payrate;   //付费率
	private  Double  arpu;      //arpu
	private  Integer income;   //月充值
	private  Integer chargeNum; //月充值人数
	
	
	public Integer getChargeNum() {
		return chargeNum;
	}
	public void setChargeNum(Integer chargeNum) {
		this.chargeNum = chargeNum;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getNewUser() {
		return newUser;
	}
	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}
	public Integer getOldUser() {
		return oldUser;
	}
	public void setOldUser(Integer oldUser) {
		this.oldUser = oldUser;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public Double getPayrate() {
		return payrate;
	}
	public void setPayrate(Double payrate) {
		this.payrate = payrate;
	}
	public Double getArpu() {
		return arpu;
	}
	public void setArpu(Double arpu) {
		this.arpu = arpu;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
