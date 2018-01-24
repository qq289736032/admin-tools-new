package com.mokylin.cabal.common.cache;

import com.mokylin.cabal.common.utils.StringUtils;
import net.sf.ehcache.CacheException;
import org.apache.commons.collections.map.ReferenceMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultCacheManager implements ICacheManager {

    private final ConcurrentMap<String, ICache> cacheMap;

    public DefaultCacheManager() {
        this.cacheMap = new ConcurrentHashMap<String, ICache>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <K, V> ICache<K, V> getCache(String name) throws CacheException {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("参数 name 非法！");
        }
        try {
            // 根据 name 从 Cache Map 中获取 Cache，若为空，则创建 Cache，并将其放入 Cache Map 中
            ICache<K, V> cache = cacheMap.get(name);
            if (cache == null) {
                // 创建一个基于 Map 的 Cache
                Map<K, V> map = new ReferenceMap(); // 强引用指向 key，弱引用指向 value
                cache = new DefalutCache<K, V>(map);
                cacheMap.putIfAbsent(name, cache);
            }
            return cache;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}
