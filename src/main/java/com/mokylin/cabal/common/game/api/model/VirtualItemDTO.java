package com.mokylin.cabal.common.game.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/7/2
 * admin-tools
 */
public class VirtualItemDTO implements Serializable {

    private static final long serialVersionUID = -5178356613137719852L;

    private boolean global;

    private List<VirtualItem> virtualItems;

    private List<String> roleIds;

    public VirtualItemDTO() {
    }

    public VirtualItemDTO(boolean global, List<VirtualItem> virtualItems, List<String> roleIds) {
        this.global = global;
        this.virtualItems = virtualItems;
        this.roleIds = roleIds;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public List<VirtualItem> getVirtualItems() {
        return virtualItems;
    }

    public void setVirtualItems(List<VirtualItem> virtualItems) {
        this.virtualItems = virtualItems;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

}
