package com.mokylin.cabal.modules.tools.dao;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.redis.Server;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者: shenggm
 * 日期: 2014/10/20 19:36
 * 项目: cabal-tools
 */
@Repository
public class GameAreaDao{
	@Resource
	private ToolDaoTemplate toolDaoTemplate;

	public List<Server> findAllList() {
		List<Server> allList=toolDaoTemplate.selectList("gameArea.findAllGameArea");
//		List<Server> resList=new ArrayList<>(allList.size());
//		if(null!=allList&&allList.size()>0){
//			for (Map<String, Object> map : allList) {
//				Server server = createServer(map);
//				resList.add(server);
//			}
//		}
		return allList;
	}
	
	public List<Server> findListByPid(String pid) {
		MybatisParameter parameter = new MybatisParameter<>();
		parameter.put("pid", pid);
		List<Server> allList=toolDaoTemplate.selectList("gameArea.findGameAreaByPid", parameter);
		return allList;
	}

	/**
	 * 回去对应serverId的服务器列表
	 * @param serverIdList
	 * @return
	 */
	public List<Server> findGameAreaByServerId(List<String> serverIdList) {
		MybatisParameter parameter = new MybatisParameter();
		parameter.put("serverIdList", serverIdList);
		List<Server> allList=toolDaoTemplate.selectList("gameArea.findGameAreaByServerId",parameter);
//		List<Server> resList=new ArrayList<>(allList.size());
//		if(null!=allList&&allList.size()>0){
//			for (Map<String, Object> map : allList) {
//				Server server = createServer(map);
//				resList.add(server);
//			}
//		}
		return allList;
	}

//	private Server createServer(Map<String, Object> map) {
//		Server server =new Server();
//		server.setId(MapUtils.getIntValue(map, "id"));
//		server.setWorldId(MapUtils.getIntValue(map, "world_id"));
//		server.setWorldName(MapUtils.getString(map, "world_name"));
//		server.setName(MapUtils.getString(map, "area_name"));
//		server.setId(MapUtils.getIntValue(map, "area_id"));
//		server.setType(MapUtils.getIntValue(map, "area_type"));
//		server.setInnerIp(MapUtils.getString(map, "inner_ip"));
//		server.setExternalIp(MapUtils.getString(map, "external_ip"));
//		server.setInnerPort(MapUtils.getIntValue(map, "inner_port"));
//		server.setTcpPort(MapUtils.getIntValue(map, "tcp_port"));
//		server.setWebPort(MapUtils.getIntValue(map, "web_port"));
//		server.setFollowerId(MapUtils.getIntValue(map, "follower_id"));
//		server.setStatus(MapUtils.getIntValue(map, "status"));
//		server.setPid(MapUtils.getIntValue(map, "pid"));
//		return server;
//	}
}
