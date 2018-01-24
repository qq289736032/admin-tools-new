package com.mokylin.cabal.modules.tools.entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.modules.tools.vo.AttachmentGoods;

/**
 * 作者: 礼包 日期: 2014/11/6 15:40 项目: cabal-tools
 */
public class Gift extends MybatisBaseBean {
	private static final long serialVersionUID = -541576818287671637L;

	private String name;
	private String content;
	private String attachments;// 附件物品组成的字符串(物品id;物品数量;是否绑定:物品id;物品数量;是否绑定...)
	private String giftId;
	private String giftType;	//礼包类型，是否重复领取

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getGiftType() {
		return giftType;
	}

	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}

	private List<AttachmentGoods> goodsList = new CopyOnWriteArrayList<AttachmentGoods>();// 附加物品列表

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}

	public List<AttachmentGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<AttachmentGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
