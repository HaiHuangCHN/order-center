//package com.nice.order.center.service.redis.deplay.queue.redisson;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author haihuang95@zto.com
// * @date 2023/1/9 00:06
// */
//public interface CustomRedissonDelayQueue {
//
//    /**
//     * 发布（同步）
//     *
//     * @param object
//     * @return
//     */
//    Boolean offer(Object object);
//
//    /**
//     * 发布（异步）
//     *
//     * @param object
//     * @return
//     */
//    Boolean offerAsync(Object object) throws ExecutionException, InterruptedException;
//
//    /**
//     * 带延迟功能的队列（同步）
//     *
//     * @param object
//     * @param time
//     * @param timeUnit
//     */
//    void offer(Object object, Long time, TimeUnit timeUnit);
//
//    /**
//     * 带延迟功能的队列（异步）
//     *
//     * @param object
//     * @param time
//     * @param timeUnit
//     */
//    void offerAsync(Object object, Long time, TimeUnit timeUnit);
//
//}
//
