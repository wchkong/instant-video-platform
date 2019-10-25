package com.wchkong.common.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wchkong.common.utils.JSONUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Used to serialize and deserialize generic object like List<Object>
 *
 * @author haoli <lihao@wandoujia.com>
 */

public final class GenericObjectCacheUtils {

    private static final Logger LOG = LoggerFactory.getLogger(GenericObjectCacheUtils.class);

    private GenericObjectCacheUtils() {
    }

    public static <T> T deserialize(Cache cache, String key, TypeReference<T> typeReference) {
        String value = cache.get(key, String.class);
        if (StringUtils.isNotBlank(value)) {
            return JSONUtils.fromJSON(value, typeReference);
        } else {
            return null;
        }
    }

    public static void put(Cache cache, String key, Object value) {
        cache.put(key, JSONUtils.toJSON(value));
    }

    public static void put(Cache cache, String key, Object value, long timeout, TimeUnit unit) {
        cache.put(key, JSONUtils.toJSON(value), timeout, unit);
    }

    /**
     * Get value of key from cache, if no value is found, calls callable to load the value
     */
    public static <T> T get(Cache cache,
                            String key,
                            TypeReference<T> typeReference,
                            CacheCallable<T> callable) {
        return get(cache, key, typeReference, callable, null);
    }

    /**
     * Get value of key from cache, if no value is found, calls callable to load the value
     */
    public static <T> T get(Cache cache,
                            String key,
                            TypeReference<T> typeReference,
                            CacheCallable<T> callable,
                            T defaultValue) {
        return get(cache, key, typeReference, callable, defaultValue, -1, null);
    }

    /**
     * Get value of key from cache, if no value is found, calls callable to load the value
     */
    public static <T> T get(Cache cache,
                            String key,
                            TypeReference<T> typeReference,
                            CacheCallable<T> callable,
                            T defaultValue,
                            long timeout,
                            TimeUnit unit) {
        T actualValue = null;
        String jsonValue = cache.get(key, String.class);
        if (jsonValue == null) {
            try {
                actualValue = callable.call(key);
                if (actualValue != null) {
                    cache.put(key, JSONUtils.toJSON(actualValue), timeout, unit);
                }
            } catch (Exception e) {
                LOG.warn("Get " + key + " from " + cache.getName() + " failed", e);
            }
        } else {
            LOG.debug("Cache: {}, key: {} hit", cache.getName(), key);
            actualValue = JSONUtils.fromJSON(jsonValue, typeReference);
        }
        if (actualValue == null) {
            actualValue = defaultValue;
        }
        return actualValue;
    }

    public static <T> List<T> mutiGet(Cache cache,
                                      List<String> keys,
                                      TypeReference<T> typeReference,
                                      T defaultValue) {
        List<T> ret = Collections.EMPTY_LIST;
        List<String> results = cache.multiGet(keys, String.class);
        if (CollectionUtils.isNotEmpty(results)) {
            ret = results.stream().map(item -> {
                if (StringUtils.isNotBlank(item)) {
                    T value = JSONUtils.fromJSON(item, typeReference);
                    if (value != null) {
                        return value;
                    }
                }
                return defaultValue;
            }).collect(Collectors.toList());
        }
        return ret;
    }

}
