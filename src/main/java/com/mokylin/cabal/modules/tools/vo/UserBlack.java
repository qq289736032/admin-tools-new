package com.mokylin.cabal.modules.tools.vo;


import java.io.Serializable;

public class UserBlack implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6922312286136946971L;

    private String pid;

    private String userId;

    private int self;       //自言自语 1表示启用 0表示不启用

    private int silence;     //禁言    1表示启用 0表示不启用

    private int freeze;      //封号    1表示启用 0表示不启用

    private String expireTime;    //失效时间

    private String createTime;


    public UserBlack() {
    }


    public UserBlack(String pid, String userId, int self, int silence, int freeze, String expireTime, String createTime) {
        this.pid = pid;
        this.userId = userId;
        this.self = self;
        this.silence = silence;
        this.freeze = freeze;
        this.expireTime = expireTime;
        this.createTime = createTime;
    }

    public UserBlack(String pid, String userId, int self, int silence, int freeze, String createTime) {
        this.pid = pid;
        this.userId = userId;
        this.self = self;
        this.silence = silence;
        this.freeze = freeze;
        this.createTime = createTime;
    }

    public UserBlack(String pid, String userId, int self, int silence, int freeze) {
        this.pid = pid;
        this.userId = userId;
        this.self = self;
        this.silence = silence;
        this.freeze = freeze;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public final int getSelf() {
        return self;
    }

    public final void setSelf(int self) {
        this.self = self;
    }

    public final int getSilence() {
        return silence;
    }

    public final void setSilence(int silence) {
        this.silence = silence;
    }

    public final int getFreeze() {
        return freeze;
    }

    public final void setFreeze(int freeze) {
        this.freeze = freeze;
    }

    public final String getUserId() {
        return userId;
    }

    public final void setUserId(String userId) {
        this.userId = userId;
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
}
