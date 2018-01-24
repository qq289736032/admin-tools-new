package com.mokylin.cabal.modules.log.entity;

import com.mokylin.cabal.common.persistence.MybatisBaseBean;
import com.mokylin.cabal.common.utils.excel.annotation.ExcelField;

/**
 * 综合日志实体类
 * @author Administrator
 *
 */
public class comprehensiveDetail extends MybatisBaseBean {

	private static final long serialVersionUID = 4521314016899773499L;
	
	private String id; 
	private String roleId;       	//角色id
	private String roleName;	 	//角色名
    private String operationType; 	//事件类型
    private String failTimes;		//失败次数
    private String succTimes;		//成功次数
    private String logHour;  		//日志时间
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@ExcelField(title="角色名", align=2, sort=20)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@ExcelField(title="事件", align=2, sort=30)
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	@ExcelField(title="失败次数", align=2, sort=40)
	public String getFailTimes() {
		return failTimes;
	}
	public void setFailTimes(String failTimes) {
		this.failTimes = failTimes;
	}
	
	@ExcelField(title="成功次数", align=2, sort=50)
	public String getSuccTimes() {
		return succTimes;
	}
	public void setSuccTimes(String succTimes) {
		this.succTimes = succTimes;
	}
	
	@ExcelField(title="时间", align=2, sort=20)
	public String getLogHour() {
		return logHour;
	}
	public void setLogHour(String logHour) {
		this.logHour = logHour;
	}
    
    
}
