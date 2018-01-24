package com.mokylin.cabal.modules.tools.vo;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/10 15:37
 * 项目: cabal-tools
 */
public class Goods extends MybatisBaseBean {

    private static final long serialVersionUID = 4548079703662030884L;

    public Goods(String id, String name, String maxStack){
        this.id = id;
        this.name = name;
        this.maxStack = NumberUtils.toInt(maxStack);
    }

    private String id;
    private String name;
    private String goodsDesc;
    private int maxStack;
    private boolean xuniGoods;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMaxStack() {
        return maxStack;
    }
    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }
    public boolean isXuniGoods() {
        return xuniGoods;
    }
    public void setXuniGoods(boolean xuniGoods) {
        this.xuniGoods = xuniGoods;
    }
    public String getGoodsDesc() {
        return goodsDesc;
    }
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
}
