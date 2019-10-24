package com.wchkong.enums;

public enum RegisterSource {
    /**
     * 注册来源
     */
    EMAIL("EMAIL"),
    TELEPHONE("PHONE"),
    QQ("QQ"),
    WECHAT("WECHAT"),
    UNKONWN("UNKONWN"),
    WECHAT_OPEN("WECHAT_OPEN");

    public static RegisterSource forValue(String text) {
        if (text != null) {
            for (RegisterSource o : RegisterSource.values()) {
                if (text.equalsIgnoreCase(o.name())) {
                    return o;
                }
            }
        }
        return UNKONWN;
    }

    RegisterSource(String platform) {
        this.platform = platform;
    }

    private String platform;

    public String getPlatform() {
        return platform;
    }

}
