package com.nice.order.center.web.controller;

import com.nice.order.center.common.util.LocalDateTimeUtil;
import com.nice.order.center.service.redis.deplay.queue.redisson.CustomRedissonDelayQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Redis Controller
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/3/24 17:36
 * @deprecated 仅供测试使用
 */
@Slf4j
@RequiredArgsConstructor
@Controller
@Deprecated
public class RedisController {

    private final StringRedisTemplate stringRedisTemplate;

    private final CustomRedissonDelayQueue customRedissonDelayQueue;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0000");

    @GetMapping("/testRedisAtomicInteger")
    @ResponseBody
    public Object testRedisAtomicInteger() {

        String ticketName = "testRedisAtomicInteger";

        RedisAtomicLong redisCount = new RedisAtomicLong(ticketName,
                Objects.requireNonNull(stringRedisTemplate.getConnectionFactory()));

        // 用来设置初始值
        // redisCount.set(150);

        // 创建 100 个线程 并发执行  increment 操作
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> {
                // TODO 两个 for loop，为何得到递增的只是 100，而不是 100 * 10 = 1000？学一下 Future 的使用
                // 配额码原子变量值增加，每次增加1
                for (int j = 0; j < 10; j++) {
                    long count = redisCount.incrementAndGet();
                    log.info(DECIMAL_FORMAT.format(String.valueOf(count)));
                }
            });
        }

        Date expireAt = LocalDateTimeUtil.convertToDate(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0,
                0, 0, 0)));

        log.info("expireAt：{}", expireAt);

        redisCount.expireAt(expireAt);

        return redisCount;
    }

    @GetMapping("/testRedisKeyExpiration")
    @ResponseBody
    public Boolean testRedisExpiration() {
        log.info("Start setting key into Redis");
        customRedissonDelayQueue.offerAsync("Test Message", 10L, TimeUnit.SECONDS);
        log.info("End setting key into Redis");
//        stringRedisTemplate.opsForValue().set("testKey", "testValue");
//        stringRedisTemplate.opsForValue().set("testKey", "testValue", Duration.ofSeconds(3L));
        return true;
    }

}