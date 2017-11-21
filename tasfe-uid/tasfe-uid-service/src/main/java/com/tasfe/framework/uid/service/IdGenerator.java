package com.tasfe.framework.uid.service;

public interface IdGenerator /*implements IdentifierGenerator*/ {
    String TYPE = "IdGenerator";



    /**
     * @Description 生成分布式ID
     *
     * @author butterfly
     * @return
     */
    Long generatorId(String biz) throws Exception ;
}