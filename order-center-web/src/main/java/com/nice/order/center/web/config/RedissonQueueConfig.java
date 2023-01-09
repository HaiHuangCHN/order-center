package com.nice.order.center.web.config;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Redisson 队列配置
 *
 * @author hai.huang.a@outlook.com
 * @date 2023/1/6 13:17
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class RedissonQueueConfig {

    private static final String CUSTOM_QUEUE_NAME = "custom_queue";

    @Bean
    public RBlockingQueue<String> customBlockingQueue(RedissonClient redissonClient) {
        return redissonClient.getBlockingQueue(CUSTOM_QUEUE_NAME);
    }

    @Bean
    public RDelayedQueue<String> customDelayedQueue(RedissonClient redissonClient, RBlockingQueue<String> customBlockingQueue) {
        return redissonClient.getDelayedQueue(customBlockingQueue);
    }

}

