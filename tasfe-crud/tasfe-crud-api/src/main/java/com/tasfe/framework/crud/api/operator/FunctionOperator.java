package com.tasfe.framework.crud.api.operator;

import com.tasfe.framework.crud.api.criteria.Criteria;

import java.math.BigDecimal;

/**
 * Created by Lait on 2017/8/6.
 */
public interface FunctionOperator {

    /**
     * 根据条件获取查询的总记录数
     *
     * @param entity 查询条件参数
     * @return 总记录数
     */
    <Entity> Long count(Entity entity,Criteria criteria) throws Exception;


    /**
     * 根据条件获取查询的总记录数
     *
     * @param entity 查询条件参数
     * @return 总记录数
     */
    <Entity> BigDecimal sum(Entity entity, Criteria criteria) throws Exception;

    //max


    //min


    //avg



}
