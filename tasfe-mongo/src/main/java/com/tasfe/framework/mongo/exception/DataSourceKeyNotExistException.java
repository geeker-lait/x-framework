package com.tasfe.framework.mongo.exception;

/**
 * Created by lait.zhang on 2017/4/22.
 */
public class DataSourceKeyNotExistException extends RuntimeException {

    public DataSourceKeyNotExistException() {
        super("传入的key不在数据源map中");
    }
}
