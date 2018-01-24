package com.mokylin.cabal.modules.log.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/23 13:19
 * 项目: cabal-tools
 */
public class GainPropDetail extends MybatisBaseBean {

    private static final long serialVersionUID = -1707168634840003289L;

    private long id;
    private String userRoleId;
    private String userName;
    private String eventType;
    private long times;
    private String goodsId;
    private String goodsName;
    private long count;
    private Date logTime;
    private Date logDay;


    @ExcelField(title="玩家角色账号", align=2, sort=20)
    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ExcelField(title="玩家角色姓名", align=2, sort=30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @ExcelField(title="事件类型", align=2, sort=40)
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    @ExcelField(title="物品编号", align=2, sort=50)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @ExcelField(title="物品名称", align=2, sort=60)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @ExcelField(title="日志事件", align=2, sort=70)
    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Date getLogDay() {
        return logDay;
    }

    public void setLogDay(Date logDay) {
        this.logDay = logDay;
    }
}
