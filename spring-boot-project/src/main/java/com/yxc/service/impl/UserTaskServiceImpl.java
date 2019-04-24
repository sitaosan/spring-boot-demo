package com.yxc.service.impl;

import com.yxc.dao.UserTaskMapper;
import com.yxc.pojo.UserTask;
import com.yxc.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserTaskServiceImpl implements UserTaskService  {

    @Autowired
    UserTaskMapper userTaskMapper;

    @Async
    @Override
    public void addUserTask(UserTask userTask) {
        try {
            Thread.sleep(5000L);
            log.info("进入异步方法");
            long beginTime = System.currentTimeMillis();
            long endTime = System.currentTimeMillis();
            int result = userTaskMapper.insertUserTask(userTask);
            log.info("插入了{}条，执行时长：{}秒",result,(endTime-beginTime)/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
