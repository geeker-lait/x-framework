package com.tasfe.framework.uid.service.impls;

import com.tasfe.framework.uid.service.algorithm.JedisIncr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Calendar;
import java.util.Date;

/**
 * 描述:   用户id生产
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-24 16:06
 */
@Service
public class UserIdGenerator {
    private Logger logger = LoggerFactory.getLogger(UserIdGenerator.class);

   /* @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JedisIncr jedisIncr;*/


    /**
     * 连接池对象
     */
    @Autowired
    private JedisPool jedisPool;

    public Long incrId() throws Exception {
        return incrUserId(getUserPrefix(new Date()));
    }
    /**
     * @param date
     * @return
     * @Description
     */
    private String getUserPrefix(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        // 今天是第多少天
        int day = c.get(Calendar.DAY_OF_YEAR);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        // 0补位操作 必须满足三位
        String dayFmt = String.format("%1$03d", day);
        // 0补位操作 必须满足2位
        String hourFmt = String.format("%1$02d", hour);
        StringBuffer prefix = new StringBuffer();
        prefix.append((year - 2000)).append(dayFmt).append(hourFmt);
        return prefix.toString();
    }

    /**
     * @param prefix
     * @return
     * @Description 支持一个小时100w个订单号的生成
     */
    private Long incrUserId(String prefix) throws Exception {
        String id = null;
        Jedis jedis = null;
                // 00001
        String key = "nyd:user:id:".concat(prefix);
        try {
            //Long index = redisTemplate.boundValueOps(key).increment(1);
            //Long index = jedisIncr.incr(key);
            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置一小时后超时，清楚key
            jedis.expire(key,60*60+100);

            // 补位操作 保证满足5位
            id = prefix.concat(String.format("%1$05d", index));
        } catch (Exception ex) {
            logger.error("分布式订单号生成失败异常: " + ex.getMessage());
            throw new Exception(ex);
        }finally {
            // 返还到连接池
            if (null != jedis) {
                jedis.close();
            }
        }
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return Long.parseLong(id);
    }


}