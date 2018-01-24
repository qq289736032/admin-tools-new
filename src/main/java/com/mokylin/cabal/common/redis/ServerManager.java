package com.mokylin.cabal.common.redis;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Component
public class ServerManager {

    private static final Logger logger = LoggerFactory.getLogger(ServerManager.class);

    @Resource
    private ToolDaoTemplate toolDaoTemplate;

    /**
     * 存放redis中的服务器信息的
     */
    // key-pid value-Map
    private Map<String, GameAreaMap> gameAreaMap = Maps.newHashMap();
    //key-worldId value server databases
    private Map<Integer,Server> gameArea = Maps.newHashMap();
//    private Map<String, BaseCrossArea> crossAreaMap = new HashMap<>();
    // key-wordId value-Server redis
    private Map<Integer, Server> gameServerMap = Maps.newHashMap();
    private Map<Integer, Server> crossServerMap = Maps.newHashMap();

    private Map<String,BattleArea> battleAreaMap = Maps.newHashMap();
    private Map<String,Country> countryMap = Maps.newHashMap();

    private Map<Integer, Server> loggerServerMap = new HashMap<>();

    private Server globalServer;

    @Resource
    private RedisManager redisManager;

    public ServerManager() {
    }

    @PostConstruct
    public void init() {

        SubScribeManager subScribeManager = new SubScribeManager();
        subScribeManager.init();

        logger.info("项目启动-缓存初始化开始");

        //初始化 gameAreaMap 从本地数据库中取
        List<Server> servers = toolDaoTemplate.selectList("gameArea.findAllGameArea");
        for (Server server : servers) {
            gameArea.put(server.getWorldId(), server);
            GameAreaMap gam = gameAreaMap.get(server.getPlatformId());
            if (gam == null) {
                gam = new GameAreaMap();
                gameAreaMap.put(server.getPlatformId(), gam);
            }
            gam.addGameArea(server);
        }
        logger.info("项目启动-缓存初始化,gameAreaMap初始化结束，大小[{}]", gameAreaMap.size());


        logger.info("载入日志服务器列表开始");
        List<Server> list = redisManager.getServerList(RedisConstant.KEY_SERVER_LOGGER);
        for (Server e : list) {
            loggerServerMap.put(e.getWorldId(), e);
        }
        logger.info("载入日志服务器列表完毕 num={}", loggerServerMap.size());

        List<Server> serverList = redisManager.getServerList(RedisConstant.KEY_SERVER_GAME);
        // 初始化 gameServerMap
        for (Server server : serverList) {
            gameServerMap.put(server.getWorldId(), server);
           // syncGameServer(server);
            getGameServer(server,gameArea.get(server.getWorldId()));

        }
        logger.info("项目启动-缓存初始化,gameServerMap初始化结束，大小[{}]", gameServerMap.size());

		List<Server> crossServerList = redisManager.getServerList(RedisConstant.KEY_SERVER_CROSS);
		// 初始化 crossServerMap
		for (Server server : crossServerList) {
			//if (server.getSubType() == 2) {   //TODO subType = 2 为战区服务器
				crossServerMap.put(server.getWorldId(), server);
			//}
		}
		logger.info("项目启动-缓存初始化,crossServerList初始化结束，大小[{}]", crossServerMap.size());


        battleAreaMap = redisManager.getBattleAreaMap();
        logger.info("项目启动-缓存初始化,battleAreaMap，大小[{}]", battleAreaMap.size());

        countryMap = redisManager.getCountryMap();
        logger.info("项目启动-缓存初始化,countryMap，大小[{}]", countryMap.size());
		// 初始化 crossServerMap
//		List<BaseCrossArea> crossAreas = redisManager.selectALLCrossAreaFromRedis();
//		for (BaseCrossArea b : crossAreas) {
//			crossAreaMap.put(b.getCrossAreaId(), b);
//		}
//		logger.info("项目启动-缓存初始化,crossAreaMap初始化结束，大小[{}]", crossAreaMap.size());
    }

    public Server getGameServer(int serverId) {
        logger.debug("根据服务器编号获取服务器信息gameServerMap:{}", JSON.toJSONString(gameServerMap));
        return gameServerMap.get(serverId);
    }

