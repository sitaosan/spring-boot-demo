package com.yxc.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "redisson")
@PropertySource("classpath:config/config.properties")
@Component
@Slf4j
@Data
public class RedissonProperties {

    private Integer timeout = 3000;

    private String address;

    private String password;

    private Integer connectionPoolSize = 64;

    private Integer connectionMinimumIdleSize = 10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentinelAddresses;

    private String masterName;

    private Integer pingTimeout = 30000;

    private Integer idleConnectionTimeout = 10000;

    private Integer connectTimeout = 30000;

    private Integer reconnectionTimeout = 30000;
}
