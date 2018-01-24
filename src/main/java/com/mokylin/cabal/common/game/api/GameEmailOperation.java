package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.modules.tools.entity.GameEmail;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/6 15:14
 * 项目: cabal-tools
 */
public interface GameEmailOperation {

    Result sendEmail(GameEmail gameEmail);

    Result sendEmail(GameEmail gameEmail, String serverId);

    Result delete(String serverId, String roleId, String id);

}
