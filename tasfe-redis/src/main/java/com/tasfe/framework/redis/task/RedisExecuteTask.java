package com.tasfe.framework.redis.task;

import com.tasfe.framework.redis.RedisDao;
import com.tasfe.framework.redis.RedisExecutor;
import com.tasfe.framework.redis.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author lait.zhang@gmail.com
 * @Description: TODO
 * @date 2017年4月5日  下午4:04:55
 */
public class RedisExecuteTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RedisExecuteTask.class);

    private RedisDao redisDao;
    private RedisExecutor executor;
    private int retry;

    public RedisExecuteTask(RedisDao redisDao, RedisExecutor executor, int retry) {
        this.redisDao = redisDao;
        this.executor = executor;
        this.retry = retry;
    }

    /*
     * @Description: TODO
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        logger.debug("执行redis异步任务，参数为retry={} ......", this.retry);
        for (int i = 0; i < this.retry; i++) {
            try {
                this.executor.execute(this.redisDao);
                return;
            } catch (Exception ex) {
                logger.error("执行redis异步任务时出现异常，异常信息：\n{}", ex);
            }
            if ((i + 1) < this.retry) {
                ServiceUtil.waitFor(100, TimeUnit.MILLISECONDS);
            }
        }
        logger.error("执行redis异步任务时出现异常，连续尝试{}次未能成功 ......", this.retry);
    }
}
