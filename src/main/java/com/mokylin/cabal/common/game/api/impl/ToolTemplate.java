package com.mokylin.cabal.common.game.api.impl;

import java.net.URI;
import java.util.List;

import org.springframework.web.client.AsyncRestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.game.api.ToolOperation;
import com.mokylin.cabal.common.game.api.model.Command;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/10/12
 * admin-tools
 */
public class ToolTemplate extends AbstractGameOperations implements ToolOperation{

    private static final String API_URL_EXE_GROOVY_SUFFIX = "/groovy/execute"; //todo 战神这里竟然用的是role，我操...坑，要改
    
    //private static final String API_URL_EXE_COMMAND_SUFFIX = "/system/command";
    private static final String API_URL_EXE_OFFCMD_SUFFIX="/system/offCmdList";
    
    private static final String API_URL_EXE_CLOSECMD_SUFFIX="/system/closeCmd";
    
    private static final String API_URL_EXE_OPENTIME_SUFFIX = "/system/modifyOpenTime";//修改游戏开服时间
    
    private static final String API_URL_EXE_COMBINETIME_SUFFIX = "/system/modifyCombineTime";//修改游戏合服时间

    public ToolTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Result groovyCode(String serverId, String code) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_EXE_GROOVY_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("codeText",code);
        return execute(object,uri);
    }
    
    @Override
    public Result offCmdList(String serverId){
    	String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_EXE_OFFCMD_SUFFIX);
        JSONObject object = new JSONObject();
        return syncExecute(object, uri);
    }
    
    @Override
    public Result closeCmd(String command,boolean status,List<String> serverIds){
        JSONObject object = new JSONObject();
        object.put("command", command);
        object.put("status", status);
        for(String serverId : serverIds){
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
            URI uri = buildUri(url + API_URL_EXE_CLOSECMD_SUFFIX);
            execute(object,uri);
        }
        return new Result(true);
    }
    
    @Override
    public Result openTime(Long modifyOpenTime, String serverIds) {
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverIds);
            URI uri = buildUri(url + API_URL_EXE_OPENTIME_SUFFIX);
            JSONObject object = new JSONObject();
            object.put("openTime",modifyOpenTime);
            execute(object,uri);

        return new Result(true);
    }
    
    @Override
    public Result combineTime(Long modifyCombineTime, String serverIds) {
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverIds);
            URI uri = buildUri(url + API_URL_EXE_COMBINETIME_SUFFIX);
            JSONObject object = new JSONObject();
            object.put("hefuTime",modifyCombineTime);
            execute(object,uri);
        return new Result(true);
    }
    
    
}
