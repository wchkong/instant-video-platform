package com.wchkong.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author haoli <lihao@wandoujia.com>
 */
public final class JSONUtils {
    private static final Logger LOG = LoggerFactory.getLogger(JSONUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final int JSON_LOG_THRESHOLD = 1024 * 2;  // 2 kB

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JSONUtils() {
    }

    public static String toJSON(Object object) {
        try {
            if (object == null) {
                return StringUtils.EMPTY;
            }
            return OBJECT_MAPPER.writer().writeValueAsString(object);
        } catch (IOException e) {
            LOG.warn("Serialize " + object + " failed", e);
            return null;
        }
    }

    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            if (StringUtils.isNotBlank(json) && json.length() <= JSON_LOG_THRESHOLD) {
                LOG.warn("Deserialize " + json + " failed", e);
            } else {
                LOG.warn("Deserialize failed", e);
            }
            return null;
        }
    }

    public static <T> T fromJSON(String json, TypeReference<T> typeReference) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (IOException e) {
            if (StringUtils.isNotBlank(json) && json.length() <= JSON_LOG_THRESHOLD) {
                LOG.warn("Deserialize " + json + " failed", e);
            } else {
                LOG.warn("Deserialize failed", e);
            }
            return null;
        }
    }

    public static String beanToJsonByJsonLib(Object object) {
        if (object == null) {
            return "null";
        }
        JSONObject jsonObj = JSONObject.parseObject(object.toString());
        return jsonObj.toString();
    }

    public static List jsonToList(String jsonStr) {
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        return (List) JSONUtils.parseJSONObj(jsonArray);
    }

    /**
     * 递归地将JSONArray转换为List对象，将JSONObject转换为Map对象
     *
     * @param obj
     * @return
     */
    private static Object parseJSONObj(Object obj) {

        Object result = null;
        if (obj == null) {
            // error
            return null;
        } else {
            if (obj instanceof JSONArray) {
                JSONArray arrayObj = (JSONArray) obj;
                List<Object> list = new ArrayList<Object>();
                for (Object element : arrayObj.toArray()) {
                    list.add(JSONUtils.parseJSONObj(element));
                }
                result = list;
            } else if (obj instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject) obj;
                Map<String, Object> map = new HashMap<String, Object>();
                for (Object key : jsonObj.keySet()) {
                    map.put(key.toString(), JSONUtils.parseJSONObj(jsonObj.get(key.toString())));
                }
                return map;
            } else {
                result = obj;
            }
        }
        return result;
    }
}
