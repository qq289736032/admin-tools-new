package com.mokylin.cabal.common.game.api.impl;

import com.mokylin.cabal.common.game.api.AnnounceOperation;
import com.mokylin.cabal.common.game.api.VipQqOperation;
import com.mokylin.cabal.common.game.api.model.ReleaseNote;
import com.mokylin.cabal.common.game.api.model.VipQq;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.modules.tools.entity.GameNotice;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/13
 * admin-tools
 */
public class VipQqTemplate extends AbstractGameOperations implements VipQqOperation {

    private static final String API_URL_ADD_VIP_SUFFIX = "/vipQq/add";

    private static final String API_URL_DELETE_VIP_SUFFIX = "/vipQq/delete";

    private static final String API_URL_VIP_SUFFIX = "/vipQq/superMember";

    public VipQqTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Result add(VipQq vipQq, List<String> serverIds) {
        for(String serverId : serverIds){
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
            URI uri = buildUri(url + API_URL_ADD_VIP_SUFFIX);
            execute(vipQq,uri);
        }

        return new Result(true);
    }

    @Override
    public Result delete(VipQq vipQq, List<String> serverIds) {
        for(String serverId : serverIds){
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
            URI uri = buildUri(url + API_URL_DELETE_VIP_SUFFIX);
            execute(vipQq,uri);
        }

        return new Result(true);
    }

    @Override
    public Result superMember(VipQq vipQq, List<String> serverIds) {
        for(String serverId : serverIds){
            String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
            URI uri = buildUri(url + API_URL_VIP_SUFFIX);
            execute(vipQq,uri);
        }

        return new Result(true);
    }
}
