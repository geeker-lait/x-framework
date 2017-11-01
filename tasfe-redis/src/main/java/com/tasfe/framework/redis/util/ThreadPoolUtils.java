package com.tasfe.framework.redis.util;

import java.util.concurrent.*;

/**
 * @author lait.zhang@gmail.com
 * @Description: TODO
 * @date 2017年10月16日  下午3:50:28
 */
public class ThreadPoolUtils {

    private ThreadFactory defaultFactory = new CustomThreadFactory();

    private ThreadPoolUtils() {
    }

    private static class ThreadPoolUtilsHolder {
        static ThreadPoolUtils INSTANCE = new ThreadPoolUtils();
    }

    public static ThreadPoolUtils getInstance() {
        return ThreadPoolUtilsHolder.INSTANCE;
    }

    public ExecutorService singleThreadPool() {
        return singleThreadPool(defaultFactory);
    }

    public ExecutorService singleThreadPool(ThreadFactory factory) {
        //return Executors.newSingleThreadExecutor();
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                factory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    public ExecutorService fixedThreadPool(int corePoolSize) {
        return fixedThreadPool(corePoolSize, defaultFactory);
    }

    public ExecutorService fixedThreadPool(int corePoolSize, ThreadFactory factory) {
        //return Executors.newFixedThreadPool(corePoolSize, Executors.defaultThreadFactory());
        return new ThreadPoolExecutor(corePoolSize, corePoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                factory);
    }

    public ExecutorService cachedThreadPool() {
        return cachedThreadPool(defaultFactory);
    }

    public ExecutorService cachedThreadPool(ThreadFactory factory) {
        //return Executors.newCachedThreadPool(Executors.defaultThreadFactory());
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                factory);
    }

    public ScheduledExecutorService scheduledThreadPool(int corePoolSize) {
        return scheduledThreadPool(corePoolSize, defaultFactory);
    }

    public ScheduledExecutorService scheduledThreadPool(int corePoolSize, ThreadFactory factory) {
        //return Executors.newScheduledThreadPool(corePoolSize, Executors.defaultThreadFactory());
        return new ScheduledThreadPoolExecutor(corePoolSize, factory);
    }

    public Thread newThread(Runnable r) {
        return this.defaultFactory.newThread(r);
    }

    public Thread newThread(Runnable r, String threadName) {
        return new CustomThreadFactory(threadName).newThread(r);
    }

    public static void shutdown(ExecutorService service) {
        try {
            if (null != service) {
                service.shutdown();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
