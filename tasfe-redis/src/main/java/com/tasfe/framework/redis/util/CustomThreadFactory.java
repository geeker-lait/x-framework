package com.tasfe.framework.redis.util;

import java.util.concurrent.ThreadFactory;

/**
 * @author lait.zhang@gmail.com
 * @Description: TODO
 * @date 2017年10月16日  下午4:08:08
 */
public class CustomThreadFactory implements ThreadFactory {

    private int counter = 0;
    private String prefix = "default";

    public CustomThreadFactory() {
    }

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }


    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, prefix + "-" + counter++);
    }
}
