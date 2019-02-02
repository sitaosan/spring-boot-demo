package com.yxc.redis;

import com.yxc.common.redis.RedisUtil;
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
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisTest() {
        log.info("============");
        User user = new User();
        user.setUserId(121l);
        user.setUserName("哈哈哈");
        user.setUserAddress("的撒大苏打");
        redisUtil.set("user", user);
        log.info("======结束====取值=====:{}", redisUtil.get("user"));
        log.info("======mytest====取值=====:{}", redisUtil.get("mytest"));
        redisUtil.remove("mytest");
    }


}
