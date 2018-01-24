package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.modules.tools.entity.Recharge;

import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/14 15:42
 * 项目: cabal-tools
 * 元宝充值操作
 */
public interface TreasureOperation {


    Result recharge(List<Recharge> rechargeList);

    Result recharge(Recharge recharge);
}
