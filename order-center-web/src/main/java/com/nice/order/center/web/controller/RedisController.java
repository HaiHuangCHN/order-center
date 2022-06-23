package com.nice.order.center.web.controller;

import com.nice.order.center.common.util.LocalDateTimeUtil;
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
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO Fill in desc
 *
 * @author haihuang95@zto.com
 * @date 2022/3/24 17:36
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class RedisController {

    private final StringRedisTemplate stringRedisTemplate;

    DecimalFormat decimalFormat = new DecimalFormat("0000");

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
                // 配额码原子变量值增加，每次增加1
                for (int j = 0; j < 10; j++) {
                    long count = redisCount.incrementAndGet();
                    System.out.println(decimalFormat.format(String.valueOf(count)));
                }
            });
        }

        log.info(LocalDateTimeUtil.convertToDate(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0, 0, 0))).toString());
        redisCount.expireAt(LocalDateTimeUtil.convertToDate(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0, 0, 0, 0))));

        return redisCount;
    }

}