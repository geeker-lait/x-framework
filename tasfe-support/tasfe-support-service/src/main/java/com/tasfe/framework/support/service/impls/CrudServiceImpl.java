package com.tasfe.framework.support.service.impls;

import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.params.Page;
import com.tasfe.framework.crud.core.CrudTemplate;
import com.tasfe.framework.support.exception.DaoException;
import com.tasfe.framework.support.service.CrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Lait on 2017/7/26.
 */
public abstract class CrudServiceImpl<M, E, PK extends Serializable> implements CrudService<M, E, PK> {

    @Autowired(required = false)
    protected CrudTemplate crudTemplate;
    private Class<E> entityClass;
    private Class<M> modelClass;

    public CrudServiceImpl() {
        modelClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


    //@Autowired
    //private GenericDao<T,ID> baseDao;
    //private BaseDao<T> baseDao;
    /*@Autowired
    protected EntityManager entityManager;*/
    //@Autowired(required = true)
    //private GenericCrudRepository<T,ID> genericCrudRepository;
   /* @Override
    public GenericCrudRepository getGenericCrudRepository() {
        //return genericCrudRepository;
        return null;
    }*/

    /**
     * 保存对象
     *
     * @param model 需要添加的对象
     */
    @Override
    public void save(E model){
        try {
            crudTemplate.save(model);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 批量保存对象
     *
     * @param modelList 需要增加的对象的集合
     *                  失败会抛异常
     */
    @Override
    public void save(List<E> modelList){
        try{
            crudTemplate.save(modelList);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 删除对象
     *
     * @param model 需要删除的对象
     *              失败会抛异常
     */
    @Override
    public void delete(M model, Criteria criteria){
        try{
            crudTemplate.delete(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 批量删除对象
     *
     * @param entitys 需要删除的对象的集合
     *                失败会抛异常
     */
    @Override
    public void delete(List<M> entitys, Criteria criteria){
        try{
            for (M entity : entitys) {
                crudTemplate.delete(entity, criteria);
            }
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 按照id删除对象
     *
     * @param id 需要删除的对象的id
     *           失败抛出异常
     */
    @SafeVarargs
    @Override
    public final void delete(PK... id){
        try{
            crudTemplate.delete(modelClass, id);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }


    @Override
    public void update(M entity, Criteria criteria){
        try{
            crudTemplate.update(entity, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    @Override
    public void update(List<M> entitys, Criteria criteria){
        try{
            for (M entity : entitys) {
                crudTemplate.update(entity, criteria);
            }
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 更新或保存对象
     *
     * @param model 需要更新的对象
     *              失败会抛出异常
     */
    @Override
    public void saveOrUpdate(E model){
        try{
            crudTemplate.save(model);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 批量更新或保存对象
     *
     * @param modelList 需要更新或保存的对象
     *                  失败会抛出异常
     */
    @Override
    public void saveOrUpdate(final List<E> modelList){
        try{
            crudTemplate.save(modelList);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键(Serializable)
     * @return model
     */
    @Override
    public M get(PK id){
        try{
            E e = crudTemplate.get(entityClass, id);
            M m = modelClass.newInstance();
            BeanUtils.copyProperties(e,m);
            return m;
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    @SafeVarargs
    @Override
    public final List<M> gets(PK... id){
        try{
            return crudTemplate.list(modelClass, id);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    @Override
    public List<M> find(Criteria criteria){
        try{
            M model= modelClass.newInstance();
            return crudTemplate.find(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    @Override
    public List<M> find(M model, Criteria criteria){
        try{
            return crudTemplate.find(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }

    }

    @Override
    public M findOne(M model,Criteria criteria){
        try{
            List<M> list=crudTemplate.find(model, criteria.limit(0,1));
            if(list!=null&&list.size()>0){
                return list.get(0);
            }
            return null;
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }


    @Override
    public Page<M> paging(Criteria criteria){
        try{
            M model = modelClass.newInstance();
            return crudTemplate.paging(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    @Override
    public Page<M> paging(M model, Criteria criteria){
        try{
            return crudTemplate.paging(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }


    /**
     * 分页查询
     *
     * @param currentPageNumber 页码
     * @param pageSize          每页数量
     * @return 查询结果
     */
    public List<M> list(final Integer currentPageNumber, final Integer pageSize){
        return null;
    }

    /**
     * 获得数量 利用Count(*)实现
     *
     * @return 数量
     */
    @Override
    public long count(M model,Criteria criteria){
        try{
            return crudTemplate.count(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }

    /**
     * 获得数量 利用Count(*)实现
     *
     * @return 数量
     */
    @Override
    public long count(Criteria criteria){
        //return crudTemplate.count();
        //return baseDao.getCount(modelClass);
        try{
            M model = modelClass.newInstance();
            return crudTemplate.count(model, criteria);
        } catch (Exception e) {
            throw new DaoException("dao操作异常！",e);
        }
    }




}