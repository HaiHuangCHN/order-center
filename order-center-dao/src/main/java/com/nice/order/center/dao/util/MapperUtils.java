package com.nice.order.center.dao.util;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.WeekendSqls;
import tk.mybatis.mapper.weekend.reflection.Reflections;

import java.util.function.Function;

/**
 * Mapper Util
 *
 * TODO Learn more
 */
public final class MapperUtils {


    private MapperUtils() {
        throw new IllegalStateException("utility class...");
    }

    /**
     * Create an Example condition
     *
     * @param type type
     * @param f    function
     * @return Example created
     */
    public static <T> Example buildExample(Class<T> type, Function<WeekendSqls<T>, WeekendSqls<T>> f) {
        WeekendSqls<T> custom = WeekendSqls.custom();
        return Example.builder(type).where(f.apply(custom)).build();
    }

    /**
     * Get field name via getter/isXxx()
     *
     * @return filed name string
     */
    public static <T, R> String getFieldName(Fn<T, R> fn) {
        return Reflections.fnToFieldName(fn);
    }


}
