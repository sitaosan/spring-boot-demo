package com.yxc.common.mq.send;

import com.yxc.common.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqUserSender {

    @Autowired
    Config config;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendInst(String msg) {
        log.info("=======> 【执行user入库操作】，发送MQ:{}",msg);
        log.info("=======> 【exchange】: ExchangeUserInsert()");
        log.info("=======> 【routingKey】: RoutingKeyUserInsert()");
        this.rabbitTemplate.convertAndSend(config.getExchangeUserInsert(),config.getRoutingKeyUsrInsert(), msg);
    }
}
