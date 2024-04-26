package com.nice.order.center.common.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO Fill in desc
 *
 * @author hai.huang.a@outlook.com
 * @date 2024/2/4 16:14
 */
public class TempTest {


    @Test
    public void testComparable() {
        long currentTimeMillis = System.currentTimeMillis();
        List<Sites> sitesList = new ArrayList<>();
        Sites site1 = new Sites();
        site1.setSiteCode("1");
        site1.setSiteName("TestA");
        site1.setDate(new Date(currentTimeMillis - 1000));
        sitesList.add(site1);
        Sites site2 = new Sites();
        site2.setSiteCode("2");
        site2.setSiteName("AestB");
        site2.setDate(new Date(currentTimeMillis - 100000));
        sitesList.add(site2);
        Sites site3 = new Sites();
        site3.setSiteCode("3");
        site3.setSiteName("TestA");
        site3.setDate(new Date(currentTimeMillis - 10000));
        sitesList.add(site3);

        sitesList = sitesList.stream().sorted().collect(Collectors.toList());

        System.out.println(sitesList);
    }

    @Test
    public void testInconsistentInstance() {
        Sites site1 = new Sites();
        site1.setSiteCode("1");
        site1.setSiteName("TestA");
        site1.setDate(new Date());
        InconsistentInstance inconsistentInstance = new InconsistentInstance();
        inconsistentInstance.setFieldOne("I am A");
        site1.setInconsistentInstance(inconsistentInstance);

        inconsistentInstance.setFieldOne("A becomes B");

        System.out.println(JacksonUtils.objectToJsonCamel(site1));
    }


}