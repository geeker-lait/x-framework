package com.tasfe.framework.uid.service.impls;

import com.tasfe.framework.uid.service.BizCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

/**
 * 描述:   用户id生产
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-24 16:06
 */
@Service
public class UserIdGenerator extends AbstractIdGenerator{
    private Logger logger = LoggerFactory.getLogger(UserIdGenerator.class);

    /**
     * 连接池对象
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * @param biz
     * @return 11 17 354 23 00002
     * @Description 支持一个小时100w个订单号的生成
     */
    public Long incrId(BizCode biz) throws Exception {

        String  prefix = getYDHPrefix(new Date());

        String id = null;
        Jedis jedis = null;
        // 00001
        String key = biz.getKey().concat(prefix);
        try {
            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置一小时后超时，清楚key
            jedis.expire(key,60*60*60+1000);

            // 补位操作 保证满足5位
            id = prefix.concat(String.format("%1$05d", index));
            if(biz.getPrefix() != null ){
                id = biz.getPrefix().toString().concat(id);
            }
        } catch (Exception ex) {
            logger.error("分布式用户ID生成失败异常: " + ex.getMessage());
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


    /*
    private Long incrUserId(BizCode biz,String prefix) throws Exception {
        String id = null;
        Jedis jedis = null;
                // 00001
        String key = biz.toString().concat(prefix);
        try {
            //Long index = redisTemplate.boundValueOps(key).increment(1);
            //Long index = jedisIncr.incr(key);
            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置一小时后超时，清楚key
            jedis.expire(key,60*60*60+1000);

            // 补位操作 保证满足5位
            id = prefix.concat(String.format("%1$05d", index));
            if(biz.getPrefix() != null ){
                id = biz.getPrefix().toString().concat(id);
            }
        } catch (Exception ex) {
            logger.error("分布式用户ID生成失败异常: " + ex.getMessage());
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
    }*/


}