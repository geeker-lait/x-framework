package com.tasfe.framework.rabbitmq;


/**
 * Created by Lait on 10/17/2016.
 */
public interface RabbitmqMessageProcesser<T> {
    void processMessage(T message);
}
