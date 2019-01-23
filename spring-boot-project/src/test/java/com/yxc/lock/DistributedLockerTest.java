package com.yxc.lock;

import com.yxc.common.lock.RedissonDistributedLocker;
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
public class DistributedLockerTest {
    @Autowired
    RedissonDistributedLocker lock;
    @Test
    public void LockTest(){
        for (int i = 0; i < 50; i++) {
            Runnable runnable = new Runnable(){

                @Override
                public void run() {
                    seckill();
                }
            };
            Thread thread=new Thread(runnable);
            thread.start();
        }
    }
    public void seckill() {
        int n = 500;
        // 返回锁的value值，供释放锁时候进行判断
        lock.lock("resource",20);
        log.info("获得了锁:{}",Thread.currentThread().getName());
        log.info("n做减法:{}",--n);
        lock.unlock("resource");
    }
}


