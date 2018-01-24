package com.mokylin.cabal.common.game.api.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.AsyncRestTemplate;

import com.mokylin.cabal.common.game.api.GameEmailOperation;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.modules.tools.entity.GameEmail;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;


/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/12 16:36
 * 项目: cabal-tools
 */
public class GameEmailTemplate extends AbstractGameOperations implements GameEmailOperation {

    private static final String API_URL_ADD_EMAIL_SUFFIX = "/email/sendEmail";

    private static final String API_URL_DELETE_EMAIL_SUFFIX = "/email/delete";

    public GameEmailTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Result sendEmail(GameEmail gameEmail) {
        List<Server> gameServerList = getWaitingToSendGameServerList(gameEmail);
        for(Server server : gameServerList){
        	String createUrl = server.createUrl();
        	
            URI uri = buildUri(createUrl+API_URL_ADD_EMAIL_SUFFIX);
            execute(gameEmail,uri);
        }

        return new Result(true);
    }

    @Override
    public Result sendEmail(GameEmail gameEmail, String serverId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url+API_URL_ADD_EMAIL_SUFFIX);
        LOGGER.info("发送邮件,serverId:{}", serverId);
        return execute(gameEmail,uri);
    }

    /**
     * 删除邮件内邮件
     * @param serverId
     * @param roleId  角色编号
     * @param id  邮件ID
     * @return
     */
    @Override
    public Result delete(String serverId, String roleId, String id) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_DELETE_EMAIL_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("roleId", roleId);
        return execute(object,uri);
    }

    /**
     * 根据公告标志isGlobal是否是所有服的列表
     * @param email
     * @return
     */
    private List<Server> getWaitingToSendGameServerList(GameEmail email){
        List<Server> gameServerList = null;
//        if(email.isGlobal()){
//            List<String> serverIdList = Arrays.asList(StringUtils.split(email.getServerIds(),","));
//            gameServerList = GameServerUtils.getGameServerListWithoutRepetition(serverIdList);
//        }else {
            List<String> serverIdList = Arrays.asList(StringUtils.split(email.getServerIds(), ","));
            gameServerList = GameAreaUtils.getGameServerListWithoutRepetition(serverIdList);
//        }
        return gameServerList;
    }
}
