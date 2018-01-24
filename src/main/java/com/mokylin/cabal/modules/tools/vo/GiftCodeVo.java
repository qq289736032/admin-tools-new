package com.mokylin.cabal.modules.tools.vo;

import java.io.Serializable;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/7/7
 * admin-tools
 */
public class GiftCodeVo implements Serializable {
    private static final long serialVersionUID = 2765374583883196438L;

    private String pid;

    private String giftName;

    private int produceNum;    //生成的code数量

    private int consumeNum;    //使用的数量


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public int getProduceNum() {
        return produceNum;
    }

    public void setProduceNum(int produceNum) {
        this.produceNum = produceNum;
    }

    public int getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(int consumeNum) {
        this.consumeNum = consumeNum;
    }
}
