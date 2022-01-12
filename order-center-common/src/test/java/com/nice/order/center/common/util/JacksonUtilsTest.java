package com.nice.order.center.common.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Fill in desc
 *
 * @author haihuang95@zto.com
 * @version 0.1.0
 * @date 2021/11/11 16:22
 * @since 0.1.0
 */
public class JacksonUtilsTest {


    @Test
    public void test1() {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("CHARGE", 0);
        hashMap.put("ONLINE_PAYMENT", 0);
        hashMap.put("BALANCE_PAYMENT", 0);
        hashMap.put("REFUND", 5);
        System.out.println(JacksonUtils.objectToJsonCamel(hashMap));
        HashMap map = JacksonUtils.jsonToObject("{\"CHARGE\":0,\"ONLINE_PAYMENT\":0,\"BALANCE_PAYMENT\":0,\"REFUND\":5}", HashMap.class);
        System.out.println(map);
        System.out.println(map.get("CHARGE"));
        System.out.println(map.get("REFUND"));
        System.out.println(map.get("unknown"));
    }


}
