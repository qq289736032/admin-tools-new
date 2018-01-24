package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.game.api.model.VipQq;
import com.mokylin.cabal.common.persistence.Result;

import java.util.List;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/13
 * admin-tools
 */
public interface VipQqOperation {

    Result add(VipQq vipQq, List<String> serverIds);

    Result delete(VipQq vipQq, List<String> serverIds);

    Result superMember(VipQq vipQq, List<String> serverIds);
}
