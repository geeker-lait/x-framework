package com.tasfe.framework.crud.api.operator;

import com.tasfe.framework.crud.api.criteria.Criteria;

import java.util.List;

/**
 * Created by Lait on 2017/7/7.
 */
public interface UpdateOperator {

    /**
     * 根据主键更新指定的对象
     *
     * @param entity
     */
    <Entity> void update(Entity entity, Criteria criteria) throws Exception;


    /**
     * 皮量更新指定的对象
     *
     * @param entityList
     */
    <Entity> void update(List<Entity> entityList,Criteria criteria) throws Exception;


    /**
     * 批量增加/更新,并返回主键,主键在对象中
     * @param record
     * @return
     */
    <T> T saveOrUpdate(T record) throws Exception;

    /**
     * 批量增加/更新,并返回主键,主键在对象中
     * @param records
     * @return
     */
    <T> void saveOrUpdate(List<T> records) throws Exception;


}
