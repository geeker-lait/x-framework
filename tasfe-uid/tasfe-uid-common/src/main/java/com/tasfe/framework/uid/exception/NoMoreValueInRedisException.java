package com.tasfe.framework.uid.exception;

/**
 * Created by lait.zhang on 2017/8/3.
 */
public class NoMoreValueInRedisException extends RuntimeException{

    public NoMoreValueInRedisException(String message) {
        super(message);
    }
}
