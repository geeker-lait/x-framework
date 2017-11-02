package com.tasfe.framework.uid.exception;

/**
 * Created by lait.zhang on 2017/8/3.
 */
public class RedisNoAddressException extends RuntimeException{

    public RedisNoAddressException(String message) {
        super(message);
    }
}
