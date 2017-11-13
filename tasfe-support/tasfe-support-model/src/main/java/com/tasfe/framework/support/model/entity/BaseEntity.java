package com.tasfe.framework.support.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lait on 2017/8/8.
 * 统一定义id的entity基类.
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 *
 * @MappedSuperclass 声明为父类，不生成实体表
 */
@MappedSuperclass
//@Access(AccessType.FIELD)
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseEntity<PK extends Serializable> extends IdEntity<PK> {

    @Column(length = 32, updatable = false, nullable = true)
    private PK createById;
    @Column(length = 32, nullable = true)
    private PK updateById;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = true)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date updateTime;

}
