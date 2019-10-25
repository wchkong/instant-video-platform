package com.wchkong.dao;

import org.jooq.Converter;

import java.util.Map;

/**
 * @Author: kongweichang
 * @Date: 2019/10/25 11:25
 */
public class MapConverter implements Converter<String, Map> {
    @Override
    public Map from(String s) {
        return null;
    }

    @Override
    public String to(Map map) {
        return null;
    }

    @Override
    public Class<String> fromType() {
        return null;
    }

    @Override
    public Class<Map> toType() {
        return null;
    }
}
