package com.tasfe.framework.mongo.pojo;

import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * 所有的实体类都要继承此类，以来生成MongoDB的主键_id<br>
 */
public class MongoModule {

    @Id
    public String _id;

    public MongoModule() {
        this.set_id(String.valueOf(UUID.randomUUID().toString().replace("-", "")));
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