    public Server getCrossServer(int serverId) {
        return crossServerMap.get(serverId);
    }

    public List<Server> getCrossServerList() {
        return new ArrayList<>(crossServerMap.values());
    }

    public Server getGlobalServer() {
        return globalServer;
    }


    public Country getCountry(String countryId){
        return countryMap.get(countryId);
    }

    public Collection<Country> getCountryList(){
        return countryMap.values();
    }

    public Collection<BattleArea> getBattleAreaList(){
        return  battleAreaMap.values();
    }


    public Collection<Server> getLoggerServerList() {
        return loggerServerMap.values();
    }


    //移除BattleArea对象中国家ID
    public void removeBattleAreaCountry(String countryId) {
        Country country = getCountry(countryId);
        BattleArea battleArea = getBattleArea(String.valueOf(country.getCrossId()));
        battleArea.removeCountry(Integer.parseInt(countryId));
        battleAreaMap.put(String.valueOf(battleArea.getCrossId()), battleArea);
        //修改redis中的数据
        redisManager.updateBattleArea(battleArea);
    }

    public BattleArea getBattleArea(String crossId) {
        return battleAreaMap.get(crossId);
    }

    public List<Country> getCountryListByCrossId(String crossId){
        BattleArea battleArea = battleAreaMap.get(crossId);
        List<Country> countryList = Lists.newArrayList();
        List<Integer> countryIdList = null;
        if(battleArea != null) {
            countryIdList =  battleArea.getCountries();
        }
        if(countryIdList != null && countryIdList.size() > 0){
            for (Integer countryId : countryIdList){
                countryList.add(countryMap.get(String.valueOf(countryId)));
            }
        }
        return countryList;
    }

    public Map<String, Country> getCountryMap(){
        return countryMap;
    }

    public void updateBattleArea(BattleSubBean obj){

        if(obj.getType() == 1){ //创建国家
            countryMap.put(String.valueOf(obj.getId()), JSON.parseObject(JSON.toJSONString(obj.getObj()),Country.class));
        }else if(obj.getType() == 2){ //创建战区
            battleAreaMap.put(String.valueOf(obj.getId()), JSON.parseObject(JSON.toJSONString(obj.getObj()),BattleArea.class));
        }
    }

    public void destroy(int type, int worldId) {
        logger.info("注销服务器 type={}，worldId={}", type, worldId);
        Server server = null;
        switch (type) {
            case SystemConstant.SERVER_TYPE_GAME: {
                destroyGameServer(worldId);
                break;
            }
            case SystemConstant.SERVER_TYPE_LOGGER: {
                server = loggerServerMap.get(worldId);
                break;
            }
            case SystemConstant.SERVER_TYPE_CROSS: {
                server = crossServerMap.get(worldId);
                break;
            }
            case SystemConstant.SERVER_TYPE_GLOBAL: {
                server = globalServer;
                break;
            }
        }
        if (server != null) {
            server.setStatus(SystemConstant.SERVER_STATUS_STOPED);
        }
    }

