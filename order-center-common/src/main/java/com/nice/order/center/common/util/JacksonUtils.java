package com.nice.order.center.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.Objects;

/**
 * JSON实用类（选型Jackson）
 */
public class JacksonUtils {


    /**
     * 默认objectMapper实例
     */
    public static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

    private static ObjectMapper objectMapperCamel;

    private JacksonUtils() {}

    private static void initObjectMapperCamel() {
        objectMapperCamel = new ObjectMapper();
        objectMapperCamel.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapperCamel.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Object转（驼峰）JSON
     *
     * @param <T>    the type parameter
     * @param object the object
     * @return string string
     * @return: return a string, if the object is null return ""
     */
    public static <T> String objectToJsonCamel(T object) {
        if (null == object)
            return null;
        if (null == objectMapperCamel) {
            initObjectMapperCamel();
        }
        try {
            return objectMapperCamel.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        if (null == objectMapperCamel) {
            initObjectMapperCamel();
        }
        try {
            return (T) objectMapperCamel.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T jsonToObjectWithCustomObjectMapper(String json, Class<T> clazz, ObjectMapper ob) {
        if (json == null) {
            return null;
        }

        Objects.requireNonNull(ob);

        try {
            return (T) ob.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
