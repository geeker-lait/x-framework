package com.tasfe.framework.rpc.communicate;

import lombok.Data;

@Data
public class RpcResponse {
    private String requestId;
    private String error;
    private Object result;
}
