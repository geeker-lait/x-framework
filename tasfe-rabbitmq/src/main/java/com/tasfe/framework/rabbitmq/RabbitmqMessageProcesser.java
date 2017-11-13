package com.tasfe.framework.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * Created by Lait on 10/17/2016.
 */
public interface RabbitmqMessageProcesser {
    void processMessage(Message message, Channel channel);
}
