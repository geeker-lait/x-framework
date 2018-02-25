package com.tasfe.framework.uid.service;

import com.tasfe.framework.uid.service.impls.BillIdGenerator;
import com.tasfe.framework.uid.service.impls.MemberIdGenerator;
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

    @Autowired
    private BillIdGenerator billIdGenerator;

    @Autowired
    private MemberIdGenerator memberIdGenerator;


    /**
     * @return
     * @Description 生成分布式ID
     */
    @Override
    public Long generatorId(BizCode biz) throws Exception {

        switch (biz) {
            // 用户id
            case USER:
            case USER_YMT:
                return userIdGenerator.incrId(biz);
            //账单id
            case BILL_NYD:
                return billIdGenerator.incrId(biz);
            // 会员id
            case MEMBER:
            case RECOMMEND_YMT:
                return memberIdGenerator.incrId(biz);

            case ORDER_NYD:
            case ORDER_YMT:
                return orderIdGenerator.incrId(biz);
        }
        return null;
        // 转成数字类型，可排序
        //return incrOrderId(biz, getOrderPrefix(new Date()));
    }

}