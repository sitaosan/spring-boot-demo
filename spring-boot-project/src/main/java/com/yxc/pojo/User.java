package com.yxc.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long userId;
    private String userName;
    private String userAddress;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
    public String toJson() {
        return JSON.toJSONString(this);
    }

}
