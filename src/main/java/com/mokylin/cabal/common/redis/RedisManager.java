package com.mokylin.cabal.common.redis;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mokylin.cabal.async.AsyncService;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.sys.constant.ChannelConstant;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import com.mokylin.cabal.modules.tools.vo.IpBlack;
import com.mokylin.cabal.modules.tools.vo.UserBlack;


/**
 * 作者: 稻草鸟人 日期: 2014/11/26 14:21 项目: cabal-tools
 */
@Component
public class RedisManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);

    /**
     * 全局库缓存
     */
    private Redis chatCache;
    private Redis gameCache;
    private Redis roleCache;
    //private Redis msgCache;

    /**
     * 战区缓存
     */
    private Redis crossAreaCache;
	/**
	 * 战区缓存
	 */
	private Redis crossCache;

    public RedisManager() {
    }

    public RedisManager(String ip, int port, int index) {
        chatCache = new Redis(ip, port, index);
    }


	@PostConstruct
	public void init() {
		chatCache = new Redis(Global.REDIS_HOST, Global.REDIS_PORT.intValue(), Global.CHAT_REDIS_INDEX);
		gameCache = new Redis(Global.REDIS_HOST, Global.REDIS_PORT.intValue(), Global.GAME_REDIS_INDEX);
		roleCache = new Redis(Global.ROLE_REDIS_HOST, Global.REDIS_PORT.intValue(), Global.ROLE_REDIS_INDEX);
		crossCache = new Redis(Global.CROSS_REDIS_HOST, Global.CROSS_REDIS_PORT.intValue(), Global.CROSS_REDIS_INDEX);
		//msgCache = new Redis(Global.REDIS_HOST, Global.REDIS_PORT.intValue(), Global.MSG_REDIS_INDEX);
	}

    public void init(String ip, int port, int globalIndex, int gameIndex) {
        chatCache = new Redis(ip, port, globalIndex);
        gameCache = new Redis(ip, port, gameIndex);
    }


    public List<IpBlack> getIpBlackList() {

        Map<String, String> map = gameCache.hgetAll(RedisConstant.KEY_BLACK_LIST_IP);
        Collection<IpBlack> ipBlacks = Maps.transformEntries(map, new Maps.EntryTransformer<String, String, IpBlack>() {
            @Override
            public IpBlack transformEntry(String key, String value) {
                return JSON.parseObject(value, IpBlack.class);
            }
        }).values();

        return new ArrayList<IpBlack>(ipBlacks);
    }

	public long addIp2BlackList(IpBlack ipBlack){
		String value = JSON.toJSONString(ipBlack);
		gameCache.publish(RedisConstant.CHANEL_BLACK_LIST_IP, ChannelConstant.BLACK_LIST_TYPE_UPDATE + RedisConstant.CHANNEL_SEPARATOR + value);
		return gameCache.hset(RedisConstant.KEY_BLACK_LIST_IP, ipBlack.getIp(), value);
	}

	public IpBlack getIpBlackByIp(String ip){
		String value = gameCache.hget(RedisConstant.KEY_BLACK_LIST_IP, ip);
		return JSON.parseObject(value, IpBlack.class);
	}

	public void delIpBlackList(String ip){
		gameCache.publish(RedisConstant.CHANEL_BLACK_LIST_IP,ChannelConstant.BLACK_LIST_TYPE_DELETE + RedisConstant.CHANNEL_SEPARATOR + ip);
		gameCache.hdel(RedisConstant.KEY_BLACK_LIST_IP, ip);
	}


    public Map<String, String> getKeywords() {
        Map<String, String> map = Maps.newHashMap();
        Set<String> keys = chatCache.keys("keyword_*");
        for (String key : keys) {
            map.put(key, chatCache.get(key));
        }
        return map;
    }

	public List<UserBlack> getUserBlackList(){
		List<UserBlack> userBlackList = Lists.newArrayList();
		Set<String> keys = gameCache.keys(RedisConstant.KEY_BLACK_LIST_USER+"*");
		for (String key : keys) {
			Map<String,String> innerMap = gameCache.hgetAll(key);
			Collection<UserBlack> userBlacks = Maps.transformEntries(innerMap, new Maps.EntryTransformer<String, String, UserBlack>() {
				@Override
				public UserBlack transformEntry(String key, String value) {
					return JSON.parseObject(value, UserBlack.class);
				}
			}).values();
			userBlackList.addAll(new ArrayList<UserBlack>(userBlacks));
		}

		return userBlackList;
	}

	public long addUser2BlackList(UserBlack userBlack){
		String value = JSON.toJSONString(userBlack);
		gameCache.publish(RedisConstant.CHANEL_BLACK_LIST_USER + userBlack.getPid(), ChannelConstant.BLACK_LIST_TYPE_UPDATE + RedisConstant.CHANNEL_SEPARATOR + value);
		return gameCache.hset(RedisConstant.KEY_BLACK_LIST_USER + userBlack.getPid(), userBlack.getUserId(), value);
	}

	public UserBlack getUserBlackByUserPid(String pid, String userId){
		String value = gameCache.hget(RedisConstant.KEY_BLACK_LIST_USER + pid, userId);
		return JSON.parseObject(value, UserBlack.class);
	}

	public void delUserBlackList(String pid, String userId){
		gameCache.publish(RedisConstant.CHANEL_BLACK_LIST_USER + pid,ChannelConstant.BLACK_LIST_TYPE_DELETE + RedisConstant.CHANNEL_SEPARATOR + userId);
		gameCache.hdel(RedisConstant.KEY_BLACK_LIST_USER + pid, userId);
	}

    public void addKeyword(String key, String value) {
        chatCache.set(key, value);
    }

    public void deleteKeyword(String key) {
        chatCache.del(key);
    }

    public List<String> getChatContent() {
        return chatCache.lrange("chat_records", 0, -1);
    }

    public long lpush(String key, String value) {
        return chatCache.lpush(key, value);
    }

    //redis添加关键字key,当角色金币大于这个值时才会记录金币的获取情况
    public void addJinbiLogLimit(String value) {
        gameCache.set(ConfigConstants.KEY_JIN_BI_LIMIT, value);
        gameCache.publish(ConfigConstants.CHANNEL_JINBI, value);
    }

    public String getJinbiLimit() {
        return gameCache.get(ConfigConstants.KEY_JIN_BI_LIMIT);
    }

    public void addGoodsLogLimit(String value) {
        gameCache.set(ConfigConstants.KEY_GOODS_LIMIT, value);
        gameCache.publish(ConfigConstants.CHANNEL_GOODS, value);
    }

    public void addGameUpdateLog(String value) {
        gameCache.set(ConfigConstants.KEY_NOTICE, value);
        gameCache.publish(ConfigConstants.CHANNEL_NOTICE, value);
    }

    public String getGameUpdateLog() {
        return gameCache.get(ConfigConstants.KEY_NOTICE);
    }


    public String getGoodsLimit() {
        return gameCache.get(ConfigConstants.KEY_GOODS_LIMIT);
    }


    public Map<String, String> getGameCrossRelation() {
        return gameCache.hgetAll(RedisConstant.KEY_GAME_CROSS);
    }

    public void setGameCrossRelation(List<String> serverIds, String crossId) {

		Set<Integer> set = Sets.newHashSet();

		//删除和crossId有关联的关系
		List<Integer> serverIdList = getGameServerListByCrossId(crossId);
		if(serverIdList != null){
			for(Integer serverId : serverIdList){
				gameCache.hdel(RedisConstant.KEY_GAME_CROSS, serverId.toString());

				//gameCache.publish(RedisConstant.CHANNEL_GAME_CROSS + serverId, crossId);	//随便发点啥告诉游戏这个服发生变化了
			}
			set.addAll(serverIdList);
		}

		//重新设定crossId的关系
		if(serverIds != null){
			for(String serverId : serverIds){
				//删除serverId旧的关系
				gameCache.hdel(RedisConstant.KEY_GAME_CROSS, serverId);
				//增加serverId新关系
				gameCache.hset(RedisConstant.KEY_GAME_CROSS, serverId, crossId);

				//gameCache.publish(RedisConstant.CHANNEL_GAME_CROSS + serverId, crossId);	//随便发点啥告诉游戏这个服发生变化了

				set.add(Integer.parseInt(serverId));
			}
		}

		for(Integer serverId : set){
			gameCache.publish(RedisConstant.CHANNEL_GAME_CROSS + serverId, crossId);	//随便发点啥告诉游戏这个服发生变化了
		}
	}

    public List<Integer> getGameServerListByCrossId(String crossId) {
        Map<String, String> map = getGameCrossRelation();
        Map<String, List<Integer>> out = new HashMap(map.size());
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            Object value = entry.getValue();
            if (out.containsKey(value)) {
                List<Integer> serverIds = out.get(value);
                serverIds.add(Integer.parseInt(entry.getKey().toString()));
            } else {
                List<Integer> list = Lists.newArrayList();
                list.add(Integer.parseInt(entry.getKey().toString()));
                out.put(value.toString(), list);
            }
        }
        List<Integer> ret = out == null ? null : out.get(crossId);
        if (ret != null) Collections.sort(ret);
        return ret;
    }

    public List<Server> getServerList(String type) {

        List<Server> ret = Lists.newArrayList();
        Map<String, String> map = gameCache.hgetAll(type);
        Collection<String> list = map.values();
        for (String e : list) {
            Server server = JSON.parseObject(e, Server.class);
            ret.add(server);
        }
        return ret;
    }



	public String getRoleIdByRoleName(String pid, String name, String defaultVal){
		//String id = roleCache.hget(pid +RedisConstant.CHANNEL_SEPARATOR + RedisConstant.KEY_ROLE_NAME, name);

		String id = roleCache.hget(pid +RedisConstant.CHANNEL_SEPARATOR + RedisConstant.KEY_ROLE_NAME, name);

		if(StringUtils.isNotEmpty(id)) {
			return id;
		}

		return defaultVal;
	}

	public String getRoleNameByRoleId(String roleId, String defaultVal){
		String name = getVal(roleId, RedisConstant.ROLE_FIELD_NAME);
		if(name != null){
			return name;
		}
		return defaultVal;
	}


	public String getUserIdByRoleId(String roleId, String defaultVal){
		String name = getVal(roleId, RedisConstant.ROLE_FIELD_USER_ID);
		if(name != null){
			return name;
		}
		return defaultVal;
	}

	public String getVal(String key, String field){
		return roleCache.hget(key,field);
	}


    public Server getServer(String keyServerGlobal) {
        return null;
    }

    public Redis getGlobalCache() {
        return chatCache;
    }

    public Redis getGameCache() {
        return gameCache;
    }

    public Redis getCrossAreaCache() {
        return crossAreaCache;
    }

	public Redis getcrossCache() {
		return crossCache;
	}

