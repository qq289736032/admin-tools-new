package com.mokylin.cabal.modules.log.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.common.utils.excel.annotation.ExcelField;

/**
 * 道具获取和消耗
 * @author Administrator
 *
 */
public class PropsDetail extends MybatisBaseBean  {

	private static final long serialVersionUID = -2801598485599802986L;
 
	private String id ;                 
	private String areaIid;             //区服
	private String opereateType;         //事件类型
	private String userId; 			    //用户名
	private String roleId;			    //角色ID
	private String roleLevel;			//角色等级
	private String roleName;			//角色名
	private String itemId;				//物品ID
	private String itemName;			//物品名
	private String value;				//消耗或获取 数量
	private String beforeValue;			//消耗或获取之前的数量
	private String afterValue;			//消耗或获取之后的数量
	private String flowType;			//流向标识（1-获得  2-消耗）
	private String logTimeDay;			//日志时间 天
	private String logTime;				//日志时间
	private String resource;			//资源线
	private String active;              //活动面板
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaIid() {
		return areaIid;
	}
	public void setAreaIid(String areaIid) {
		this.areaIid = areaIid;
	}
	
	@ExcelField(title="事件类型", align=2, sort=50,key="str812")
	public String getOpereateType() {
		return opereateType;
	}
	public void setOpereateType(String opereateType) {
		this.opereateType = opereateType;
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
   
	
	@ExcelField(title="角色名", align=2, sort=20,key="str7")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@ExcelField(title="物品名称", align=2, sort=60 ,key="str438")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	@ExcelField(title="道具变化数量", align=2, sort=70, key="str918")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	@ExcelField(title="道具变化前的值", align=2, sort=80, key="str919")
	public String getBeforeValue() {
		return beforeValue;
	}
	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}
	
	
	@ExcelField(title="道具变化后的值", align=2, sort=90, key="str920")
	public String getAfterValue() {
		return afterValue;
	}
	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	@ExcelField(title="道具变化", align=2, sort=50, key="str917")
	public String getFlowType() {
		return flowType;
	}
	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}
	
	
	public String getLogTimeDay() {
		return logTimeDay;
	}
	public void setLogTimeDay(String logTimeDay) {
		this.logTimeDay = logTimeDay;
	}
	
	
	@ExcelField(title="时间", align=2, sort=100, key="str3")
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	
	@ExcelField(title="资源线", align=2, sort=30, key="str916")
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	@ExcelField(title="活动面板", align=2, sort=40, key="str1888")
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
	
	
	
	
}
