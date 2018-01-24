package com.mokylin.cabal.common.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BattleArea {
	private int crossId;// 跨服服务器的ID
	private String name;// 战区名
	private List<Integer> countries = new ArrayList<>();// 国家列表
	private Date addTime;// 创建时间

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
	
	public boolean containsCountry(Integer countryId){
		return countries.contains(countryId);
	}

	public List<Integer> getCountries() {
		return countries;
	}

	public void addCountry(Integer countryId) {
		this.countries.add(countryId);
	}

	public void removeCountry(Integer countryId) {
		this.countries.remove(countryId);
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
