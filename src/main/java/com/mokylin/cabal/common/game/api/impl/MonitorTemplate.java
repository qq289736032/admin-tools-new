package com.mokylin.cabal.common.game.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.game.api.MonitorOperation;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/26 17:48
 * 项目: cabal-tools
 */
public class MonitorTemplate extends AbstractGameOperations implements MonitorOperation {

    private static final String API_URL_ADD_MONITOR_SUFFIX = "/monitor/chatMonitor";

    private static final String API_URL_CANCEL_MONITOR_SUFFIX = "/monitor/cancelMonitor";


    public MonitorTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Result chatMonitor(String serverId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url+API_URL_ADD_MONITOR_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("isMonitor",true);
        return execute(object,uri);
    }

    @Override
    public Result chatMonitor(List<String> serverIds) {
        List<String> serverIdList = GameAreaUtils.getGameServerIdListWithoutRepetition(serverIds); //排除已经合服的服
        for(String serverId : serverIdList){
            chatMonitor(serverId);
        }
        return new Result(true);
    }

    @Override
    public Result cancelMonitor(String serverId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url+API_URL_CANCEL_MONITOR_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("isCancel",true);
        return execute(object,uri);
    }

    @Override
    public Result cancelMonitor(List<String> serverIds) {
        List<String> serverIdList = GameAreaUtils.getGameServerIdListWithoutRepetition(serverIds); //排除已经合服的服
        for(String serverId : serverIdList){
            cancelMonitor(serverId);
        }
        return new Result(true);
    }
}
