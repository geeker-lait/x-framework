package com.tasfe.framework.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.ReceiveAndReplyCallback;
import org.springframework.amqp.core.ReplyToAddressCallback;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.core.ParameterizedTypeReference;

/**
 * Created by Lait on 10/17/2017.
 */
public class RabbitmqProducerProxy implements RabbitOperations {

    private RabbitTemplate rabbitTemplate;

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public <T> T execute(ChannelCallback<T> channelCallback) throws AmqpException {
        return rabbitTemplate.execute(channelCallback);
    }

    @Override
    public <T> T invoke(OperationsCallback<T> operationsCallback) throws AmqpException {
        return rabbitTemplate.invoke(operationsCallback);
    }

    @Override
    public boolean waitForConfirms(long l) throws AmqpException {
        return rabbitTemplate.waitForConfirms(l);
    }

    @Override
    public void waitForConfirmsOrDie(long l) throws AmqpException {
        rabbitTemplate.waitForConfirmsOrDie(l);
    }

    @Override
    public ConnectionFactory getConnectionFactory() {
        return rabbitTemplate.getConnectionFactory();

    }

    @Override
    public void send(String s, String s1, Message message, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.send(s,s1,message,correlationData);
    }

    @Override
    public void correlationConvertAndSend(Object o, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.correlationConvertAndSend(o,correlationData);
    }

    @Override
    public void convertAndSend(String s, Object o, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.convertAndSend(s,o,correlationData);
    }

    @Override
    public void convertAndSend(String s, String s1, Object o, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.convertAndSend(s,s1,o,correlationData);
    }

    @Override
    public void convertAndSend(Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.convertAndSend(o,messagePostProcessor,correlationData);
    }

