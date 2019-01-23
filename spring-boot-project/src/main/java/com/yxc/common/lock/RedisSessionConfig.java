package com.yxc.common.lock;

import com.yxc.common.config.RedissonProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redission的配置信息的加载类
 * **/
@Configuration
@Slf4j
public class RedisSessionConfig {
    @Autowired
    private RedissonProperties redissonProperties;

    @Bean
    RedissonClient redissonSingle() {
        log.info("=====================redissonSingle==============================");
        log.info("=====================timeout:{}", redissonProperties.getTimeout());
        log.info("=====================pingTimeout:{}", redissonProperties.getPingTimeout());
        log.info("=====================connectionPoolSize:{}",  redissonProperties.getConnectionPoolSize());
        log.info("=====================idleConnectionTimeout:{}",  redissonProperties.getIdleConnectionTimeout());
        log.info("=====================connectTimeout:{}", redissonProperties.getConnectTimeout());
        log.info("=====================reconnectionTimeout:{}",  redissonProperties.getReconnectionTimeout());
        log.info("=====================connectionMinimumIdleSize:{}", redissonProperties.getConnectionMinimumIdleSize());

        String redisAddr = redissonProperties.getAddress();

        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(redisAddr)
                .setTimeout(redissonProperties.getTimeout())//默认3000ms
                .setPingTimeout(redissonProperties.getPingTimeout())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setIdleConnectionTimeout(redissonProperties.getIdleConnectionTimeout())//如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
                .setConnectTimeout(redissonProperties.getConnectTimeout())
                .setReconnectionTimeout(redissonProperties.getReconnectionTimeout())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize());

        if(StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }
}
