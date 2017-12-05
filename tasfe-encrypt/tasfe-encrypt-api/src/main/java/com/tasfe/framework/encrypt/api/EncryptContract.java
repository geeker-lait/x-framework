package com.tasfe.framework.encrypt.api;

import com.tasfe.framework.encrypt.model.dto.DecryptRequest;
import com.tasfe.framework.encrypt.model.dto.EncryptRequest;
import com.tasfe.framework.support.model.ResponseData;

public interface EncryptContract {

    /**
     * 返回包含加密过后的序列化对象
     * @param encryptRequest
     * @param <T> 返回加密过后的序列化对象
     * @return
     */
    <T>ResponseData<T> encrypt(EncryptRequest encryptRequest);


    <T>ResponseData<T> decrypt(DecryptRequest decryptRequest);

}
