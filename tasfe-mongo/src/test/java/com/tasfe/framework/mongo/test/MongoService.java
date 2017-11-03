package com.tasfe.framework.mongo.test;

import com.tasfe.framework.mongo.annotation.DynamicMongoSource;
import com.tasfe.framework.mongo.factory.MongoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lait.zhang on 2017/4/23.
 */
@Service
public class MongoService {

    @Autowired
    MongoFactory mongoFactory;

    @DynamicMongoSource(db = "admin")
    public void findAdmin() {
        System.err.println(mongoFactory.dynamicMongoDao.getMongoTemplate().findAll(Admin.class, "system.users"));
    }

    @DynamicMongoSource(key = "localMongo", db = "idea")
    public void findIdea() {
        System.err.println(mongoFactory.dynamicMongoDao.findListBean(Idea.class));
    }

    @DynamicMongoSource(db = "idea")
    public void addTest() {
        Idea i = new Idea();
        i.setName("lait");
        i.setCnName("亮哥");
        mongoFactory.dynamicMongoDao.insert(i);
    }
}
