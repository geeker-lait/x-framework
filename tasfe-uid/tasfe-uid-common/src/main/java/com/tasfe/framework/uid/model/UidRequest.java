package com.tasfe.framework.uid.model;

import java.io.Serializable;

/**
 * Created by lait.zhang on 2017/8/3.
 */
public class UidRequest implements Serializable{

    /**
     * 请求类型：0-创建id
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UidRequest(int type) {
        this.type = type;
    }

    public UidRequest() {
    }


}
