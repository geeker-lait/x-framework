package com.tasfe.framework.encrypt.model.bo;

import lombok.Data;

import java.util.Date;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-12-05 10:57
 */
@Data
public class KeyStoreInfos {
    private Long id;
    // 业务线id
    private String businessId;
    // key
    private String key;
    // 公钥
    private String publ;
    // 私钥
    private String priv;

    //创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
}