package com.tasfe.framework.crud.api.criteria;

import com.tasfe.framework.crud.api.enums.Operator;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lait on 2017/8/11.
 */
//@Data
public class Criteria {
    // entity 所对应的表
    private Class<?> clazz;
    // limit
    private Limit limit;
    // 是否根据主键更新
    private boolean updateById = true;
    // 函数表达式
    private String expression;
    // id 集合
    private List<Long> ids = new ArrayList<>();
    // where
    private Where where = new Where(this);
    // field字段
    private List<String> fields = new ArrayList<>();
    // order
    private List<String> order = new ArrayList<>();
    // group
    private List<String> group = new ArrayList<>();

    private Criteria() {

    }

    private <T> void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }


    public static <T> Criteria from(Class<T> clazz) {
        Criteria criteria = new Criteria();
        criteria.setClazz(clazz);
        return criteria;
    }


    public Criteria fields(String... fields) {
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }

    public Where where() {
        return where;
    }

    public Where groupWhere() {
        where = new Where(this);
        return where;
    }


    public Criteria group(Where where) {
        return this;
    }


    public Criteria orderBy(String... fileds) {
        this.order.addAll(Arrays.asList(fileds));
        return this;
    }


    public Criteria limit(int start, int offset) {
        this.limit = new Limit(start, offset);
        return this;
    }

    public Criteria limit(Limit limit) {
        this.limit = limit;
        return this;
    }

    /**
     * @return
     */
    public Criteria sum(String expression) {
        this.expression = expression;
        return this;
    }


    public static void main(String[] args) {
        Criteria criteria = Criteria.from(Object.class).where().and("key", Operator.EQ).endWhere().limit(1, 10);

    }


    public Criteria groupBy(String... fileds) {

        return this;
    }

    public Criteria list(Long... ids) {
        this.ids.addAll(Arrays.asList(ids));
        return this;
    }


    /**
     * 默认为true
     *
     * @return
     */
    public Criteria whioutId() {
        this.updateById = false;
        return this;
    }

    public boolean isUpdateById() {
        return this.updateById;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public List<String> getFields() {
        return fields;
    }

    public Where getWhere() {
        return where;
    }

    public List<String> getOrder() {
        return order;
    }

    public Limit getLimit() {
        return limit;
    }

    public String getExpression() {
        return expression;
    }
}
