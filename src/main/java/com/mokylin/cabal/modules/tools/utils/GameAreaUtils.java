package com.mokylin.cabal.modules.tools.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.redis.SystemConstant;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/20 16:01
 * 项目: cabal-tools
 */
public class GameAreaUtils {

    public static final Logger logger = LoggerFactory.getLogger(GameAreaUtils.class);


//    public static final String CACHE_GAME_SERVER_MAP = "gameAreaMap";

//    public static final String CACHE_GAME_SERVER_LIST = "gameAreaList";

    private static ServerManager serverManager = SpringContextHolder.getBean(ServerManager.class);


//    public static List<Server> getValidServerList(String pid){
//
//
//    }


    /**
     * 返回 HTTP URL
     *
     * @param serverId
     * @return http://192.168.1.1:8080
     */
    public static String getGameRemoteUrlByServerId(String serverId) {

        Server server = serverManager.getGameServer(Integer.parseInt(serverId));
        if(server == null){
            return "";
        }
        return server.createUrl();
    }


    public static Server getGameServerByServerId(String serverId){
        return serverManager.getGameServer(Integer.parseInt(serverId));
    }


    /**
     * 获取所有HTTP URL 并去除重复
     *
     * @return
     */
    public static List<String> getGameServerIdListWithoutRepetition() {
        List<Server> gameServerList = serverManager.getGameServerList();

        Set<String> returnSet = Sets.newHashSet();
        //去除重复的URL
        for (Server server : gameServerList) {
            returnSet.add(server.createUrl());
        }
        return new ArrayList<String>(returnSet);
    }

    /**
     * 去除合服的server
     *
     * @param serverIdList
     * @return serverIds
     */
    public static List<String> getGameServerIdListWithoutRepetition(List<String> serverIdList) {
//        Map<String, Server> map = Maps.newHashMap();
//        Map<Integer, Server> gameServer = serverManager.getGameServerMap();
//        for (Server server : gameServer.values()) {
//            map.put(server.createUrl(), server);
//        }
//        List<String> serverIds = Lists.newArrayList();
//        for (Server server : map.values()) {
//            serverIds.add(String.valueOf(server.getWorldId()));
//        }
        List<String> serverIds = Lists.newArrayList();
        List<Server> list = getGameServerListWithoutRepetition(serverIdList);
        for(Server server : list){
        	 if(server == null) {
                 logger.info("呃呃呃...这里有个空的服务器....从哪里冒出来了");
                 continue;
             }
            serverIds.add(String.valueOf(server.getWorldId()));
        }

        return serverIds;
    }

    /**
     * 去除合服的Server
     *
     * @return
     */
    public static List<Server> getGameServerListWithoutRepetition() {
        List<Server> gameServerList = serverManager.getGameServerList();
        List<Server> returnList = Lists.newArrayList();

        for (Server server : gameServerList) {
            if (server.getFollowerId() == 0 && server.getStatus() != SystemConstant.SERVER_STATUS_STOPED) {
                returnList.add(server);
            }
        }
        return returnList;
    }

    /**
     * 去除合服的Server
     * 如果未合服直接添加到列表，如果是已合服则根据FollowId（最终合服的目标服）查询合并的服添加到集合SET防止重复
     * @return
     */
    public static List<Server> getGameServerListWithoutRepetition(List<String> serverIdList) {
    	//List<Server> returnGameServerList = Lists.newArrayList();
        Set<Server> tmp = Sets.newHashSet();
        for (String serverId : serverIdList) {
            Server s = serverManager.getGameServer(Integer.parseInt(serverId));
            if(s == null) {
                continue;
            }
            if (s.getFollowerId() == 0 && s.getStatus() != SystemConstant.SERVER_STATUS_STOPED) {
                //returnGameServerList.add(s);
                tmp.add(s);
            }else {
            	Server  server=serverManager.getGameServer(s.getFollowerId());
            	if( server== null) {
                    continue;
                }
                tmp.add(server);
            }
        }
        return new ArrayList<Server>(tmp);
    }


    /**
     * 根据平台编号获取服务器列表
     * @param pid
     * @return
     */
    public static Collection<Server> getGameServerList(String pid){
        return serverManager.getGameServerList(pid);
    }


    public static List<Server> getGameServerListByPid(String pid) {
        Collection<Server> servers = getGameServerList(pid);
        List<Server> ret = new ArrayList<Server>(servers);
        return ret;
    }


    public static Server getRandomGameServer(String pid) {
        List<Server> serverList = getGameServerListByPid(pid);
        return serverList.get(0);
    }

    /**
     * 根据平台ID获取平台名称，没有获取到则返回默认值
     * @param id
     * @param defaultVal
     * @return
     */
	public static String getGamePlatformNameById(String id, String defaultVal) {
		for (GamePlatform gamePlatform : UserUtils.getGamePlatformList()) {
			if (gamePlatform.getPid().equals(id)) {
				return gamePlatform.getName();
			}
		}
		return defaultVal;
	}
	
	public static GamePlatform getGamePlatformByPId(String pid) {
		for (GamePlatform gamePlatform : UserUtils.getGamePlatformList()) {
			if (gamePlatform.getPid().equals(pid)) {
				return gamePlatform;
			}
		}
		return null;
	}

}
