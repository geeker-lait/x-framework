package com.tasfe.framework.uid.service.impls;

import com.tasfe.framework.uid.service.IdGenerator;
import com.tasfe.framework.uid.service.algorithm.IdWorker;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-20 17:03
 */
public class DefaultIdGenerator implements IdGenerator /*IdentifierGenerator*/ {

    private static final IdWorker idWorker = new IdWorker();

    public Serializable generate(/*SharedSessionContractImplementor sharedSessionContractImplementor, Object o*/) {
       return null;
    }

    @Override
    public Long generatorId(String biz) {
        return idWorker.getId();
    }
}