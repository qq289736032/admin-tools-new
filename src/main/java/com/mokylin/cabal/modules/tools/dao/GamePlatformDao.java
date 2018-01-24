package com.mokylin.cabal.modules.tools.dao;

import com.mokylin.cabal.common.persistence.BaseDao;
import com.mokylin.cabal.common.persistence.Parameter;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/20 15:48
 * 项目: cabal-tools
 */
@Repository
public class GamePlatformDao extends BaseDao<GamePlatform> {

    public List<GamePlatform> findAllList(){
        return find("from GamePlatform where delFlag=:p1 order by name", new Parameter(GamePlatform.DEL_FLAG_NORMAL));
    }

    public GamePlatform findByName(String name){
        return getByHql("from GamePlatform where delFlag = :p1 and name = :p2", new Parameter(GamePlatform.DEL_FLAG_NORMAL, name));
    }

    public GamePlatform findByPid(String pid){
        return getByHql("from GamePlatform where delFlag = :p1 and pid = :p2", new Parameter(GamePlatform.DEL_FLAG_NORMAL, pid));
    }
}
