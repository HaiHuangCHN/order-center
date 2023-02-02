package com.nice.order.center.service.util;

/**
 * 第三种：静态内部类
 *
 * @author haihuang95@zto.com
 * @date 2023/1/9 00:06
 */
public final class Singleton3 {

    private static final Singleton3 INSTANCE = new Singleton3();

    private Singleton3() {}

    public static Singleton3 getInstance() {
        return INSTANCE;
    }

}