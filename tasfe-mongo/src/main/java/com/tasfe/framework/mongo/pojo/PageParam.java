package com.tasfe.framework.mongo.pojo;

/**
 * page的limit skip,limit类似mysql的2个相关分页参数
 */
public class PageParam {

    private int skip;
    private int limit;

    public PageParam(int skip, int limit) {
        this.skip = skip;
        this.limit = limit;
    }

    public int getSkip() {
        return skip;
    }

    public PageParam setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public PageParam setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + limit;
        result = prime * result + skip;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PageParam other = (PageParam) obj;
        if (limit != other.limit)
            return false;
        if (skip != other.skip)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PageParam [skip=" + skip + ", limit=" + limit + "]";
    }

}
