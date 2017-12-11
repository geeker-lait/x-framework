package com.tasfe.framework.crud.core.encrypt;

import lombok.Data;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-12-11 10:58
 */
@Data
public class EncryptParams {
    // 待加密/解密的key
    private String key;
    // 待加密/解密的val
    private String val;
    // 原字段名称
    private String orgKey;
}