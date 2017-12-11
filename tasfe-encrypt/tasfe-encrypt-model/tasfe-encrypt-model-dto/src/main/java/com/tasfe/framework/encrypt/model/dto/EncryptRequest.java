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
    // key
    private String key;
    // 加密需要的key,用于批量
    private String businessId;
    // 待加密数据
    private String data;
    // 单个or批量
    //private JSON data;
    // private JSONObject data;
    //单个
    //private JSONObject data;
    // 批量
    //private JSONArray datas;












    public static void main(String[] args) {
        JSON jsonArray = new JSONArray();
        JSON jsonObject = new JSONObject();
        //jsonArray.toJavaObject();
        //jsonArray.add(jsonObject);
        System.out.println(jsonArray instanceof  JSONObject);
        System.out.println(jsonArray);
    }

}
