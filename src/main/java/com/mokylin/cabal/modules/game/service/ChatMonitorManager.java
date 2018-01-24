package com.mokylin.cabal.modules.game.service;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.config.ChatDTO;
import com.mokylin.cabal.common.config.TalkDTO;
import com.mokylin.cabal.common.game.api.GameTemplate;
import com.mokylin.cabal.common.io.MsgType;
import com.mokylin.cabal.common.io.SessionManager;
import com.mokylin.cabal.common.redis.RedisConstant;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/3/31 18:03
 * 项目: admin-tools
 */
@Service
@DependsOn("gameTemplate")
public class ChatMonitorManager {

    private static final Logger logger = LoggerFactory.getLogger(ChatMonitorManager.class);

    private static GameTemplate gameTemplate = SpringContextHolder.getBean(GameTemplate.class);


    @Autowired
    private RedisManager redisManager;

    private SessionManager sessionManager = SessionManager.getInstance();
    private Map<String, List<String>> observerMap = new ConcurrentHashMap<>();
    private Map<Integer, TalkDTO> talkMap = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger(0);

    // private final Cache<String, TalkDTO> questionlist =
    // CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.HOURS).build();
    public Collection<TalkDTO> getQuestionlist() {
        return talkMap.values();
    }

    public TalkDTO remove(int id) {
        TalkDTO dto = talkMap.remove(id);
        if (dto != null) {
            List<String> list = this.getUserList(dto.getPid(), dto.getAreaId());
            sessionManager.broadcast(list, MsgType.REMOVE_QUESTION_S2C_Msg, new Object[]{dto.getId()});
        }
        return dto;
    }

    public static ChatMonitorManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final ChatMonitorManager INSTANCE = SpringContextHolder.getBean(ChatMonitorManager.class);
    }

    // 关闭所有监听
    public void closeAllMonitors() {
        logger.info("关闭所有聊天监控开始");
        Set<String> list = observerMap.keySet();
        for (String e : list) {
            String[] array = StringUtils.split(e, ":");
            String pid = array[0];
            int areaId = Integer.parseInt(array[1]);
            this.closeMonitor(pid, areaId);
        }
        logger.info("关闭所有聊天监控完毕");
    }

    public void openMonitor(String pid, int areaId) {

        gameTemplate.monitorOperation().chatMonitor(String.valueOf(areaId));

//        BroadcastChat_C2S_Msg msg = new BroadcastChat_C2S_Msg();
//        msg.setBroadcast(true);
//        gameClientManager.getGameClientByAreaId(pid, areaId).broadcastChat(msg);
    }

    public void closeMonitor(String pid, int areaId) {
        try {
            gameTemplate.monitorOperation().cancelMonitor(String.valueOf(areaId));
//            BroadcastChat_C2S_Msg msg = new BroadcastChat_C2S_Msg();
//            msg.setBroadcast(false);
//            gameClientManager.getGameClientByAreaId(pid, areaId).broadcastChat(msg);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void add2ObserverList(String pid, int areaId, String userId) {
        List<String> list = observerMap.get(pid + ":" + areaId);
        if (list == null) {
            list = new ArrayList<>();
            observerMap.put(pid + ":" + areaId, list);

        }

        this.openMonitor(pid, areaId);  //只要有人监控，就给游戏服发消息  避免游戏重启，但是监控的人没有全部断开导致监控失败

//        if (CollectionUtils.isEmpty(list)) {
//            this.openMonitor(pid, areaId);
//        }
        if (!list.contains(userId)) {
            list.add(userId);
        }
        logger.info("######add observerMap######,【{}】", JSON.toJSONString(observerMap));
    }

    public void remove4ObserverList(String pid, int areaId, String userId) {
        List<String> list = observerMap.get(pid + ":" + areaId);
        if (list == null) {
            list = new ArrayList<>();
            observerMap.put(pid + ":" + areaId, list);
        }
        list.remove(userId);
        if (CollectionUtils.isEmpty(list)) {
            this.closeMonitor(pid, areaId);
        }
        logger.info("######remove observerMap######,【{}】", JSON.toJSONString(observerMap));
    }

    public List<String> getUserList(String pid, int areaId) {
        return observerMap.get(pid + ":" + areaId);
    }

    public void remove4ObserverList(String userId) {
        Set<Entry<String, List<String>>> list = observerMap.entrySet();
        for (Entry<String, List<String>> e : list) {
            String key = e.getKey();
            List<String> value = e.getValue();
            value.remove(userId);
            if (CollectionUtils.isEmpty(value)) {
                String[] array = StringUtils.split(key, ":");
                String pid = array[0];
                int areaId = Integer.parseInt(array[1]);
                this.closeMonitor(pid, areaId);
            }

        }

    }

    public void broadcast(ChatDTO dto) {
        logger.debug("{}", JSON.toJSONString(dto));
        List<String> list = this.getUserList(dto.getPid(), dto.getAreaId());
        sessionManager.broadcast(list, MsgType.BROADCAST_S2C_Msg, dto.toVO());
    }

    public void sendPlayer(String pid, int worldId, int areaId, String userId, long playerId, String playerName, String content) {
        TalkDTO talkDTO = new TalkDTO(pid, worldId, areaId, userId, playerId, playerName, content);
        redisManager.publish(worldId, RedisConstant.CHANNEL_CHAT_GM2PLAYER_Msg, talkDTO);
        // sessionManager.relayMsg(MsgType.Chat_S2C_Msg, userId, new Object[] {
        // playerId, playerName, notice });

    }

    public void relay2GM(TalkDTO dto) {
        logger.info("{}", dto);
        String userId = dto.getUserId();
        if (userId.equals("0")) {
            // 转到在线客服问题列表里
            // 持久化
            // 广播给连线的玩家
            dto.setId(id.incrementAndGet());
            talkMap.put(dto.getId(), dto);
            //	List<Integer> list = this.getUserList(dto.getPid(), dto.getAreaId());
            sessionManager.broadcast(MsgType.BROADCAST_QUESTION_S2C_Msg, dto.toVO());

        } else {
            sessionManager.relayMsg(MsgType.Chat_S2C_Msg, userId, dto.toVO());
        }
    }
}
