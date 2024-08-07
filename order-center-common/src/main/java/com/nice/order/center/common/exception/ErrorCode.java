package com.nice.order.center.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 错误码
 *
 * 用户登陆及权限相关：1000 - 1999
 * 非业务类的系统错误：2000 - 2999
 *
 * @author hai.huang.a@outlook.com
 * @date 2024/7/10 13:17
 */
@Slf4j
public class ErrorCode {

    private static final Map<String, IErrorCode> LOOKUP_CACHE_MAP = new HashMap<>();

    static {
        Class<?>[] declaredClasses = null;
        try {
            declaredClasses = ErrorCode.class.newInstance().getClass().getDeclaredClasses();
        } catch (Throwable e) {
            // ignore
        }
        if (declaredClasses == null) {
            throw new RuntimeException("Not register declared class, init error system failed");
        }
        for (Class<?> clazz : declaredClasses) {
            IErrorCode[] errorCodes = (IErrorCode[])clazz.getEnumConstants();
            for (IErrorCode errorCode : errorCodes) {
                log.debug("Current errorCode is registering, code is [{}], error code type is [{}]",
                        errorCode.getErrorCode(), errorCode.getClass());
                if (LOOKUP_CACHE_MAP.containsKey(errorCode.getErrorCode())) {
                    throw new RuntimeException(
                            "Same errorCode in current system, error code is " + errorCode.getErrorCode());
                }
                LOOKUP_CACHE_MAP.put(errorCode.getErrorCode(), errorCode);
            }
        }
    }

    public static IErrorCode getEnum(String code) {
        if (LOOKUP_CACHE_MAP.containsKey(code)) {
            return LOOKUP_CACHE_MAP.get(code);
        }
        return SYSTEM_ERROR_CODE.NOT_REGISTERED_ERROR_CODE;
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, IErrorCode> getAllErrorCode() {
        return (Map<Integer, IErrorCode>)SerializationUtils.clone((Serializable)LOOKUP_CACHE_MAP);
    }

    /**
     * 系统错误
     */
    @Getter
    @AllArgsConstructor
    public enum SYSTEM_ERROR_CODE implements IErrorCode {

        /**
         * 未经注册的错误码
         */
        NOT_REGISTERED_ERROR_CODE("2000", "not.registered.error.code"),

        /**
         * 内部中断
         */
        INTERNAL_INTERRUPTED("2001", "internal.interrupted"),

        /**
         * 内部错误
         */
        INTERNAL_ERROR("2002", "internal.error"),

        /**
         * 并发异常
         */
        CONCURRENT_ERROR("2003", "concurrent.error"),
        ;

        private final String errorCode;

        private final String errorMessage;

    }

    /**
     * 数据库相关错误
     */
    @Getter
    @AllArgsConstructor
    public enum DB_ERROR implements IErrorCode {

        /**
         * 插入错误
         */
        INSERT_ERROR("3000", "insert.error"),

        /**
         * 更新错误
         */
        UPDATE_ERROR("3001", "update.error"),

        /**
         * 删除错误
         */
        DELETE_ERROR("3002", "delete.error"),
        ;

        private final String errorCode;

        private final String errorMessage;

    }

    /**
     * 通用业务错误码
     */
    @Getter
    @AllArgsConstructor
    public enum GENERAL_BUSINESS_ERROR implements IErrorCode {

        /**
         * 处理出错，请联系客服
         */
        CONTACT_SERVICE_DESK("4000", "contact.service.desk"),

        /**
         * 未定义具体的错误码时，可临时用这个通用的业务错误码代替
         * <p>
         * 注：仅供临时使用，建议将业务错误具体化
         */
        GENERAL_BUSINESS_ERROR_CODE("4001", "general.business.error.code"),
        ;

        private final String errorCode;

        private final String errorMessage;

    }

}
