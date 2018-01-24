package com.mokylin.cabal.modules.tools.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mokylin.cabal.common.service.BaseService;
import com.mokylin.cabal.common.utils.CacheUtils;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.dao.GamePlatformDao;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/20 15:58
 * 项目: cabal-tools
 */
@Service
public class GamePlatformService extends BaseService {


    @Resource
    private GamePlatformDao gamePlatformDao;

    public GamePlatform get(String id){
        return gamePlatformDao.get(id);
    }

    @Transactional(readOnly = false)
    public void save(GamePlatform gamePlatform) {
        gamePlatformDao.save(gamePlatform);
        CacheUtils.remove(UserUtils.CACHE_GAME_PLATFORM_LIST);
    }

    @Transactional(readOnly = false)
    public void delete(String id) {
        gamePlatformDao.deleteById(id);
        CacheUtils.remove(UserUtils.CACHE_GAME_PLATFORM_LIST);
    }
}
