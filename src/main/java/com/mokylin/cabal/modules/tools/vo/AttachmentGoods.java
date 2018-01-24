package com.mokylin.cabal.modules.tools.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.mokylin.cabal.common.utils.StringUtils;

public class AttachmentGoods {

    @JSONField(name = "templateId")
    private String id;//物品
    private String count;//数量

    @JSONField(name = "bind")
    private String binding;//是否绑定

    @JSONField(name = "endTime")
    private String expireTime; //过期时间

    private String attributesInfo; //装备属性

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getAttributesInfo() {
        return attributesInfo;
    }

    public void setAttributesInfo(String attributesInfo) {
        this.attributesInfo = attributesInfo;
    }

    @Override
    public String toString() {
        return StringUtils.join(new String[]{id, count, binding, expireTime}, ";");
    }
}
