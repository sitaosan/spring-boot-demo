package com.yxc.common.mq.receiver;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.yxc.common.utils.VerfyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 监听消息也是接收者
 *
 * **/
@Component
@Slf4j
public class UserMsgListener {

    @RabbitListener(queues = {"${config.queueUserInsert}"})
    @RabbitHandler
    private void insert(Message message, Channel channel) {
        log.info("=======>【user入库操作 MQ】,收到MQ , msg:{}", JSON.toJSONString(message));
        if(VerfyUtil.isNull(message)){
            log.error("=======>【user入库操作 MQ】，消息为null！！");
            return;
        }

        String jsonStr = new String(message.getBody());
        log.info("=======>【user入库操作 MQ】, jsonStr:{}",jsonStr);
    }
}
