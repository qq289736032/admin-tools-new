package com.mokylin.cabal.modules.tools.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/19
 * admin-tools
 *
 * IP 黑名单
 */
public class IpBlack implements Serializable {

    private static final long serialVersionUID = -7571559329354977674L;

    private String ip;

    private int silence;     //禁言    1表示启用 0表示不启用

    private int freeze;      //封号    1表示启用 0表示不启用

    private String expireTime;    //失效时间

    private String createTime;    //添加时间

    public IpBlack(String ip, int silence, int freeze, String expireTime, String createTime) {
        this.ip = ip;
        this.silence = silence;
        this.freeze = freeze;
        this.expireTime = expireTime;
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public IpBlack(String ip, int silence, int freeze) {
        this.ip = ip;
        this.silence = silence;
        this.freeze = freeze;
    }

    public IpBlack() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getSilence() {
        return silence;
    }

    public void setSilence(int silence) {
        this.silence = silence;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    @Override
    public String toString() {
        return "IpBlack{" +
                "ip='" + ip + '\'' +
                ", silence='" + silence + '\'' +
                ", freeze='" + freeze + '\'' +
                '}';
    }
}
