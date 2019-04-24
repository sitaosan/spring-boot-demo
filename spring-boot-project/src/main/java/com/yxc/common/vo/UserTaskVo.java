package com.yxc.common.vo;

import lombok.Data;

@Data
public class UserTaskVo extends BaseVo {
    private Long userId;
    private Long id;
    private String content;

    @Override
    public String toString() {
        return "UserTaskVo{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
