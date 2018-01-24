package com.mokylin.cabal.modules.tools.entity;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.tools.vo.AttachmentGoods;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/6 15:40
 * 项目: cabal-tools
 */
public class GameEmail extends MybatisBaseBean {
    private static final long serialVersionUID = -541576818287671637L;

    private int isGlobal;//是否全服方式
    private String serverIds;
    private String receiverNames;//指定用户发送方式时的用户列表
    private String receiverUserIds;  //指定用户发送方式的用户id列表
    private String title;
    private String content;
    private int jb;//附加金币数量
    private int yb;//附加金币数量
    private int delayHours;
    private String attachments;//附件物品组成的字符串(物品id;物品数量;是否绑定:物品id;物品数量;是否绑定...)
    private String emailStatus;

    private String createName;  //申请人姓名

    private Date createTime;    //申请时间

    private String approveBy;
    private String approveName;

    private String receiveTime;     //领取时间

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    private List<AttachmentGoods> goodsList = new CopyOnWriteArrayList<AttachmentGoods>();//附加物品列表

    public int getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(int isGlobal) {
        this.isGlobal = isGlobal;
    }

    public String getServerIds() {
        return serverIds;
    }

    public void setServerIds(String serverIds) {
        this.serverIds = serverIds;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getJb() {
        return jb;
    }

    public void setJb(int jb) {
        this.jb = jb;
    }

    public int getYb() {
        return yb;
    }

    public void setYb(int yb) {
        this.yb = yb;
    }

    public int getDelayHours() {
        return delayHours;
    }

    public void setDelayHours(int delayHours) {
        this.delayHours = delayHours;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    /**
     * 返回指定用户名的List形式对象
     * @return
     */
    public List<String> getReceiverNameList(){
        List<String> list = new ArrayList();
        String[] receiverNames = StringUtils.split(this.receiverNames, ",");
        if(receiverNames == null){
            return list;
        }
        for (String name : StringUtils.split(this.receiverNames, ",")) {
            list.add(StringUtils.trim(name));
        }
        return list;
    }

    /**
     * 返回指定serverId的List形式对象
     * @return
     */
    public List<String> getServerIdList(){
        String[] array = StringUtils.split(this.serverIds, ",");
        if(array == null || array.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.asList(array);
    }

    /**
     * 得到附件物品的json
     */
    public String getAttachmentsJson(){
        if (StringUtils.isBlank(attachments)) {
            return null;
        }
        List resultList = new ArrayList();
        String[] goodsArray = StringUtils.contains(attachments, ":") ? StringUtils.split(attachments, ":") : StringUtils.split(attachments, ":");
        for (String goodsString : goodsArray) {
            Map map = new HashMap();
            String[] array = StringUtils.split(goodsString, ";");
            if (ArrayUtils.isEmpty(array)) { continue; }

            if (array.length == 2) {
                map.put("goodsId", array[0]);
                map.put("goodsCount", array[1]);
            }
            else if (array.length == 3) {
                map.put("goodsId", array[0]);
                map.put("goodsCount", array[1]);
                map.put("bindOrNot", array[2]);
            }else if(array.length == 4){
                map.put("goodsId", array[0]);
                map.put("goodsCount", array[1]);
                map.put("bindOrNot", array[2]);
                map.put("expireTime",array[3]);
                /*try {
                    map.put("expireTime", DateUtil.parseDateTime(array[3]).getTime());
                } catch (ParseException e) {
                    log.error("",e);
                }*/
            }
            else{
                continue;
            }
            resultList.add(map);
        }
        return JSON.toJSONString(resultList);
    }

    public List<AttachmentGoods> getGoodsList() {return goodsList;}
    public void setGoodsList(List<AttachmentGoods> goodsList) {this.goodsList = goodsList;}


    public boolean isGlobal(){
        return this.isGlobal == 1 ? true : false;
    }
}
