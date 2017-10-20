package com.tasfe.framework.rpc.test;

public class HelloServiceImpl implements HelloService {

    @Override
    public void say(String name) {
        System.out.println("print name = " + name);
    }
}
