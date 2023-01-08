package com.nice.order.center.service.redis.deplay.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * Redis 延时队列
 *
 * @author hai.huang.a@outlook.com
 * @date 2023/1/8 13:17
 */
@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // Get the expired key
        String expiredKey = message.toString();
        log.info("Start handling expiredKey={}", expiredKey);
        try {
            // do something with the expiredKey
        } catch (Exception exception) {
            log.error("Exception occurs when handle expiredKey=" + expiredKey, exception);
        }
        log.info("End handling expiredKey={}", expiredKey);
    }

}

