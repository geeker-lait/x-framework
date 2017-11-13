package com.tasfe.framework.crud.test.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by hefusang on 2017/8/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="users_session")
public class UserSession {

    private Long id;

    private Long userId;

    private String code;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
