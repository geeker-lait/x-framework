package com.tasfe.framework.uid.service.impls;

import com.tasfe.framework.uid.service.IdGenerator;
import com.tasfe.framework.uid.service.algorithm.JedisIncr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-20 17:02
 */
@Service
public class JedisIdGenerator implements IdGenerator {


    private Logger logger = LoggerFactory.getLogger(JedisIdGenerator.class);
    /**
     * JedisClient对象
     */
    @Autowired
    private JedisIncr jedisIncr;


    /**
     * @Description
     *
     * @author butterfly
     * @param date
     * @return
     */
    private String getOrderPrefix(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        // 今天是第多少天
        int day = c.get(Calendar.DAY_OF_YEAR);
        int hour =  c.get(Calendar.HOUR_OF_DAY);
        // 0补位操作 必须满足三位
        String dayFmt = String.format("%1$03d", day);
        // 0补位操作 必须满足2位
        String hourFmt = String.format("%1$02d", hour);
        StringBuffer prefix = new StringBuffer();
        prefix.append((year - 2000)).append(dayFmt).append(hourFmt);
        return prefix.toString();
    }


    /**
     * @Description 支持一个小时100w个订单号的生成
     *
     * @author butterfly
     * @param prefix
     * @return
     */
    private Long incrOrderId(String biz, String prefix) throws Exception {
        String orderId = null;
        // 00001
        String key = "nyd:#{biz}:id:".replace("#{biz}", biz).concat(prefix);
        try {
            //Long index = jedisIncr.boundValueOps(key).increment(Long.valueOf(prefix));
            Long index = jedisIncr.incr(key);
            // 补位操作 保证满足5位
            orderId = prefix.concat(String.format("%1$05d", index));
        } catch(Exception ex) {
            logger.error("分布式订单号生成失败异常: "+ ex.getMessage());
            throw new Exception(ex);
        }
        if (StringUtils.isEmpty(orderId)) return null;
        return Long.parseLong(orderId);
    }

    /**
     * @Description 生成分布式ID
     *
     * @author butterfly
     * @return
     */
    @Override
    public Long generatorId(String biz) throws Exception {
        // 转成数字类型，可排序
        return incrOrderId(biz, getOrderPrefix(new Date()));
    }

}