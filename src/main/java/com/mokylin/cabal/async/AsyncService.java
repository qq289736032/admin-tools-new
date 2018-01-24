package com.mokylin.cabal.async;

import com.google.common.collect.Lists;
import com.mokylin.cabal.common.game.api.GameTemplate;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.utils.CodecUtil;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.modules.tools.entity.GameEmail;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/10/8
 * admin-tools
 */
@Service
public class AsyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);

    private ServerManager serverManager = SpringContextHolder.getBean("serverManager");

    private GameTemplate gameTemplate = SpringContextHolder.getBean("gameTemplate");

    @Async
    public void sendEmail(GameEmail email) {
        Result result = gameTemplate.gameEmailOperation().sendEmail(email);
    }


    @Async
    public void executeCode(String code, List<String> serverIds) {
        List<Server> gameServerList = null;
        if (serverIds != null) {
            gameServerList = GameAreaUtils.getGameServerListWithoutRepetition(serverIds);
            for (Server server : gameServerList) {
                gameTemplate.getToolOperation().groovyCode(String.valueOf(server.getWorldId()), code);
            }
        } else {
            //全服
            try {
                List<Server> serList = serverManager.getGameServerList();
                List<String> heServlist = Lists.newArrayList();
                for (Server s : serList) {
                    heServlist.add(String.valueOf(s.getWorldId()));
                }
                //合服去重复
                gameServerList = GameAreaUtils.getGameServerListWithoutRepetition(heServlist);
                for (Server server : gameServerList) {
                    gameTemplate.getToolOperation().groovyCode(String.valueOf(server.getWorldId()), code);
                }
            } catch (Exception e) {
                LOGGER.error("执行代码出错", e);
            }
        }
    }
}
