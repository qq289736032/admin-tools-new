package com.mokylin.cabal.modules.tools.vo;

import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.game.api.model.VirtualItem;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/7/1
 * admin-tools
 */
public class VirtualItemVo implements Serializable {

    private static final long serialVersionUID = -6241294833986910207L;

    private int global;

    private String serverIds;

    private String roleIds;  // === receiverUserIds

    private String receiverNames;//指定用户发送方式时的用户列表
    private String receiverUserIds; //指定用户发送方式的用户id列表

    private List<VirtualItem> items;

    private String virtualItems;


    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
    }

    public String getServerIds() {
        return serverIds;
    }

    public void setServerIds(String serverIds) {
        this.serverIds = serverIds;
    }

    public String getRoleIds() {
        return receiverUserIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public List<VirtualItem> getItems() {
        return items;
    }

    public void setItems(List<VirtualItem> items) {
        this.items = items;
    }

    public String getReceiverNames() {
        return receiverNames;
    }

    public void setReceiverNames(String receiverNames) {
        this.receiverNames = receiverNames;
    }

    public String getReceiverUserIds() {
        return receiverUserIds;
    }

    public void setReceiverUserIds(String receiverUserIds) {
        this.receiverUserIds = receiverUserIds;
    }

    public boolean isGlobal(){
        boolean flag = false;
        if(global == 1){
            flag = true;
        }
        return flag;
    }

    public String getVirtualItems() {
        return StringUtils.join(items.toArray(), ";");
    }

    public void setVirtualItems(String virtualItems) {
        this.virtualItems = virtualItems;
    }
}
