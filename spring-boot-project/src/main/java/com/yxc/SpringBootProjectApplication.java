package com.yxc;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yxc.dao")
@Slf4j
public class SpringBootProjectApplication {
    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        log.info("=========项目启动开始=========");
        SpringApplication.run(SpringBootProjectApplication.class, args);
        long endTime = System.currentTimeMillis();
        log.info("=========项目启动完毕共耗时："+((endTime-beginTime)/1000)+"秒");
    }

}

