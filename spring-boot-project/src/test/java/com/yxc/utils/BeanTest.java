package com.yxc.utils;


import com.yxc.common.utils.SpringBeanUtil;
import com.yxc.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class BeanTest {
    @Test
    public void getBeanTest(){
        User user = (User) SpringBeanUtil.getBean("myUser");
        log.info("===user====:{}",user.toString());
    }
}
