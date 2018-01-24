package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.persistence.Result;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/8 17:08
 * 项目: admin-tools
 */
public interface GuildOperation {

    /**
     * @param guildId 公会Id
     * @return
     */
    Result delete(String serverId, Long guildId);


    Result transfer(String serverId, Long guildId, String roleId);


    Result change(String serverId, Long guildId, String newName);
}
