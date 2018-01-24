package com.mokylin.cabal.common.persistence;


import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.utils.AdminDefaultThreadFactory;
import com.mokylin.cabal.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 游戏数据库DAO
 * @author donghui
 */
@Component
public class GameDaoTemplate{
	private final Logger log = LoggerFactory.getLogger(getClass());
    //private final Logger monitorLogger = LoggerFactory.getLogger("monitor");
	
	@Resource private SqlSessionTemplate toolSqlSession;
	@Resource private SqlSessionTemplate gameSqlSession;

	private static ExecutorService exec = Executors.newFixedThreadPool(50, new AdminDefaultThreadFactory("gamedao"));
	
	public <E> Page<E> paging(String statement, MybatisParameter<E> parameter) {
		Page<E> page = parameter.getPage();
		List<E> dataList = selectList(statement, parameter);
		page.setList(dataList);
		return page;
	}

	public <E> Page<E> paging(String serverId, String statement, MybatisParameter<E> parameter) {
		String originalServerId = LookupContext.getCurrentServerId();
		LookupContext.setCurrentServerId(serverId);
		Page<E> page = parameter.getPage();
		List<E> dataList = selectList(statement, parameter);
		page.setList(dataList);
		LookupContext.setCurrentServerId(originalServerId);
		return page;
	}
	
	public <E> List<E> selectList(String statement) {
		log.info(statement);
		return gameSqlSession.selectList(statement);
	}
	
	public <E> List<E> selectList(String statement, Object parameter) {
		log.info(statement);
		return gameSqlSession.selectList(statement, parameter);
	}
	
	public <T> T selectOneByServerId(String serverId, String statement ) {
		return this.selectOneByServerId(serverId, statement, null);
	}
	
	public <T> T selectOneByServerId(String serverId, String statement, Object parameter) {
		try{
			//if (DbConnectionUtils.testGameServer(serverId)) {
				String originalServerId = LookupContext.getCurrentServerId();
				LookupContext.setCurrentServerId(serverId);
				T t = gameSqlSession.selectOne(statement, parameter);
				LookupContext.setCurrentServerId(originalServerId);
				return t;
			//}
		}
		catch(Exception e){
			log.error("服务器serverId:[" + serverId + "] ERROR",e);
		}
		return null;
	}
	
	public <E> List<E> selectListByServerId(String serverId, String statement ) {
		return this.selectListByServerId(serverId, statement, null);
	}
	
