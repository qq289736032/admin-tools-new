package com.mokylin.cabal.modules.tools.vo;

import com.mokylin.cabal.common.redis.BattleArea;
import com.mokylin.cabal.common.redis.Country;
import com.mokylin.cabal.common.redis.Server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BattleAreaVO {
	private int crossId;// 跨服服务器的ID
	private String worldName;
	private String name;
	private int type;
	private String ip;
	private int port;
	private boolean valid;
	//private List<CountryVO> countryList = new ArrayList<>();
	private List<Country> countryList = new ArrayList<>();
	private Date addTime;
	private Date modifyTime;

	public BattleAreaVO(BattleArea area, Server cross) {
		crossId = area.getCrossId();
		name = area.getName();
		worldName = cross.getWorldName();
		ip = cross.getInnerIp();
		port = cross.getInnerPort();
		valid = cross.isValid();
		addTime = area.getAddTime();
	}

	public void addCountry(Country country) {
		countryList.add(country);
	}


	public int getCrossId() {
		return crossId;
	}

	public void setCrossId(int crossId) {
		this.crossId = crossId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getWorldName() {
		return worldName;
	}

	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}
}
