package com.mokylin.cabal.modules.ws.entity;

import java.util.Date;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/25
 * admin-tools
 */
public class ApiRateLimit {

    private String ip;

    private Date before;

    private long times;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }
}
