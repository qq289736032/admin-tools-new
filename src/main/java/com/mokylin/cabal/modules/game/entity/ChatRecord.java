package com.mokylin.cabal.modules.game.entity;

import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/27 13:37
 * 项目: cabal-tools
 */
public class ChatRecord {

    private String chatTime;
    private String content;
    private String roleId;
    private String roleName;
    private String serverId;

    public ChatRecord() {
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
