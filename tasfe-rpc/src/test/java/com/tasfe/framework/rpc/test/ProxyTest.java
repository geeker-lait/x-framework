package com.tasfe.framework.rpc.test;

import com.tasfe.framework.rpc.client.DefaultRpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;

public class ProxyTest {


    @Test
    public void testProxy(){
        HelloService helloService = DefaultRpcClient.create(HelloService.class);
        helloService.say("lait");
    }

}
