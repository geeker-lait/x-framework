package com.tasfe.framework.crud.core;

import com.tasfe.framework.crud.api.enums.CrudMethod;
import com.tasfe.framework.crud.api.Crudable;
import com.tasfe.framework.crud.api.enums.StoragerType;
import com.tasfe.framework.crud.api.annotation.Route;
import com.tasfe.framework.crud.api.annotation.Sharding;
import com.tasfe.framework.crud.api.annotation.Storager;
import com.tasfe.framework.crud.api.params.CrudParam;
import com.tasfe.framework.crud.core.sharding.CrudShardingDataSource;
import com.tasfe.framework.crud.core.utils.FieldReflectUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by Lait on 2017/5/29.
 */
@Service
public class CrudRouter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private CrudShardingDataSource crudShardingDataSource;
    // RDB写数据全
    private List<DataSource> writeDataSourceList;

    // RDB读数据源
    private List<DataSource> readDataSourceList;

    /**
     * 路由数据
     * @param crudParam
     * @return
     * @throws Exception
     * CrudMethod cm,Class<T> clazz,T entity,List<T> tlist,CrudParam param
     */
    @Route
    public Object route(CrudParam crudParam) throws Exception {
        Class<?> clazz = crudParam.getQueryClazz();
        if(clazz !=null ){
            Storager storager = clazz.getAnnotation(Storager.class);
            Crudable crudable = null;
            StoragerType[] storagerTypes = null;
            if (storager != null) {
                storagerTypes = storager.storage();
            } else {
                storagerTypes = new StoragerType[]{StoragerType.MYSQL};
            }

            Sharding sharding = clazz.getAnnotation(Sharding.class);
            if(sharding != null){
                String key = sharding.key();
            }


            for (StoragerType storagerType : storagerTypes) {
                crudable = (Crudable)applicationContext.getBean(storagerType.toString().toLowerCase());

                //Method method ;
                //method.invoke(crudable,obj);


                CrudMethod cm = CrudMethod.get(crudParam.getCrudMethod());
                switch (cm) {
                    case IN:
                        Object object = crudParam.getEntity();
                        //Object shardingValue = FieldReflectUtil.getFieldValue(object,key);
                        //Table table = clazz.getAnnotation(Table.class);
                        //String shardingDatabaseAndTable = sharding(table.name(),shardingValue);

                        // 获取写数据源
                        /*for(DataSource dataSource:readDataSourceList){

                        }*/
                        crudable._in(object);
                        break;
                    case INS:
                        crudable._ins(crudParam.getEntityList());
                        break;
                    case GET:
                        return crudable._get(clazz, crudParam.getPk());
                    case LIST:
                        return crudable._list(clazz,crudParam.getPks());
                    case FIND:
                        return crudable._find(crudParam.getEntity(), crudParam.getCriteria());
                    case QUERY:
                        //return crudable._list(clazz, crudParam);
                        break;
                    case UPD:
                        return crudable._upd(crudParam.getEntity(),crudParam.getCriteria());
                    case UPDS:
                        crudable._upds(crudParam.getEntityList(),crudParam.getCriteria());
                        break;
                    case DEL:
                        return crudable._del(crudParam.getEntity(),crudParam.getCriteria());
                    case DELS:
                        crudable._dels(crudParam.getQueryClazz(),crudParam.getPks());
                        break;
                    case COUNT:
                        return crudable._count(clazz, crudParam);
                    case SUM:
                        return crudable._sum(crudParam);
                    case TREE:
                        return crudable._tree(crudParam.getEntity(),crudParam.getCriteria());
                }
            }
        } else {
            // 做点提示
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
//        crudShardingDataSource = applicationContext.getBean(CrudShardingDataSource.class);
//        writeDataSourceList = crudShardingDataSource.getWriteDataSources();
//        readDataSourceList = crudShardingDataSource.getReadDataSources();
    }


    private String sharding(String tableName,Object shardingValue){
        int total = crudShardingDataSource.getShardingCapacity();
        int sv = Integer.valueOf(shardingValue.toString());
        int dbCount = crudShardingDataSource.getShardingDatabaseCount();
        int tbCount = crudShardingDataSource.getShardingTableCount();
        long divisor = Math.round(sv / total);
        long coefficient = sv % total;
        String databaseName = crudShardingDataSource.getShardingDatabaseName();
        long ds = coefficient % dbCount + divisor * dbCount;
        String strcatChar = crudShardingDataSource.getStrcatChar();
        long tb = coefficient % tbCount;
        return databaseName+strcatChar+ds+"." + tableName+strcatChar + tb;
    }

    public static void main(String[] args) {


    }
}
