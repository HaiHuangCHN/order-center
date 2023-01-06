package com.nice.order.center.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 随机IP生成器
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/9/18 19:19
 */
public class RandomIPGeneratorUtilTest {

    @Test
    void testRandomIp() throws InterruptedException {
        while (true) {
            System.out.println(RandomIPGeneratorUtil.getRandomIp());
            Thread.sleep(1000L);
        }
    }

}
