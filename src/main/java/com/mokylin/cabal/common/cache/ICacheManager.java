package com.mokylin.cabal.common.cache;

import net.sf.ehcache.CacheException;

public interface ICacheManager {

    /**
     * 根据名称获取 Cache 对象
     *
     * @param name Cache 名称
     * @return Cache 对象
     * @throws CacheException
     */
    <K, V> ICache<K, V> getCache(String name) throws CacheException;
}