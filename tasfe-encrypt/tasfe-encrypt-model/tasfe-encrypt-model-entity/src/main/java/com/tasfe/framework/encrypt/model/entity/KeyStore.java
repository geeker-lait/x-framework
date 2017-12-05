package com.tasfe.framework.encrypt.model.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-12-04 21:49
 */
@Data
@Table(name = "t_key_store")
public class KeyStore {
    @Id
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
