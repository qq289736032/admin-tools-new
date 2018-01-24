package com.mokylin.cabal.modules.tools.entity;

import com.mokylin.cabal.common.persistence.IdEntity;
import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/30 11:18
 * 项目: cabal-tools
 * 游戏公告
 */
public class GameNotice extends MybatisBaseBean {

    private static final long serialVersionUID = -105349226744446552L;

    private String content;

    private String serverIds;

    private Date startTime;

    private Date finishTime;

    private Integer stepTime;

    private Integer repeatCount;

    private String noticeType;

    private String noticeStatus;

    private Integer isGlobal;

    private String gamePlatformId;




    public GameNotice() {
    }

    public Integer getStepTime() {
        return stepTime;
    }

    public void setStepTime(Integer stepTime) {
        this.stepTime = stepTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServerIds() {
        return serverIds;
    }

    public void setServerIds(String serverIds) {
        this.serverIds = serverIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public Integer getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(Integer isGlobal) {
        this.isGlobal = isGlobal;
    }

    public String getGamePlatformId() {
        return gamePlatformId;
    }

    public void setGamePlatformId(String gamePlatformId) {
        this.gamePlatformId = gamePlatformId;
    }

    public boolean isGlobal(){
        return this.isGlobal == 1 ? true : false;
    }


}
