package com.mokylin.cabal.modules.tools.entity;//package com.mokylin.cabal.modules.tools.entity;
//
//import com.alibaba.fastjson.annotation.JSONField;
//import com.mokylin.cabal.common.persistence.IdEntity;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//import org.hibernate.annotations.NotFound;
//import org.hibernate.annotations.NotFoundAction;
//import org.hibernate.validator.constraints.Length;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import java.util.Date;
//
///**
// * 作者: 稻草鸟人
// * 日期: 2014/10/20 19:19
// * 项目: cabal-tools
// */
//@Entity
//@Table(name = "game_server")
//@DynamicInsert
//@DynamicUpdate
////@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//public class GameServer extends IdEntity<GameServer> {
//    private static final long serialVersionUID = -7929994839220884854L;
//
//    private String serverId;
//    private String serverName;
//    private String description;
////    private GamePlatform gamePlatform;
//    private String gameDbUrl;
//    private String LogDbUrl;
//    private String gateUrl;
//    private String gameServerRemoteUrl;
//    private Integer serverStatus;
//    private Integer isHefu;
//    private String hefuTarget;
//    private String hefuTime;
//    private Integer isTest;
//    private Date serverOpenTime;
//
//
////    @ManyToOne
////    @JoinColumn(name = "game_platform_id")
////    @NotFound(action = NotFoundAction.IGNORE)
////    public GamePlatform getGamePlatform() {
////        return gamePlatform;
////    }
////
////    public void setGamePlatform(GamePlatform gamePlatform) {
////        this.gamePlatform = gamePlatform;
////    }
//
//
//    @Length(min = 1, max = 100)
//    public String getServerId() {
//        return serverId;
//    }
//
//    public void setServerId(String serverId) {
//        this.serverId = serverId;
//    }
//
//    @Length(min = 1, max = 255)
//    public String getServerName() {
//        return serverName;
//    }
//
//    public void setServerName(String serverName) {
//        this.serverName = serverName;
//    }
//
//    @Length(min = 1, max = 255)
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Length(min = 1, max = 200)
//    public String getGameDbUrl() {
//        return gameDbUrl;
//    }
//
//    public void setGameDbUrl(String gameDbUrl) {
//        this.gameDbUrl = gameDbUrl;
//    }
//
//
//    @Length(min = 1, max = 200)
//    public String getLogDbUrl() {
//        return LogDbUrl;
//    }
//
//    public void setLogDbUrl(String logDbUrl) {
//        LogDbUrl = logDbUrl;
//    }
//
//    @Length(min = 1, max = 200)
//    public String getGateUrl() {
//        return gateUrl;
//    }
//
//    public void setGateUrl(String gateUrl) {
//        this.gateUrl = gateUrl;
//    }
//
//    @Length(min = 1, max = 200)
//    public String getGameServerRemoteUrl() {
//        return gameServerRemoteUrl;
//    }
//
//    public void setGameServerRemoteUrl(String gameServerRemoteUrl) {
//        this.gameServerRemoteUrl = gameServerRemoteUrl;
//    }
//
//    @NotNull
//    public Integer getServerStatus() {
//        return serverStatus;
//    }
//
//    public void setServerStatus(Integer serverStatus) {
//        this.serverStatus = serverStatus;
//    }
//
//    @NotNull
//    public Integer getIsHefu() {
//        return isHefu;
//    }
//
//    public void setIsHefu(Integer isHefu) {
//        this.isHefu = isHefu;
//    }
//
//    @NotNull
//    public Integer getIsTest() {
//        return isTest;
//    }
//
//    public void setIsTest(Integer isTest) {
//        this.isTest = isTest;
//    }
//
//
//    @Length(min = 1, max = 36)
//    public String getHefuTarget() {
//        return hefuTarget;
//    }
//
//    public void setHefuTarget(String hefuTarget) {
//        this.hefuTarget = hefuTarget;
//    }
//
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    public String getHefuTime() {
//        return hefuTime;
//    }
//
//    public void setHefuTime(String hefuTime) {
//        this.hefuTime = hefuTime;
//    }
//
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
//    public Date getServerOpenTime() {
//        return serverOpenTime;
//    }
//
//    public void setServerOpenTime(Date serverOpenTime) {
//        this.serverOpenTime = serverOpenTime;
//    }
//}
