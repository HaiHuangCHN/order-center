package com.nice.order.center.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * SnowFlakeShortUrlUtil Test
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/6/24 00:15
 */
@Slf4j
class SnowFlakeShortUrlUtilTest {

    @Test
    void testNextId() {
        SnowFlakeShortUrlUtil snowFlake = new SnowFlakeShortUrlUtil(10, 4);
        for (int i = 0; i < (1 << 4); i++) {
            // 10进制
            System.out.println(snowFlake.nextId());
        }
    }

    @Test
    void testMove() {
        System.out.println(-1L ^ (-1L << 5));
    }

}
