package com.mokylin.cabal.common.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 匹配玩法
 * @author Administrator
 *
 */
public class BattleCrossArea extends BaseCrossArea{
	private Map<Integer,Server> serversMap=new HashMap<Integer, Server>();

	public BattleCrossArea() {
		
	}
	public BattleCrossArea(String crossAreaName,Server crossServer) {
		super(crossAreaName, crossServer);
	}

	@Override
	public List<Server> getAllServers() {
		return new ArrayList<>(serversMap.values());
	}

	/* 
	 * 从redis中初始化数据的时候会调用此方法给serversMap做数据初始话
	 * (non-Javadoc)
	 * @see com.mokylin.cabal.common.redis.BaseCrossArea#setAllServers(java.util.List)
	 */
	@Override
	public void setAllServers(List<Server> servers) {
		for (Server s : servers) {
			serversMap.put(s.getWorldId(), s);
		}
	}
	@Override
	public void removeAllServers() {
		serversMap.clear();
	}

	@Override
	public int getCrossType() {
		return SystemConstant.BATTLE_CROSS;
	}

	@Override
	public boolean isExistServer(Server server) {
		return null!=serversMap.get(server.getWorldId());
	}
	public void addServer(Server server) {
		serversMap.put(server.getWorldId(), server);
	}
	@Override
	public String getServerName(){
		StringBuffer result=new StringBuffer();
		for (Server s : serversMap.values()) {
			result.append(s.getWorldName()+" ");
		}
		return result.toString();
	}

	@Override
	public String toJson() {
		String json = JSONObject.toJSONString(this);
		return json;
	}
	@Override
	public String getCrossAreaId() {
		return getCrossType()+"_"+getCrossServer().getWorldId();
	}
}
