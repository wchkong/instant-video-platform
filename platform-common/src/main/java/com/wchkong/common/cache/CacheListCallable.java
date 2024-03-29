package com.wchkong.common.cache;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

public interface CacheListCallable {

    Set<ZSetOperations.TypedTuple<String>> listCall(String key) throws Exception;

}
