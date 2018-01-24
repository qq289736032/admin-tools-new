package com.mokylin.cabal.common.game.api.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.game.api.model.ReleaseNote;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.mokylin.cabal.common.game.api.AnnounceOperation;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.modules.tools.entity.GameNotice;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/30 16:58
 * 项目: cabal-tools
 */
public class AnnounceTemplate extends AbstractGameOperations implements AnnounceOperation {

    private static final String API_URL_ADD_ANNOUNCE_SUFFIX = "/announce/addAnnounce";

    //private static final String API_URL_ADD_ANNOUNCE_SUFFIX = "http://192.168.3.234:10086/echo";

    private static final String API_URL_DELETE_ANNOUNCE_SUFFIX = "/announce/deleteAnnounce";


    public static final String API_URL_RELEASE_NOTE = "/announce/releaseNote";

    public AnnounceTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }


    @Override
    public Result addAnnounce(GameNotice notice) {
        List<Server> gameServerList = getWaitingToSendGameServerList(notice);
        for(Server server : gameServerList){
            URI uri = buildUri(server.createUrl()+API_URL_ADD_ANNOUNCE_SUFFIX);
            execute(notice,uri);
        }

        return new Result(true);
    }

    @Override
    public Result deleteAnnounce(GameNotice notice) {
        List<Server> gameServerList = getWaitingToSendGameServerList(notice);
        for(Server server : gameServerList){
            URI uri = buildUri(server.createUrl()+API_URL_DELETE_ANNOUNCE_SUFFIX);
            execute(notice,uri);
        }
        return new Result(true);
    }

    @Override
    public Result releaseNote(ReleaseNote note) {
        List<String> serverIdList = GameAreaUtils.getGameServerIdListWithoutRepetition(note.getServerIdList());
        for(String serverId : serverIdList){
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
            URI uri = buildUri(url + API_URL_RELEASE_NOTE);
            execute(note,uri);
        }
        return new Result(true);
    }

    /**
     * 根据公告标志isGlobal是否是所有服的列表
     * @param notice
     * @return
     */
    private List<Server> getWaitingToSendGameServerList(GameNotice notice){
        List<Server> gameServerList = null;
        if(notice.isGlobal()){
            gameServerList = GameAreaUtils.getGameServerListWithoutRepetition();
        }else {
            List<String> serverIdList = Arrays.asList(StringUtils.split(notice.getServerIds(), ","));
            gameServerList = GameAreaUtils.getGameServerListWithoutRepetition(serverIdList);
        }
        return gameServerList;
    }
}
