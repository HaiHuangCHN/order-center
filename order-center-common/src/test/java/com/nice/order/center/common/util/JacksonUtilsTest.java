package com.nice.order.center.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JacksonUtils Test
 *
 * @author hai.huang.a@outlook.com
 * @date 2021/11/11 16:22
 */
@Slf4j
class JacksonUtilsTest {

    @Test
    void test1() {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("CHARGE", 0);
        hashMap.put("ONLINE_PAYMENT", 0);
        hashMap.put("BALANCE_PAYMENT", 0);
        hashMap.put("REFUND", 5);
        System.out.println(JacksonUtils.objectToJsonCamel(hashMap));
        HashMap<String, Integer> map = JacksonUtils.jsonToObject("{\"CHARGE\":0,\"ONLINE_PAYMENT\":0,\"BALANCE_PAYMENT\":0," +
                "\"REFUND\":5}", HashMap.class);
        System.out.println(map);
        System.out.println(map.get("CHARGE"));
        System.out.println(map.get("REFUND"));
        System.out.println(map.get("unknown"));
    }

    @Test
    public void test2() {
        try {
            Map<String, String> siteNameChannelCodeMapping = getSiteNameChannelCodeMapping();
            String semicolonStart = "'";
            String semicolonEnd = "', ";
            String comma = ", ";
            ObjectMapper om = new ObjectMapper();
            om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            Path path = Paths.get("/Users/haihuang/Desktop/sqlResult.json");
            List<String> allLines = Files.readAllLines(path);
            System.out.println(allLines);
            for (String line : allLines) {
                List<PickEnt> pickEntList = om.readValue(line, new TypeReference<List<PickEnt>>() {});
                System.out.println(JacksonUtils.objectToJsonCamel(pickEntList));
                for (PickEnt pickEnt : pickEntList) {
                    String head = "INSERT INTO `t_base_pickup_warehouse` (`id`,`pw_code`,`pw_name`,`pw_name_short`,`pw_type`," +
                            "`country`,`province`,`city`,`street_address`,`zip_code`,`link_name`,`phone`,`mobile`,`email`," +
                            "`remark`,`create_user_id`,`create_time`,`modify_user_id`,`modify_time`,`opening_remark`," +
                            "`is_active`,`max_pcs`,`limit_pcs`,`is_rental`,`rent_price`,`rent_cur`,`station_zm`,`hub_zm`," +
                            "`pw_mode`,`shipway_code`,`pw_coe_code`,`cur_postlist_num`,`bag_num`, `district`) VALUES (null, ";
                    StringBuilder sb = new StringBuilder(head);

                    sb.append(semicolonStart).append(pickEnt.getCode()).append(semicolonEnd);
                    sb.append(semicolonStart).append(pickEnt.getName()).append(semicolonEnd);
                    sb.append(semicolonStart).append(pickEnt.getName()).append(semicolonEnd);
                    sb.append(semicolonStart).append("COE").append(semicolonEnd);

                    sb.append(semicolonStart).append("中國").append(semicolonEnd);
                    sb.append(semicolonStart).append(pickEnt.getProvince()).append(semicolonEnd);
                    sb.append(semicolonStart).append(pickEnt.getCity()).append(semicolonEnd);
                    sb.append(semicolonStart).append(pickEnt.getAddr()).append(semicolonEnd);
                    sb.append(semicolonStart).append("").append(semicolonEnd);
                    if (pickEnt.getPrincipal() == null) {
                        sb.append(pickEnt.getPrincipal()).append(comma);
                    } else {
                        sb.append(semicolonStart).append(pickEnt.getPrincipal()).append(semicolonEnd);
                    }
                    if (pickEnt.getPriMob() == null) {
                        sb.append(pickEnt.getPriMob()).append(comma);
                    } else {
                        sb.append(semicolonStart).append(pickEnt.getPriMob()).append(semicolonEnd);
                    }
                    if (pickEnt.getPriMob() == null) {
                        sb.append(pickEnt.getPriMob()).append(comma);
                    } else {
                        sb.append(semicolonStart).append(pickEnt.getPriMob()).append(semicolonEnd);
                    }
                    sb.append(semicolonStart).append("").append(semicolonEnd);

                    sb.append(semicolonStart).append("").append(semicolonEnd);
                    sb.append(semicolonStart).append("2558").append(semicolonEnd);// 2558
                    sb.append("NOW(), ");
                    sb.append(semicolonStart).append("2558").append(semicolonEnd);// 2558
                    sb.append("NOW(), ");
                    sb.append(semicolonStart).append(pickEnt.getOperatingHours()).append(semicolonEnd);

                    sb.append(semicolonStart).append("1").append(semicolonEnd);
                    sb.append("null, ");
                    sb.append("null, ");
                    sb.append("null, ");
                    sb.append("null, ");
                    sb.append("null, ");
                    sb.append(semicolonStart).append(pickEnt.getCode()).append(semicolonEnd);
                    sb.append(semicolonStart).append(siteNameChannelCodeMapping.get(pickEnt.getCode())).append(semicolonEnd);

                    sb.append(semicolonStart).append("").append(semicolonEnd);
                    sb.append(semicolonStart).append(siteNameChannelCodeMapping.get(pickEnt.getCode())).append(semicolonEnd);
                    sb.append(semicolonStart).append(pickEnt.getCode()).append(semicolonEnd);
                    sb.append("null, ");
                    sb.append("null, ");
                    sb.append(semicolonStart).append(pickEnt.getDistrict()).append("');");
                    System.out.println(sb);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Map<String, String> getSiteNameChannelCodeMapping() {
        try {
            List<Sites> siteList = new ArrayList<>(1000);
            ObjectMapper om = new ObjectMapper();
            om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Path path = Paths.get("/Users/haihuang/Desktop/sqlResult2.json");
            List<String> allLines = Files.readAllLines(path);
            for (String line : allLines) {
                List<Sites> siteListMapped = om.readValue(line, new TypeReference<List<Sites>>() {});
                siteList.addAll(siteListMapped);
            }
            return siteList.stream().collect(Collectors.toMap(Sites::getSiteCode, Sites::getChannelCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
