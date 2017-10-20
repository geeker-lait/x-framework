package com.tasfe.framework.rpc.client;

import com.tasfe.framework.rpc.client.proxy.ObjectProxy;

import java.lang.reflect.Proxy;

public class DefaultRpcClient implements RpcClient {


    /**
     * 创建代理对象
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> T create(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new ObjectProxy<T>(interfaceClass));
    }


    @Override
    public RpcClient setAppKeyParamName(String paramName) {
        return null;
    }

    @Override
    public RpcClient setSessionIdParamName(String paramName) {
        return null;
    }

    @Override
    public RpcClient setMethodParamName(String paramName) {
        return null;
    }

    @Override
    public RpcClient setVersionParamName(String paramName) {
        return null;
    }

    @Override
    public RpcClient setFormatParamName(String paramName) {
        return null;
    }

    @Override
    public RpcClient setLocaleParamName(String paramName) {
        return null;
    }

    @Override
    public RpcClient setSignParamName(String paramName) {
        return null;
    }

    @Override
    public void setSessionId(String sessionId) {

    }
}
