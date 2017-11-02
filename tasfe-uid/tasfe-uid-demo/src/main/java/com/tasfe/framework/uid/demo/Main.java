package com.tasfe.framework.uid.demo;

import com.tasfe.framework.uid.id.TasfeUid;

/**
 * Created by lait.zhang on 2017/8/4.
 */
public class Main {

    public static void main(String[] args) {
        final TasfeUid tasfeUid = new TasfeUid("127.0.0.1:2181", "127.0.0.1:6379");
        //final JedisUtil jedisUtil = JedisUtil.newInstance("192.168.56.102", 6379);
        for (int i = 0; i < 1500; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    long id = 0;
                    try {
                        id = tasfeUid.nextId();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("TasfeUid nextId : " + id + ",time:" + System.currentTimeMillis());
                    //jedisUtil.incr("count");
                }
            });
            thread.start();
        }
    }


}
