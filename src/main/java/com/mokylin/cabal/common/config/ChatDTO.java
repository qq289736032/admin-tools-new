package com.mokylin.cabal.common.config;

public class ChatDTO {
	private String pid;
	private int areaId;
	private int channelType;
	private int userId;
	private long roleId;
	private String roleName;
	private String content;
	private String ip;

	public ChatDTO() {

	}

	public ChatDTO(String pid, int areaId, int channelType, int userId, long roleId, String roleName, String content, String ip) {
		this.pid = pid;
		this.areaId = areaId;
		this.channelType = channelType;
		this.userId = userId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.content = content;
		this.ip = ip;
	}

	public ChatDTO(String pid, int areaId, int channelType, long roleId, String roleName,String content) {
		this.pid = pid;
		this.areaId = areaId;
		this.channelType = channelType;
		this.roleId = roleId;
		this.roleName=roleName;
		this.content = content;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object[] toVO() {
		return new Object[] { areaId, channelType, roleId, roleName, content, pid, userId, ip };
	}
}