    public void register(Server server) {
        logger.info("添加服务器 server={}", server.toString());
        switch (server.getType()) {
            case SystemConstant.SERVER_TYPE_GAME: {
                syncGameServer(server);
                gameServerMap.put(server.getWorldId(), server);
                break;
            }
            case SystemConstant.SERVER_TYPE_LOGGER: {
                loggerServerMap.put(server.getWorldId(), server);
                break;
            }
            case SystemConstant.SERVER_TYPE_CROSS: {
                crossServerMap.put(server.getWorldId(), server);
                break;
            }
            case SystemConstant.SERVER_TYPE_GLOBAL: {
                // globalServer = server;
                break;
            }
        }
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    /**
     * 注册游戏服务器
     *
     * @param server
     */
    private void registerGameServer(Server server) {
        try {
            MybatisParameter parameter = createNewMybatisParameter();
           // parameter.put("server", server);
            parameter.put("worldId", server.getWorldId());
            parameter.put("version", server.getVersion());
            List<Map<String, Object>> gameServer = toolDaoTemplate.selectList("gameArea.findGameAreaByWorldId", parameter);
            if (null != gameServer && gameServer.size() > 0) {
                toolDaoTemplate.update("gameArea.update", parameter);
            } else {
                toolDaoTemplate.insert("gameArea.insert", parameter);
            }
            gameServerMap.put(server.getWorldId(), server);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 注销游戏服务器
     *
     * @param worldId
     */
    private void destroyGameServer(int worldId) {
        try {
            MybatisParameter parameter = createNewMybatisParameter();

            parameter.put("worldId", worldId);
            //List<Map<String, Object>> gameServer = toolDaoTemplate.selectList("gameArea.findGameAreaByWorldId", parameter);
            Server gameServer = gameServerMap.get(worldId);
            if (null != gameServer) {
                gameServer.setStatus(SystemConstant.SERVER_STATUS_STOPED);
                Server server = getGameServerByServerId(gameServer.getPlatformId(), gameServer.getWorldId());
                if (server != null) {
                    parameter.put("status", SystemConstant.SERVER_STATUS_STOPED);// 设置服务器为停止状态
                    toolDaoTemplate.update("gameArea.update", parameter);
                }
            }

            gameArea.put(worldId,gameServer);

            //服务器发生变化的时候，gameAreaMap也要更新
            GameAreaMap map = gameAreaMap.get(gameServer.getPlatformId());
            if (map == null) {
                map = new GameAreaMap();
                gameAreaMap.put(gameServer.getPlatformId(), map);
            }
            map.addGameArea(gameServer);
            logger.info("gameAreaMap 修改数据，平台:【{}】,服务器：【{}】，大小【{}】,状态【{}】", gameServer.getPlatformId(),
                    gameServer.getWorldId(), gameAreaMap.size(),gameServer.getStatus());


//            gameServerMap.remove(worldId);    //这里不能remove掉，remove之后切换数据源会报空指针
            gameServerMap.put(worldId,gameServer);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * 创建执行sql的基本条件，设置必须的初始值
     *
     * @return
     */
    private MybatisParameter createNewMybatisParameter() {
        MybatisParameter parameter = new MybatisParameter<>();
        parameter.put("id", IdGen.uuid());
        return parameter;
    }

    /**
     * 先保存平台，在保存服务器信息
     * @param server
     */
    private void insertGameAreaAndGamePlatform(Server server){
        saveGamePlatform(server);
        insert(server);
    }

    /**
     * 如果平台不存在则保存平台
     * @param server
     */
    private void saveGamePlatform(Server server){
        MybatisParameter parameter = new MybatisParameter();
        parameter.put("pid", String.valueOf(server.getPlatformId()));
        GamePlatform gamePlatform = toolDaoTemplate.selectOne("gamePlatform.findGamePlatformByPid",parameter);
        if(gamePlatform == null){
            parameter.put("id", IdGen.uuid());
            parameter.put("name",String.valueOf(server.getPlatformId()));
            parameter.put("pid", String.valueOf(server.getPlatformId()));
            parameter.put("description", String.valueOf(server.getPlatformId()));
            toolDaoTemplate.insert("gamePlatform.insert",parameter);
        }
    }

    private void insert(Server server) {
        //更新gameAreaMap
        gameArea.put(server.getWorldId(), server);
        logger.info("gameArea 添加数据，大小【{}】",gameArea.size());

        GameAreaMap map = gameAreaMap.get(server.getPlatformId());
        if (map == null) {
            map = new GameAreaMap();
            gameAreaMap.put(server.getPlatformId(), map);
            logger.info("gameAreaMap 添加数据，大小【{}】",gameAreaMap.size());
        }
        map.addGameArea(server);
        //server.setOpenTime(new Date());
        //更新数据库
        MybatisParameter parameter = createNewMybatisParameter();
        parameter.put("server", server);
        parameter.put("pid", server.getPlatformId());
        parameter.put("version", server.getVersion());
        toolDaoTemplate.insert("gameArea.insert", parameter);
    }


    /**
     * 服务器重启的时候，获取redis中最新的服务器列表信息，更新到数据库
     * 订阅的服务器也走这边
     * 如果本地数据库中不存在当前服，则插入，否则更新本地数据库
     *
     * @param server
     */
    public void syncGameServer(Server server) {
        Map map = Maps.newHashMap();
        map.put("worldId", server.getWorldId());
        //Server gameServer = toolDaoTemplate.selectOne("gameArea.selectByWorldId",map);
        Server gameServer= gameArea.get(server.getWorldId());
        if (gameServer == null) {
            insertGameAreaAndGamePlatform(server);
        } else {
            if (!gameServer.equals(server)) {
                MybatisParameter parameter = createNewMybatisParameter();
                GameAreaMap gam = gameAreaMap.get(server.getPlatformId());
           	    gam.addGameArea(server);
                gameArea.put(server.getWorldId(),server);

                parameter.put("worldId", server.getWorldId());
                parameter.put("server", server);
                parameter.put("pid", server.getPlatformId());
                parameter.put("version", server.getVersion());
                toolDaoTemplate.update("gameArea.updateByWorldId", parameter);
            }
        }
    }

    public Server getGameServerByServerId(String pid, int serverId) {
        Server server = null;
        GameAreaMap temp = gameAreaMap.get(pid);
        if (temp != null) {
            server = temp.getGameAreaByWordId(serverId);
        }
        return server;
    }


    public Server getGameServerByWorldId(int worldId) {
        return gameServerMap.get(worldId);
    }

    public Map<Integer, Server> getGameServerMap() {
        return gameServerMap;
    }

    public List<Server> getGameServerList(List<String> serverId) {
        List<Server> serverList = Lists.newArrayList();
        for (String id : serverId) {
            serverList.add(this.getGameServer(Integer.parseInt(id)));
        }
        return serverList;
    }

    public List<Server> getGameServerList() {
        List<Server> serverList = Lists.newArrayList();
        for (Map.Entry<Integer, Server> entry : gameServerMap.entrySet()) {
            serverList.add(entry.getValue());
        }
        return serverList;
    }


    public List<Server> getValidGameServerList(String pid){
        List<Server> ret = Lists.newArrayList();
        Collection<Server> list = getGameServerList(pid);
        for(Server server : list){
            if(server.isValid()){
                ret.add(server);
            }
        }
        return ret;
    }

    // 更具平台ID获取服务器列表
    public Collection<Server> getGameServerList(String pid) {
        GameAreaMap map = gameAreaMap.get(pid);
        if (map == null) {
            return Collections.emptyList();
        }
        return map.values();
    }

    //根据平台ID和areaId获取服务器Id
    public String getGameServer(String pid, String areaId){
        Collection<Server> servers = getGameServerList(pid);
        if(!servers.isEmpty()){
            for(Server server : new ArrayList<Server>(servers)){
                if(String.valueOf(server.getId()).equals(areaId)){
                    return String.valueOf(server.getWorldId());
                }
            }
        }
        return "";
    }



    // 获取所有平台的ID
    public Collection<String> getPlatformIdList() {
        return gameAreaMap.keySet();
    }

    public class GameAreaMap {
        private Map<Integer, Server> serverMap = Maps.newHashMap();

        public void addGameArea(Server server) {
            serverMap.put(server.getWorldId(), server);
        }

        public Server getGameAreaByWordId(Integer wordId) {
            return serverMap.get(wordId);
        }

        public Collection<Server> values() {
            return serverMap.values();
        }

        public Set<Integer> keys() {
            return serverMap.keySet();
        }
        public void removeGameWorld(Server server) {
            serverMap.remove(server.getWorldId());
        }
    }

//	/**
//	 * 获取战区信息
//	 * 
//	 * @return
//	 */
//	public Map<String, BaseCrossArea> getCrossAreaMap() {
//		return crossAreaMap;
//	}

    /**
     * 获取匹配服务器战区信息
     *
     * @return
     */
//    public List<BaseCrossArea> getBattleCrossArea() {
//        List<BaseCrossArea> result = new ArrayList<>();
//        for (BaseCrossArea b : crossAreaMap.values()) {
//            if (b.getCrossType() == SystemConstant.BATTLE_CROSS) {
//                result.add(b);
//            }
//        }
//        return result;
//    }

	/**
	 * 获取国家战区信息
	 * 
	 * @return
	 */
//	public List<BaseCrossArea> getCountryCrossArea() {
//		List<BaseCrossArea> result = new ArrayList<>();
//		for (BaseCrossArea b : crossAreaMap.values()) {
//			if (b.getCrossType() == SystemConstant.COUNTRY_CROSS) {
//				result.add(b);
//			}
//		}
//		return result;
//	}
	/**
	 * 获取所有战区信息
	 * 
	 * @return
	 */
//	public List<BaseCrossArea> getAllCrossArea() {
//		return new ArrayList<>(crossAreaMap.values());
//	}

	/**
	 * 添加战场区域
	 * @param bca
	 */
//	public void addCrossArea(BaseCrossArea bca) {
//		crossAreaMap.put(bca.getCrossAreaId(), bca);
//		redisManager.getCrossAreaCache().hset(RedisConstant.KEY_CROSS_AREA, bca.getCrossAreaId(), bca.toJson());
//	}
//
//	/**
//	 * 通过战区类型和战区ID,获取指定的战区
//	 * @param crossAreaType 战区类型
//	 * @param wroldId 跨服服务器ID
//	 * @return
//	 */
//	public BaseCrossArea getCrossArea(String crossAreaType,String wroldId) {
//		return crossAreaMap.get(crossAreaType+"_"+wroldId);
//	}
//	/**
//	 * 删除所有战区信息
//	 */
//	public void removeAllCrossArea() {
//		crossAreaMap.clear();
//		redisManager.getCrossAreaCache().del(RedisConstant.KEY_CROSS_AREA);
//	}
//	/**
//	 * 删除指定的战区信息
//	 * @param bca
//	 */
//	public void removeCrossArea(BaseCrossArea bca) {
//		crossAreaMap.remove(bca.getCrossAreaId());
//		redisManager.getCrossAreaCache().hdel(RedisConstant.KEY_CROSS_AREA, bca.getCrossAreaId());;
//	}
//	/**
//	 * 删除指定的战区信息
//	 * @param crossAreaType 战区类型
//	 * @param crossAreaId 跨服服务器ID
//	 */
//	public void removeCrossAreaById(String crossAreaType,String crossAreaId) {
//		crossAreaMap.remove(crossAreaType+"_"+crossAreaId);
//		redisManager.getCrossAreaCache().hdel(RedisConstant.KEY_CROSS_AREA, crossAreaType+"_"+crossAreaId);;
//	}
//    /**
//     * 获取所有战区信息
//     *
//     * @return
//     */
//    public List<BaseCrossArea> getALlCrossArea() {
//        return new ArrayList<>(crossAreaMap.values());
//    }
//
//    public void addCrossArea(int crossAreaType, BaseCrossArea bca) {
//        crossAreaMap.put(crossAreaType + "_" + bca.getCrossServer().getWorldId(), bca);
//    }
//
//    public void getCrossArea(int crossAreaType, int wroldId) {
//        crossAreaMap.get(crossAreaType + "_" + wroldId);
//    }
    public void removeBattle(String crossId) {
        battleAreaMap.remove(crossId);
    }
    
    public void getGameServer(Server server,Server gameServer) {
        Map map = Maps.newHashMap();
        map.put("worldId", server.getWorldId());
       // Server gameServer = toolDaoTemplate.selectOne("gameArea.selectByWorldId",map);
        if (gameServer == null) {
            insertGameAreaAndGamePlatform(server);
        } else {
            if (!gameServer.equals(server)) {
            	 GameAreaMap gam = gameAreaMap.get(server.getPlatformId());
            	 gam.addGameArea(server);
            	 MybatisParameter parameter = createNewMybatisParameter();
                parameter.put("worldId", server.getWorldId());
                parameter.put("server", server);
                parameter.put("pid", server.getPlatformId());
                parameter.put("version", server.getVersion());
                toolDaoTemplate.update("gameArea.updateByWorldId", parameter);
            }
        }
    }

}
