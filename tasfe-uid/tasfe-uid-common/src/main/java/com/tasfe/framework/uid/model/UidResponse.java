package com.tasfe.framework.uid.model;

import java.io.Serializable;

/**
 * Created by lait.zhang on 2017/10/19.
 */
public class UidResponse implements Serializable{

    public String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
