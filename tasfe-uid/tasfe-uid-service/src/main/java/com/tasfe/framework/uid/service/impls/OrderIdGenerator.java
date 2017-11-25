package com.tasfe.framework.uid.service.impls;

import com.tasfe.framework.uid.service.algorithm.JedisIncr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

/**
 * 描述:   订单id
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-24 16:44
 */
@Service
public class OrderIdGenerator {

    private Logger logger = LoggerFactory.getLogger(OrderIdGenerator.class);

   /* @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisIncr jedisIncr;*/
    /**
     * 连接池对象
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * @param date
     * @return
     * @Description
     */
    private String getUserPrefix(Date date) {
        long prefix = date.getTime();
        return prefix + "";
    }


    /**
     * 产品编号(2) + 时间(13）+ 流水(3)
     *
     * @return
     * @Description 支持每秒999个订单号的生成
     */
    public Long incrId() throws Exception {
        String orderId = null;
        Jedis jedis = null;
        // 00001String key = "nyd:user:id:".concat(prefix);
        // 13位
        Long time = new Date().getTime();
        String key = "nyd:order:id:".concat(time + "");
        try {
            //Long index = redisTemplate.boundValueOps(key).increment(1);
            //Long index = jedisIncr.incr(key);

            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置1099后超时key
            jedis.expire(key, 999 + 100);

            // 补位操作 保证满足3位
            orderId = "10".concat(time.toString()).concat(String.format("%1$03d", index));
        } catch (Exception ex) {
            logger.error("分布式订单号生成失败异常: " + ex.getMessage());
            throw new Exception(ex);
        } finally {
            // 返还到连接池
            if (null != jedis) {
                jedis.close();
            }
        }
        if (StringUtils.isEmpty(orderId)) return null;
        return Long.parseLong(orderId);
    }


    public static void main(String[] args) {
        System.out.println("10".concat(String.valueOf(new Date().getTime())).concat(String.format("%1$03d", 111)).length());
    }


}