package com.tasfe.framework.uid.server;

/**
 * Created by lait.zhang on 2017/8/4.
 */
public class BootstrapTest {

    public static void main(String[] args) throws Exception {
        args = new String[]{"-zookeeper127.0.0.1:2181", "-redis127.0.0.1:6379"};
        UidBootstrap bootstrap = new UidBootstrap();
        bootstrap.main(args);
    }
}
