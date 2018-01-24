package com.mokylin.cabal.common.game.api;

import java.util.List;

import com.mokylin.cabal.common.persistence.Result;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/10/12
 * admin-tools
 */
public interface ToolOperation {

    Result groovyCode(String serverId, String code);

    Result offCmdList(String serverId);
    
    Result closeCmd(String command, boolean status, List<String> serverIds);
    
    Result openTime(Long modifyOpenTime, String serverIds);
    
    Result combineTime(Long modifyCombineTime, String serverIds);
}
