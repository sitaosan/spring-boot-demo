package com.yxc.common.config;

import com.yxc.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean(name="myUser")
    public User getUser(){
        User user  = new User();
        user.setUserId(1111L);
        user.setUserName("大三大四");
        user.setUserAddress("股份公司");
        return user;
    }
}
