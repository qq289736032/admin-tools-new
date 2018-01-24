package com.mokylin.cabal.common.redis;

import java.util.*;

/**
 * 国家战区玩法
 * @author Administrator
 *
 */
public class CountryCrossArea extends BaseCrossArea{
	
	/**
	 * 战区对应的国家 key-->1,2,3,4,默认每个战区的四个国家ID
	 */
	private Map<Integer, Country> countrys;

	public CountryCrossArea() {
		this(null,null);
	}

	public CountryCrossArea(String crossAreaName,Server crossServer) {
		super(crossAreaName, crossServer);
		countrys = new HashMap<Integer, Country>();
		countrys.put(1, new Country(1));
		countrys.put(2, new Country(2));
		countrys.put(3, new Country(3));
		countrys.put(4, new Country(4));
	}

	public Map<Integer, Country> getCountrys() {
		return countrys;
	}

//	public void setCountrys(Map<Integer, Country> countrys) {
//		this.countrys = countrys;
//	}

	@Override
	public List<Server> getAllServers() {
		List<Server> result=new ArrayList<>();
		for (Country c : countrys.values()) {
			result.addAll(c.getAllServers());
		}
		return result;
	}
	@Override
	public void setAllServers(List<Server> servers) {
		//TODO
	}
	@Override
	public void removeAllServers() {
		for (Country c : countrys.values()) {
			c.removeAll();
		}
	}

	@Override
	public int getCrossType() {
		return SystemConstant.COUNTRY_CROSS;
	}

	@Override
	public boolean isExistServer(Server server) {
		for (Country c : countrys.values()) {
			if(c.isExistServer(server)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 战区管理中的国家
	 * 
	 * @author Administrator
	 */
	public class Country extends Server {
		/**
		 * 战斗力
		 */
		private int fightPower;
		/**
		 * 国家ID
		 */
		private int countryId;

		private Map<Integer, Server> serverMap = new HashMap<>();
		/**
		 * 提供给redis反射初始化用
		 */
		public Country() {
		}

		private Country(int countryId) {
			this.countryId=countryId;
		}

		public void addServer(Server server) {
			serverMap.put(server.getWorldId(), server);
		}

		/**
		 * @param id
		 *            和数据库的中的area_id是一致的关系
		 * @return
		 */
		public Server getServerById(int id) {
			return serverMap.get(id);
		}

		/**
		 * 获取所有服务器区
		 * 
		 * @return
		 */
		public ArrayList<Server> getAllServers() {
			return new ArrayList<>(serverMap.values());
		}

		public Set<Integer> getAllServerId() {
			return serverMap.keySet();
		}

		/**
		 * @param server
		 * @return true 存在
		 */
		public boolean isExistServer(Server server) {
			return null != serverMap.get(server.getWorldId());
		}

		/**
		 * @param serverId
		 * @return 从国家中删除指定的服务器
		 */
		public Server removeServer(int serverId) {
			return serverMap.remove(serverMap);
		}

		public int getFightPower() {
			return fightPower;
		}

		public void setFightPower(int fightPower) {
			this.fightPower = fightPower;
		}

		public int getCountry() {
			return countryId;
		}

		public void setCountry(int country) {
			this.countryId = country;
		}

		public void removeAll() {
			serverMap.clear();
		}

	}

	@Override
	public String getCrossAreaId() {
		return getCrossType()+"_"+getCrossServer().getWorldId();
	}
}
