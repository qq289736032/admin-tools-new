package com.mokylin.cabal.modules.tools.vo;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/6 15:47
 * 项目: cabal-tools
 */
public class GameEmailVo implements Serializable {

    private static final long serialVersionUID = 147653879249088327L;

    private int isGlobal;//是否全服方式
    private String serverId;
    private String receiverNames;//指定用户发送方式时的用户列表
    private String receiverUserids; //指定用户发送方式的用户id列表
    private String title;
    private String content;
    private int jb;//附加金币数量
    private int yb;//附加金币数量
    private int delayHours;//延迟小时数
    private List<AttachmentGoods> goodsList = new CopyOnWriteArrayList<AttachmentGoods>();//附加物品列表

    public String getReceiverUserids() {
        return receiverUserids;
    }
    public void setReceiverUserids(String receiverUserids) {
        this.receiverUserids = receiverUserids;
    }

    public int getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(int isGlobal) {
        this.isGlobal = isGlobal;
    }

    public String getServerId() {return serverId;}
    public void setServerId(String serverId) {this.serverId = serverId;}

    public String getReceiverNames() {	return receiverNames;}
    public void setReceiverNames(String receiverNames) {this.receiverNames = receiverNames;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public int getJb() {return jb;}
    public void setJb(int jb) {this.jb = jb;}

    public int getYb() {return yb;}
    public void setYb(int yb) {this.yb = yb;}

    public int getDelayHours() {return delayHours;}
    public void setDelayHours(int delayHours) {this.delayHours = delayHours;}

    public List<AttachmentGoods> getGoodsList() {return goodsList;}
    public void setGoodsList(List<AttachmentGoods> goodsList) {this.goodsList = goodsList;}
}
