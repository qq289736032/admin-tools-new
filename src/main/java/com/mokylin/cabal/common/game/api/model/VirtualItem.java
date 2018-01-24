package com.mokylin.cabal.common.game.api.model;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.common.utils.StringUtils;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/7/1
 * admin-tools
 */
public class VirtualItem extends MybatisBaseBean {


    private String itemId;

    private int itemNum;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }


    @Override
    public String toString() {
        return StringUtils.join(new String[]{itemId, String.valueOf(itemNum)}, ":");
    }
}
