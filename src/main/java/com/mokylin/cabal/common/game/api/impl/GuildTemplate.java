package com.mokylin.cabal.common.game.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.game.api.GuildOperation;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URI;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/1/8 17:08
 * 项目: admin-tools
 * 公会操作
 */
public class GuildTemplate extends AbstractGameOperations implements GuildOperation {

    private static final String API_URL_GUILD_DELETE_SUFFIX = "/guild/delete";
    private static final String API_URL_GUILD_TRANSFER_SUFFIX = "/guild/transfer";
    private static final String API_URL_GUILD_CHANGE_SUFFIX = "/guild/change";

    public GuildTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }

    /**
     * 解散公会
     * @param serverId
     * @param guildId 公会Id
     * @return
     */
    @Override
    public Result delete(String serverId, Long guildId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_GUILD_DELETE_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("guildId",guildId);
        return execute(object,uri);
    }

    /**
     * 公会转让
     * @param serverId
     * @param guildId
     * @param roleId
     * @return
     */
    @Override
    public Result transfer(String serverId, Long guildId, String roleId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_GUILD_TRANSFER_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("guildId",guildId);
        object.put("roleId",roleId);
        return execute(object,uri);
    }

    @Override
    public Result change(String serverId, Long guildId, String newName) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_GUILD_CHANGE_SUFFIX);
        JSONObject object = new JSONObject();
        object.put("guildId",guildId);
        object.put("name",newName);
        return execute(object,uri);
    }
}
