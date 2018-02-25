package com.tasfe.framework.uid.service;

public enum BizCode {
    USER("nyd:user:id:",null),
    ORDER_NYD("nyd:order:id:",10),
    BILL_NYD("nyd:bill:id:",10),
    MEMBER("nyd:member:id:",8),

    USER_YMT("ymt:user:id:",11),
    ORDER_YMT("ymt:order:id:",11),
    RECOMMEND_YMT("ymt:recommend:id:",6);

    private String key;
    private Integer prefix;


    BizCode(String key, Integer prefix) {
        this.key = key;
        this.prefix = prefix;
    }

    public String getKey() {
        return key;
    }

    public Integer getPrefix() {
        return prefix;
    }
}
