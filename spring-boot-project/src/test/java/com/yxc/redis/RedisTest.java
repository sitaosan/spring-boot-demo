package com.yxc.redis;

import com.yxc.common.redis.RedisConfig;
import com.yxc.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class RedisTest {
    @Autowired
    @Qualifier("myRedisTemplate")
    private  RedisTemplate<String,Object> redisTemplate;

    @Test
    public void redisTest(){
      log.info("============");
      User user = new User();
      user.setUserId(123l);
      user.setUserName("哈哈哈");
      user.setUserAddress("的撒大苏打");
      redisTemplate.opsForValue().set("user",user);
      log.info("======结束====取值=====:{}",redisTemplate.opsForValue().get("user"));
      log.info("======mytest====取值=====:{}",redisTemplate.opsForValue().get("mytest"));
      redisTemplate.delete("mytest");
    }


}
