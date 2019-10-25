package com.wchkong.common.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

public class Base64Utils {

    /**
     * 安全的base64编码
     */
    public static String safeUrlBase64Encode(String originStr) {
        if (StringUtils.isEmpty(originStr))
            return originStr;
        String encodeBase64 = new BASE64Encoder().encode(originStr.getBytes());
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        //safeBase64Str = safeBase64Str.replaceAll("=", "");
        return safeBase64Str;
    }

    /**
     * 普通的base64编码
     */
    public static String urlBase64Encode(String originStr) {
        if (StringUtils.isEmpty(originStr))
            return originStr;
        return new BASE64Encoder().encode(originStr.getBytes());
    }
}
