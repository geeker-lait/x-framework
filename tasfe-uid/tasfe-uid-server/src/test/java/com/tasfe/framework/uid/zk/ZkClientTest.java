package com.tasfe.framework.uid.zk;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lait.zhang on 2017/8/3.
 */
public class ZkClientTest {

    @Test
    public void testRegister() throws InterruptedException, IOException, KeeperException {
        ZkClient client = new ZkClient("127.0.0.1:2181");
        client.register("192.168.1.102");
    }

    @Test
    public void testIncrease() throws InterruptedException, IOException, KeeperException {
        ZkClient client = new ZkClient("127.0.0.1:2181");
        int increase = client.increase("testRegister");
        System.out.println(increase);
    }

    @Test
    public void testDecrease() throws InterruptedException, IOException, KeeperException {
        ZkClient client = new ZkClient("127.0.0.1:2181");
        int increase = client.decrease("testRegister");
        System.out.println(increase);
    }

}
