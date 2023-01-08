//package com.nice.order.center.web.config;
//
//import org.redisson.api.RBlockingQueue;
//import org.redisson.api.RDelayedQueue;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// *
// * @author hai.huang.a@outlook.com
// * @date 2023/1/6 13:17
// */
//@Configuration
//public class RedissonQueueConfig {
//
//    private final String queueName = "queue";
//
//    @Bean
//    public RBlockingQueue<String> rBlockingQueue(@Qualifier("redissonSingle") RedissonClient redissonClient) {
//        return redissonClient.getBlockingQueue(queueName);
//    }
//
//    @Bean(name = "rDelayedQueue")
//    public RDelayedQueue<String> rDelayedQueue(@Qualifier("redissonSingle") RedissonClient redissonClient,
//                                               @Qualifier("rBlockingQueue") RBlockingQueue<String> blockQueue) {
//        return redissonClient.getDelayedQueue(blockQueue);
//    }
//}
//
