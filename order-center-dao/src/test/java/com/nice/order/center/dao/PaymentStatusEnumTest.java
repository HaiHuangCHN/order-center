package com.nice.order.center.dao;

import com.nice.order.center.dao.entity.PaymentStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/6/24 00:15
 */
@Slf4j
class PaymentStatusEnumTest {

    /**
     * 无关紧要的验证性测试
     */
    @Test
    void test1() {
        System.out.println(PaymentStatusEnum.PAY_STATUS_MAPPING);
        System.out.println(PaymentStatusEnum.PAY_STATUS_MAPPING.get(null));
        System.out.println(PaymentStatusEnum.PAY_STATUS_MAPPING.get(1));
        System.out.println(PaymentStatusEnum.PAY_STATUS_MAPPING.get(2));
        Integer a = null;
        System.out.println(Arrays.asList(1, 2, 3, 4).get(a));
    }

}
