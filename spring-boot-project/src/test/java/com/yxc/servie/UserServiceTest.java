package com.yxc.servie;

import com.yxc.common.vo.UserVo;
import com.yxc.pojo.User;
import com.yxc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private UserVo userVo;
    @Before
    public void init(){
        userVo = new UserVo();
        userVo.setPageNum(1);
        userVo.setPageSize(100);
    }
    @Test
    public void getUsersTest() {
        List<User> list = userService.getUsers(userVo);
        for (int i = 0; i < list.size(); i++) {
            log.info("第{}个user==:{}",(i+1),list.get(i).toJson());
        }

    }
}
