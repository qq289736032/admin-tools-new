package com.mokylin.cabal.common.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Country {
	private int id;// 唯一键
	private int num;// 当前第几个国家(1,2,3...1,2,3....)
	private String kingName;// 国王名
	private String name;// 国家名
	private List<Integer> servers = new ArrayList<>();// 当前服务器列表
	private Date addTime;// 创建时间
	private int crossId;//战区id

	public int getCrossId() {
		return crossId;
	}

	public void setCrossId(int crossId) {
		this.crossId = crossId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public List<Integer> getServers() {
		return servers;
	}

	public void addServer(Integer server) {
		this.servers.add(server);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getKingName() {
		return kingName;
	}

	public void setKingName(String kingName) {
		this.kingName = kingName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
