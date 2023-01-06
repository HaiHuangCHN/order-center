package com.nice.order.center.common.util;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务执行分配器
 * <p>
 * 设计目标为执行少量按需分配执行的小任务
 * 且该任务可在极端情况下不予执行，直接丢弃
 * <p>
 * 任务队列无界，使用时请注意任务堆积
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/1/19 21:51
 */
@Slf4j
public final class Allocator {

    /**
     * task consumer
     */
    private final ScheduledThreadPoolExecutor allocator;

    /**
     * shared singleton
     */
    private static final class InstanceHandler {
        private static final Allocator INSTANCE;

        static {
            INSTANCE = new Allocator("allocator", Runtime.getRuntime().availableProcessors());
        }
    }

    public static Allocator defaultInstance() {
        return InstanceHandler.INSTANCE;
    }

    public Allocator(String threadName) {
        (allocator = new ScheduledThreadPoolExecutor(
                1, new DaemonThreadFactory(threadName)))
                .setRemoveOnCancelPolicy(true);
    }

    public Allocator(String threadName, int corePoolSize) {
        Preconditions.checkArgument(corePoolSize > 0,
                "corePoolSize must be greater than 0...");

        (allocator = new ScheduledThreadPoolExecutor(
                corePoolSize, new DaemonThreadFactory(threadName)))
                .setRemoveOnCancelPolicy(true);
    }

    /**
     * 延迟执行
     *
     * @param command 待执行逻辑体
     * @param delay   延迟时间数值
     * @param unit    延迟时间单位
     * @author 韩广阔
     */
    public void delay(Runnable command, long delay, TimeUnit unit) {
        allocator.schedule(() -> {
            try {
                command.run();
            } catch (Throwable t) {
                log.error("something error happened, stack track:", t);
            }
        }, delay, unit);
    }

    /**
     * 固定间隔执行
     *
     * @param command      待执行逻辑体
     * @param initialDelay 初始延迟执行数值
     * @param delay        间隔执行时间数值
     * @param unit         时间单位
     * @author 韩广阔
     */
    public void fixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        allocator.scheduleWithFixedDelay(() -> {
            try {
                command.run();
            } catch (Throwable t) {
                log.error("something error happened, stack track:", t);
            }
        }, initialDelay, delay, unit);
    }

    /**
     * 固定时间可抢占执行
     *
     * @param command      待执行逻辑体
     * @param initialDelay 初始延迟执行数值
     * @param period       间隔执行时间数值
     * @param unit         时间单位
     * @author 韩广阔
     */
    public void fixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        allocator.scheduleAtFixedRate(() -> {
            try {
                command.run();
            } catch (Throwable t) {
                log.error("something error happened, stack track:", t);
            }
        }, initialDelay, period, unit);
    }

    /**
     * the factory produces daemon threads
     */
    private static final class DaemonThreadFactory implements ThreadFactory {

        private final String threadName;

        private final AtomicInteger counter = new AtomicInteger();

        private DaemonThreadFactory(String threadName) {
            Preconditions.checkArgument(StringUtils.isNotBlank(threadName),
                    "threadName must not be blank...");
            this.threadName = threadName;
        }

        @Override
        public Thread newThread(@Nonnull Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            String threadName = String.format("%s-%02d", this.threadName, counter.getAndIncrement());
            thread.setName(threadName);
            return thread;
        }
    }

}
