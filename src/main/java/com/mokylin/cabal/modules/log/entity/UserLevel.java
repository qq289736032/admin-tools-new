package com.mokylin.cabal.modules.log.entity;

import com.google.common.collect.Lists;
import com.mokylin.cabal.common.persistence.BaseEntity;

import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/11
 * admin-tools
 *
 * 流失、回流用户等级
 */
public class UserLevel extends BaseEntity{


    private String levelInterval; //等级区间

    private Integer weekUser = 0;   //周流失/回流用户数

    private Integer doubleWeekUser = 0; //双周流失/回流用户数

    private Integer monthUser = 0;  //月流失/回流用户数

    private Integer startLevel;

    private Integer endLevel;

    public Integer getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(Integer startLevel) {
        this.startLevel = startLevel;
    }

    public Integer getEndLevel() {
        return endLevel;
    }

    public void setEndLevel(Integer endLevel) {
        this.endLevel = endLevel;
    }


    public UserLevel() {

    }

    public String getLevelInterval() {
        return levelInterval;
    }

    public void setLevelInterval(String levelInterval) {
        this.levelInterval = startLevel+"-"+endLevel;
    }

    public UserLevel(String levelInterval, Integer weekUser, Integer doubleWeekUser, Integer monthUser, Integer startLevel, Integer endLevel) {
        this.levelInterval = levelInterval;
        this.weekUser = weekUser;
        this.doubleWeekUser = doubleWeekUser;
        this.monthUser = monthUser;
        this.startLevel = startLevel;
        this.endLevel = endLevel;
    }

    public Integer getWeekUser() {
        return weekUser;
    }

    public void setWeekUser(Integer weekUser) {
        this.weekUser = weekUser;
    }

    public Integer getDoubleWeekUser() {
        return doubleWeekUser;
    }

    public void setDoubleWeekUser(Integer doubleWeekUser) {
        this.doubleWeekUser = doubleWeekUser;
    }

    public Integer getMonthUser() {
        return monthUser;
    }

    public void setMonthUser(Integer monthUser) {
        this.monthUser = monthUser;
    }



}