//	public void saveCrossAreaToRedis(BaseCrossArea crossArea) {
//		this.crossCache.hset(RedisConstant.KEY_CROSS_AREA, crossArea.getCrossType() + "_" + crossArea.getCrossServer().getWorldId(), crossArea.toJson());
//	}



	public void updateBattleArea(BattleArea battleArea){
		String value = JSON.toJSONString(battleArea);
		crossCache.hset(RedisConstant.BATTLE_KEY, String.valueOf(battleArea.getCrossId()), value);
		//改好了，要不要通知游戏服？？  费铃说目前不需要
    }


    /**
     * 客服和玩家私聊
     */
    public void publish(int worldId, int type, Object chat) {
        String value = JSON.toJSONString(chat);
        chatCache.publish(RedisConstant.CHANNEL_SERVER + worldId, type + RedisConstant.CHANNEL_SEPARATOR + value);
    }


	public Map<String,BattleArea> getBattleAreaMap(){
		Map<String,String> map = crossCache.hgetAll(RedisConstant.BATTLE_KEY);
		Map<String,BattleArea> ret = Maps.newHashMap();
		for(Map.Entry<String,String> entry : map.entrySet()){
			ret.put(entry.getKey(), JSONObject.parseObject(entry.getValue(), BattleArea.class));
		}
		return ret;
	}


	public Map<String, Country> getCountryMap(){
		Map<String,String> map = crossCache.hgetAll(RedisConstant.COUNTRY_KEY);
		Map<String, Country> ret = Maps.newHashMap();
		for(Map.Entry<String,String> entry : map.entrySet()){
			ret.put(entry.getKey(), JSONObject.parseObject(entry.getValue(), Country.class));
		}
		return ret;
	}

	public Map<String, String> getAllChenMi(){
		return gameCache.hgetAll(RedisConstant.KEY_GATE_OPEN_CHENMI);
	}

	public void addChenMi(String pid, String isOpen){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(pid, isOpen);

		//更新redis
		gameCache.hset(RedisConstant.KEY_GATE_OPEN_CHENMI, pid, String.valueOf(isOpen));
		//发布redis事件
		gameCache.publish(RedisConstant.CHANNEL_GATE_CHENMI, jsonObj.toString());
	}

	/** 删除指定平台的沉迷配置 */
	public  void delChenMi(String pid){
		//更新redis
		gameCache.hdel(RedisConstant.KEY_GATE_OPEN_CHENMI, pid);
		//发布redis事件
		gameCache.publish(RedisConstant.CHANNEL_GATE_CHENMI_DEL, pid);
	}


	public Map<String, String> getAllClientUrl(){
		return gameCache.hgetAll(RedisConstant.KEY_CLIENT_URL);
	}

	public void addClient(String serverId, String isOpen){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(serverId, isOpen);

		//更新redis
		gameCache.hset(RedisConstant.KEY_CLIENT_URL, serverId, String.valueOf(isOpen));
		//发布redis事件
		gameCache.publish(RedisConstant.CHANNEL_CLIENT_URL, jsonObj.toString());
	}

	/** 删除指定平台的沉迷配置 */
	public  void delClient(String serverId){
		//更新redis
		gameCache.hdel(RedisConstant.KEY_CLIENT_URL, serverId);
		//发布redis事件
		gameCache.publish(RedisConstant.CHANNEL_CLIENT_URL_DEL, serverId);
	}

