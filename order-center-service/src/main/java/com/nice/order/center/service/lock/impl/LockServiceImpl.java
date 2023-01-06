package com.nice.order.center.service.lock.impl;

import com.nice.order.center.common.exception.BusinessException;
import com.nice.order.center.common.exception.ErrorCode;
import com.nice.order.center.service.lock.LockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@Service
public class LockServiceImpl implements LockService {

    private final RedissonClient rClient;

    public LockServiceImpl(RedissonClient rClient) {
        this.rClient = rClient;
    }

    @Override
    public void run(String key, Runnable r,
                    long waitTime, long leaseTime, TimeUnit timeUnit) {
        RLock lock = rClient.getLock(key);
        try {
            if (!lock.tryLock(waitTime, leaseTime, timeUnit)) {
                log.error("锁获取超时,key[{}]", key);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR_CODE.CONCURRENT_ERROR);
            }
            r.run();
        } catch (InterruptedException e) {
            log.error("获取锁线程被中断,key:[{}]堆栈信息:{}{}", key, System.lineSeparator(), e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR_CODE.INTERNAL_INTERRUPTED);
        } finally {
            if (lock.isLocked()) {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    @Override
    public <T> T run(String key, Supplier<T> s,
                     long waitTime, long leaseTime, TimeUnit timeUnit) {
        RLock lock = rClient.getLock(key);
        try {
            if (!lock.tryLock(waitTime, leaseTime, timeUnit)) {
                log.error("锁获取超时,key[{}]", key);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR_CODE.CONCURRENT_ERROR);
            }
            return s.get();
        } catch (InterruptedException e) {
            log.error("获取锁线程被中断,key:[{" + key + "}]堆栈信息:{}{}", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR_CODE.INTERNAL_INTERRUPTED);
        } finally {
            if (lock.isLocked()) {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

}
