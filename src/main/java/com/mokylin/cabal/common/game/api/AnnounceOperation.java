package com.mokylin.cabal.common.game.api;

import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.game.api.model.ReleaseNote;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.modules.tools.entity.GameNotice;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/30 16:55
 * 项目: cabal-tools
 */
public interface AnnounceOperation {

    Result addAnnounce(GameNotice notice);

    Result deleteAnnounce(GameNotice notice);

    Result releaseNote(ReleaseNote object);
}
