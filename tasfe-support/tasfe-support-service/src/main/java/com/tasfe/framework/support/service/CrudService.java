package com.tasfe.framework.support.service;


import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.params.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lait on 2017/7/28.
 */
public interface CrudService<M,E,PK extends Serializable> {

    //GenericCrudRepository getGenericCrudRepository();

    /************************* jpa 实现 方便快速开发  *************************/
    /**
     * 保存对象
     *
     * @param entity 需要添加的对象
     */
    void save(E entity) throws Exception;

    /**
     * 批量保存对象
     *
     * @param entitys 需要增加的对象的集合
     *                失败会抛异常
     */
    void save(List<E> entitys) throws Exception;

    /**
     * 删除对象
     *
     * @param model 需要删除的对象
     *               失败会抛异常
     */
    void delete(M model, Criteria criteria) throws Exception;

    /**
     * 批量删除对象
     *
     * @param entitys 需要删除的对象的集合
     *                失败会抛异常
     */
    void delete(List<M> entitys, Criteria criteria) throws Exception;

    /**
     * @param id
     * @throws Exception
     */
    void delete(PK... id) throws Exception;


    void update(M model, Criteria criteria) throws Exception;

    void update(List<M> models, Criteria criteria) throws Exception;

    /**
     * 更新或保存对象
     *
     * @param entity 需要更新的对象
     *               失败会抛出异常
     */
    void saveOrUpdate(E entity) throws Exception;

    /**
     * 批量更新或保存对象
     *
     * @param entitys 需要更新或保存的对象
     *                失败会抛出异常
     */
    void saveOrUpdate(List<E> entitys) throws Exception;

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键(Serializable)
     * @return model
     */
    M get(PK id) throws Exception;


    /**
     * @param id
     * @return
     * @throws Exception
     */
    List<M> gets(PK... id) throws Exception;


    /**
     * like
     *
     * @return List
     */
    List<M> find(M model, Criteria criteria) throws Exception;


    /**
     * like
     *
     * @return List
     */
    List<M> find(Criteria criteria) throws Exception;

    /**
     * 分页查询
     *
     * @param model
     * @param criteria
     * @return 查询结果
     */
    Page<M> paging(M model, Criteria criteria) throws Exception;


    Page<M> paging(Criteria criteria) throws Exception;


}
