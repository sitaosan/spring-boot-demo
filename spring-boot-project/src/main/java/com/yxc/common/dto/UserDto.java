package com.yxc.common.dto;

import lombok.Data;

@Data
public class UserDto extends BaseDto{
    private Long userId;
    private String userName;
    private String userAddress;
    /**
     * 分页用，limitd后第一个参数
     */
    private Integer startIdx;

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", startIdx=" + startIdx +
                '}';
    }
}
