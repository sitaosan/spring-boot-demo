package com.yxc.common.vo;

import lombok.Data;

@Data
public  class BaseVo {
    /**
     * 分页使用的参数，分页大小
     */
    private Integer pageSize;

    /**
     * 分页使用的参数，当前分页号
     */
    private Integer pageNum;

    @Override
    public String toString() {
        return "BaseVo{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
