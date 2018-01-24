package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/13 16:57
 * 项目: cabal-tools
 * 元宝充值
 */
public class Recharge extends MybatisBaseBean {

    private static final long serialVersionUID = -2845825385562011255L;

    private String userIds;      //玩家账号
    private String roleIds;      //角色账号
    private String roleNames;    //角色名
    private String moneyType;   //货币类型
    private Integer moneyNum;   //货币数量
    private String serverId;    //
    private String rechargeType;    //充值类型
    private String rechargeStatus;  //充值状态
    private String remark;          //备注
    private String approveBy;       
    private String approveName;		//审核人


    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public Integer getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(Integer moneyNum) {
        this.moneyNum = moneyNum;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }
}
