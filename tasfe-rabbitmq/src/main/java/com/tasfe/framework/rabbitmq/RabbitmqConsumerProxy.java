package com.tasfe.framework.rabbitmq;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lait on 10/17/2016.
 */
public class RabbitmqConsumerProxy implements ChannelAwareMessageListener, InitializingBean {
    private Logger logger = LoggerFactory.getLogger(RabbitmqConsumerProxy.class);

    private RabbitmqMessageProcesser rabbitmqMessageProcesser;

    private int threadNum = 5;
    private int maxQueueSize = 2000;

    private ExecutorService executorService = new ThreadPoolExecutor(threadNum,
            threadNum, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(maxQueueSize),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void onMessage(final Message message, final Channel channel) throws Exception {
        logger.info("开始处理消息: " + message.toString());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Transaction trans = Cat.newTransaction("Service", "RabbitmqConsumerProxy.onMessage(final Message message, final Channel channel)");
                try {
                    rabbitmqMessageProcesser.processMessage(message, channel);
                    //Cat.logMetricForCount("RabbitmqConsumerProxy.onMessage(final Message message, final Channel channel).count");
                    //trans.setStatus("0");
                } catch (Exception ae) {
                   // trans.setStatus(ae);
                    throw ae;
                } finally {
                    //trans.complete();
                }
            }
        });

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

    public RabbitmqMessageProcesser getRabbitmqMessageProcesser() {
        return rabbitmqMessageProcesser;
    }

    public void setRabbitmqMessageProcesser(RabbitmqMessageProcesser rabbitmqMessageProcesser) {
        this.rabbitmqMessageProcesser = rabbitmqMessageProcesser;
    }
}
