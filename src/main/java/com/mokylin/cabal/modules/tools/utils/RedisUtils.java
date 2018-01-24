package com.mokylin.cabal.modules.tools.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.core.DataContext;
import com.mokylin.cabal.common.redis.BattleArea;
import com.mokylin.cabal.common.redis.Country;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/3/2 11:50
 * 项目: admin-tools
 */
public class RedisUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class.getName());

    private static RedisManager redisManager = SpringContextHolder.getBean(RedisManager.class);
    private static ServerManager serverManager = SpringContextHolder.getBean(ServerManager.class);

    public static String getRoleNameByRoleId(String roleId,String defaultVal){
        return redisManager.getRoleNameByRoleId(roleId,defaultVal);
    }


    public static String getRoleIdByRoleName(String pid ,String name, String defaultVal){
        return redisManager.getRoleIdByRoleName(pid, name, defaultVal);
    }

    public static String getPlatformByRoleId(String roleId,String defaultVal){
        return redisManager.getRoleNameByRoleId(roleId,defaultVal);
    }
    
    public static String getUserIdByRoleId(String roleId,String defaultVal){
        return redisManager.getUserIdByRoleId(roleId,defaultVal);
    }

    public static String getGameServerListByCrossId(String crossId) {
        String ret;
        List<Integer> list = redisManager.getGameServerListByCrossId(crossId);
        ret = list == null? "" : StringUtils.join(list.iterator(),",");
        return ret;
    }

    public static List<Server> getGameServerListByPid(){
        List<Server> serverList = Lists.newArrayList();
        Collection<Server> servers = serverManager.getGameServerList(DataContext.getInstance(UserUtils.getUser().getId()).getPid());
        for(Server server : servers){
            serverList.add(server);
        }
        Collections.sort(serverList, new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                return o1.getWorldId() - o2.getWorldId();
            }
        });
        return serverList;
    }


    public static Server getGameServer(String serverId) {
        return serverManager.getGameServer(Integer.parseInt(serverId));
    }

    /**
     * 处理合服问题
     * @param serverId
     * @return
     */
    public static Server getActualGameServer(String serverId){
        LOGGER.info("选择实际服务器（处理合服问题）,当前服务器:{}", serverId);
        Server server = serverManager.getGameServer(Integer.parseInt(serverId));
        if(server != null && server.getFollowerId() >0) {
            server = serverManager.getGameServer(server.getFollowerId());
        }
        return server;
    }

    
    public static  List<Country> getCountry(String crossId){
    	Collection<BattleArea> battleArea = serverManager.getBattleAreaList();
		Map<String, Country> countryAllMap = serverManager.getCountryMap();
    	Map<String, Country> countryMap=Maps.newHashMap();   
		List<Integer> countryIds=null;
		for(BattleArea area : battleArea){
			countryIds = area.getCountries();
			if(countryIds.size()==0){
				for(Iterator<Map.Entry<String,Country>> it=countryAllMap.entrySet().iterator();it.hasNext();){
					  Map.Entry<String,Country> me=it.next(); 
					  //遍历所有contry,从中取出不同的id,讲id存放
					  if(me.getKey()!=null){
						  countryMap.put(me.getKey(), countryAllMap.get(me.getKey()));
					  }
				}
			}else{
				for(Integer countryId : countryIds){
					//countryMap.put(String.valueOf(countryId), serverManager.getCountry(String.valueOf(countryId)));
					for(Iterator<Map.Entry<String,Country>> it=countryAllMap.entrySet().iterator();it.hasNext();){
						  Map.Entry<String,Country> me=it.next(); 
						  //遍历所有contry,从中取出不同的id,讲id存放
						  if(!me.getKey().equals(String.valueOf(countryId))){
							  countryMap.put(me.getKey(), countryAllMap.get(me.getKey()));
						  }
					}
				}
			}
			BattleArea  b=serverManager.getBattleArea(crossId);
	    	List<Integer> countryBatIds=b.getCountries();
	    	for(Integer countryId : countryBatIds){
	    		countryMap.remove(String.valueOf(countryId));
			}
		}
		return new ArrayList<Country>(countryMap.values());
	}

}
