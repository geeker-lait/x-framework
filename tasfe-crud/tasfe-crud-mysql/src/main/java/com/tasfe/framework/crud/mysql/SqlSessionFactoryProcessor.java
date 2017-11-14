package com.tasfe.framework.crud.mysql;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lait on 2017/7/7.
 */
@Component
public class SqlSessionFactoryProcessor implements BeanFactoryPostProcessor {
    //private final static String MAPPER_LOCATION_PATH = "com/tasfe/framework/crud/mysql/impls/mybatis/mapper/CrudMapper.xml";

    private final static String MAPPER_LOCATION_PATH =
            "META-INF/mybatis/mappers/InsertMapper.xml," +
                    "META-INF/mybatis/mappers/SelectMapper.xml," +
                    "META-INF/mybatis/mappers/UpdateMapper.xml," +
                    "META-INF/mybatis/mappers/DeleteMapper.xml," +
                    "META-INF/mybatis/mappers/FunctionMapper.xml";

    private final static String INSERT_MAPPER = "META-INF/mybatis/mappers/InsertMapper.xml";
    private final static String UPDATE_MAPPER = "META-INF/mybatis/mappers/UpdateMapper.xml";
    private final static String SELECT_MAPPER = "META-INF/mybatis/mappers/SelectMapper.xml";
    private final static String DELETE_MAPPER = "META-INF/mybatis/mappers/DeleteMapper.xml";
    private final static String FUNCTION_MAPPER = "META-INF/mybatis/mappers/FunctionMapper.xml";

    private List<Resource> resources = new LinkedList<Resource>(){
        {
            add(new ClassPathResource(INSERT_MAPPER));
            add( new ClassPathResource(UPDATE_MAPPER));
            add(new ClassPathResource(SELECT_MAPPER));
            add(new ClassPathResource(DELETE_MAPPER));
            add(new ClassPathResource(FUNCTION_MAPPER));
        }
    };


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        /**
         * 在原有的mapper.xml集合中增加CrudMapper.xml文件
         */

        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("sqlSessionFactory");
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();


        //SqlSessionFactoryBean sqlSessionFactoryBean = configurableListableBeanFactory.getBean(SqlSessionFactoryBean.class);
        try {
            //Field field = FieldReflectUtil.findField(SqlSessionFactoryBean.class, "mapperLocations");

            /*Resource mapperLocations[] = (Resource[]) FieldReflectUtil.getFieldValue(sqlSessionFactoryBean,field);
            if(mapperLocations != null){
                List<Resource> mpList =  Arrays.asList(mapperLocations);
                mpList.add(resource);
                resources = (Resource[]) mpList.toArray();
                //resources = new Resource[mapperLocations.length+1];
                //System.arraycopy(mapperLocations, 0, resources, 1, mapperLocations.length);
            }*/
            //mapperLocations = resources;
            /**
             * 重新设值
             */
            String proName = "mapperLocations";
            //Resource resource = new ClassPathResource(MAPPER_LOCATION_PATH);
            if (mutablePropertyValues.contains(proName)) {
                String mapperLocations = "";
                Object object = mutablePropertyValues.getPropertyValue(proName).getValue();
                if ((object instanceof TypedStringValue))
                {
                    TypedStringValue typedStringValue = (TypedStringValue)object;
                    mapperLocations = typedStringValue.getValue();
                    this.resources.add(new ClassPathResource(mapperLocations));
                }
                else if ((object instanceof ManagedList))
                {
                    ManagedList managedList = (ManagedList)object;
                    for (Object obj : managedList) {
                        if ((obj instanceof TypedStringValue))
                        {
                            TypedStringValue typedStringValue = (TypedStringValue)obj;
                            mapperLocations = typedStringValue.getValue();
                            this.resources.add(new ClassPathResource(mapperLocations));
                        }
                    }
                }
                mutablePropertyValues.addPropertyValue(proName, this.resources.toArray());
            } else {
                mutablePropertyValues.add(proName, this.resources.toArray());
            }
            //sqlSessionFactoryBean.setMapperLocations(resources);
        } catch (Exception e) {
            throw new BeanInitializationException(e.getMessage());
        }
    }
}
