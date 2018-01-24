package com.mokylin.cabal.modules.game.utils;


public class CoopRequest {

    /**
     * 接口授权编号
     */
    private String apiKey;

    /**
     * 服务器id
     */
    private String serverId;

    /**
     * 运营商编号
     */
    private String coopName;

    /**
     * 运营商分配的用户UID
     */
    private String userId;

    /**
     * 游戏内防沉迷标志
     */
    private Integer fangChemMiFlag;

    /**
     * 沉迷标识
     */
    private String cmFlag;
    /**
     * 请求的时间戳
     */
    private String timestamp;

    /**
     * 签名
     */
    private String sign;

    /**
     * 原始url（请求地址）
     */
    private String url;

    /**
     * 用户登录令牌
     */
    private String tocken;

    /**
     * 游戏(区)服在多玩平台内部的编号
     */
    private String dwservId;

    /**
     * 客户端登录标识
     */
    private String client;

    private String coopGame;
    private String coopServer;
    /**
     * yy禁言时间
     */
    private String yykeeptime;

    /**
     * 台湾
     */
    private String username;
    /**
     * 是否使用https协议
     */
    private String protocol;


    /**
     * 用户Vip等级，传入数值型，如3
     */
    private String vipLevel_1;


    /**
     * 用户会员等级，传入数值型，如2
     */
    private String memLevel_2;

    /**
     * 是否是年费会员，y或n: y表示是年费会员，n表示不是年费会员
     */
    private String annualFlag_3;

    /**
     * 是否是会员，y或n: y表示是会员，n表示不是会员
     */

    private String memberFlag_4;

    /**
     * 是否是Vip，y或n: y表示是Vip，n表示不是Vip
     */
    private String vipFlag_5;

    /**
     * userSign
     *
     * @return
     */
    private String userSign;

    /**
     * vipSign
     */
    private String vipSign;
    /**
     * server
     */
    private String server;

    /**
     * 是否有角色
     *
     * @return
     */
    private String isRole;

    /**
     * 邀请人
     *
     * @return
     */
    private String invite;
    /**
     * R2登陆类型
     */
    private String type;

    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsRole() {
        return isRole;
    }

    public void setIsRole(String isRole) {
        this.isRole = isRole;
    }

    public String getVipSign() {
        return vipSign;
    }

    public void setVipSign(String vipSign) {
        this.vipSign = vipSign;
    }


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServerId() {
        return serverId;
    }

    public String getCoopName() {
        return coopName;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getFangChemMiFlag() {
        return fangChemMiFlag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public void setCoopName(String coopName) {
        this.coopName = coopName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFangChemMiFlag(Integer fangChemMiFlag) {
        this.fangChemMiFlag = fangChemMiFlag;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getCmFlag() {
        return cmFlag;
    }

    public void setCmFlag(String cmFlag) {
        this.cmFlag = cmFlag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    public String getDwservId() {
        return dwservId;
    }

    public void setDwservId(String dwservId) {
        this.dwservId = dwservId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCoopGame() {
        return coopGame;
    }

    public void setCoopGame(String coopGame) {
        this.coopGame = coopGame;
    }

    public String getCoopServer() {
        return coopServer;
    }

    public void setCoopServer(String coopServer) {
        this.coopServer = coopServer;
    }

    public String getYykeeptime() {
        return yykeeptime;
    }

    public void setYykeeptime(String yykeeptime) {
        this.yykeeptime = yykeeptime;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVipLevel_1() {
        return vipLevel_1;
    }

    public void setVipLevel_1(String vipLevel_1) {
        this.vipLevel_1 = vipLevel_1;
    }

    public String getMemLevel_2() {
        return memLevel_2;
    }

    public void setMemLevel_2(String memLevel_2) {
        this.memLevel_2 = memLevel_2;
    }

    public String getAnnualFlag_3() {
        return annualFlag_3;
    }

    public void setAnnualFlag_3(String annualFlag_3) {
        this.annualFlag_3 = annualFlag_3;
    }

    public String getMemberFlag_4() {
        return memberFlag_4;
    }

    public void setMemberFlag_4(String memberFlag_4) {
        this.memberFlag_4 = memberFlag_4;
    }

    public String getVipFlag_5() {
        return vipFlag_5;
    }

    public void setVipFlag_5(String vipFlag_5) {
        this.vipFlag_5 = vipFlag_5;
    }

}
