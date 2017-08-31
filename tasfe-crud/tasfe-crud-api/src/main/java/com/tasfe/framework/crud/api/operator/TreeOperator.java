package com.tasfe.framework.crud.api.operator;

import com.tasfe.framework.crud.api.criteria.Criteria;

import java.util.List;

/**
 * Created by Lait on 2017/8/24.
 */
public interface TreeOperator {
    /**
     * 菜单树
     * @param entity
     * @param criteria
     * @param <Entity>
     * @return
     * @throws Exception
     */
    <Entity> List<Entity> tree(Entity entity, Criteria criteria) throws Exception;
}
