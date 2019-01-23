package com.yxc.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "config", ignoreUnknownFields = false)
@PropertySource("classpath:config/config.properties")
@Data
public class Config {
    private String  exchangeUserInsert;
    private String routingKeyUsrInsert;
    private String queueUserInsert;

}
