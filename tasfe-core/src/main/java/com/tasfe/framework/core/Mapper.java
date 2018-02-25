package com.tasfe.framework.core;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


/**
 * 描述:定制版MyBatis Mapper插件接口，如需其他接口参考官方文档自行添加。
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2018-02-25 10:47
 */
public interface Mapper<T>
        extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T> {
}
