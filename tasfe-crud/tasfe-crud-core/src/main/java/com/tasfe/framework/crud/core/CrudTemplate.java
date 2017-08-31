package com.tasfe.framework.crud.core;

import com.tasfe.framework.crud.api.enums.CrudMethod;
import com.tasfe.framework.crud.api.CrudOperator;
import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.params.CrudParam;
import com.tasfe.framework.crud.api.params.Page;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.math.BigDecimal;
import java.util.List;

/**
 * 利用mybatis的sqlSession,实现默认方式
 * Created by Lait on 2017/5/29.
 */
public abstract class CrudTemplate implements CrudOperator, ApplicationContextAware {

    private static ThreadLocal<String> method = new ThreadLocal<>();
    private static ThreadLocal<Object> param = new ThreadLocal<>();

    private ApplicationContext applicationContext;

    @Autowired
    private CrudRouter crudRouter;


    /**
     * 自定义xml文件，设置参数
     *
     * @return Class
     */
    public CrudTemplate forParam(Object param) {
        CrudTemplate.param.set(param);
        return this;
    }


    /**
     * 自定义xml文件,设置执行方法名
     *
     * @param method
     * @return
     */
    public CrudTemplate exec(String method) {
        CrudTemplate.method.set(method);
        return this;
    }


    /**
     * 自定义xml文件,执行并填充对象模型
     *
     * @param clazz
     * @param <Entity>
     * @return
     */
    public <Entity> Entity fill(Class<Entity> clazz) {
        Object param = CrudTemplate.param.get();
        String method = CrudTemplate.method.get();
        Entity entity = null;
        //Entity entity = this.getSqlSession().selectOne(getStatementName(method), param);
        CrudTemplate.param.remove();
        CrudTemplate.method.remove();
        return entity;
    }


    public CrudTemplate params(CrudParam crudParam) {

        return this;
    }


    public <T> T forClass(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();

        return t;
    }


    /**
     * 掺入数据
     *
     * @return
     */
    @Override
    public <T> void save(T record) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.IN, record);
        crudRouter.route(crudParam);
    }

    @Override
    public <T> void save(List<T> records) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.INS, records);
        crudRouter.route(crudParam);
    }

    @Override
    public <T> T saveOrUpdate(T record) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.INS, record);
        crudRouter.route(crudParam);
        return null;
    }

    @Override
    public <T> void saveOrUpdate(List<T> records) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.INS, records);
        crudRouter.route(crudParam);
    }

    /**
     * 获取数据
     *
     * @param id
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> T get(Class<T> entiyClass, Long id) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.GET, entiyClass, id);
        return (T) crudRouter.route(crudParam);
    }

    @Override
    public <Entity> List<Entity> list(Class<Entity> entiyClass, Long... ids) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.LIST, entiyClass, ids);
        Criteria criteria = Criteria.from(entiyClass).list(ids);
        return (List<Entity>) crudRouter.route(crudParam);
    }

    @Override
    public <Entity> List<Entity> find(Entity entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.FIND, entity);
        crudParam.setCriteria(criteria);
        return (List<Entity>) crudRouter.route(crudParam);
    }

    @Override
    public <Entity> List<Entity> query(Entity entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.QUERY, criteria);
        crudParam.setEntity(entity);
        return (List<Entity>) crudRouter.route(crudParam);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    @Override
    public <T> void del(Class<T> clazz, Long... id) throws Exception {
        CrudParam crudParam = new CrudParam();
        crudParam.setPks(id);
        crudParam.setQueryClazz(clazz);
        crudParam.setCrudMethod(CrudMethod.DELS.toString());
        crudRouter.route(crudParam);

    }


    @Override
    public <T> void del(T entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.DEL, entity);
        crudParam.setCriteria(criteria);
        crudRouter.route(crudParam);
    }


    /**
     * 更新数据
     *
     * @param entity
     * @param <Entity>
     */
    @Override
    public <Entity> void update(Entity entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.UPD, entity);
        crudParam.setCriteria(criteria);
        crudRouter.route(crudParam);
    }


    @Override
    public <Entity> void update(List<Entity> entities, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.UPDS, entities);
        crudParam.setCriteria(criteria);
        crudRouter.route(crudParam);

    }


    /**
     * 分页数据
     *
     * @param entity
     * @param criteria
     * @param <Entity>
     * @return
     * @throws Exception
     */
    @Override
    public <Entity> Page<Entity> paging(Entity entity, Criteria criteria) throws Exception {
        Page<Entity> pager = new Page<>();
        //查询总数
        pager.setTotalSize(count(entity, criteria));
        //获取分页数据
        CrudParam crudParam = new CrudParam(CrudMethod.FIND, entity);
        crudParam.setCriteria(criteria);
        Object list = crudRouter.route(crudParam);
        pager.setList((List<Entity>) list);
        return pager;
    }

    @Override
    public <Entity> Long count(Entity entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.COUNT, entity);
        crudParam.setCriteria(criteria);
        return (Long) crudRouter.route(crudParam);
    }

    @Override
    public <Entity> BigDecimal sum(Entity entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.SUM, entity);
        crudParam.setCriteria(criteria);
        return (BigDecimal) crudRouter.route(crudParam);
    }


    @Override
    public <Entity> List<Entity> tree(Entity entity, Criteria criteria) throws Exception {
        CrudParam crudParam = new CrudParam(CrudMethod.TREE, entity);
        crudParam.setCriteria(criteria);
        return (List<Entity>) crudRouter.route(crudParam);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
