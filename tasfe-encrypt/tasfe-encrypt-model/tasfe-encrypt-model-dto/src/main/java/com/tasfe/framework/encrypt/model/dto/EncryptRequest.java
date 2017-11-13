package com.tasfe.framework.encrypt.model.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * 需要加密的数据(按照key所对应的 value进行加密,里面包含)
 */
@Data
public class EncryptRequest implements Serializable {
    // 加密需要的key,用于批量
    private String key;
    // 单个or批量
    private JSON data;
    // private JSONObject data;











    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        jsonArray.add(jsonObject);
        System.out.println(jsonArray);
    }

}
