package com.yxc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/***
 * 线程池配置
 * 线程池注解：@EnableAsync
 * @author yangx
 * @date 2019/04/24
 * */
@Configuration
@EnableAsync
@ComponentScan("com.yxc.service.*")
public class ThreadConfig {

    @Value("${corePoolSize}")
    private int corePoolSize;

    @Value("${maxPoolSize}")
    private int maxPoolSize;

    @Value("${queueCapacity}")
    private int queueCapacity;
    @Bean
    public Executor executor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
