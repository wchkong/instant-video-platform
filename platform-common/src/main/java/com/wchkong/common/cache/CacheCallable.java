package com.wchkong.common.cache;

/**
 * @author haoli <lihao@wandoujia.com>
 */
public interface CacheCallable<T> {

    T call(String key) throws Exception;
}