	public <E> List<E> selectListByServerId(String serverId, String statement, Object parameter) {
		try{
			//if (DbConnectionUtils.testGameServer(serverId)) {
				String originalServerId = LookupContext.getCurrentServerId();
				LookupContext.setCurrentServerId(serverId);
				List<E> list = gameSqlSession.selectList(statement, parameter);
				LookupContext.setCurrentServerId(originalServerId);
				return list;
			//}
		}
		catch(Exception e){
			log.error("服务器serverId:[" + serverId + "] ERROR",e);
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * 返回所有服务器的结果集(同一个logDbUrl的serverId视为一个)
	 * @param serverIdList 服务器ID集合
	 * @param statement Mapping ID
	 * @param parameter 查询参数
	 * @return 所有服务器的List形式的查询结果的合集
	 */
	public <E> List<E> selectListByServerIdList(List<String> serverIdList, String statement, Object parameter) {
		if (CollectionUtils.isEmpty(serverIdList)) {
			return Collections.EMPTY_LIST;
		}
		serverIdList = toolSqlSession.selectList("gameServer.getServerIdByGameDbUrlWithoutRepetition", serverIdList);//去重复gameDbUrl
		List<E> resultList = new ArrayList();
		Map<String, List<E>> resultMap = this.selectByServerIdList(serverIdList, statement, parameter);
		for (List<E> list : resultMap.values()) {
			resultList.addAll(list);
		}
		return resultList;
	}
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param serverIdList 
	 * @param statement Mapping ID
	 * @param parameter 查询参数
	 * @return key:服务器Id value:对应serverId查询结果
	 */
	public <E> Map<String, List<E>> selectMapByServerIdList(List<String> serverIdList, String statement, Object parameter) {
		if (CollectionUtils.isEmpty(serverIdList)) {
			return Collections.EMPTY_MAP;
		}
		serverIdList = toolSqlSession.selectList("gameServer.getServerIdByGameDbUrlWithoutRepetition", serverIdList);//去重复gameDbUrl
		return this.selectByServerIdList(serverIdList, statement, parameter);
	}
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param statement Mapping ID
	 * @return  key:服务器Id value:对应serverId查询结果
	 */
	public <E> Map<String, List<E>> selectMapAllServers(String statement) {
		return this.selectMapAllServers(statement, null);
	}
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param statement Mapping Id
	 * @param parameter 查询参数
	 * @return key:服务器Id value:对应serverId查询结果
	 */
	public <E> Map<String, List<E>> selectMapAllServers(String statement, Object parameter) {
        List<String> serverIdList = toolSqlSession.selectList("gameServer.getAllServerIdByGameDbUrlWithoutRepetition");//合并同样logDbUrl的serverId
        log.info("查询所有服务器的数据 statement:[{}] parameter:[{}]", statement, parameter);
        return this.selectByServerIdList(serverIdList, statement, parameter);
    }
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param statement Mapping Id
	 * @param parameter 查询参数
	 * @return key:服务器Id value:对应serverId查询结果
	 */
	public <E> Map<String, List<E>> selectMapAllWorlds(String statement, Object parameter) {
        List<String> serverIdList = toolSqlSession.selectList("gameArea.findAllGameWorldId");//合并同样logDbUrl的serverId
        log.info("查询所有服务器的数据 statement:[{}] parameter:[{}]", statement, parameter);
        return this.selectByServerIdList(serverIdList, statement, parameter);
    }

    public <E> Map<String, List<E>> selectMapAllTestServers(String statement, Object parameter) {
        List<String> serverIdList = toolSqlSession.selectList("gameServer.getAllTestServerId");//合并同样logDbUrl的serverId
        log.info("查询所有服务器的数据 statement:[{}] parameter:[{}]", statement, parameter);
        return this.selectByServerIdList(serverIdList, statement, parameter);
    }
    
	public <T> T selectOneGameArea(String statement,Object parameter) {
		return toolSqlSession.selectOne(statement,parameter);
	}
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param statement Mapping Id
	 * @return  所有服务器的List形式的查询结果的合集
	 */
	public <E> List<E>  selectListAllServers(String statement) {
		List<E> resultList = new ArrayList();
		Map<String, List<E>> resultMap = this.selectMapAllServers(statement);
		for (List<E> list : resultMap.values()) {
			resultList.addAll(list);
		}
		return resultList;
	}
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param statement Mapping Id
	 * @param parameter 查询参数
	 * @return 所有服务器的List形式的查询结果的合集
	 */
	public <E> List<E>  selectListAllServers(String statement, Object parameter) {
		List<E> resultList = new ArrayList();
		Map<String, List<E>> resultMap = this.selectMapAllServers(statement, parameter);
		for (List<E> list : resultMap.values()) {
			resultList.addAll(list);
		}
		return resultList;
	}
	
	/**
	 * 返回所有服务器的结果集(同一个gameDbUrl的serverId视为一个)
	 * @param statement Mapping Id
	 * @param parameter 查询参数
	 * @return 所有服务器的List形式的查询结果的合集
	 */
	public <E> List<E>  selectListAllWorlds(String statement, Object parameter) {
		List<E> resultList = new ArrayList();
		Map<String, List<E>> resultMap = this.selectMapAllWorlds(statement, parameter);
		for (List<E> list : resultMap.values()) {
			resultList.addAll(list);
		}
		return resultList;
	}

    public <E> List<E>  selectListAllTestServers(String statement, Object parameter) {
        List<E> resultList = new ArrayList();
        Map<String, List<E>> resultMap = this.selectMapAllTestServers(statement, parameter);
        for (List<E> list : resultMap.values()) {
            resultList.addAll(list);
        }
        return resultList;
    }
	
	/**
	 * 返回所有服务器的结果集(注:忽略合服情况)
	 * @param statement Mapping Id
	 * @param parameter 查询参数
	 * @return 所有服务器的List形式的查询结果的合集
	 */
	public <E> List<E>  selectListAllServersAndHeFu(String statement, Object parameter) {
		List<E> resultList = new ArrayList();
		List<String> serverIdList = toolSqlSession.selectList("gameServer.getAllServerId");
		Map<String, List<E>> resultMap = this.selectByServerIdList(serverIdList, statement, parameter);
		for (List<E> list : resultMap.values()) {
			resultList.addAll(list);
		}
		return resultList;
	}
	
	public <T> T selectOne(String statement) {
		return gameSqlSession.selectOne(statement);
	}

	public <T> T selectOne(String statement, Object parameter) {
		return gameSqlSession.selectOne(statement, parameter);
	}
	
	public int insert(String statement) {
		return gameSqlSession.insert(statement);
	}
	
	public int insert(String statement, Object parameter) {
		return gameSqlSession.insert(statement, parameter);
	}

	public int update(String statement) {
		return gameSqlSession.update(statement);
	}
	
	public int update(String statement, Object parameter) {
		return gameSqlSession.update(statement, parameter);
	}

	public int delete(String statement) {
		return gameSqlSession.delete(statement);
	}
	
	public int delete(String statement, Object parameter) {
		return gameSqlSession.delete(statement, parameter);
	}
	
	/**
	 * 按serverIdList查询各个服务器数据(只按照serverId查询不做dburl去重)
	 * @param serverIdList 
	 * @param statement Maping Id
	 * @param parameter 查询参数
	 * @return key:服务器Id value:对应serverId查询结果
	 */
	private <E> Map<String, List<E>> selectByServerIdList(List<String> serverIdList, final String statement, final Object parameter) {
		if (CollectionUtils.isEmpty(serverIdList)) {
			return Collections.EMPTY_MAP;
		}
		StopWatch sw = new StopWatch();
		sw.start();
		List<Callable<Map<String, List<E>>>> callableList = new ArrayList();
		for (final String serverIds : serverIdList) {
            Callable<Map<String, List<E>>> callable = new Callable() {
				@Override
				public Map<String, List<E>> call() throws Exception {
					Map<String, List<E>> map = new HashMap();
					String serverId = StringUtils.substringBefore(serverIds, ",");
					LookupContext.setCurrentServerId(serverId);
					List<E> list = gameSqlSession.selectList(statement, parameter);
                    //monitorLogger.info("服务器[{}],list[{}]",serverIds, JSON.toJSONString(list));
					map.put(serverIds, list);
					return map;
				}
			};
            callableList.add(callable);
        }
		Map<String, List<E>> resultMap = new LinkedHashMap();
		try {
			List<Future<Map<String, List<E>>>> futrueList= exec.invokeAll(callableList);
			for (Future<Map<String, List<E>>> future : futrueList) {
				try {
					resultMap.putAll(future.get());
				} catch (Exception e) {
                    //monitorLogger.error(null,e);
					log.error(null,e);
				}
			}
		} catch (InterruptedException e) {
            //monitorLogger.error(null,e);
			log.error(null,e);
		}
		log.info("服务器数量:{} statementName:{} 执行时间:{}", serverIdList.size(), statement, sw.getTime());
		return resultMap;
	}
	
	
	private <E> Map<String, List<E>> selectByWorldIdList(List<String> serverIdList, final String statement, final Object parameter) {
		if (CollectionUtils.isEmpty(serverIdList)) {
			return Collections.EMPTY_MAP;
		}
		StopWatch sw = new StopWatch();
		sw.start();
		List<Callable<Map<String, List<E>>>> callableList = new ArrayList();
		for (final String serverIds : serverIdList) {
            Callable<Map<String, List<E>>> callable = new Callable() {
				@Override
				public Map<String, List<E>> call() throws Exception {
					Map<String, List<E>> map = new HashMap();
					String serverId = StringUtils.substringBefore(serverIds, ",");
					LookupContext.setCurrentServerId(serverId);
					List<E> list = selectListByServerId(serverIds,statement, parameter);
                    //monitorLogger.info("服务器[{}],list[{}]",serverIds, JSON.toJSONString(list));
					map.put(serverIds, list);
					return map;
				}
			};
            callableList.add(callable);
        }
		Map<String, List<E>> resultMap = new LinkedHashMap();
		try {
			List<Future<Map<String, List<E>>>> futrueList= exec.invokeAll(callableList);
			for (Future<Map<String, List<E>>> future : futrueList) {
				try {
					resultMap.putAll(future.get());
				} catch (Exception e) {
                    //monitorLogger.error(null,e);
					log.error(null,e);
				}
			}
		} catch (InterruptedException e) {
            //monitorLogger.error(null,e);
			log.error(null,e);
		}
		log.info("服务器数量:{} statementName:{} 执行时间:{}", serverIdList.size(), statement, sw.getTime());
		return resultMap;
	}
	
}
