package com.tasfe.framework.crud.api.params;

import com.tasfe.framework.crud.api.criteria.Limit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by Lait on 2017/7/7.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page<T> {
    /**
     * 当前页数
     */
    private Integer pageNo;
    /**
     * 每页展示条数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer pageCount;
    /**
     * 总数据条数
     */
    private Long totalSize;
    /**
     * 分页查询数据结果清单
     */
    private List<T> list;

    public static Limit resolve(int pageNo,int pageSize){
        int start = (pageNo-1)*pageSize;
        if(start < 0){
            start = 0;
        }
        //int offset = start + pageSize;
        Limit limit = new Limit();
        limit.setStart(start);
        limit.setOffset(pageSize);
        return limit;
    }
}
