package com.mokylin.cabal.common.game.api;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/30 16:53
 * 项目: cabal-tools
 */
public interface Game {

    AnnounceOperation announceOperation();

    GameEmailOperation gameEmailOperation();

    TreasureOperation treasureOperation();

    RoleOperation roleOperation();

    MonitorOperation monitorOperation();

    GuildOperation guildOperation();

    VipQqOperation vipQqOperation();
}
