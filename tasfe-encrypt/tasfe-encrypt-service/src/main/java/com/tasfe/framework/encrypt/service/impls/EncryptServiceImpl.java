package com.tasfe.framework.encrypt.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tasfe.framework.encrypt.api.EncryptContract;
import com.tasfe.framework.encrypt.api.annotation.Encrypt;
import com.tasfe.framework.encrypt.model.dto.DecryptRequest;
import com.tasfe.framework.encrypt.model.dto.EncryptRequest;
import com.tasfe.framework.encrypt.service.EncryptyService;
import com.tasfe.framework.encrypt.service.util.AESUtils;
import com.tasfe.framework.encrypt.service.util.ReflectUtils;
import com.tasfe.framework.support.model.ResponseData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EncryptServiceImpl implements EncryptContract,EncryptyService {

    @Encrypt()
    private String al;
    @Override
    public <T> ResponseData<T> encrypt(EncryptRequest encryptRequest,Class<?> clazz) {
        JSON data = encryptRequest.getData();
        String key = encryptRequest.getKey();

        if(data instanceof JSONArray){
            JSONArray _data = (JSONArray)data;
            JSONArray newJsonArray = new JSONArray();
            _data.forEach((m)->{
                JSONObject newJD = new JSONObject();
                if(m instanceof JSONObject){
                    JSONObject jo = (JSONObject)m;
                    Set<String> keySet = jo.keySet();
                    keySet.forEach(k->{
                        Object value = jo.get(k);
                        String ed = AESUtils.encryptData(key,value.toString());
                        newJD.put(k,ed);
                    });
                    newJsonArray.add(newJD);
                }
            });


        }

        if(data instanceof JSONObject){
            JSONObject _data = (JSONObject)data;
        }

        Map<String,Field> fieldMap = ReflectUtils.getBeanPropertyFieldsMap(clazz,true);
        fieldMap.forEach((i,f)->{
            Encrypt encrypt = f.getAnnotation(Encrypt.class);

            if(encrypt != null){
                String k = null != encrypt.value()?encrypt.value():f.getName();
                //Object v = ReflectUtils.get
            }



        });







        List<T> list = new ArrayList<>();
        //ResponseData.success().setData(list)

        AESUtils.encryptData(key,"");
        //ResponseData.success().setData(list);
        return null;
    }

    @Override
    public <T> ResponseData<T> decrypt(DecryptRequest decryptRequest,Class<?> clazz) {
        return null;
    }

    public static void main(String[] args) {
        /*EncryptRequest encryptRequest = new EncryptRequest();
        EncryptServiceImpl encryptService = new EncryptServiceImpl();
        ResponseData<List<AESUtils>> aesUtils = encryptService.encrypt(encryptRequest,AESUtils.class);

        aesUtils.getData();



        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject());*/

        JSONObject o = new JSONObject();
        System.out.println( o instanceof JSONObject);

    }
}
