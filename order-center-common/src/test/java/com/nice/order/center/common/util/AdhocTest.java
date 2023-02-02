package com.nice.order.center.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO Fill in desc
 *
 * @author hai.huang.a@outlook.com
 * @date 2022/12/9 10:14
 */
@Slf4j
class AdhocTest {

    @Test
    void testEncryptStr() {
        String reqJson = "{\"senderAddress\":\"浙江省嘉兴市海宁市之江路118号\",\"adresseeRawPhone\":\"0746-3431348\"," +
                "\"commodityWeight\":20,\"isPackage\":1,\"adresseeAddress\":\"广东省佛山市南海区桂城佛平三路紫金城A幢三楼A3030室\"," +
                "\"senderRawPhone\":\"00000000\",\"commodiy\":\"黄瓜\",\"packageRequire\":\"箱\"," +
                "\"senderPhone\":\"00000000\",\"createBy\":1769,\"adresseePerson\":\"张泽平\",\"bigAmount\":2000," +
                "\"createdSiteId\":2172,\"senderPerson\":\"姚枫\",\"adresseePhone\":\"0746-3431348\"," +
                "\"serviceType\":111,\"shippingMethod\":\"外贸仓\",\"dataSource \":1313," +
                "\"substituteNo\":\"77130123456789\"}";
        String dataDigest = MD5Util.md5(reqJson, "ztky_468b076f-bb45-4957-acfa-13592ff0d702", "utf-8", true);
        Map<String, String> paramM = new HashMap<>();
        paramM.put("msg_type", "ADDSUBSTIUTEORDER");
        paramM.put("cpcode", "571008000016");
        paramM.put("param", reqJson);
        paramM.put("data_digest", dataDigest);

        System.out.println(dataDigest);
    }

    @Test
    void testStream() {
        Map<String, String> mapA = new HashMap<>();
        mapA.put("orderStatus", "SUBMIT");
        mapA.put("amount", "1");

        Map<String, String> mapB = new HashMap<>();
        mapB.put("orderStatus", "DRAFT");
        mapB.put("amount", "2");

        System.out.println(JacksonUtils.objectToJsonCamel(mapA));

        List<Map<String, String>> a = Stream.of(mapA).collect(Collectors.toList());

        System.out.println(JacksonUtils.objectToJsonCamel(a));

        List<Map<String, String>> b = Stream.of(mapA, mapB).collect(Collectors.toList());

        System.out.println(JacksonUtils.objectToJsonCamel(b));
    }

}
