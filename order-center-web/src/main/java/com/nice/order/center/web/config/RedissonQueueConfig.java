//package com.nice.order.center.web.config;
//
//import org.redisson.api.RBlockingQueue;
//import org.redisson.api.RDelayedQueue;
//import org.redisson.api.RedissonClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Redisson 队列配置
// *
// * @author hai.huang.a@outlook.com
// * @date 2023/1/6 13:17
// */
//@Configuration
//public class RedissonQueueConfig {
//
//    private static final String QUEUE_NAME = "queue";
//
//    @Bean
//    public RBlockingQueue<String> rBlockingQueue(RedissonClient redissonClient) {
//        return redissonClient.getBlockingQueue(QUEUE_NAME);
//    }
//
//    @Bean
//    public RDelayedQueue<String> rDelayedQueue(RedissonClient redissonClient, RBlockingQueue<String> rBlockingQueue) {
//        return redissonClient.getDelayedQueue(rBlockingQueue);
//    }
//}
//
