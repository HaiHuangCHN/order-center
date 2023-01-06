package com.nice.order.center.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * Twitter 的 SnowFlake算法，使用SnowFlake算法生成一个整数
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/6/23 23:56
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SnowFlakeShortUrlUtil {

    /* 起始的时间戳 */
    private static final long START_TIMESTAMP = 1624698370256L;
    /* 起始的时间戳 */

    /* 每一部分占用的位数 */
    // 序列号占用的位数
    private static final long SEQUENCE_BIT = 12;
    // 机器标识占用的位数
    private static final long MACHINE_BIT = 5;
    // 数据中心占用的位数
    private static final long DATA_CENTER_BIT = 5;
    /* 每一部分占用的位数 */

    /* 每一部分的最大值 */
    // TODO 不是很懂啊
    //  (0)1 0000 0000 0000 = -4096 = -1L << 12
    // ^(0)1                = -1
    //  (1)0 1111 1111 1111 = 4095
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    // 11111 =31
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    // 11111 =31
    private static final long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    /* 每一部分的最大值 */

    /* 每一部分向左的位移 */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
    /* 每一部分向左的位移 */

    // dataCenterId + machineId = 10bit工作机器ID
    // 数据中心
    private long dataCenterId;
    // 机器标识
    private long machineId;
    // 序列号
    private volatile long sequence = 0L;
    // 上一次时间戳
    private volatile long lastTimeStamp = -1L;
    // 当前时间戳
    private volatile long currentTimeStamp = -1L;

    private long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastTimeStamp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    /**
     * 根据指定的数据中心ID和机器标志ID生成指定的序列号
     *
     * @param dataCenterId
     * @param machineId
     */
    public SnowFlakeShortUrlUtil(long dataCenterId, long machineId) {
        Assert.isTrue(dataCenterId >= 0 && dataCenterId <= MAX_DATA_CENTER_NUM, "dataCenterId is illegal!");
        Assert.isTrue(machineId >= 0 || machineId <= MAX_MACHINE_NUM, "machineId is illegal!");
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 生成下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        currentTimeStamp = System.currentTimeMillis();
        Assert.isTrue(currentTimeStamp >= lastTimeStamp, "Clock moved backwards");
        if (currentTimeStamp == lastTimeStamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大,获取下一个毫秒
            if (sequence == 0L) {
                currentTimeStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        lastTimeStamp = currentTimeStamp;
        return (currentTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT                    //数据中心部分
                | machineId << MACHINE_LEFT                           //机器标识部分
                | sequence;                                           //序列号部分
    }

}