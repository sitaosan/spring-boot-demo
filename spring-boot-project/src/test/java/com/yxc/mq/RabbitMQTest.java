package com.yxc.mq;

import com.alibaba.fastjson.JSON;
import com.yxc.common.mq.send.RabbitMqUserSender;
import com.yxc.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class RabbitMQTest {

    @Autowired
    RabbitMqUserSender rabbitMqUserSender;

    @Test
    public void mqTest(){
        User user  = new User();
        user.setUserId(111L);
        user.setUserName("MQ测试");
        user.setUserAddress("北京市朝阳区");
        rabbitMqUserSender.sendInst(JSON.toJSONString(user));
    }
}
