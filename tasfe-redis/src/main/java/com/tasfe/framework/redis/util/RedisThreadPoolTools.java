package com.tasfe.framework.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * @author lait.zhang@gmail.com
 * @Description: TODO
 * @date 2017年4月5日  上午11:17:23
 */
public class RedisThreadPoolTools {

    private static final Logger logger = LoggerFactory.getLogger(RedisThreadPoolTools.class);

    private final ExecutorService service;
    private final ThreadFactory factory = new CustomThreadFactory("redis-cluster");

    private RedisThreadPoolTools() {
        this.service = ThreadPoolUtils.getInstance().fixedThreadPool(10, factory);
    }

    private RedisThreadPoolTools(int size) {
        this.service = ThreadPoolUtils.getInstance().fixedThreadPool(size, factory);
    }

    static class RedisThreadPoolHolder {
        static RedisThreadPoolTools INSTANCE = new RedisThreadPoolTools();
    }

    //并发发送线程池
    public static RedisThreadPoolTools getInstance() {
        return RedisThreadPoolHolder.INSTANCE;
    }

    public void execute(Runnable r) {
        try {
            this.service.execute(r);
        } catch (Exception ex) {
            logger.error("线程调度发生异常，异常信息如下：\n{}", ex);
        }
    }
}