//	/** 设置测试服的sid*/
//	public  void updateTestSid(String serverId){
//		//更新redis
//		gameCache.hset(RedisConstant.KEY_TEST_SID, RedisConstant.KEY_TEST_SID, serverId);
//		//发布redis事件
//		gameCache.publish(RedisConstant.CHANNEL_TEST_SID, serverId);
//	}
//	/** 获得测试服的sid*/
//	public String getTestSid(){
//		//更新redis
//		return gameCache.hget(RedisConstant.KEY_TEST_SID, RedisConstant.KEY_TEST_SID);
//	}

	public void updateCountry(Country country){
		String value = JSON.toJSONString(country);
		crossCache.hset(RedisConstant.COUNTRY_KEY, String.valueOf(country.getId()), value);
	}
	
	/**
	 * 页面上存储coopbase
	 */
	public  void setCoopBase(String coopname,String jsonString){
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(coopname, jsonString);
		//更新redis
		gameCache.hset(RedisConstant.KEY_GATE_CB, coopname, jsonString);
		//发布redis事件
		gameCache.publish(RedisConstant.CHANNEL_GATE_CB, jsonString);
	}
	
	public List<CoopBase> getAllCoopBase(){
		Map<String,String> map= gameCache.hgetAll(RedisConstant.KEY_GATE_CB);
		
		
		Collection<CoopBase> coopBase = Maps.transformEntries(map, new Maps.EntryTransformer<String, String, CoopBase>() {
			@Override
			public CoopBase transformEntry(String key, String value) {
				return JSON.parseObject(value, CoopBase.class);
			}
		}).values();

		return new ArrayList<CoopBase>(coopBase);
	}
	

	/** 获取指定coopname 的 coop_base 配置 */
	public  CoopBase getCoopBase (String field){
		String value= gameCache.hget(RedisConstant.KEY_GATE_CB, field);
		return JSON.parseObject(value, CoopBase.class);
	}
	
	public void destory(){
		try{
			if( chatCache != null ){
				chatCache.destory();
			}
			if( gameCache != null ){
				gameCache.destory();
			}
			if( roleCache != null ){
				roleCache.destory();
			}
			if( crossAreaCache != null ){
				crossAreaCache.destory();
			}
			if( crossAreaCache != null ){
				crossAreaCache.destory();
			}
		}catch(Exception e){
			LOGGER.error("关闭redis订阅出错", e);
		}
	}
	
	public String getAllModules(){
		return gameCache.get(RedisConstant.GAME_COMMAND);
	}

}
