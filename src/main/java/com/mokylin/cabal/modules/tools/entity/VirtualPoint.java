package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/7/1
 * admin-tools
 */
public class VirtualPoint extends MybatisBaseBean {

    private String serverId;

    private String roleIds;

    private String goods;

    private String createBy;

    private String createName;

    private String approveBy;	// 更新者

    private String approveName;	// 更新者



    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String getCreateName() {
        return createName;
    }

    @Override
    public void setCreateName(String createName) {
        this.createName = createName;
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
