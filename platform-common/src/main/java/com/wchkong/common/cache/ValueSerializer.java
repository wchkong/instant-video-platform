package com.wchkong.common.cache;

import java.util.List;
import java.util.Set;

/**
 * @author haoli <lihao@wandoujia.com>
 */
public interface ValueSerializer {

    String serialize(Object value);

    List<String> serialize(List values);

    <T> T deserialize(String serializedValue, Class<T> targetClass);

    <T> List<T> deserialize(List<String> serializedValues, Class<T> targetClass);

    <T> Set<T> deserialize(Set<String> serializedValues, Class<T> targetClass);
}
