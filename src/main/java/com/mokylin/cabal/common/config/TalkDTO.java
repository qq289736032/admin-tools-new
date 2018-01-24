package com.mokylin.cabal.common.config;

public class TalkDTO {
	private int id;
	private String pid;
	private int areaId;
	private int worldId;
	private String userId;
	private long roleId;
	private String roleName;
	private String content;
	private boolean callback;

	public TalkDTO() {

	}

	public TalkDTO(String pid, int worldId, int areaId, String userId, long roleId, String roleName, String content) {
		this.pid = pid;
		this.worldId = worldId;
		this.areaId = areaId;
		this.userId = userId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWorldId() {
		return worldId;
	}

	public void setWorldId(int worldId) {
		this.worldId = worldId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isCallback() {
		return callback;
	}

	public void setCallback(boolean callback) {
		this.callback = callback;
	}

	public Object[] toVO() {
		return new Object[] {id, areaId, roleId, roleName, content };
	}

}
