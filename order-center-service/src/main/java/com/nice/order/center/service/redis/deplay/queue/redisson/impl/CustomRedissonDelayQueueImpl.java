package com.nice.order.center.service.redis.deplay.queue.redisson.impl;

import com.nice.order.center.service.redis.deplay.queue.redisson.CustomRedissonDelayQueue;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RFuture;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 定制化 RedissonDelayQueue 实现
 *
 * @author haihuang95@zto.com
 * @date 2023/1/9 00:06
 */
@Service
@Slf4j
public class CustomRedissonDelayQueueImpl implements CustomRedissonDelayQueue {

    private final RDelayedQueue<Object> customDelayedQueue;

    public CustomRedissonDelayQueueImpl(RDelayedQueue<Object> customDelayedQueue) {
        this.customDelayedQueue = customDelayedQueue;
    }

    @Override
    public Boolean offer(Object object) {
        return customDelayedQueue.offer(object);
    }

    @Override
    public Boolean offerAsync(Object object) throws ExecutionException, InterruptedException {
        boolean flag;
        RFuture<Boolean> rFuture = customDelayedQueue.offerAsync(object);
        try {
            flag = rFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            log.info("offerAsync exception", e);
            throw e;
        }
        return flag;
    }

    @Override
    public void offer(Object object, Long time, TimeUnit timeUnit) {
        customDelayedQueue.offer(object, time, timeUnit);
    }

    @Override
    public void offerAsync(Object object, Long time, TimeUnit timeUnit) {
        customDelayedQueue.offerAsync(object, time, timeUnit);
    }

}

