package com.yxc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***
 *
 * @EurekaServer注解
 * 访问地址：http://localhost:8080/
 * 会报错：com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
 * 因为它既是一个server也是一个client，为了不报错，配置一下注册地址，让它往自己身上注册
 * */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {

        SpringApplication.run(EurekaApplication.class, args);
    }

}
