package com.nice.order.center.service.util;

/**
 * 第五种（静态内部类）
 *
 * @author haihuang95@zto.com
 * @date 2023/1/9 00:06
 */
public class Singleton {

    private Singleton() {}

    /**
     * 借用类加载机制延迟实例化
     */
    private static final class SingletonHolder {
        private static final Singleton SINGLETON;

        static {
            SINGLETON = new Singleton();
        }
    }

    /**
     * 获取实例
     */
    public static Singleton getInstance() {
        return SingletonHolder.SINGLETON;
    }

}