package com.nice.order.center.service.redis.deplay.queue.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author haihuang95@zto.com
 * @date 2023/1/9 00:06
 */
@Component
@Slf4j
public class RedissonTask {

    private final RBlockingQueue<Object> customBlockingQueue;

    public RedissonTask(RBlockingQueue<Object> customBlockingQueue) {
        this.customBlockingQueue = customBlockingQueue;
    }

    @PostConstruct
    public void take() {
        new Thread(() -> {
            while (true) {
                try {
                    log.info("===============rBlockingQueue.take()===============" + customBlockingQueue.take());
                } catch (InterruptedException e) {
                    log.error("rBlockingQueue.take() exception", e);
                }
            }
        }).start();
    }

}

