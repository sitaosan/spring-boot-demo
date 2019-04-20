package com.yxc;

import com.yxc.common.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.yxc.*"}) // 扫描该包路径下的所有spring组件
@SpringBootApplication
@MapperScan("com.yxc.dao")
@Slf4j
public class SpringBootProjectApplication {
    @Bean
    public SpringBeanUtil getSpringBeanUtil(){
        return new SpringBeanUtil();
    }
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        log.info("=========项目启动开始=========");
        SpringApplication.run(SpringBootProjectApplication.class, args);
        long endTime = System.currentTimeMillis();
        log.info("=========项目启动完毕共耗时:{}",((endTime-beginTime)/1000)+"秒");
    }
}

