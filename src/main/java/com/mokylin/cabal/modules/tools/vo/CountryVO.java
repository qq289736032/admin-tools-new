package com.mokylin.cabal.modules.tools.vo;

import com.mokylin.cabal.common.redis.Server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CountryVO {

	private int id;
	private String name;
	private long roleId;
	private String roleName;
	private long power;

	private Date addTime;
	private List<Server> list = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getPower() {
		return power;
	}

	public void setPower(long power) {
		this.power = power;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public List<Server> getList() {
		return list;
	}
	public void setList(List<Server> list) {
		this.list = list;
	}
}
