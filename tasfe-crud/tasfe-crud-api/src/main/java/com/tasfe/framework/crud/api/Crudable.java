package com.tasfe.framework.crud.api;

import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.params.CrudParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Lait on 2017/7/10.
 */
public interface Crudable {
    /**
     * 单插入
     */
    String IN = "_in";
    /**
     * 批插入
     */
    String INS = "_ins";


    /**
     * 获取单条,id查询
     */
    String GET = "_get";
    /**
     * 获取一条或多条,equals条件查询
     */
    String LIST = "_list";

    /**
     * 获取一条或多条,like条件查询
     */
    String FIND = "_find";

    /**
     * 关联或复杂条件查询
     */
    String QUERY = "_query";


    /**
     * 单条更新
     */
    String UPD = "_upd";

    /**
     * 单条或多条更新
     */
    String UPDS = "_upds";


    /**
     * 单挑删除,id删除
     */
    String DEL = "_del";
    /**
     * 单挑或多条删除，条件删除
     */
    String DELS = "_dels";

    /**
     * 条数
     */
    String COUNT = "_count";

    /**
     * 求和函数
     */
    String SUM = "_sum";

    /**
     * 获取最大值
     */
    String MAX = "_max";

    /**
     * 获取最小值
     */
    String MIN = "_min";

    /**
     * 获取平均值
     */
    String AVG = "_avg";


    String TREE = "_tree";

    /**
     * 获取存储器的名字
     *
     * @return
     */
    String getStoragerName();



    /*********************************** insert ***********************************/
    /**
     * 插入数据
     *
     * @param <T> pojo类
     * @param t   pojo对象
     * @return 数据条数
     * @throws Exception
     */
    <T> void _in(T t) throws Exception;

    <T> void _ins(List<T> list) throws Exception;

    /*********************************** get/find/query ***********************************/

    /**
     * 根据主键查询
     *
     * @param <T>   pojo类
     * @param clazz pojo类-class对象
     * @param pk    主键值
     * @return pojo对象
     * @throws Exception
     */
    <T,PK extends Serializable> T _get(Class<T> clazz, PK pk) throws Exception;


    /**
     * @param clazz
     * @param pk
     * @param <T>
     * @return
     * @throws Exception
     */
    <T,PK extends Serializable> List<T> _list(Class<T> clazz, PK... pk) throws Exception;


    /**
     * 高级查询
     *
     * @param t        pojo类-class对象
     * @param criteria 查询参数
     * @return
     * @throws Exception
     */
    <T> List<T> _find(T t, Criteria criteria) throws Exception;

    /**
     * 高级查询,指定返回列
     *
     * @param params   pojo类-class对象
     * @param criteria 查询参数
     * @return
     * @throws Exception
     */
    <T> List<T> _query(Map<String, Object> params, Criteria criteria,Class<T> clazz) throws Exception;

    /*********************************** delete ***********************************/

    /**
     * 删除
     * <p>
     * 根据主键删除
     * </p>
     *
     * @param <T>   pojo类
     * @param clazz pojo类-class对象
     * @param pk    主键值
     * @return 数据条数
     */
    <T,PK extends Serializable> void _dels(Class<T> clazz, PK... pk)  throws Exception ;

    /**
     * 删除
     * <p>
     * 根据条件删除
     * </p>
     *
     * @param t        pojo类
     * @param criteria
     * @param <T>
     * @return 数据条数
     */
    <T> int _del(T t, Criteria criteria)  throws Exception ;


    /*********************************** update ***********************************/
    /**
     * 更新
     * <p>
     * 根据主键更新
     * </p>
     * <p>
     * 更新pojo的所有字段，包括空值(null值)字段
     * </p>
     *
     * @param <T> pojo类
     * @param t   pojo对象
     * @return 数据条数
     * @throws Exception
     */
    <T> int _upd(T t,Criteria criteria) throws Exception;

    /**
     * 更新
     * <p>
     * 根据条件更新
     * </p>
     * <p>
     * 更新pojo的指定字段集
     * </p>
     *
     * @throws Exception
     */
    <T> void _upds(List<T> entitys, Criteria criteria) throws Exception;

    /**
     * 高级查询
     *
     * @param clazz     pojo类-class对象
     * @param crudParam 查询参数
     * @return
     * @throws Exception
     */
    <T> Long _count(Class<T> clazz, CrudParam crudParam) throws Exception;


    /**
     * 获取最大数
     *
     * @param crudParam
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Number _max(CrudParam crudParam) throws Exception;


    /**
     * 获取最大数
     *
     * @param crudParam
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Number _min(CrudParam crudParam) throws Exception;


    /**
     * 获取最大数
     *
     * @param crudParam
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Number _avg(CrudParam crudParam) throws Exception;


    /**
     * 获取最大数
     *
     * @param crudParam
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> Number _sum(CrudParam crudParam) throws Exception;



    <T> List<T> _tree(T t, Criteria criteria) throws Exception;

}
