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
 * 描述:   账单id
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-24 16:44
 */
@Service
public class BillIdGenerator {

    private Logger logger = LoggerFactory.getLogger(BillIdGenerator.class);

    /**
     * 连接池对象
     */
    @Autowired
    private JedisPool jedisPool;


    /**
     * 产品编号(2) + 时间(13）+ 流水(3)
     *
     * @return
     * @Description 支持每秒999个订单号的生成
     */
    public Long incrId(BizCode bizCode) throws Exception {
        String orderId = null;
        Jedis jedis = null;
        // 13位
        Long time = new Date().getTime();
        String key = bizCode.getKey().concat(time.toString());
        try {
            jedis = jedisPool.getResource();
            Long index = jedis.incr(key);
            // 设置1099后超时key
            jedis.expire(key, 999 + 100);
            // 补位操作 保证满足3位
            orderId = time.toString().concat(String.format("%1$03d", index));
            if(bizCode.getPrefix() != null){
                orderId = bizCode.getPrefix().toString().concat(orderId);
            }
        } catch (Exception ex) {
            logger.error("分布式账单号生成失败异常: " + ex.getMessage());
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