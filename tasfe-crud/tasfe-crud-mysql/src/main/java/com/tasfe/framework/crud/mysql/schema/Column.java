package com.tasfe.framework.crud.mysql.schema;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2018-01-06 14:06
 */
@Table(name="t_column")
@Data
public class Column {
    @Id
    private Long id;
    // table id
    private Long pid;
    // 资源id
    private Long resid;
    private String name;
    private String typ;


}