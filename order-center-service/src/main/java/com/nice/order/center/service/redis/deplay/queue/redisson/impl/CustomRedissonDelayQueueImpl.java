//package com.nice.order.center.service.redis.deplay.queue.redisson.impl;
//
//import com.nice.order.center.service.redis.deplay.queue.redisson.DelayQueue;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RDelayedQueue;
//import org.redisson.api.RFuture;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author haihuang95@zto.com
// * @date 2023/1/9 00:06
// */
//@Service
//@Slf4j
//public class CustomRedissonDelayQueueImpl implements CustomRedissonDelayQueue {
//
//    private final RDelayedQueue<Object> rDelayedQueue;
//
//    public CustomRedissonDelayQueueImpl(RDelayedQueue<Object> rDelayedQueue) {
//        this.rDelayedQueue = rDelayedQueue;
//    }
//
//    @Override
//    public Boolean offer(Object object) {
//        return rDelayedQueue.offer(object);
//    }
//
//    @Override
//    public Boolean offerAsync(Object object) throws ExecutionException, InterruptedException {
//        boolean flag;
//        RFuture<Boolean> rFuture = rDelayedQueue.offerAsync(object);
//        try {
//            flag = rFuture.get();
//        } catch (InterruptedException | ExecutionException e) {
//            log.info("offerAsync exception", e);
//            throw e;
//        }
//        return flag;
//    }
//
//    @Override
//    public void offer(Object object, Long time, TimeUnit timeUnit) {
//        rDelayedQueue.offer(object, time, timeUnit);
//    }
//
//    @Override
//    public void offerAsync(Object object, Long time, TimeUnit timeUnit) {
//        rDelayedQueue.offerAsync(object, time, timeUnit);
//    }
//
//}
//
