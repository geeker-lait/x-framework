package com.tasfe.framework.uid.service;

import com.tasfe.framework.uid.service.algorithm.JedisIncr;
import com.tasfe.framework.uid.service.impls.OrderIdGenerator;
import com.tasfe.framework.uid.service.impls.UserIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-20 17:02
 */
@Service
public class JedisIdFactory implements IdGenerator {

    private Logger logger = LoggerFactory.getLogger(JedisIdFactory.class);

    @Autowired
    private UserIdGenerator userIdGenerator;
    @Autowired
    private OrderIdGenerator orderIdGenerator;

    /**
     * JedisClient对象
     */
    @Autowired
    private JedisIncr jedisIncr;

    /**
     * @return
     * @Description 生成分布式ID
     * @author butterfly
     */
    @Override
    public Long generatorId(BizCode biz) throws Exception {

        switch (biz) {
            // 用户id
            case USER:
                return userIdGenerator.incrId();
            //账单id
            case BILL:
                break;
            // 借款单id
            case LOAN:
                return null;
            case ORDER_NYD:
                return orderIdGenerator.incrId();
        }
        return null;
        // 转成数字类型，可排序
        //return incrOrderId(biz, getOrderPrefix(new Date()));
    }

}