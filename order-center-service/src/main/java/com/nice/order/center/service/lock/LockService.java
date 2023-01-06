package com.nice.order.center.service.lock;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 带锁运行服务
 *
 * @author hai.huang.a@outlook.com
 * @date 2023/1/6 13:17
 */
public interface LockService {

    /**
     * 无返回值运行
     *
     * @param key       锁的key
     * @param r         无返回值lambda/实现类
     * @param waitTime  抢占等待时间
     * @param leaseTime 租用时间
     * @param timeUnit  时间单位
     * @author hai.huang.a@outlook.com
     * @date 2023/1/6 13:17
     */
    void run(String key, Runnable r,
             long waitTime, long leaseTime, TimeUnit timeUnit);

    /**
     * 有返回值运行
     *
     * @param key       锁的key
     * @param s         有返回值lambda/实现类
     * @param waitTime  抢占等待时间
     * @param leaseTime 租用时间
     * @param timeUnit  时间单位
     * @author hai.huang.a@outlook.com
     * @date 2023/1/6 13:17
     */
    <T> T run(String key, Supplier<T> s,
              long waitTime, long leaseTime, TimeUnit timeUnit);

}
