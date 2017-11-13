package com.tasfe.framework.encrypt.api;

import com.alibaba.fastjson.JSONObject;

public interface EncryptContract {
    public JSONObject encrypt(EncryptRequest encryptRequest);

}
