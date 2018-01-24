package com.mokylin.cabal.modules.rebate.entity;

import java.util.Date;

public class RebateRatio {
    private String id;

    private Long dayAmount;

    private Integer rebateRatio;

    private String createName;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(Long dayAmount) {
        this.dayAmount = dayAmount;
    }

    public Integer getRebateRatio() {
        return rebateRatio;
    }

    public void setRebateRatio(Integer rebateRatio) {
        this.rebateRatio = rebateRatio;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}