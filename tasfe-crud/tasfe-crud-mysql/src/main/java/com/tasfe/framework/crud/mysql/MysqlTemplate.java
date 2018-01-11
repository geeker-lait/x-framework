package com.tasfe.framework.crud.mysql;

import com.tasfe.framework.crud.api.Crudable;
import com.tasfe.framework.crud.api.configs.Configs;
import com.tasfe.framework.crud.api.enums.Condition;
import com.tasfe.framework.crud.api.enums.StoragerType;
import com.tasfe.framework.crud.api.criteria.Criteria;
import com.tasfe.framework.crud.api.params.CrudParam;
import com.tasfe.framework.crud.api.criteria.Kvc;
import com.tasfe.framework.crud.api.enums.Operator;
import com.tasfe.framework.crud.core.CrudTemplate;
import com.tasfe.framework.crud.api.operator.mysql.RdbOperator;
import com.tasfe.framework.crud.core.utils.FieldReflectUtil;
import com.tasfe.framework.crud.core.utils.GeneralMapperReflectUtil;
import com.tasfe.framework.crud.core.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lait on 2017/5/29.
 * mysql实现
 */
@Component("mysql")
public class MysqlTemplate extends CrudTemplate implements RdbOperator, InitializingBean {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private boolean camelCase = false;

    @PostConstruct
    private void init() {
        this.camelCase = sqlSessionFactory.getConfiguration().isMapUnderscoreToCamelCase();
    }

