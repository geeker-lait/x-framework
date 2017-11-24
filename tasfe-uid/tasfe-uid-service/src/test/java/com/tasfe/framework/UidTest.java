package com.tasfe.framework;

import com.tasfe.framework.uid.service.BizCode;
import com.tasfe.framework.uid.service.IdGenerator;
import com.tasfe.framework.uid.service.algorithm.JedisIncr;
import com.tasfe.framework.uid.service.JedisIdFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-20 17:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:tasfe-framework-redis-configure.xml"})
public class UidTest {

   /* @Autowired
    private JedisIdFactory jedisIdGenerator;*/


    @Autowired
    private JedisIncr jedisIncr;

    @Autowired
    private JedisPool jedisPool;


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private IdGenerator idGenerator;


    @Test
    public void incrOrderId() throws Exception {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {

                    Long id = null;
                    while (true) {
                        try {
                            id = idGenerator.generatorId(BizCode.ORDER_NYD);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(id);
                    }
                }
            });
        }
        System.in.read();
    }


    @Test
    public void testIncrOrderId() throws Exception {
        Jedis jedis = jedisPool.getResource();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.execute(new Runnable() {

                public void run() {
                    while(true){
                        try {
                            String id = jedis.lpop("lait2");
                            //Long id = idGenerator.generatorId(BizCode.ORDER_NYD);
                            System.out.println(id);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }
        System.in.read();
        /*for(int i=0;i<10000;i++) {
            String  id= jedis.lpop("lait1");
            //Long id = idGenerator.generatorId(BizCode.ORDER_NYD);
            System.out.println(id);
        }*/
    }


    @Test
    public void incrUserId() throws Exception {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {


            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Long id = idGenerator.generatorId(BizCode.USER);
                            System.out.println(id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            /*Long id = redisTemplate.boundValueOps("ddd").increment(1);
            System.out.println(id);*/
        }
        System.in.read();


    }


    /**
     * 自增
     *
     * @param key
     * @param liveTime
     * @return
     */
    public Long incr(String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        //this.redisTemplate.boundValueOps(key).increment(step);
        //初始设置过期时间
        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
        }
        return increment;
    }


    public void inc() {
        Long id = redisTemplate.boundValueOps("ddd").increment(1);
        System.out.println(id);
    }


    @Test
    public void dddd() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {

            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    inc();
                }
            });
            /*Long id = redisTemplate.boundValueOps("ddd").increment(1);
            System.out.println(id);*/
        }
    }


    @Test
    public void ddd() throws Exception {
        final RedisAtomicLong entityIdCounter = new RedisAtomicLong("key", redisTemplate.getConnectionFactory());
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10000);
        String key = "lait";
        long liveTime = new Date().getTime();

        for (int i = 0; i < 10; i++) {

            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    while (true) {

                        Long increment = entityIdCounter.getAndIncrement();
                        System.out.println(increment);
                        //this.redisTemplate.boundValueOps(key).increment(step);
                        //初始设置过期时间
                        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
                            entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
                        }

                    }
                }
            });

           /* Long increment = entityIdCounter.getAndIncrement();
            //this.redisTemplate.boundValueOps(key).increment(step);
            //初始设置过期时间
            if ((null == increment || increment.longValue() == 0) && liveTime > 0) {
                entityIdCounter.expire(liveTime, TimeUnit.SECONDS);
            }
            System.out.println(increment);*/
        }


        /*for(int i=0;i<100;i++){
            Long d = jedisIncr.incr("dd");
            System.out.println(d);
        }*/
        System.in.read();

    }

    @Test
    public void testGeneratorId() throws Exception {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3000; i++) {
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    while (true)
                        try {
                            System.out.println(idGenerator.generatorId(BizCode.USER));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });
        }
        System.in.read();
    }
}