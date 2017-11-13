package com.tasfe.framework.encrypt.model.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class DecryptRequest implements Serializable {
    // 密钥
    private String key;
    // 单个 or 批量
    private JSON data;
}
