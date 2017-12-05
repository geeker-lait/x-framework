package com.tasfe.framework.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;

/**
 * Created by Lait on 10/17/2016.
 */
@Setter
public class RabbitmqConsumerProxy extends AbstractAdaptableMessageListener implements ChannelAwareMessageListener, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(RabbitmqConsumerProxy.class);

    private RabbitmqMessageProcesser rabbitmqMessageProcesser;

    private MessageConverter messageConverter;


    /*private int threadNum = 5;
    private int maxQueueSize = 2000;

    private ExecutorService executorService = new ThreadPoolExecutor(threadNum,
            threadNum, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(maxQueueSize),
            new ThreadPoolExecutor.CallerRunsPolicy());*/

    @Override
    public void onMessage(final Message message, final Channel channel) {
        // 获取对象
        Object msg = messageConverter.fromMessage(message);
        try {
            /**
             * 消费者在开启acknowledge的情况下，对接收到的消息可以根据业务的需要异步对消息进行确认。
             * 然而在实际使用过程中，由于消费者自身处理能力有限，从rabbitmq获取一定数量的消息后，希望rabbitmq不再将队列中的消息推送过来，
             * 当对消息处理完后（即对消息进行了ack，并且有能力处理更多的消息）再接收来自队列的消息。
             * 在这种场景下，我们可以通过设置basic.qos信令中的prefetch_count来达到这种效果。
             */
            //channel.basicQos(5);
            /**We're about to tell the server to deliver us the messages from the queue.
             * Since it will push us messages asynchronously,
             * we provide a callback in the form of an object that will buffer the messages
             * until we're ready to use them. That is what QueueingConsumer does.*/
            //QueueingConsumer consumer = new QueueingConsumer(channel);
            /**
             * 把名字为orderQueue的Channel的值回调给QueueingConsumer,即使一个worker在处理消息的过程中停止了，这个消息也不会失效
             */
            //channel.basicConsume(messages.getMessageProperties().getConsumerQueue(), false, consumer);
            //Cat.logMetricForCount("RabbitmqConsumerProxy.onMessage(final Message message, final Channel channel).count");
            //trans.setStatus("0");
            rabbitmqMessageProcesser.processMessage(msg);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception ae) {
            // trans.setStatus(ae);
            try {
                // 处理失败,调用nack，并将消息重新回到队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } finally {
            //trans.complete();
        }

        /*
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Transaction trans = Cat.newTransaction("Service", "RabbitmqConsumerProxy.onMessage(final Message message, final Channel channel)");
                // 强转对象
                T msg = messageConverter.fromMessage(message);
                try {
                    *//**
                     * 消费者在开启acknowledge的情况下，对接收到的消息可以根据业务的需要异步对消息进行确认。
                     * 然而在实际使用过程中，由于消费者自身处理能力有限，从rabbitmq获取一定数量的消息后，希望rabbitmq不再将队列中的消息推送过来，
                     * 当对消息处理完后（即对消息进行了ack，并且有能力处理更多的消息）再接收来自队列的消息。
                     * 在这种场景下，我们可以通过设置basic.qos信令中的prefetch_count来达到这种效果。
                     *//*
                    //channel.basicQos(5);
                    *//**We're about to tell the server to deliver us the messages from the queue.
                     * Since it will push us messages asynchronously,
                     * we provide a callback in the form of an object that will buffer the messages
                     * until we're ready to use them. That is what QueueingConsumer does.*//*
                    //QueueingConsumer consumer = new QueueingConsumer(channel);
                    *//**
                     * 把名字为orderQueue的Channel的值回调给QueueingConsumer,即使一个worker在处理消息的过程中停止了，这个消息也不会失效
                     *//*
                    //channel.basicConsume(messages.getMessageProperties().getConsumerQueue(), false, consumer);
                    //Cat.logMetricForCount("RabbitmqConsumerProxy.onMessage(final Message message, final Channel channel).count");
                    //trans.setStatus("0");
                    rabbitmqMessageProcesser.processMessage(msg);

                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                } catch (Exception ae) {
                    // trans.setStatus(ae);
                    // 处理失败,调用nack，并将消息重新回到队列
                    try {
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } finally {
                    //trans.complete();
                }
            }
        });*/

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

   /* @Override
    public void onMessage(final Message message) {
        logger.info("开始处理消息: " + message.toString());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                rabbitmqMessageProcesser.processMessage(message);
            }
        });
    }*/
/*
    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public RabbitmqMessageProcesser getRabbitmqMessageProcesser() {
        return rabbitmqMessageProcesser;
    }

    public void setRabbitmqMessageProcesser(RabbitmqMessageProcesser rabbitmqMessageProcesser) {
        this.rabbitmqMessageProcesser = rabbitmqMessageProcesser;
    }*/
}
