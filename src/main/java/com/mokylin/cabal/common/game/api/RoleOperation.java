package com.mokylin.cabal.common.game.api;

import com.mokylin.cabal.common.game.api.model.VirtualItemDTO;
import com.mokylin.cabal.common.persistence.Result;

import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/17 13:52
 * 项目: cabal-tools
 */
public interface RoleOperation {

    Result jinYan(String serverId, String roleId, String expireTime);

    Result fenHao(String serverId, String roleId, String expireTime);

    Result batchJinYan(List<String> roleIds);

    Result batchFenHao(List<String> roleIds);

    Result batchJinYan(List<String> roleIds, String expireTime);

    Result batchFenHao(List<String> roleIds, String expireTime);

    Result batchCancelJinYan(List<String> roleIds);

    Result batchCancelFenHao(List<String> roleIds);

    Result cancelJinYan(String roleId, String serverId);

    Result cancelFenHao(String roleId, String serverId);

    Result updateRoleType(String serverId, String roleId, String roleType);

    Result delete(String serverId, Long roleId);

    Result roleOnline(String serverId);

    Result notice(String serverId, Long roleId);
    
    /**
     * 设置YY福利号
     * @param serverId 服务器ID
     * @param roleId 角色ID
     * @param state 0：不是  1:是
     * @return
     */
    Result setYYWelfare(String serverId,Long roleId,int state);
    
    /**
     * 是不是YY福利号
     * @param roleId 角色ID
     * @param state 0：不是  1:是
     * @return
     */
    Result isYYWelfare(String serverId,Long roleId);

    /**
     * 给指定玩家发送虚拟物品
     * @param serverId
     * @param virtualItem
     * @return
     */
    Result sendVirtualGoods(String serverId, VirtualItemDTO virtualItem);


    /**
     * 全服玩家发送虚拟物品
     * @param serverIds
     * @param virtualItem
     * @return
     */
    Result sendVirtualGoods(List<String> serverIds, VirtualItemDTO virtualItem);
    
    /**
     * 编辑玩家货币
     * @param serverId
     * @param roleId
     * @param currencyType
     * @param value
     * @return
     */
    Result editRoleCurrency(String serverId, Long roleId, int currencyType, int value);
    
    /**
     * 编辑玩家等级
     * @param serverId
     * @param roleId
     * @param levelType
     * @param value
     * @return
     */
    Result editRoleLevel(String serverId, Long roleId, int levelType, int value);
    
    /**
     * 删除玩家道具
     * @param serverId
     * @param roleId
     * @param itemId
     * @return
     */
    Result delRoleItem(String serverId, Long roleId, Long itemId, Integer itemNum);
}
