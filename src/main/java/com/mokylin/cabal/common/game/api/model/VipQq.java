package com.mokylin.cabal.common.game.api.model;

import java.io.Serializable;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/13
 * admin-tools
 */
public class VipQq implements Serializable {

    private static final long serialVersionUID = -525997459215938783L;

    private String qq;  //QQ号

    private String pic; //图片地址

    private String minRecharge; //最小充值需求

    private boolean status;  //状态
    
    private String   mmRecharge;//月最小充值


    public String getMmRecharge() {
		return mmRecharge;
	}

	public void setMmRecharge(String mmRecharge) {
		this.mmRecharge = mmRecharge;
	}

	public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMinRecharge() {
        return minRecharge;
    }

    public void setMinRecharge(String minRecharge) {
        this.minRecharge = minRecharge;
    }
}