    @Override
    public void convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.convertAndSend(s,o,messagePostProcessor,correlationData);
    }

    @Override
    public void convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        rabbitTemplate.convertAndSend(s,s1,o,messagePostProcessor,correlationData);
    }

    @Override
    public Object convertSendAndReceive(Object o, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(o,correlationData);
    }

    @Override
    public Object convertSendAndReceive(String s, Object o, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,o,correlationData);
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,s1,o,correlationData);
    }

    @Override
    public Object convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(o,messagePostProcessor,correlationData);
    }

    @Override
    public Object convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,o,messagePostProcessor,correlationData);
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,s1,o,messagePostProcessor,correlationData);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, CorrelationData correlationData, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(o,correlationData,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, CorrelationData correlationData, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,o,correlationData,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, CorrelationData correlationData, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,s1,o,correlationData,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(o,messagePostProcessor,correlationData,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,o,messagePostProcessor,correlationData,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, MessagePostProcessor messagePostProcessor, CorrelationData correlationData, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,s1,o,messagePostProcessor,correlationData,parameterizedTypeReference);
    }

    @Override
    public void send(Message message) throws AmqpException {
        rabbitTemplate.send(message);
    }

    @Override
    public void send(String s, Message message) throws AmqpException {
        rabbitTemplate.send(s,message);
    }

    @Override
    public void send(String s, String s1, Message message) throws AmqpException {
        rabbitTemplate.send(s,s1,message);
    }

    @Override
    public void convertAndSend(Object o) throws AmqpException {
        rabbitTemplate.convertAndSend(o);
    }

    @Override
    public void convertAndSend(String s, Object o) throws AmqpException {
        rabbitTemplate.convertAndSend(s,o);
    }

    @Override
    public void convertAndSend(String s, String s1, Object o) throws AmqpException {
        rabbitTemplate.convertAndSend(s,s1,o);
    }

    @Override
    public void convertAndSend(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        rabbitTemplate.convertAndSend(o,messagePostProcessor);
    }

    @Override
    public void convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        rabbitTemplate.convertAndSend(s,o,messagePostProcessor);
    }

    @Override
    public void convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        rabbitTemplate.convertAndSend(s,s1,o,messagePostProcessor);
    }

    @Override
    public Message receive() throws AmqpException {
        return rabbitTemplate.receive();
    }

    @Override
    public Message receive(String s) throws AmqpException {
        return rabbitTemplate.receive(s);
    }

    @Override
    public Message receive(long l) throws AmqpException {
        return rabbitTemplate.receive(l);
    }

    @Override
    public Message receive(String s, long l) throws AmqpException {
        return rabbitTemplate.receive(s,l);
    }

    @Override
    public Object receiveAndConvert() throws AmqpException {
        return rabbitTemplate.receiveAndConvert();
    }

    @Override
    public Object receiveAndConvert(String s) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(s);
    }

    @Override
    public Object receiveAndConvert(long l) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(l);
    }

    @Override
    public Object receiveAndConvert(String s, long l) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(s,l);
    }

    @Override
    public <T> T receiveAndConvert(ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(parameterizedTypeReference);
    }

    @Override
    public <T> T receiveAndConvert(String s, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(s,parameterizedTypeReference);
    }

    @Override
    public <T> T receiveAndConvert(long l, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(l,parameterizedTypeReference);
    }

    @Override
    public <T> T receiveAndConvert(String s, long l, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.receiveAndConvert(s,l,parameterizedTypeReference);
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return rabbitTemplate.receiveAndReply(receiveAndReplyCallback);
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        return rabbitTemplate.receiveAndReply(s,receiveAndReplyCallback);
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s, String s1) throws AmqpException {
        return rabbitTemplate.receiveAndReply(receiveAndReplyCallback,s,s1);
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s1, String s2) throws AmqpException {
        return rabbitTemplate.receiveAndReply(s,receiveAndReplyCallback);
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return rabbitTemplate.receiveAndReply(receiveAndReplyCallback,replyToAddressCallback);
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        return rabbitTemplate.receiveAndReply(s,receiveAndReplyCallback,replyToAddressCallback);
    }

    @Override
    public Message sendAndReceive(Message message) throws AmqpException {
        return rabbitTemplate.sendAndReceive(message);
    }

    @Override
    public Message sendAndReceive(String s, Message message) throws AmqpException {
        return rabbitTemplate.sendAndReceive(s,message);
    }

    @Override
    public Message sendAndReceive(String s, String s1, Message message) throws AmqpException {
        return rabbitTemplate.sendAndReceive(s,s1,message);
    }

    @Override
    public Object convertSendAndReceive(Object o) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(o);
    }

    @Override
    public Object convertSendAndReceive(String s, Object o) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,o);
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,s1,o);
    }

    @Override
    public Object convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(o,messagePostProcessor);
    }

    @Override
    public Object convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,o,messagePostProcessor);
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        return rabbitTemplate.convertSendAndReceive(s,s1,o,messagePostProcessor);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(o,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,o,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,s1,o,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(o,messagePostProcessor,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,o,messagePostProcessor,parameterizedTypeReference);
    }

    @Override
    public <T> T convertSendAndReceiveAsType(String s, String s1, Object o, MessagePostProcessor messagePostProcessor, ParameterizedTypeReference<T> parameterizedTypeReference) throws AmqpException {
        return rabbitTemplate.convertSendAndReceiveAsType(s,s1,o,messagePostProcessor,parameterizedTypeReference);
    }


    /*    @Override
    public <T> T execute(ChannelCallback<T> channelCallback) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.execute(ChannelCallback<T> channelCallback)");
        T t = null;
        try {
            t = rabbitTemplate.execute(channelCallback);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.execute(ChannelCallback<T> channelCallback).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return t;
    }

    @Override
    public void send(Message message) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.send(Message message)");
        try {
            rabbitTemplate.send(message);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.send(Message message).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }

    }

    @Override
    public void send(String s, Message message) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.send(String s, Message message)");
        try {
            rabbitTemplate.send(s, message);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.send(String s, Message message).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }

    }

    @Override
    public void send(String s, String s1, Message message) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.send(String s, String s1, Message message)");
        try {
            rabbitTemplate.send(s, s1, message);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.send(String s, String s1, Message message).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public void convertAndSend(Object o) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertAndSend(Object o)");
        try {
            rabbitTemplate.convertAndSend(o);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertAndSend(Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public void convertAndSend(String s, Object o) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertAndSend(String s, Object o)");
        try {
            rabbitTemplate.convertAndSend(s, o);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertAndSend(String s, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public void convertAndSend(String s, String s1, Object o) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertAndSend(String s, String s1, Object o)");
        try {
            rabbitTemplate.convertAndSend(s, s1, o);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertAndSend(String s, String s1, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public void convertAndSend(Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertAndSend(Object o, MessagePostProcessor messagePostProcessor)");
        try {
            rabbitTemplate.convertAndSend(o, messagePostProcessor);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertAndSend(Object o, MessagePostProcessor messagePostProcessor).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public void convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor)");
        try {
            rabbitTemplate.convertAndSend(s, o, messagePostProcessor);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertAndSend(String s, Object o, MessagePostProcessor messagePostProcessor).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public void convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor)");
        try {
            rabbitTemplate.convertAndSend(s, s1, o);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertAndSend(String s, String s1, Object o, MessagePostProcessor messagePostProcessor).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
    }

    @Override
    public Message receive() throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receive()");
        Message message = null;
        try {
            message = rabbitTemplate.receive();
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receive().count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return message;
    }

    @Override
    public Message receive(String s) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receive(String s)");
        Message message = null;
        try {
            message = rabbitTemplate.receive(s);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receive(String s).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return message;
    }

    @Override
    public Message receive(long l) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receive(long l)");
        Message message = null;
        try {
            message = rabbitTemplate.receive(l);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receive(long l).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return message;
    }

    @Override
    public Message receive(String s, long l) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receive(String s, long l)");
        Message message = rabbitTemplate.receive(s, l);
        // 埋点统计数量
        Cat.logMetricForCount("RabbitmqProducerProxy.receive(String s, long l).count");
        return message;
    }

    @Override
    public Object receiveAndConvert() throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndConvert()");
        Object obj = null;
        try {
            obj = rabbitTemplate.receiveAndConvert();
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndConvert().count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object receiveAndConvert(String s) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndConvert(String s)");
        Object obj = null;
        try {
            obj = rabbitTemplate.receiveAndConvert(s);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndConvert(String s).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object receiveAndConvert(long l) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndConvert(long l)");
        Object obj = null;
        try {
            obj = rabbitTemplate.receiveAndConvert(l);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndConvert(long l).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object receiveAndConvert(String s, long l) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndConvert(String s, long l)");
        Object obj = null;
        try {
            obj = rabbitTemplate.receiveAndConvert(s, l);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndConvert(String s, long l).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback)");
        Boolean b = null;
        try {
            b = rabbitTemplate.receiveAndReply(receiveAndReplyCallback);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return b;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback)");
        Boolean b = null;
        try {
            b = rabbitTemplate.receiveAndReply(s, receiveAndReplyCallback);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return b;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s, String s1) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s, String s1)");
        Boolean b = null;
        try {
            b = rabbitTemplate.receiveAndReply(receiveAndReplyCallback, s, s1);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s, String s1).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return b;
    }


    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s1, String s2) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.send(String s, Message message)");
        Boolean b = null;
        try {
            b = rabbitTemplate.receiveAndReply(receiveAndReplyCallback, s1, s2);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, String s1, String s2).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return b;
    }

    @Override
    public <R, S> boolean receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.send(String s, Message message)");
        Boolean b = null;
        try {
            b = rabbitTemplate.receiveAndReply(receiveAndReplyCallback, replyToAddressCallback);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndReply(ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return b;
    }

    @Override
    public <R, S> boolean receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.send(String s, Message message)");
        Boolean b = null;
        try {
            b = rabbitTemplate.receiveAndReply(s, receiveAndReplyCallback, replyToAddressCallback);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.receiveAndReply(String s, ReceiveAndReplyCallback<R, S> receiveAndReplyCallback, ReplyToAddressCallback<S> replyToAddressCallback).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return b;
    }

    @Override
    public Message sendAndReceive(Message message) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.sendAndReceive(Message message)");
        Message _message = null;
        try {
            _message = rabbitTemplate.sendAndReceive(message);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return _message;
    }

    @Override
    public Message sendAndReceive(String s, Message message) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.sendAndReceive(String s, Message message)");
        Message _message = null;
        try {
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            _message = rabbitTemplate.sendAndReceive(s, message);

            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return _message;

    }

    @Override
    public Message sendAndReceive(String s, String s1, Message message) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.sendAndReceive(String s, String s1, Message message)");
        Message _message = null;
        try {
            _message = rabbitTemplate.sendAndReceive(s, s1, message);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }

        return _message;
    }

    @Override
    public Object convertSendAndReceive(Object o) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertSendAndReceive(Object o)");
        Object obj = null;
        try {
            obj = rabbitTemplate.convertSendAndReceive(o);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object convertSendAndReceive(String s, Object o) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertSendAndReceive(String s, Object o)");
        Object obj = null;
        try {
            obj = rabbitTemplate.convertSendAndReceive(s, o);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o)");
        Object obj = rabbitTemplate.convertSendAndReceive(s, s1, o);
        // 埋点统计数量
        Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o).count");
        return obj;
    }

    @Override
    public Object convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor) throws
            AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor)");
        Object obj = null;
        try {
            obj = rabbitTemplate.convertSendAndReceive(o, messagePostProcessor);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(Object o, MessagePostProcessor messagePostProcessor).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor) throws
            AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor)");
        Object obj = null;
        try {
            obj = rabbitTemplate.convertSendAndReceive(s, o, messagePostProcessor);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, Object o, MessagePostProcessor messagePostProcessor).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }

    @Override
    public Object convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor
            messagePostProcessor) throws AmqpException {
        Transaction trans = Cat.newTransaction("Service", "RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor messagePostProcessor)");
        Object obj = null;
        try {
            obj = rabbitTemplate.convertSendAndReceive(s, s1, o, messagePostProcessor);
            // 埋点统计数量
            Cat.logMetricForCount("RabbitmqProducerProxy.convertSendAndReceive(String s, String s1, Object o, MessagePostProcessor messagePostProcessor).count");
            trans.setStatus("0");
        } catch (AmqpException ae) {
            trans.setStatus(ae);
            throw ae;
        } finally {
            trans.complete();
        }
        return obj;
    }*/
}
