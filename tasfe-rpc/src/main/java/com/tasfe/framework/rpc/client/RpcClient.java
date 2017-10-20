package com.tasfe.framework.rpc.client;

/**
 *
 */
public interface RpcClient {



    /**
     * 设置method系统参数的参数名，下同
     *
     * @param paramName
     * @return
     */
    RpcClient setAppKeyParamName(String paramName);

    /**
     * 设置sessionId的参数名
     *
     * @param paramName
     * @return
     */
    RpcClient setSessionIdParamName(String paramName);

    /**
     * 设置method的参数名
     *
     * @param paramName
     * @return
     */
    RpcClient setMethodParamName(String paramName);

    /**
     * 设置version的参数名
     *
     * @param paramName
     * @return
     */
    RpcClient setVersionParamName(String paramName);

    /**
     * 设置format的参数名
     *
     * @param paramName
     * @return
     */
    RpcClient setFormatParamName(String paramName);

    /**
     * 设置locale的参数名
     *
     * @param paramName
     * @return
     */
    RpcClient setLocaleParamName(String paramName);

    /**
     * 设置sign的参数名
     *
     * @param paramName
     * @return
     */
    RpcClient setSignParamName(String paramName);

    /**
     * 设置sessionId
     *
     * @param sessionId
     */
    void setSessionId(String sessionId);

    /**
     * 创建一个新的服务请求
     * @return
     */
    //ClientRequest buildClientRequest();

    /**
     * 添加自定义的转换器
     *
     * @param RpcConverter
     */
    //void addRpcConvertor(RpcConverter rpcConverter);

}
