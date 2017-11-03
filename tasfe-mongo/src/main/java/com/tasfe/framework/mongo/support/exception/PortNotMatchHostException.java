package com.tasfe.framework.mongo.support.exception;

/**
 * Created by lait.zhang on 2017/4/23.
 */
public class PortNotMatchHostException extends RuntimeException {
    public PortNotMatchHostException() {
        super();
    }

    public PortNotMatchHostException(String message) {
        super(message);
    }
}
