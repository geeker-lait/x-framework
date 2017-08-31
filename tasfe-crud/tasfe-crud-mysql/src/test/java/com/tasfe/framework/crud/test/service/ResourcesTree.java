package com.tasfe.framework.crud.test.service;

import lombok.Data;

import java.util.List;

/**
 * Created by Lait on 2017/8/24.
 */
@Data
public class ResourcesTree{
        private Long id;

        // 父亲节点id
        private Long pid;

        // 资源的类型
        private Integer typ;

        // 资源名称
        private String name;

        // 资源的值
        private String val;

        // 资源的描述/备注说明
        private String descr;

        // 资源的状态
        private Integer status;

        // 排序
        private Integer sort;

        // 图标
        private String icon;

        // 打开方式(_bank,target)
        private Integer target;

        private List<ResourcesTree> childs;
}
