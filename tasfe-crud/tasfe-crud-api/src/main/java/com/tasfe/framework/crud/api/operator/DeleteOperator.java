package com.tasfe.framework.crud.api.operator;

import com.tasfe.framework.crud.api.criteria.Criteria;

/**
 * Created by Lait on 2017/7/7.
 */
public interface DeleteOperator {

    /**
     * 根据id删除/根据指定的id集合删除
     *
     * @param ids
     */
    <T> void del(Class<T> clazz,Long... ids) throws Exception;


    /**
     * 根据条件删除记录
     * @param records
     * @param criteria
     * @param <T>
     * @throws Exception
     */
    <T> void del(T records, Criteria criteria) throws Exception;


}

