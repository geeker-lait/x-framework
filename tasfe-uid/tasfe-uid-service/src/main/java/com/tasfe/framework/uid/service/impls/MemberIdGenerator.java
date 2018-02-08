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
 * 描述: 会员
 * 8 +
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2018-01-26 13:57
 */
@Service
public class MemberIdGenerator extends AbstractIdGenerator {
    private Logger logger = LoggerFactory.getLogger(MemberIdGenerator.class);

    //private String bizPrefix = "8";

    /**
     * 连接池对象
     */
    @Autowired
    private JedisPool jedisPool;

    /**
     * @param bizCode
     * @return
     * @Description 支持一个小时100w个会员的生成
     */
    public Long incrId(BizCode bizCode) throws Exception {

        String id = null;
        Jedis jedis = null;
        String prefix = getYDHPrefix(new Date());
        // 00001
        String key = bizCode.getKey().concat(prefix);
        try {
            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置一小时后超时，清楚key
            jedis.expire(key,60*60*60+1000);

            // 补位操作 保证满足5位
            id = prefix.concat(String.format("%1$05d", index));
            if(bizCode.getPrefix()!= null){
                id = bizCode.getPrefix().toString().concat(id);
            }
        } catch (Exception ex) {
            logger.error("分布式会员号生成失败异常: " + ex.getMessage());
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
        //return incrMemberId(bizPrefix + getYDHPrefix(new Date()));
    }



    /*private Long incrMemberId(String prefix) throws Exception {
        String id = null;
        Jedis jedis = null;
        // 00001
        String key = "nyd:member:id:".concat(prefix);
        try {
            //Long index = redisTemplate.boundValueOps(key).increment(1);
            //Long index = jedisIncr.incr(key);
            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置一小时后超时，清楚key
            jedis.expire(key,60*60*60+1000);

            // 补位操作 保证满足5位
            id = prefix.concat(String.format("%1$05d", index));
        } catch (Exception ex) {
            logger.error("分布式会员号生成失败异常: " + ex.getMessage());
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

    public static void main(String[] args) {
        MemberIdGenerator memberIdGenerator = new MemberIdGenerator();
        System.out.println("8" + memberIdGenerator.getYDHPrefix(new Date()));
    }
}