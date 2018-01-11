package com.tasfe.framework.crud.mysql.schema;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2018-01-06 14:05
 */
@Table(name="t_database")
@Data
public class Database {
    @Id
    private Long id;
    private String name;
    private String host;
    private String username;
    private String pwd;
}