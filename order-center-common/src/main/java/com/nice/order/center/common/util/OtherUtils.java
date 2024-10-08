package com.nice.order.center.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class OtherUtils {


    public static Map<String, Object> convertObjToMap(Object obj) {
        Map<String, Object> objKeyValueMap = new HashMap<>();
        if (obj == null) {
            return Collections.emptyMap();
        }
        String[] fieldNames = OtherUtils.getFiledName(obj);
        for (int j = 0; j < fieldNames.length; j++) {
            Object value = OtherUtils.getFieldValueByName(fieldNames[j], obj);
            if (null != value) {
                objKeyValueMap.put(fieldNames[j], value);
            }

        }
        return objKeyValueMap;
    }

    private static String[] getFiledName(Object obj) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> tempClass = obj.getClass();
        while (tempClass != null) {
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        String[] fieldNames = new String[fieldList.size()];
        for (int i = 0; i < fieldList.size(); i++) {
            fieldNames[i] = fieldList.get(i).getName();
        }
        return fieldNames;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert object into map
     *
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Class<? extends Object> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    /**
     * This function is used to encode the sign and signature of the order API
     *
     * @param key key, str message
     * @return str encode
     */
    public static String encodeBase64(String key, String message) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            String encode = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes("UTF-8")));
            return encode;
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode sign string");
        }
    }


}
