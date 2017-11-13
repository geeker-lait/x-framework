package com.tasfe.framework.uid.service.impls;

/*
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
*/

import java.io.Serializable;

public class IdGenerator /*implements IdentifierGenerator*/ {
    public static final String TYPE = "IdGenerator";

    private static final IdWorker idWorker = new IdWorker();

    public Serializable generate(/*SharedSessionContractImplementor sharedSessionContractImplementor, Object o*/) {
        return idWorker.getId();
    }
}