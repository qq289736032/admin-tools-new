package com.mokylin.cabal.common.game.api.impl;

import com.mokylin.cabal.common.game.api.TreasureOperation;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.modules.tools.entity.Recharge;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.URI;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/14 15:45
 * 项目: cabal-tools
 * 元宝充值
 */
public class TreasureTemplate extends AbstractGameOperations implements TreasureOperation {

    private static final String API_URL_ADD_RECHARGE_SUFFIX = "/treasure/recharge";

    public TreasureTemplate(AsyncRestTemplate restTemplate) {
        super(restTemplate);
    }


    @Override
    public Result recharge(List<Recharge> rechargeList) {
        for(Recharge recharge : rechargeList){
            String url = GameAreaUtils.getGameRemoteUrlByServerId(recharge.getServerId());
            URI uri = buildUri(url+API_URL_ADD_RECHARGE_SUFFIX);
            execute(recharge,uri);
        }
        return new Result(true);
    }

    @Override
    public Result recharge(Recharge recharge){
        String url = GameAreaUtils.getGameRemoteUrlByServerId(recharge.getServerId());
        URI uri = buildUri(url+API_URL_ADD_RECHARGE_SUFFIX);
        return execute(recharge,uri);
    }
}
