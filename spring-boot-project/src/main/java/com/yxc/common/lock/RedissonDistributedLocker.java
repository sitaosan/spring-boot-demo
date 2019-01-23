package com.yxc.common.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedissonDistributedLocker implements DistributedLocker {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void lock(String lockKey) {
        RLock rLock =  redissonClient.getLock(lockKey);
        rLock.lock();
    }

    @Override
    public void unlock(String lockKey) {
        RLock rLock =  redissonClient.getLock(lockKey);
        rLock.unlock();
    }

    @Override
    public void lock(String lockKey, int timeout) {
        RLock rLock =  redissonClient.getLock(lockKey);
        rLock.lock(timeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryLock(String lockKey, int timeout, Thread thread) {
        log.info("============> redisson分布式锁,【尝试获取锁】，lockName=" + lockKey + ", 预设锁自动过期时间：" + timeout + "秒, " + "线程名称：" + thread.getName());
        RLock rLock = redissonClient.getLock(lockKey);
        boolean flag = false;
        //5分钟获取不到锁则直接失败
        try{
            if (rLock.tryLock(1000 * 5, timeout, TimeUnit.SECONDS)) {
                log.info("============> redisson分布式锁,【获取锁成功】，lockName=" + lockKey + ", 预设锁自动过期时间：" + timeout + "秒, " + "线程名称：" + thread.getName());
                return true;
            }else{
                log.info("============> redisson分布式锁,【获取锁失败】，lockName=" + lockKey + ", 预设锁自动过期时间：" + timeout + "秒, " + "线程名称：" + thread.getName());
                return false;
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            log.info("################> tryLock异常： " + e);
            return false;
        }
    }

    @Override
    public boolean isLocked(String lockKey) {
        log.info("============> redisson分布式锁,【判断redisson锁是否被其他线程占用】，lockName=" + lockKey);
        RLock rLock = redissonClient.getLock(lockKey);
        return rLock.isLocked();
    }
}
