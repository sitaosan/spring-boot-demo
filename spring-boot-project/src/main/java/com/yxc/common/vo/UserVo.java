package com.yxc.common.vo;

import lombok.Data;

@Data
public class UserVo extends BaseVo{
    private Long userId;
    private String userName;
    private String userAddress;

    @Override
    public String toString() {
        return "UserVo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
