package com.yxc.common.handler;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class PageReult<P> implements Serializable {
    /**
     * 默认分页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 默认页码
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 分页使用的参数，分页大小
     */
    private int pageSize;

    /**
     * 分页使用的参数，当前分页号
     */
    private int pageNum;

    /**
     * 分页使用的参数，总数据条数
     */
    private int totalCount;

    /**
     * 分页使用的参数，总页数
     */
    private int pageCount;
    /**
     * 查询结果数据
     */
    private List<P> datas = new ArrayList<>();

    public PageReult(int pageSize, int pageNum, int totalCount, List<P> datas) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalCount = totalCount;
        this.datas = datas;
        if (this.pageSize == 0) {
            pageCount = 0;
        } else if (this.totalCount % this.pageSize == 0) {
            pageCount = this.totalCount / this.pageSize;
        } else {
            pageCount = totalCount / this.pageSize + 1;
        }
    }

    @Override
    public String toString() {
        return "PageReult{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                ", pageCount=" + pageCount +
                ", datas=" + datas +
                '}';
    }
    public String toJson(){
        return JSON.toJSONString(this);
    }
}
