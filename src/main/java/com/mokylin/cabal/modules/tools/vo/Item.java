package com.mokylin.cabal.modules.tools.vo;

import java.sql.Timestamp;

public class Item {
	
	private long      id;
	private String    templateId;
	private int       position;
	private long      userRoleId;
	private int       slotNum;
	private int       count;
	private int       bind;
	private int       rareLevel;
	private int       level;
	private String    attributesInfo;
	private String    module;
	private long      endTime;
	private Timestamp addTime;
	private Timestamp modifyTime;
	private String petAttributes;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public long getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public int getSlotNum() {
		return slotNum;
	}
	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getBind() {
		return bind;
	}
	public void setBind(int bind) {
		this.bind = bind;
	}
	public int getRareLevel() {
		return rareLevel;
	}
	public void setRareLevel(int rareLevel) {
		this.rareLevel = rareLevel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getAttributesInfo() {
		return attributesInfo;
	}
	public void setAttributesInfo(String attributesInfo) {
		this.attributesInfo = attributesInfo;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public Timestamp getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getPetAttributes() {
		return petAttributes;
	}
	public void setPetAttributes(String petAttributes) {
		this.petAttributes = petAttributes;
	}
	
	
	
}
