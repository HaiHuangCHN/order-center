package com.nice.order.center.service.util;

/**
 * 第五种：静态内部类
 *
 * @author haihuang95@zto.com
 * @date 2023/1/9 00:06
 */
public final class Singleton5 {

    private Singleton5() {}

    /**
     * 借用类加载机制延迟实例化
     */
    private static final class SingletonHolder {
        private static final Singleton5 SINGLETON;

        static {
            SINGLETON = new Singleton5();
        }
    }

    /**
     * 获取实例
     */
    public static Singleton5 getInstance() {
        return SingletonHolder.SINGLETON;
    }

}