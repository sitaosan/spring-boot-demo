package com.yxc.common.lock;

/**
 * 分布式锁接口
 * **/
public interface DistributedLocker {
    /*
     * 上锁
     * @param lockKey
     * */
    void lock(String lockKey);
    /*
     * 解锁
     * @param lockKey
     * */
    void unlock(String lockKey);
    /*
     * 上锁
     * @param lockKey
     * @param timeout
     * */
    void lock(String lockKey, int timeout);
    /**
     * 尝试获取锁
     * @param lockKey
     * @param timeout
     * @param thread
     * @return
     */
    boolean tryLock(String lockKey, int timeout, Thread thread);

    /**
     * 判断是否已经被其他线程锁住
     * @param lockKey
     * @return
     */
    boolean isLocked(String lockKey);
}
