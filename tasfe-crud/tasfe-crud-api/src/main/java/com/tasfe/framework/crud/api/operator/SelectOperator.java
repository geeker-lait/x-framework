package com.tasfe.framework.crud.api.operator;

import com.tasfe.framework.crud.api.criteria.Criteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lait on 2017/7/7.
 */
public interface SelectOperator{

    /**
     * @param id
     * @return
     */
    <Entity,PK extends Serializable> Entity get(Class<Entity> entity,PK id) throws Exception;

    /**
     * 根据id获取/根据指定的id集合查询
     *
     * @param ids
     * @return
     */
    <Entity,PK extends Serializable> List<Entity> list(Class<Entity> entity,PK... ids) throws Exception;


    /**
     * 使用like/equal查询
     *
     * @param entity
     * @return
     */
    <Entity> List<Entity> find(Entity entity,Criteria criteria) throws Exception;


}
