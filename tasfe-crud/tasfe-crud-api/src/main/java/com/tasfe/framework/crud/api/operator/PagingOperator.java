package com.tasfe.framework.crud.api.operator;

import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.params.Page;

/**
 * Created by Lait on 2017/7/7.
 */
public interface PagingOperator {
    /**
     * 分页查询，获取当前分页查询的总记录数
     *
     * @param entity
     * @param criteria 查询条件参数
     * @return 分页记录列表
     */
    <Entity> Page<Entity> paging(Entity entity, Criteria criteria) throws Exception;
}
