package com.tasfe.framework.uid.service.algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-20 17:13
 */
@Service
public class JedisIncr {

    /**
     * 连接池对象
     */
    @Autowired
    private JedisPool jedisPool;



   /* @PostConstruct
    public void init(){
        Jedis jedis = jedisPool.getResource();
        String[] v = new String[100];
        for(int i =0;i<v.length; i++){
            v[i] = "lait_"+i;
        }
        jedis.rpush("lait2", v);
    }*/

    /**
     * @Description
     *
     * @param key
     * @param value
     * @throws Exception
     */
    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception ex) {
            throw ex;
        } finally {
            // 返还到连接池
            if (null != jedis)
                jedis.close();
        }
    }

    /**
     * @Description 自增生成ID
     *
     * @param key
     * @throws Exception
     */
    public Long incr(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            Long index = jedis.incr(key);
            // 设置1秒后超时
            //jedis.expire(key,1);
            return index;
        } catch (Exception ex) {
            throw ex;
        } finally {
            // 返还到连接池
            if (null != jedis)
                jedis.close();
        }
    }


    /**
     * @Description
     *
     * @param key
     * @return
     * @throws Exception
     */
    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception ex) {
            throw ex;
        } finally {
            // 返还到连接池
            if (null != jedis)
                jedis.close();
        }
    }




}