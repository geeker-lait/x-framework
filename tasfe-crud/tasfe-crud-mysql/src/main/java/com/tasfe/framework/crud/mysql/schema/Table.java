package com.tasfe.framework.crud.mysql.schema;

import lombok.Data;

import javax.persistence.Id;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2018-01-06 14:05
 */
@javax.persistence.Table(name="t_table")
@Data
public class Table {

    @Id
    private Long id;

    // 数据库id
    private Long pid;
    // 资源id
    private Long resid;

    private String name;

    private Integer sort;

}