    /********************************** in *************************************/
    @Override
    public <T> void _in(T t) throws Exception {
        Map<String, Object> param = new HashMap<>();
        Class<?> clazz = t.getClass();
        String tableName = GeneralMapperReflectUtil.getTableName(clazz);
        Map<String, String> mapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t, camelCase);
        param.put("_table_", tableName);
        param.put("_kv_mapping_", mapping);
        sqlSession.update(Crudable.IN, param);
        if(param.get("id") == null){
            return;
        }
        Field field = FieldReflectUtil.findField(t.getClass(), "id");
        if (null != field) {
            FieldReflectUtil.setFieldValue(t, field, Long.valueOf(param.get("id").toString()));
        }
    }

    @Override
    public <T> void _ins(List<T> list) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        String tableName = "";
        List<String> columns = new ArrayList<String>();

        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        for (T t : list) {
            if (tableName.equals("")) {
                Class<?> clazz = t.getClass();
                tableName = GeneralMapperReflectUtil.getTableName(clazz);
            }
            if (columns.size() == 0) {
                Class<?> clazz = t.getClass();
                columns = GeneralMapperReflectUtil.getAllColumns(clazz, camelCase);
            }
            Map<String, String> mapping = GeneralMapperReflectUtil.getAllFieldValueMapping(t);
            dataList.add(mapping);
        }
        param.put("_table_", tableName);
        param.put("_columns_", columns);
        param.put("_datas_", dataList);
        sqlSession.update(Crudable.INS, param);
    }


    /********************************** upd *************************************/
    @Override
    public <T> int _upd(T t, Criteria criteria) throws Exception {

        Map<String, String> mapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t, camelCase);

        String pk = null;
        Object pv = null;
        boolean customPK = false;
        // 检查主键是否存在，如果不存在则报错！
        if (criteria.isUpdateById()) {
            pk = GeneralMapperReflectUtil.getPrimaryKey(criteria.getClazz());
            pv = mapping.get(pk);
            // 移除主键（主键不更新）
            mapping.remove(pk);
            // 如果自定义了id规则，则使用自定的id规则
            for(Kvc kvc:criteria.getWhere().getKvcs()){
                if(kvc.getKey().equals(pk)){
                    customPK = true;
                    break;
                }
            }
            if(!customPK){
                criteria.getWhere().and(pk,Operator.EQ);
            }
        }

        // 如果有指定更新字段的话
        Map<String,String> fieldMapping = new HashMap<>();
        for (String key : criteria.getFields()) {
            // 如果数据库是驼峰转下滑线的格式，支持用户在field 字段填写domain域中的字段
            if (camelCase) {
                key = StringUtil.camelToUnderline(key);
            }
            fieldMapping.put(key,mapping.get(key));
            mapping = fieldMapping;
        }

        Map<String, Object> param = fillParams(null,criteria);
        param.put("_kv_mapping_", mapping);
        param.putAll(mapping);
        param.put(pk,pv);
        return sqlSession.update(Crudable.UPD, param);
    }

    /**
     * 考虑如何使用批量更新
     *
     * @param entity
     * @param criteria
     * @param <T>
     * @throws Exception
     */
    @Override
    public <T> void _upds(List<T> entity, Criteria criteria) throws Exception {
        for (T t : entity) {
            _upd(t, criteria);
            //Map<String, Object> param = fillParams(criteria);
            //param.put("_kv_mapping_",GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t, camelCase));
            //sqlSession.update(Crudable.UPD,param);
        }
    }


    /********************************** get *************************************/

    @Override
    public <T,PK extends Serializable> T _get(Class<T> clazz, PK id) throws Exception {
        Map<String, Object> param = new HashMap<>();

        String tableName = GeneralMapperReflectUtil.getTableName(clazz);
        String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);
        List<String> queryColumn = GeneralMapperReflectUtil.getAllColumns(clazz, camelCase);

        param.put("_table_", tableName);
        param.put("_pk_", primaryKey);
        param.put("_pv_", id);
        param.put("_columns_", queryColumn);
        Map map = sqlSession.selectOne(Crudable.GET, param);
        T t = clazz.newInstance();
        BeanUtils.populate(t, map);
        return t;
    }

    @Override
    public <T,PK extends Serializable> List<T> _list(Class<T> clazz, PK... ids) throws Exception {
        Map<String, Object> param = new HashMap<>();

        param.put("_table_", GeneralMapperReflectUtil.getTableName(clazz));
        param.put("_columns_", GeneralMapperReflectUtil.getAllColumns(clazz, camelCase));
        param.put("_pks_", ids);

        List<Map> maps = sqlSession.selectList(Crudable.LIST, param);
        List<T> ts = new ArrayList<>();
        for (Map map : maps) {
            T t = clazz.newInstance();
            BeanUtils.populate(t, map);
            ts.add(t);
        }
        return ts;
    }


    @Override
    public <T> List<T> _find(T entity, Criteria criteria) throws Exception {

        Map<String, Object> param = fillParams(entity,criteria);

        List<T> result = new ArrayList<>();
        List<Map<String, Object>> resultMaps = sqlSession.selectList(Crudable.FIND, param);
        // 处理结果
        if (resultMaps != null && resultMaps.size() != 0) {
            if (camelCase) {
                for (Map<String, Object> resultMap : resultMaps) {
                    result.add(GeneralMapperReflectUtil.parseToBean(resultMap, (Class<T>)entity.getClass()/*(Class<T>) criteria.getClazz()*/, true));
                }
            } else {
                for (Map<String, Object> resultMap : resultMaps) {
                    BeanUtils.populate(entity, resultMap);
                    result.add(entity);
                }
            }
        }
        return result;
    }

    @Override
    public <T> List<T> _query(Map<String, Object> params, Criteria criteria, Class<T> clazz) throws Exception {
        return null;
    }


    /********************************** del *************************************/

    @Override
    public <T> int _del(T entity, Criteria criteria) throws Exception {
        Map<String, Object> param = fillParams(null,criteria);
        param.putAll(GeneralMapperReflectUtil.getFieldValueMappingExceptNull(entity, camelCase));
        return sqlSession.delete(Crudable.DELS, param);
    }

    @Override
    public <T,PK extends Serializable> void _dels(Class<T> clazz, PK... pk) throws Exception {
        Map<String, Object> param = new HashMap<>();

        String tableName = GeneralMapperReflectUtil.getTableName(clazz);
        String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);
        param.put("_table_", tableName);
        param.put("_pk_", primaryKey);

        List<Map<String, Object>> datas = new ArrayList<>();
        for (PK id : pk) {
            Map pvmap = new HashMap<String, Object>();
            pvmap.put(primaryKey, id);
            datas.add(pvmap);
            param.put("_datas_", datas);

            // 先循环删除
            param.put("_pv_", id);
            sqlSession.delete(Crudable.DEL, param);
        }

    }


    /********************************** funs *************************************/
    /**
     * count 函数
     * @param clazz     pojo类-class对象
     * @param crudParam 查询参数
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> Long _count(Class<T> clazz, CrudParam crudParam) throws Exception {
        Map<String, Object> param = fillParams(crudParam.getEntity(),crudParam.getCriteria());
        param.putAll(GeneralMapperReflectUtil.getFieldValueMappingExceptNull(crudParam.getEntity(), camelCase));
        Long totalSize = sqlSession.selectOne(Crudable.COUNT, param);
        return totalSize;
    }

    /**
     * sum 函数
     * @param crudParam
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> BigDecimal _sum(CrudParam crudParam) throws Exception {
        Map<String, Object> param = fillParams(crudParam.getEntity(),crudParam.getCriteria());
        //param.putAll(GeneralMapperReflectUtil.getFieldValueMappingExceptNull(crudParam.getEntity(), camelCase));
        param.put("_sum_exp_",crudParam.getCriteria().getExpression());
        BigDecimal totalSize = sqlSession.selectOne(Crudable.SUM, param);
        return totalSize;
    }


    @Override
    public <T> Number _max(CrudParam crudParam) throws Exception {
        return null;
    }

    @Override
    public <T> Number _min(CrudParam crudParam) throws Exception {
        return null;
    }

    @Override
    public <T> Number _avg(CrudParam crudParam) throws Exception {
        return null;
    }


    @Override
    public <T> List<T> _tree(T t, Criteria criteria) throws Exception {
        Map<String, Object> param = fillParams(t,criteria);

        List<T> result = new ArrayList<>();
        List<Map<String, Object>> resultMaps = sqlSession.selectList(Crudable.TREE, param);
        // 处理结果
        if (resultMaps != null && resultMaps.size() != 0) {
            if (camelCase) {
                for (Map<String, Object> resultMap : resultMaps) {
                    result.add(GeneralMapperReflectUtil.parseToBean(resultMap, (Class<T>) criteria.getClazz(), true));
                }
            } else {
                for (Map<String, Object> resultMap : resultMaps) {
                    BeanUtils.populate(t, resultMap);
                    result.add(t);
                }
            }
        }
        return result;
    }

    /***************************** tree *******************************/


    public final void afterPropertiesSet() throws IllegalArgumentException, BeanInitializationException {
        try {
            sqlSession = new SqlSessionTemplate(sqlSessionFactory);
        } catch (Exception var2) {
            throw new BeanInitializationException("Initialization of DAO failed", var2);
        }
    }

    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

    @Override
    public String getStoragerName() {
        return StoragerType.MYSQL.toString();
    }


    private <T>Map<String, Object> fillParams(T entity,Criteria criteria) throws Exception {
        if (criteria == null) {
            throw new Exception("criteria is null!");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("_table_", GeneralMapperReflectUtil.getTableName(criteria.getClazz()));
        param.put("_columns_", criteria.getFields().size() == 0 ? GeneralMapperReflectUtil.getAllColumns(criteria.getClazz(), camelCase) : criteria.getFields());
        /**
         * 创建模板表达式
         * and id = #{id} or user_id > #{user_}
         */
        List<Kvc> finalKvcList;
        if(null != entity){
            Map<String, String> kvMapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(entity, camelCase);
            // 设置参数
            param.putAll(kvMapping);
            List<Kvc> kvcList = new ArrayList<>();
            kvMapping.forEach((k,v)->{
                // 如果没有自定义where条件，那么全部统一用eq
                Kvc kvc = new Kvc(Condition.AND.value(),k,Operator.EQ.value());
                kvcList.add(kvc);
            });
            // 用自定义的key覆盖默认条件(重写model)
            List<Kvc> kvcs = criteria.getWhere().getKvcs();
            List<Kvc> insertList = new ArrayList<>();
            for(int i=0;i<kvcs.size();i++){
                boolean isExist = false;
                for(int j=0;j<kvcList.size(); j++){
                    if(kvcs.get(i).getKey().equals(kvcList.get(j).getKey())){
                        kvcList.get(j).setCondition(kvcs.get(i).getCondition());
                        kvcList.get(j).setSymbol(kvcs.get(i).getSymbol());
                        if(kvcs.get(i).getVal()!=null) {
                            kvcs.get(j).setVal(kvcs.get(i).getVal());
                        }
                        isExist = true;
                        break;
                    }
                }
                if(isExist == false) {
                    Kvc temptKvc = new Kvc();
                    temptKvc.setCondition(kvcs.get(i).getCondition());
                    temptKvc.setSymbol(kvcs.get(i).getSymbol());
                    temptKvc.setKey(kvcs.get(i).getKey());
                    temptKvc.setVal(kvcs.get(i).getVal());
                    insertList.add(temptKvc);
                }
            }
            if(insertList.size()>0) {
                kvcList.addAll(insertList);
            }
            finalKvcList = kvcList;
        } else {
            finalKvcList = criteria.getWhere().getKvcs();
        }
        StringBuffer exp = new StringBuffer("1=1 ");
        for (Kvc kvc : finalKvcList) {
            exp.append(" " + kvc.getCondition() + " ");
            exp.append("`" + kvc.getKey() + "`");

            // 处理模糊
            Object kv = kvc.getVal();
            String kexp = "#{" + kvc.getKey() + "}";
            if (Operator.LIKE.value().equals(kvc.getSymbol())) {
                exp.append(" LIKE CONCAT(CONCAT('%'," + (null != kv ? kv : kexp) + "),'%')");
                //exp.append(" LIKE '%" + (null != kv ? kv : kexp) + "%'");
                //kvc.setVal("CONCAT(CONCAT('%'," + (null != kv ? kv : kexp) + "),'%')");
                //exp.append("CONCAT(CONCAT('%'," + (null != kv ? kv : kexp) + "),'%')");
            } else if (Operator.LLIKE.value().equals(kvc.getSymbol())) {
                exp.append(" LIKE CONCAT('%'," + (null != kv ? kv : kexp) + ")");
                //kvc.setVal("CONCAT('%'," + (null != kv ? kv : kexp) + ")");
            } else if (Operator.RLIKE.value().equals(kvc.getSymbol())) {
                exp.append(" LIKE CONCAT(" + (null != kv ? kv : kexp) + ",'%')");
                //kvc.setVal("CONCAT(" + (null != kv ? kv : kexp) + ",'%')");
            } else {
                exp.append(kvc.getSymbol());
                // 处理有值或无值的情况
                if (kv != null) {
                    exp.append("'" + kv + "'");
                } else {
                    exp.append(kexp);
                }
            }
        }

        param.put("_where_exp_", exp.toString());

        param.put("_order_exp_", criteria.getOrder());

        param.put("_limit_", criteria.getLimit());

        param.put("_group_by_", criteria.groupBy());

        return param;

    }
}
