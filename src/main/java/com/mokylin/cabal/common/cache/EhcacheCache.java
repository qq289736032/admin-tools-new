package com.mokylin.cabal.common.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class EhcacheCache<K, V> implements ICache<K, V> {

    private Ehcache ehcache;

    public EhcacheCache(Ehcache ehcache) {
        if (ehcache == null) {
            throw new IllegalArgumentException("参数 ehcache 非法！");
        }
        this.ehcache = ehcache;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K key) throws CacheException {
        try {
            if (key == null) {
                return null;
            } else {
                Element element = ehcache.get(key);
                if (element == null) {
                    return null;
                } else {
                    return (V) element.getObjectValue();
                }
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        try {
            V previous = get(key);
            Element element = new Element(key, value);
            ehcache.put(element);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        try {
            V previous = get(key);
            ehcache.remove(key);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        try {
            ehcache.removeAll();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public long size() {
        try {
            return ehcache.getSize();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keys() {
        try {
            List<K> keys = ehcache.getKeys();
            if (CollectionUtils.isNotEmpty(keys)) {
                return Collections.unmodifiableSet(new LinkedHashSet<K>(keys));
            } else {
                return Collections.emptySet();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            Set<K> keys = keys();
            if (CollectionUtils.isNotEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (K key : keys) {
                    V value = get(key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }
}