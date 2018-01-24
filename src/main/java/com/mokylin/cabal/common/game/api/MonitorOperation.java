package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.persistence.Result;

import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/26 17:46
 * 项目: cabal-tools
 */
public interface MonitorOperation {

    Result chatMonitor(String serverId);

    Result chatMonitor(List<String> serverIds);

    Result cancelMonitor(String serverId);

    Result cancelMonitor(List<String> serverIds);
}
