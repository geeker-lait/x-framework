package com.tasfe.framework.rabbitmq.test;

import com.tasfe.framework.rabbitmq.RabbitmqProducerProxy;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by Lait on 10/17/2016.
 */
public class RabbitmqDemoProdcucer {
    public static void main(String args[]) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("mobanker-demo-rabbitmq-producer.xml");
        UserMessage userMessage = new UserMessage();
        RabbitmqProducerProxy rabbitmqProducerProxy = classPathXmlApplicationContext.getBean("rabbitmqProducerProxy",RabbitmqProducerProxy.class);

        //MessageProperties messageProperties = classPathXmlApplicationContext.getBean("messageProperties",MessageProperties.class);;
        //org.springframework.messaging.Message message = MessageBuilder.withPayload(userMessage).setHeader("oo","hh").build();
        for(int i=0;i<100;i++){
            //String s = "lait.zhang-" + i;
            //Message message = new Message(s.getBytes(),messageProperties);
            //rabbitmqProducerProxy.send(messages);
            //rabbitmqProducerProxy.send("sps.lait",message);
            // dfaS
            rabbitmqProducerProxy.convertAndSend("lait.test",new UserMessage(i,"lait_"+i));
        }
        //Message receiveMessage = rabbitmqProducerProxy.sendAndReceive(messages);
        //System.out.println("=================" + receiveMessage.getBody());
    }
}
