package com.nice.order.center.service.redis.deplay.queue.redisson;

import com.nice.order.center.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author haihuang95@zto.com
 * @date 2023/1/9 00:06
 */
@Component
@Slf4j
public class RedissonTask {

    private final RedissonClient redissonClient;

    private volatile RBlockingQueue<Object> rBlockingQueue;

    public RedissonTask(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @PostConstruct
    public void take() {
        initQueue();
        new Thread(() -> {
            while (true) {
                try {
                    log.info("===============rBlockingQueue.take()===============" + rBlockingQueue.take());
                } catch (InterruptedException e) {
                    log.error("rBlockingQueue.take() exception", e);
                }
            }
        }).start();
    }

    private void initQueue() {
        if (rBlockingQueue == null) {
            synchronized (RedissonTask.class) {
                if (rBlockingQueue == null) {
                    rBlockingQueue = redissonClient.getBlockingQueue(Constants.CUSTOM_QUEUE_NAME);
                }
            }
        }
    }

//    第五种（静态内部类）
//    /**
//     * 借用类加载机制延迟实例化
//     */
//    private static final class InstanceHandler {
//        private static final RBlockingQueue<Object> R_BLOCKING_QUEUE;
//
//        static {
//            R_BLOCKING_QUEUE = redissonClient.getBlockingQueue(QUEUE_NAME);
//        }
//    }
//
//    /**
//     * 获取实例
//     */
//    public static RBlockingQueue<Object> getInstance() {
//        return InstanceHandler.R_BLOCKING_QUEUE;
//    }

}

